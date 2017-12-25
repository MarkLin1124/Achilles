package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.GameInfo;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.GameListHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/25.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListHolder> {
    public static final String TAG = GameListAdapter.class.getSimpleName();

    private ArrayList<GameInfo> mList = new ArrayList<>();
    private OnAdapterItemClick onAdapterItemClick;
    private Context context;

    public GameListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GameListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_list, parent, false);
        return new GameListHolder(view);
    }

    @Override
    public void onBindViewHolder(GameListHolder holder, final int position) {
        holder.tvGameDate.setText(mList.get(position).gameDate);
        holder.tvGameName.setText(String.format(context.getResources().getString(R.string.game_list_name), mList.get(position).gameName, mList.get(position).enemyName));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterItemClick != null) {
                    onAdapterItemClick.onItemClick(mList.get(position));
                }
            }
        });

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

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<GameInfo> mList) {
        this.mList.clear();
        this.mList = mList;

        notifyDataSetChanged();
    }

    public void setOnAdapterItemClick(OnAdapterItemClick onAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick;
    }
}
