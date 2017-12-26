package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/26.
 */

public class BoxScoreHolder extends RecyclerView.ViewHolder {
    public static final String TAG = BoxScoreHolder.class.getSimpleName();

    public TextView tvPlayerName, tvTwoPoint, tvThreePoint, tvFreeThrow, tvRebound, tvAssist, tvBlock, tvSteal, tvTurnOver, tvFoul, tvTotalPoints;

    public BoxScoreHolder(View itemView) {
        super(itemView);

        tvPlayerName = (TextView) itemView.findViewById(R.id.tv_player_name);
        tvTwoPoint = (TextView) itemView.findViewById(R.id.tv_two_point);
        tvThreePoint = (TextView) itemView.findViewById(R.id.tv_three_point);
        tvFreeThrow = (TextView) itemView.findViewById(R.id.tv_free_throw);
        tvRebound = (TextView) itemView.findViewById(R.id.tv_rebound);
        tvAssist = (TextView) itemView.findViewById(R.id.tv_assist);
        tvBlock = (TextView) itemView.findViewById(R.id.tv_block);
        tvSteal = (TextView) itemView.findViewById(R.id.tv_steal);
        tvTurnOver = (TextView) itemView.findViewById(R.id.tv_turnover);
        tvFoul = (TextView) itemView.findViewById(R.id.tv_foul);
        tvTotalPoints = (TextView) itemView.findViewById(R.id.tv_total_point);
    }
}
