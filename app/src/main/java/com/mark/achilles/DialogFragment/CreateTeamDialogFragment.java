package com.mark.achilles.DialogFragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mark.achilles.Constant.DatabaseConstant;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/24.
 */

public class CreateTeamDialogFragment extends BaseDialogFragment {
    public static final String TAG = CreateTeamDialogFragment.class.getSimpleName();
    @BindView(R.id.et_team_name)
    EditText etTeamName;
    @BindView(R.id.et_team_code)
    EditText etTeamCode;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_create_team, container, false);
        unbinder = ButterKnife.bind(this, view);

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
                dismiss();
                break;
            case R.id.btn_confirm:
                if (checkData() && onDialogClickListener != null) {
                    DatabaseHelper.getInstance(getContext()).createTeam(etTeamName.getText().toString(), Integer.parseInt(etTeamCode.getText().toString()));
                    onDialogClickListener.OnPositiveButtonClick(getTag());
                    dismiss();
                }
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(etTeamName.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_team_name_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etTeamCode.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_team_code_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etTeamCode.getText().toString().length() != 4) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_team_code_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
