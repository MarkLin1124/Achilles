package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mark.achilles.Constant.Constant;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/25.
 */

public class SimpleDialogFragment extends BaseDialogFragment {
    public static final String TAG = SimpleDialogFragment.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    private String sTitle, sContent;

    public static SimpleDialogFragment newInstance(Bundle bundle) {
        SimpleDialogFragment dialogFragment = new SimpleDialogFragment();
        dialogFragment.setArguments(bundle);

        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.SmallDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_simple, container, false);

        if (getArguments() != null && getArguments().containsKey(Constant.TITLE)) {
            sTitle = getArguments().getString(Constant.TITLE, "");
        }

        if (getArguments() != null && getArguments().containsKey(Constant.CONTENT)) {
            sContent = getArguments().getString(Constant.CONTENT, "");
        }

        unbinder = ButterKnife.bind(this, view);

        if (!TextUtils.isEmpty(sTitle)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(sTitle);
        } else {
            tvTitle.setVisibility(View.GONE);
        }

        tvContent.setText(sContent);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                if (onDialogClickListener != null) {
                    onDialogClickListener.OnNegativeButtonClick(getTag());
                }
                dismiss();
                break;
            case R.id.btn_confirm:
                if (onDialogClickListener != null) {
                    onDialogClickListener.OnPositiveButtonClick(getTag());
                }
                dismiss();
                break;
        }
    }
}
