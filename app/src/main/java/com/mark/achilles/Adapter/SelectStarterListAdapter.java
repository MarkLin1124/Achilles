package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Module.Player;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.SelectStarterListHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/26.
 */

public class SelectStarterListAdapter extends RecyclerView.Adapter<SelectStarterListHolder> {
    public static final String TAG = SelectStarterListAdapter.class.getSimpleName();

    private Context context;
    private ArrayList<Player> mList = new ArrayList<>();

    public SelectStarterListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SelectStarterListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_starter, parent, false);
        return new SelectStarterListHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectStarterListHolder holder, int position) {
        holder.tvPlayerName.setText(String.format(context.getResources().getString(R.string.select_starter_name), mList.get(position).playerNum, mList.get(position).playerName));
        holder.cbStarter.setChecked(mList.get(position).starter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<Player> mList) {
        this.mList.clear();
        this.mList = mList;

        notifyDataSetChanged();
    }

    public ArrayList<Player> getPlayerList() {
        return mList;
    }
}
