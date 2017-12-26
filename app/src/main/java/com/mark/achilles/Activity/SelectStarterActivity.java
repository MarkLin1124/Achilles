package com.mark.achilles.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mark.achilles.Adapter.SelectStarterListAdapter;
import com.mark.achilles.Constant.Constant;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.Player;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marklin on 2017/12/26.
 */

public class SelectStarterActivity extends BaseActivity {
    public static final String TAG = SelectStarterActivity.class.getSimpleName();
    @BindView(R.id.btn_start)
    ImageButton btnStart;
    @BindView(R.id.recyclerview_player_list)
    RecyclerView recyclerviewPlayerList;

    private Team mTeam;
    private GameInfo mGameInfo;

    private SelectStarterListAdapter selectStarterListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_starter);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(Team.TAG)) {
                mTeam = getIntent().getExtras().getParcelable(Team.TAG);
            }
            if (getIntent().getExtras().containsKey(GameInfo.TAG)) {
                mGameInfo = getIntent().getExtras().getParcelable(GameInfo.TAG);
            }
        }

        selectStarterListAdapter = new SelectStarterListAdapter(this);
        recyclerviewPlayerList.setAdapter(selectStarterListAdapter);
        selectStarterListAdapter.setData(DatabaseHelper.getInstance(this).getPlayerList(mTeam._id));
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        if (getSelectPlayerList().size() == 5) {
            for (Player player : selectStarterListAdapter.getPlayerList()) {
                DatabaseHelper.getInstance(SelectStarterActivity.this).createBoxScore(createBoxScore(player));
            }
            DatabaseHelper.getInstance(SelectStarterActivity.this).createBoxScore(createEnemy());

            Bundle bundle = new Bundle();
            bundle.putParcelable(GameInfo.TAG, mGameInfo);
            startActivity(new Intent().setClass(SelectStarterActivity.this, MainActivity.class).putExtras(bundle));
        } else {
            Toast.makeText(SelectStarterActivity.this, getString(R.string.select_starter_player_hint), Toast.LENGTH_SHORT).show();
        }
    }

    private BoxScore createBoxScore(Player player) {
        BoxScore boxScore = new BoxScore();
        boxScore.playerID = player._id;
        boxScore.gameID = mGameInfo._id;
        boxScore.onCourt = player.starter;

        return boxScore;
    }

    private BoxScore createEnemy() {
        BoxScore boxScore = new BoxScore();
        boxScore.playerID = Constant.ENEMY;
        boxScore.gameID = mGameInfo._id;
        boxScore.onCourt = true;

        return boxScore;
    }

    private ArrayList<Player> getSelectPlayerList() {
        ArrayList<Player> starterList = new ArrayList<>();
        for (Player player : selectStarterListAdapter.getPlayerList()) {
            if (player.starter) {
                starterList.add(player);
            }
        }
        return starterList;
    }
}
