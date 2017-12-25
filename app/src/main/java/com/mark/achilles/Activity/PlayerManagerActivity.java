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
import com.mark.achilles.Interface.OnListDialogClickListener;
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
    private ArrayList<Player> list;

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

        list = DatabaseHelper.getInstance(PlayerManagerActivity.this).getPlayerList(mTeam._id);
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
        final Player player = (Player) parcelable;

        ArrayList<String> list = new ArrayList<>();
        list.add(getString(R.string.edit));
        list.add(getString(R.string.delete));
        openSimpleListDialogFragment(list, DIALOG_SIMPLE_LIST, new OnListDialogClickListener() {
            @Override
            public void OnListItemClick(int position, Parcelable parcelable) {
                switch (position) {
                    case 0:
                        Bundle b = new Bundle();
                        b.putParcelable(Team.TAG, mTeam);
                        b.putParcelable(Player.TAG, player);
                        openDialogFragment(CreatePlayerDialogFragment.newInstance(b), DIALOG_CREATE_PLAYER, PlayerManagerActivity.this);
                        break;
                    case 1:
                        DatabaseHelper.getInstance(PlayerManagerActivity.this).deletePlayer(player);
                        PlayerManagerActivity.this.list = DatabaseHelper.getInstance(PlayerManagerActivity.this).getPlayerList(mTeam._id);
                        managerAdapter.setData(PlayerManagerActivity.this.list);
                        break;
                }
            }
        });
    }

    @Override
    public void OnPositiveButtonClick(String dialogTag) {
        switch (dialogTag) {
            case DialogConstant.DIALOG_CREATE_PLAYER:
                list = DatabaseHelper.getInstance(PlayerManagerActivity.this).getPlayerList(mTeam._id);
                managerAdapter.setData(list);
                break;
        }
    }

    @Override
    public void OnNegativeButtonClick(String dialogTag) {

    }
}
