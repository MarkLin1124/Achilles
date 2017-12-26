package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Adapter.SelectPlayerAdapter;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/26.
 */

public class SelectPlayerDialogFragment extends BaseDialogFragment {
    public static final String TAG = SelectPlayerDialogFragment.class.getSimpleName();

    @BindView(R.id.recyclerview_list)
    RecyclerView recyclerviewList;
    Unbinder unbinder;

    private GameInfo mGameInfo;

    private ArrayList<BoxScore> mList = new ArrayList<>();
    private SelectPlayerAdapter mAdapter;

    public static SelectPlayerDialogFragment newInstance(Bundle bundle) {
        SelectPlayerDialogFragment fragment = new SelectPlayerDialogFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_simple_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(GameInfo.TAG)) {
            mGameInfo = getArguments().getParcelable(GameInfo.TAG);
        }

        mAdapter = new SelectPlayerAdapter(this);
        recyclerviewList.setAdapter(mAdapter);
        mAdapter.setOnListDialogClickListener(onListDialogClickListener);

        mList = DatabaseHelper.getInstance(getContext()).getOnCourtBoxScore(mGameInfo._id);
        mAdapter.setData(mList);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
