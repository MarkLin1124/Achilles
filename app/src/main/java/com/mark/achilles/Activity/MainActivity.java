package com.mark.achilles.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mark.achilles.Constant.Constant;
import com.mark.achilles.Constant.DialogConstant;
import com.mark.achilles.DialogFragment.SelectPlayerDialogFragment;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.Interface.OnListDialogClickListener;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mark.achilles.Constant.ActionConstant.*;

public class MainActivity extends BaseActivity implements OnDialogClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.btn_two_point_made)
    ImageButton btnTwoPointMade;
    @BindView(R.id.btn_two_point_miss)
    ImageButton btnTwoPointMiss;
    @BindView(R.id.btn_three_point_made)
    ImageButton btnThreePointMade;
    @BindView(R.id.btn_three_point_miss)
    ImageButton btnThreePointMiss;
    @BindView(R.id.btn_free_throw_made)
    ImageButton btnFreeThrowMade;
    @BindView(R.id.btn_free_throw_miss)
    ImageButton btnFreeThrowMiss;
    @BindView(R.id.btn_change_player)
    ImageButton btnChangePlayer;
    @BindView(R.id.tv_user_score)
    TextView tvUserScore;
    @BindView(R.id.tv_user_team_foul)
    TextView tvUserTeamFoul;
    @BindView(R.id.btn_change_ball)
    ImageButton btnChangeBall;
    @BindView(R.id.btn_reset_foul)
    ImageButton btnResetFoul;
    @BindView(R.id.tv_enemy_score)
    TextView tvEnemyScore;
    @BindView(R.id.tv_enemy_team_foul)
    TextView tvEnemyTeamFoul;
    @BindView(R.id.recyclerview_history)
    RecyclerView recyclerviewHistory;
    @BindView(R.id.btn_block)
    ImageButton btnBlock;
    @BindView(R.id.btn_assist)
    ImageButton btnAssist;
    @BindView(R.id.btn_off_rebound)
    ImageButton btnOffRebound;
    @BindView(R.id.btn_def_rebound)
    ImageButton btnDefRebound;
    @BindView(R.id.btn_personal_foul)
    ImageButton btnPersonalFoul;
    @BindView(R.id.btn_steal)
    ImageButton btnSteal;
    @BindView(R.id.btn_turnover)
    ImageButton btnTurnover;

    private ArrayList<BoxScore> boxScoreArrayList = new ArrayList<>();
    private GameInfo mGameInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(Constant.LIST)) {
                boxScoreArrayList = (ArrayList<BoxScore>) getIntent().getExtras().getSerializable(Constant.LIST);
            }
            if (getIntent().getExtras().containsKey(GameInfo.TAG)) {
                mGameInfo = getIntent().getExtras().getParcelable(GameInfo.TAG);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @OnClick({R.id.btn_two_point_made, R.id.btn_two_point_miss, R.id.btn_three_point_made, R.id.btn_three_point_miss, R.id.btn_free_throw_made, R.id.btn_free_throw_miss, R.id.btn_change_player, R.id.btn_change_ball, R.id.btn_reset_foul, R.id.btn_block, R.id.btn_assist, R.id.btn_off_rebound, R.id.btn_def_rebound, R.id.btn_personal_foul, R.id.btn_steal, R.id.btn_turnover})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_two_point_made:
                addAction(TWO_POINT_MADE);
                break;
            case R.id.btn_two_point_miss:
                addAction(TWO_POINT_MISS);
                break;
            case R.id.btn_three_point_made:
                addAction(THREE_POINT_MADE);
                break;
            case R.id.btn_three_point_miss:
                addAction(THREE_POINT_MISS);
                break;
            case R.id.btn_free_throw_made:
                addAction(FREE_THROW_MADE);
                break;
            case R.id.btn_free_throw_miss:
                addAction(FREE_THROW_MISS);
                break;
            case R.id.btn_change_player:
                break;
            case R.id.btn_change_ball:
                break;
            case R.id.btn_reset_foul:
                break;
            case R.id.btn_block:
                addAction(BLOCK);
                break;
            case R.id.btn_assist:
                addAction(ASSIST);
                break;
            case R.id.btn_off_rebound:
                addAction(OFF_REBOUND);
                break;
            case R.id.btn_def_rebound:
                addAction(DEF_REBOUND);
                break;
            case R.id.btn_personal_foul:
                addAction(FOUL);
                break;
            case R.id.btn_steal:
                addAction(STEAL);
                break;
            case R.id.btn_turnover:
                addAction(TURNOVER);
                break;
        }
    }

    private void addAction(final int action) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GameInfo.TAG, mGameInfo);
        openListDialogFragment(SelectPlayerDialogFragment.newInstance(bundle), "", new OnListDialogClickListener() {
            @Override
            public void OnListItemClick(int position, Parcelable parcelable) {
                BoxScore boxScore = (BoxScore) parcelable;
                boxScore.add(action);
                DatabaseHelper.getInstance(MainActivity.this).updateBoxScore(boxScore, action);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                openSimpleDialogFragment("", getString(R.string.back_homepage_content), DialogConstant.DIALOG_INTENT_TO_HOMEPAGE, this);
                break;
            case R.id.history:
                break;
            case R.id.box:
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        openSimpleDialogFragment("", getString(R.string.back_homepage_content), DialogConstant.DIALOG_INTENT_TO_HOMEPAGE, this);
    }

    @Override
    public void OnPositiveButtonClick(String dialogTag) {
        switch (dialogTag) {
            case DialogConstant.DIALOG_INTENT_TO_HOMEPAGE:
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void OnNegativeButtonClick(String dialogTag) {

    }
}
