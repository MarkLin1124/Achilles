package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.Player;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.PlayerManagerListHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/25.
 */

public class PlayerManagerAdapter extends RecyclerView.Adapter<PlayerManagerListHolder> {
    public static final String TAG = PlayerManagerAdapter.class.getSimpleName();

    private ArrayList<Player> list = new ArrayList<>();
    private Context context;
    private OnAdapterItemClick onAdapterItemClick;

    public PlayerManagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PlayerManagerListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_manager, parent, false);
        return new PlayerManagerListHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerManagerListHolder holder, final int position) {
        holder.tvPlayerNum.setText(Integer.toString(list.get(position).playerNum));
        holder.tvPlayerName.setText(list.get(position).playerName);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onAdapterItemClick != null) {
                    onAdapterItemClick.onItemLongClick(list.get(position));
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<Player> list) {
        this.list.clear();
//        this.list = list;

        Player player = new Player();
        player.playerName = "test123";
        player.playerNum = 99;
        this.list.add(player);

        notifyDataSetChanged();
    }

    public void setOnAdapterItemClick(OnAdapterItemClick onAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick;
    }
}
