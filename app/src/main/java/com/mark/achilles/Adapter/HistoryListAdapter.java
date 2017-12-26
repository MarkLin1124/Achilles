package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Constant.Constant;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.Module.History;
import com.mark.achilles.Module.Player;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.SimpleTextHolder;

import java.util.ArrayList;

import static com.mark.achilles.Constant.ActionConstant.*;

/**
 * Created by marklin on 2017/12/26.
 */

public class HistoryListAdapter extends RecyclerView.Adapter<SimpleTextHolder> {
    public static final String TAG = HistoryListAdapter.class.getSimpleName();

    private Context context;
    private GameInfo mGameInfo;

    private ArrayList<History> mList = new ArrayList<>();
    private ArrayList<BoxScore> boxScoreArrayList;

    private OnAdapterItemClick onAdapterItemClick;

    public HistoryListAdapter(Context context, GameInfo mGameInfo) {
        this.context = context;
        this.mGameInfo = mGameInfo;
        boxScoreArrayList = DatabaseHelper.getInstance(context).getBoxScoreList(mGameInfo._id);
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list, parent, false);
        return new SimpleTextHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, final int position) {
        String name = "";

        for (BoxScore boxScore : boxScoreArrayList) {
            if (boxScore._id == mList.get(position).boxScoreId) {
                if (boxScore.playerID == Constant.ENEMY) {
                    name = context.getString(R.string.enemy_box);
                } else {
                    Player player = DatabaseHelper.getInstance(context).getPlayer(boxScore.playerID);
                    name = String.format(context.getString(R.string.select_starter_name), player.playerNum, player.playerName);
                }
                break;
            }
        }

        holder.tvItem.setText(String.format(context.getString(R.string.history_item_action), name, getAction(mList.get(position).action)));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onAdapterItemClick != null) {
                    onAdapterItemClick.onItemLongClick(mList.get(position));
                }
                return true;
            }
        });
    }

    private String getAction(int action) {
        String playerAction = "";
        switch (action) {
            case TWO_POINT_MADE:
                playerAction = context.getString(R.string.two_point_made);
                break;
            case TWO_POINT_MISS:
                playerAction = context.getString(R.string.two_point_miss);
                break;
            case THREE_POINT_MADE:
                playerAction = context.getString(R.string.three_point_made);
                break;
            case THREE_POINT_MISS:
                playerAction = context.getString(R.string.three_point_miss);
                break;
            case FREE_THROW_MADE:
                playerAction = context.getString(R.string.free_throw_made);
                break;
            case FREE_THROW_MISS:
                playerAction = context.getString(R.string.free_throw_miss);
                break;
            case OFF_REBOUND:
                playerAction = context.getString(R.string.off_rebound);
                break;
            case DEF_REBOUND:
                playerAction = context.getString(R.string.def_rebound);
                break;
            case ASSIST:
                playerAction = context.getString(R.string.assist);
                break;
            case BLOCK:
                playerAction = context.getString(R.string.block);
                break;
            case STEAL:
                playerAction = context.getString(R.string.steal);
                break;
            case FOUL:
                playerAction = context.getString(R.string.foul);
                break;
            case TURNOVER:
                playerAction = context.getString(R.string.turnover);
                break;
        }

        return playerAction;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void refreshHistory() {
        mList.clear();
        mList = DatabaseHelper.getInstance(context).getHistoryList(mGameInfo._id);

        notifyDataSetChanged();
    }

    public void setOnAdapterItemClick(OnAdapterItemClick onAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick;
    }
}
