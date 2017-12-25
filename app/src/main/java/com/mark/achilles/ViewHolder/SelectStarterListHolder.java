package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/26.
 */

public class SelectStarterListHolder extends RecyclerView.ViewHolder {
    public static final String TAG = SelectStarterListHolder.class.getSimpleName();

    public TextView tvPlayerName;
    public CheckBox cbStarter;

    public SelectStarterListHolder(View itemView) {
        super(itemView);

        tvPlayerName = (TextView) itemView.findViewById(R.id.tv_player_name);
        cbStarter = (CheckBox) itemView.findViewById(R.id.cb_starter);
    }
}
