package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.Team;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.TeamListHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/24.
 */

public class TeamListAdapter extends RecyclerView.Adapter<TeamListHolder> {
    public static final String TAG = TeamListAdapter.class.getSimpleName();

    private ArrayList<Team> list = new ArrayList<>();
    private Context context;
    private OnAdapterItemClick onAdapterItemClick;

    public TeamListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TeamListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_list, parent, false);
        return new TeamListHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamListHolder holder, final int position) {
        holder.tvTeamName.setText(list.get(position).teamName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterItemClick != null) {
                    onAdapterItemClick.onItemClick(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<Team> list) {
        this.list.clear();
        this.list = list;

        notifyDataSetChanged();
    }

    public void setOnAdapterItemClick(OnAdapterItemClick onAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick;
    }
}
