package com.mark.achilles.Activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mark.achilles.Adapter.PlayerManagerAdapter;
import com.mark.achilles.Constant.DialogConstant;
import com.mark.achilles.DialogFragment.CreatePlayerDialogFragment;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.Module.Player;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mark.achilles.Constant.DialogConstant.*;

/**
 * Created by marklin on 2017/12/25.
 */

public class PlayerManagerActivity extends BaseActivity implements OnAdapterItemClick, OnDialogClickListener {
    public static final String TAG = PlayerManagerActivity.class.getSimpleName();
    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.btn_add_player)
    ImageButton btnAddPlayer;
    @BindView(R.id.recyclerview_player_list)
    RecyclerView recyclerviewPlayerList;

    private Team mTeam;
    private PlayerManagerAdapter managerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_manager);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Team.TAG)) {
            mTeam = getIntent().getExtras().getParcelable(Team.TAG);
        }

        tvTeamName.setText(mTeam.teamName);
        managerAdapter = new PlayerManagerAdapter(this);
        recyclerviewPlayerList.setAdapter(managerAdapter);
        managerAdapter.setOnAdapterItemClick(this);

        ArrayList<Player> list = DatabaseHelper.getInstance(PlayerManagerActivity.this).getPlayerList(mTeam._id);
        managerAdapter.setData(list);
    }

    @OnClick(R.id.btn_add_player)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Team.TAG, mTeam);
        openDialogFragment(CreatePlayerDialogFragment.newInstance(bundle), DIALOG_CREATE_PLAYER, this);
    }

    @Override
    public void onItemClick(Parcelable parcelable) {

    }

    @Override
    public void onItemLongClick(Parcelable parcelable) {
        Player player = (Player) parcelable;
    }

    @Override
    public void OnPositiveButtonClick(String dialogTag) {
        switch (dialogTag) {
            case DialogConstant.DIALOG_CREATE_PLAYER:
                ArrayList<Player> list = DatabaseHelper.getInstance(PlayerManagerActivity.this).getPlayerList(mTeam._id);
                managerAdapter.setData(list);
                break;
        }
    }

    @Override
    public void OnNegativeButtonClick(String dialogTag) {

    }
}
