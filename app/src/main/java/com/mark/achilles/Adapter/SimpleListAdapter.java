package com.mark.achilles.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mark.achilles.DialogFragment.SimpleListDialogFragment;
import com.mark.achilles.Interface.OnListDialogClickListener;
import com.mark.achilles.R;

import java.util.ArrayList;

/**
 * Created by marklin on 2017/12/25.
 */

public class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.SimpleListHolder> {
    public static final String TAG = SimpleListAdapter.class.getSimpleName();

    private SimpleListDialogFragment dialogFragment;

    private ArrayList<String> mList = new ArrayList<>();
    private OnListDialogClickListener onListDialogClickListener;

    public SimpleListAdapter(SimpleListDialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    @Override
    public SimpleListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_list, parent, false);
        return new SimpleListHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleListHolder holder, final int position) {
        holder.tvItem.setText(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListDialogClickListener != null) {
                    onListDialogClickListener.OnListItemClick(position, null);
                }

                dialogFragment.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnListDialogClickListener(OnListDialogClickListener onListDialogClickListener) {
        this.onListDialogClickListener = onListDialogClickListener;
    }

    public void setData(ArrayList<String> mList) {
        this.mList.clear();
        this.mList = mList;

        notifyDataSetChanged();
    }

    public class SimpleListHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;

        public SimpleListHolder(View itemView) {
            super(itemView);

            tvItem = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
