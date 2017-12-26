package com.mark.achilles.Activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mark.achilles.Adapter.HistoryListAdapter;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.History;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mark.achilles.Constant.ActionConstant.FOUL;
import static com.mark.achilles.Constant.ActionConstant.FREE_THROW_MADE;
import static com.mark.achilles.Constant.ActionConstant.THREE_POINT_MADE;
import static com.mark.achilles.Constant.ActionConstant.TWO_POINT_MADE;
import static com.mark.achilles.Constant.Constant.ENEMY;

/**
 * Created by marklin on 2017/12/26.
 */

public class HistoryActivity extends BaseActivity implements OnAdapterItemClick {
    public static final String TAG = History.class.getSimpleName();
    @BindView(R.id.recyclerview_history)
    RecyclerView recyclerviewHistory;

    private GameInfo mGameInfo;
    private HistoryListAdapter historyListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(GameInfo.TAG)) {
            mGameInfo = getIntent().getExtras().getParcelable(GameInfo.TAG);
        }

        historyListAdapter = new HistoryListAdapter(HistoryActivity.this, mGameInfo);
        recyclerviewHistory.setAdapter(historyListAdapter);
        historyListAdapter.setOnAdapterItemClick(this);
        historyListAdapter.refreshHistory();
        recyclerviewHistory.smoothScrollToPosition(0);
    }

    @Override
    public void onItemClick(Parcelable parcelable) {

    }

    @Override
    public void onItemLongClick(Parcelable parcelable) {
        final History history = (History) parcelable;

        openSimpleDialogFragment(getString(R.string.delete), getString(R.string.history_delete_hint), "", new OnDialogClickListener() {
            @Override
            public void OnPositiveButtonClick(String dialogTag) {
                BoxScore boxScore = DatabaseHelper.getInstance(HistoryActivity.this).getBoxScore(history.boxScoreId);
                DatabaseHelper.getInstance(HistoryActivity.this).updateBoxScore(boxScore.remove(history.action), history.action);
                DatabaseHelper.getInstance(HistoryActivity.this).deleteHistory(history);
                updateGameInfo(boxScore, history.action);

                historyListAdapter.refreshHistory();
            }

            @Override
            public void OnNegativeButtonClick(String dialogTag) {

            }
        });
    }

    public void updateGameInfo(BoxScore boxScore, int action) {
        if (boxScore.playerID == ENEMY) {
            switch (action) {
                case TWO_POINT_MADE:
                    mGameInfo.enemyScore = mGameInfo.enemyScore - 2;
                    break;
                case THREE_POINT_MADE:
                    mGameInfo.enemyScore = mGameInfo.enemyScore - 3;
                    break;
                case FREE_THROW_MADE:
                    mGameInfo.enemyScore = mGameInfo.enemyScore - 1;
                    break;
                case FOUL:
                    mGameInfo.enemyTeamFoul = mGameInfo.enemyTeamFoul - 1;
                    break;
            }
        } else {
            switch (action) {
                case TWO_POINT_MADE:
                    mGameInfo.userScore = mGameInfo.userScore - 2;
                    break;
                case THREE_POINT_MADE:
                    mGameInfo.userScore = mGameInfo.userScore - 3;
                    break;
                case FREE_THROW_MADE:
                    mGameInfo.userScore = mGameInfo.userScore - 1;
                    break;
                case FOUL:
                    mGameInfo.userTeamFoul = mGameInfo.userTeamFoul - 1;
                    break;
            }
        }

        DatabaseHelper.getInstance(HistoryActivity.this).updateGameInfo(mGameInfo);
    }
}
