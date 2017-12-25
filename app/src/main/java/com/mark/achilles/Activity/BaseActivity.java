package com.mark.achilles.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mark.achilles.Constant.Constant;
import com.mark.achilles.DialogFragment.BaseDialogFragment;
import com.mark.achilles.DialogFragment.SimpleListDialogFragment;
import com.mark.achilles.Interface.OnDialogClickListener;
import com.mark.achilles.Interface.OnListDialogClickListener;
import com.mark.achilles.R;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/24.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void openDialogFragment(BaseDialogFragment fragment, String tag, OnDialogClickListener dialogClickListener) {
        fragment.show(getSupportFragmentManager(), tag);

        if (dialogClickListener != null) {
            fragment.setOnDialogClickListener(dialogClickListener);
        }
    }

    protected void openSimpleListDialogFragment(ArrayList<String> list, String tag, OnListDialogClickListener onListDialogClickListener) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.LIST, list);
        SimpleListDialogFragment dialogFragment = SimpleListDialogFragment.newInstance(bundle);
        dialogFragment.setonListDialogClickListener(onListDialogClickListener);
        dialogFragment.show(getSupportFragmentManager(), tag);
    }
}
