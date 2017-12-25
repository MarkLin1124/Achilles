package com.mark.achilles.Activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.mark.achilles.Adapter.GameListAdapter;
import com.mark.achilles.Constant.DialogConstant;
import com.mark.achilles.DialogFragment.CreateGameDialogFragment;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marklin on 2017/12/25.
 */

public class GameListActivity extends BaseActivity implements OnAdapterItemClick, OnDialogClickListener {
    public static final String TAG = GameListActivity.class.getSimpleName();
    @BindView(R.id.btn_create_game)
    ImageButton btnCreateGame;
    @BindView(R.id.recyclerview_game_list)
    RecyclerView recyclerviewGameList;

    private Team mTeam;
    private ArrayList<GameInfo> mList = new ArrayList<>();
    private GameListAdapter gameListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Team.TAG)) {
            mTeam = getIntent().getExtras().getParcelable(Team.TAG);
            mList = DatabaseHelper.getInstance(this).getGameInfoList(mTeam._id);
        }

        gameListAdapter = new GameListAdapter(this);
        gameListAdapter.setOnAdapterItemClick(this);
        recyclerviewGameList.setAdapter(gameListAdapter);
        gameListAdapter.setData(mList);
    }

    @OnClick(R.id.btn_create_game)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Team.TAG, mTeam);
        openDialogFragment(CreateGameDialogFragment.newInstance(bundle), DialogConstant.DIALOG_CREATE_GAME_INFO, this);
    }

    @Override
    public void onItemClick(Parcelable parcelable) {

    }

    @Override
    public void onItemLongClick(Parcelable parcelable) {
        final GameInfo gameInfo = (GameInfo) parcelable;
        openSimpleDialogFragment("刪除比賽紀錄?", "是否要刪除該比賽紀錄?", "", new OnDialogClickListener() {
            @Override
            public void OnPositiveButtonClick(String dialogTag) {
                DatabaseHelper.getInstance(GameListActivity.this).deleteGameInfo(gameInfo);
                mList = DatabaseHelper.getInstance(GameListActivity.this).getGameInfoList(mTeam._id);
                gameListAdapter.setData(mList);
            }

            @Override
            public void OnNegativeButtonClick(String dialogTag) {

            }
        });
    }

    @Override
    public void OnPositiveButtonClick(String dialogTag) {
        switch (dialogTag) {
            case DialogConstant.DIALOG_CREATE_GAME_INFO:
                mList = DatabaseHelper.getInstance(this).getGameInfoList(mTeam._id);
                gameListAdapter.setData(mList);
                break;
        }
    }

    @Override
    public void OnNegativeButtonClick(String dialogTag) {

    }
}
