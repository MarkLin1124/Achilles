package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/24.
 */

public class CreateTeamDialogFragment extends BaseDialogFragment {
    public static final String TAG = CreateTeamDialogFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_create_team, container, false);
        return view;
    }
}
