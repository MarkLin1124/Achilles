package com.mark.achilles.DialogFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.Player;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by marklin on 2017/12/25.
 */

public class CreatePlayerDialogFragment extends BaseDialogFragment {
    public static final String TAG = CreatePlayerDialogFragment.class.getSimpleName();
    @BindView(R.id.et_player_num)
    EditText etPlayerNum;
    @BindView(R.id.et_player_name)
    EditText etPlayerName;
    @BindView(R.id.cb_leader)
    CheckBox cbLeader;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;

    private Team mTeam;
    private Player player;

    public static CreatePlayerDialogFragment newInstance(Bundle bundle) {
        CreatePlayerDialogFragment fragment = new CreatePlayerDialogFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_create_player, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null && getArguments().containsKey(Team.TAG)) {
            mTeam = getArguments().getParcelable(Team.TAG);
        }
        if (getArguments() != null && getArguments().containsKey(Player.TAG)) {
            player = getArguments().getParcelable(Player.TAG);
            etPlayerNum.setText(Integer.toString(player.playerNum));
            etPlayerName.setText(player.playerName);
            cbLeader.setChecked(player.isLeader);
        } else {
            player = new Player();
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
                    player.teamID = mTeam._id;
                    player.playerNum = Integer.parseInt(etPlayerNum.getText().toString());
                    player.playerName = etPlayerName.getText().toString();
                    if (cbLeader.isChecked()) {
                        player.isLeader = true;
                    }

                    if (player._id == -1) {
                        //new player
                        DatabaseHelper.getInstance(getContext()).createPlayer(player);
                    } else {
                        //edit player
                        DatabaseHelper.getInstance(getContext()).updatePlayer(player);
                    }

                    onDialogClickListener.OnPositiveButtonClick(getTag());
                    dismiss();
                }
                break;
        }
    }

    private boolean checkData() {
        if (TextUtils.isEmpty(etPlayerNum.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_player_number_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etPlayerName.getText().toString())) {
            Toast.makeText(getContext(), getResources().getString(R.string.create_player_name_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
