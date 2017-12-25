package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Adapter.SimpleListAdapter;
import com.mark.achilles.Constant.Constant;
import com.mark.achilles.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/25.
 */

public class SimpleListDialogFragment extends BaseDialogFragment {
    public static final String TAG = SimpleListDialogFragment.class.getSimpleName();
    @BindView(R.id.recyclerview_list)
    RecyclerView recyclerviewList;
    Unbinder unbinder;

    private ArrayList<String> mList = new ArrayList<>();
    private SimpleListAdapter mAdapter;

    public static SimpleListDialogFragment newInstance(Bundle bundle) {
        SimpleListDialogFragment simpleListDialogFragment = new SimpleListDialogFragment();
        simpleListDialogFragment.setArguments(bundle);

        return simpleListDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SmallDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_simple_list, container, false);

        if (getArguments() != null && getArguments().containsKey(Constant.LIST)) {
            mList = (ArrayList<String>) getArguments().getSerializable(Constant.LIST);
        }

        unbinder = ButterKnife.bind(this, view);
        mAdapter = new SimpleListAdapter(this);
        recyclerviewList.setAdapter(mAdapter);
        mAdapter.setOnListDialogClickListener(onListDialogClickListener);
        mAdapter.setData(mList);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
