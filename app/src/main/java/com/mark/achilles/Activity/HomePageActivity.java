package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/24.
 */

public class HomePageActivity extends BaseActivity {
    public static final String TAG = HomePageActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }
}
