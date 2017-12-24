package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marklin on 2017/12/24.
 */

public class TeamManagerActivity extends BaseActivity {
    public static final String TAG = TeamManagerActivity.class.getSimpleName();
    @BindView(R.id.btn_add_team)
    ImageButton btnAddTeam;
    @BindView(R.id.recyclerview_team_list)
    RecyclerView recyclerviewTeamList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_manager);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_team)
    public void onViewClicked() {

    }
}
