package com.mark.achilles.Activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mark.achilles.Adapter.ChangePlayerAdapter;
import com.mark.achilles.Constant.Constant;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mark.achilles.Constant.ActionConstant.ON_COURT;

/**
 * Created by marklin on 2017/12/26.
 */

public class ChangePlayerActivity extends BaseActivity {
    public static final String TAG = ChangePlayerActivity.class.getSimpleName();
    @BindView(R.id.btn_confirm)
    ImageButton btnConfirm;
    @BindView(R.id.recyclerview_bench)
    RecyclerView recyclerviewBench;
    @BindView(R.id.recyclerview_court)
    RecyclerView recyclerviewCourt;

    private GameInfo mGameInfo;

    private ArrayList<BoxScore> mList = new ArrayList<>();
    private ArrayList<BoxScore> benchList = new ArrayList<>();
    private ArrayList<BoxScore> onCourtList = new ArrayList<>();

    private ChangePlayerAdapter benchAdapter, onCourtAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_substitution);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(GameInfo.TAG)) {
            mGameInfo = getIntent().getExtras().getParcelable(GameInfo.TAG);
            mList = DatabaseHelper.getInstance(this).getBoxScoreList(mGameInfo._id);
            setList();
        }

        benchAdapter = new ChangePlayerAdapter(this);
        recyclerviewBench.setAdapter(benchAdapter);
        benchAdapter.setData(benchList);
        benchAdapter.setOnAdapterItemClick(onBenchAdapterClick);

        onCourtAdapter = new ChangePlayerAdapter(this);
        recyclerviewCourt.setAdapter(onCourtAdapter);
        onCourtAdapter.setData(onCourtList);
        onCourtAdapter.setOnAdapterItemClick(onCourtAdapterClick);
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (onCourtAdapter.getBoxScoreList().size() != 5) {
            Toast.makeText(ChangePlayerActivity.this, getString(R.string.on_court_player_hint), Toast.LENGTH_SHORT).show();
        } else {
            for (BoxScore boxScore : onCourtAdapter.getBoxScoreList()) {
                DatabaseHelper.getInstance(ChangePlayerActivity.this).updateBoxScore(boxScore, ON_COURT);
            }
            for (BoxScore boxScore : benchAdapter.getBoxScoreList()) {
                DatabaseHelper.getInstance(ChangePlayerActivity.this).updateBoxScore(boxScore, ON_COURT);
            }
            finish();
        }
    }

    private void setList() {
        for (BoxScore boxScore : mList) {
            if (boxScore.playerID != Constant.ENEMY) {
                if (boxScore.onCourt) {
                    onCourtList.add(boxScore);
                } else {
                    benchList.add(boxScore);
                }
            }
        }
    }

    private OnAdapterItemClick onBenchAdapterClick = new OnAdapterItemClick() {
        @Override
        public void onItemClick(Parcelable parcelable) {
            BoxScore boxScore = (BoxScore) parcelable;
            benchAdapter.remove(boxScore);

            boxScore.onCourt = true;
            onCourtAdapter.add(boxScore);
        }

        @Override
        public void onItemLongClick(Parcelable parcelable) {

        }
    };

    private OnAdapterItemClick onCourtAdapterClick = new OnAdapterItemClick() {
        @Override
        public void onItemClick(Parcelable parcelable) {
            BoxScore boxScore = (BoxScore) parcelable;
            onCourtAdapter.remove(boxScore);

            boxScore.onCourt = false;
            benchAdapter.add(boxScore);
        }

        @Override
        public void onItemLongClick(Parcelable parcelable) {

        }
    };
}
