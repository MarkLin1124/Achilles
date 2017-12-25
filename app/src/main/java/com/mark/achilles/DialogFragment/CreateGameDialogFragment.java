package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/25.
 */

public class CreateGameDialogFragment extends BaseDialogFragment {
    public static final String TAG = CreateGameDialogFragment.class.getSimpleName();
    @BindView(R.id.et_game_name)
    EditText etGameName;
    @BindView(R.id.et_game_enemy)
    EditText etGameEnemy;
    @BindView(R.id.et_game_court)
    EditText etGameCourt;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    private Team mTeam;

    public static CreateGameDialogFragment newInstance(Bundle bundle) {
        CreateGameDialogFragment fragment = new CreateGameDialogFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_create_game, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Team.TAG)) {
            mTeam = getArguments().getParcelable(Team.TAG);
        }

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
                    DatabaseHelper.getInstance(getContext()).createGameInfo(createGameInfo());
                    onDialogClickListener.OnPositiveButtonClick(getTag());
                    dismiss();
                }
                break;
        }
    }

    private GameInfo createGameInfo() {
        GameInfo gameInfo = new GameInfo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        gameInfo.gameDate = simpleDateFormat.format(calendar.getTime());
        gameInfo.gameName = etGameName.getText().toString();
        gameInfo.gameTeam = mTeam._id;
        gameInfo.gameCourt = etGameCourt.getText().toString();
        gameInfo.enemyName = etGameEnemy.getText().toString();

        return gameInfo;
    }

    public boolean checkData() {
        if (TextUtils.isEmpty(etGameName.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_game_name_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etGameEnemy.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_game_enemy_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etGameCourt.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_game_court_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
