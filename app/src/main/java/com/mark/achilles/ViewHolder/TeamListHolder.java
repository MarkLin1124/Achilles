package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/24.
 */

public class TeamListHolder extends RecyclerView.ViewHolder {
    public static final String TAG = TeamListHolder.class.getSimpleName();

    public TextView tvTeamName;

    public TeamListHolder(View itemView) {
        super(itemView);

        tvTeamName = (TextView) itemView.findViewById(R.id.tv_team_name);
    }
}
