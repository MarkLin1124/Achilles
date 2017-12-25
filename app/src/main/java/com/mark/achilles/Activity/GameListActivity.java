package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marklin on 2017/12/25.
 */

public class GameListActivity extends BaseActivity {
    public static final String TAG = GameListActivity.class.getSimpleName();
    @BindView(R.id.btn_create_game)
    ImageButton btnCreateGame;
    @BindView(R.id.recyclerview_game_list)
    RecyclerView recyclerviewGameList;

    private Team mTeam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(Team.TAG)) {
            mTeam = getIntent().getExtras().getParcelable(Team.TAG);
        }
    }

    @OnClick(R.id.btn_create_game)
    public void onViewClicked() {
    }
}
