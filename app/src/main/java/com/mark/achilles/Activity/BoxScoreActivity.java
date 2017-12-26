package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.mark.achilles.Adapter.BoxScoreAdapter;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marklin on 2017/12/26.
 */

public class BoxScoreActivity extends BaseActivity {
    public static final String TAG = BoxScoreActivity.class.getSimpleName();
    @BindView(R.id.recyclerview_box_score)
    RecyclerView recyclerviewBoxScore;

    private GameInfo mGameInfo;
    private BoxScoreAdapter boxScoreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_score);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(GameInfo.TAG)) {
            mGameInfo = getIntent().getExtras().getParcelable(GameInfo.TAG);
        }

        boxScoreAdapter = new BoxScoreAdapter(this);
        recyclerviewBoxScore.setAdapter(boxScoreAdapter);
        boxScoreAdapter.setData(DatabaseHelper.getInstance(this).getBoxScoreList(mGameInfo._id));
    }
}
