package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by marklin on 2017/12/24.
 */

public class HomePageActivity extends BaseActivity {
    public static final String TAG = HomePageActivity.class.getSimpleName();
    @BindView(R.id.playermanager)
    Button btnManager;
    @BindView(R.id.record)
    Button btnStartRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.playermanager, R.id.record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.playermanager:
                break;
            case R.id.record:
                break;
        }
    }
}
