package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.mark.achilles.DialogFragment.CreateTeamDialogFragment;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mark.achilles.Constant.DialogConstant.DIALOG_CREATE_TEAM;

/**
 * Created by marklin on 2017/12/24.
 */

public class TeamManagerActivity extends BaseActivity implements OnDialogClickListener {
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
        openDialogFragment(new CreateTeamDialogFragment(), DIALOG_CREATE_TEAM, this);
    }

    @Override
    public void OnPositiveButtonClick(String dialogTag) {
        switch (dialogTag){
            case DIALOG_CREATE_TEAM:
                break;
        }
    }

    @Override
    public void OnNegativeButtonClick(String dialogTag) {
        switch (dialogTag){
            case DIALOG_CREATE_TEAM:
                break;
        }
    }
}
