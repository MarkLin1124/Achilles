package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.achilles.DialogFragment.SelectPlayerDialogFragment;
import com.mark.achilles.Helper.DatabaseHelper;
import com.mark.achilles.Interface.OnListDialogClickListener;
import com.mark.achilles.Module.BoxScore;
import com.mark.achilles.Module.Player;
import com.mark.achilles.R;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/26.
 */

public class SelectPlayerAdapter extends RecyclerView.Adapter<SelectPlayerAdapter.SimpleListHolder> {
    public static final String TAG = SelectPlayerAdapter.class.getSimpleName();

    private OnListDialogClickListener onListDialogClickListener;

    private SelectPlayerDialogFragment dialogFragment;
    private ArrayList<BoxScore> mList = new ArrayList<>();

    public SelectPlayerAdapter(SelectPlayerDialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    @Override
    public SelectPlayerAdapter.SimpleListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list, parent, false);
        return new SelectPlayerAdapter.SimpleListHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectPlayerAdapter.SimpleListHolder holder, final int position) {
        Player player = DatabaseHelper.getInstance(dialogFragment.getContext()).getPlayer(mList.get(position).playerID);
        holder.tvItem.setText(String.format(dialogFragment.getContext().getResources().getString(R.string.select_starter_name), player.playerNum, player.playerName));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListDialogClickListener != null) {
                    onListDialogClickListener.OnListItemClick(position, mList.get(position));
                }

                dialogFragment.dismiss();
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

    public void setOnListDialogClickListener(OnListDialogClickListener onListDialogClickListener) {
        this.onListDialogClickListener = onListDialogClickListener;
    }

    public class SimpleListHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;

        public SimpleListHolder(View itemView) {
            super(itemView);

            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
