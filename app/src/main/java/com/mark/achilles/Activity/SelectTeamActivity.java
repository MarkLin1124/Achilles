package com.mark.achilles.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mark.achilles.Adapter.TeamListAdapter;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marklin on 2017/12/25.
 */

public class SelectTeamActivity extends BaseActivity implements OnAdapterItemClick {
    public static final String TAG = SelectTeamActivity.class.getSimpleName();
    @BindView(R.id.recyclerview_team_list)
    RecyclerView recyclerviewTeamList;

    private TeamListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);
        ButterKnife.bind(this);

        mAdapter = new TeamListAdapter(SelectTeamActivity.this);
        recyclerviewTeamList.setAdapter(mAdapter);
        mAdapter.setOnAdapterItemClick(this);

        ArrayList<Team> list = DatabaseHelper.getInstance(SelectTeamActivity.this).getTeamList();
        mAdapter.setData(list);
    }

    @Override
    public void onItemClick(Parcelable parcelable) {
        Team team = (Team) parcelable;

        Bundle bundle = new Bundle();
        bundle.putParcelable(Team.TAG, team);
        startActivity(new Intent().setClass(SelectTeamActivity.this, GameListActivity.class).putExtras(bundle));
    }

    @Override
    public void onItemLongClick(Parcelable parcelable) {

    }
}
