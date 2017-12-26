package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnAdapterItemClick;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.Player;
import com.mark.achilles.R;
import com.mark.achilles.ViewHolder.SimpleTextHolder;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/26.
 */

public class ChangePlayerAdapter extends RecyclerView.Adapter<SimpleTextHolder> {
    public static final String TAG = ChangePlayerAdapter.class.getSimpleName();

    private ArrayList<BoxScore> mList = new ArrayList<>();
    private Context context;

    private OnAdapterItemClick onAdapterItemClick;

    public ChangePlayerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SimpleTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list, parent, false);
        return new SimpleTextHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleTextHolder holder, final int position) {
        Player player = DatabaseHelper.getInstance(context).getPlayer(mList.get(position).playerID);
        holder.tvItem.setText(String.format(context.getString(R.string.select_starter_name), player.playerNum, player.playerName));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAdapterItemClick != null) {
                    onAdapterItemClick.onItemClick(mList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(ArrayList<BoxScore> mList) {
        this.mList.clear();
        this.mList = mList;

        notifyDataSetChanged();
    }

    public void remove(BoxScore boxScore) {
        for (BoxScore b : mList) {
            if (b._id == boxScore._id) {
                mList.remove(b);
                break;
            }
        }

        notifyDataSetChanged();
    }

    public void add(BoxScore boxScore) {
        mList.add(boxScore);

        notifyDataSetChanged();
    }

    public void setOnAdapterItemClick(OnAdapterItemClick onAdapterItemClick) {
        this.onAdapterItemClick = onAdapterItemClick;
    }

    public ArrayList<BoxScore> getBoxScoreList() {
        return mList;
    }
}
