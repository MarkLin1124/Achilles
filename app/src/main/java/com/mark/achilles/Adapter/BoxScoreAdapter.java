package com.mark.achilles.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Constant.Constant;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.Player;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.BoxScoreHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/26.
 */

public class BoxScoreAdapter extends RecyclerView.Adapter<BoxScoreHolder> {
    public static final String TAG = BoxScoreAdapter.class.getSimpleName();

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CONTENT = 1;

    private ArrayList<Object> mList = new ArrayList<>();
    private Context context;

    public BoxScoreAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BoxScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_box_score, parent, false);
        return new BoxScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(BoxScoreHolder holder, int position) {
        if (getItemViewType(position) == TYPE_TITLE) {
            setTitle(holder);
        } else {
            BoxScore boxScore = (BoxScore) mList.get(position);
            setContent(holder, boxScore);

            if (position % 2 == 0) {
                holder.itemView.setBackgroundResource(R.color.d_white);
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof BoxScore) {
            return TYPE_CONTENT;
        } else {
            return TYPE_TITLE;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<BoxScore> mList) {
        this.mList.clear();

        this.mList.add("title");
        for (BoxScore boxScore : mList) {
            if (boxScore.playerID != Constant.ENEMY) {
                this.mList.add(boxScore);
            }
        }

        notifyDataSetChanged();
    }

    private void setTitle(BoxScoreHolder holder) {
        holder.tvPlayerName.setText(context.getString(R.string.box_score_player_name_constant));
        holder.tvTwoPoint.setText(context.getString(R.string.box_score_two_point_constant));
        holder.tvThreePoint.setText(context.getString(R.string.box_score_three_point_constant));
        holder.tvFreeThrow.setText(context.getString(R.string.box_score_free_throw_constant));
        holder.tvRebound.setText(context.getString(R.string.box_score_rebound_constant));
        holder.tvAssist.setText(context.getString(R.string.box_score_assist_constant));
        holder.tvBlock.setText(context.getString(R.string.box_score_block_constant));
        holder.tvSteal.setText(context.getString(R.string.box_score_steal_constant));
        holder.tvFoul.setText(context.getString(R.string.box_score_foul_constant));
        holder.tvTurnOver.setText(context.getString(R.string.box_score_turnover_constant));
        holder.tvTotalPoints.setText(context.getString(R.string.box_score_total_point_constant));
    }

    private void setContent(BoxScoreHolder holder, BoxScore boxScore) {
        Player player = DatabaseHelper.getInstance(context).getPlayer(boxScore.playerID);
        holder.tvPlayerName.setText(player.playerName);
        holder.tvTwoPoint.setText(String.format(context.getString(R.string.format), boxScore.twoPointMade, (boxScore.twoPointMade + boxScore.twoPointMiss)));
        holder.tvThreePoint.setText(String.format(context.getString(R.string.format), boxScore.threePointMade, (boxScore.threePointMade + boxScore.threePointMiss)));
        holder.tvFreeThrow.setText(String.format(context.getString(R.string.format), boxScore.freeThrowMade, (boxScore.freeThrowMade + boxScore.freeThrowMiss)));
        holder.tvRebound.setText(String.format(context.getString(R.string.format), boxScore.offRebound, boxScore.defRebound));
        holder.tvAssist.setText(Integer.toString(boxScore.assist));
        holder.tvBlock.setText(Integer.toString(boxScore.block));
        holder.tvSteal.setText(Integer.toString(boxScore.steal));
        holder.tvFoul.setText(Integer.toString(boxScore.personalFoul));
        holder.tvTurnOver.setText(Integer.toString(boxScore.turnover));
        holder.tvTotalPoints.setText(Integer.toString(boxScore.getTotalPoint()));
    }
}
