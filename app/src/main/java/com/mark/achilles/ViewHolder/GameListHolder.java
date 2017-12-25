package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/25.
 */

public class GameListHolder extends RecyclerView.ViewHolder {
    public static final String TAG = GameListHolder.class.getSimpleName();

    public TextView tvGameDate, tvGameName;

    public GameListHolder(View itemView) {
        super(itemView);

        tvGameDate = (TextView) itemView.findViewById(R.id.tv_game_date);
        tvGameName = (TextView) itemView.findViewById(R.id.tv_game_name);
    }
}
