package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/25.
 */

public class PlayerManagerListHolder extends RecyclerView.ViewHolder {
    public static final String TAG = PlayerManagerListHolder.class.getSimpleName();

    public TextView tvPlayerNum, tvPlayerName;

    public PlayerManagerListHolder(View itemView) {
        super(itemView);

        tvPlayerNum = (TextView) itemView.findViewById(R.id.tv_player_num);
        tvPlayerName = (TextView) itemView.findViewById(R.id.tv_player_name);
    }
}
