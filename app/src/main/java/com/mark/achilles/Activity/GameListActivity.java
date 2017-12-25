package com.mark.achilles.Activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.mark.achilles.Adapter.GameListAdapter;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
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

public class GameListActivity extends BaseActivity implements OnAdapterItemClick {
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
    }

    @Override
    public void onItemClick(Parcelable parcelable) {

    }

    @Override
    public void onItemLongClick(Parcelable parcelable) {

    }
}
