package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mark.achilles.Adapter.HistoryListAdapter;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.History;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marklin on 2017/12/26.
 */

public class HistoryActivity extends BaseActivity {
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
        historyListAdapter.refreshHistory();
        recyclerviewHistory.smoothScrollToPosition(0);
    }
}
