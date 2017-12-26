package com.mark.achilles.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mark.achilles.R;

/**
 * Created by marklin on 2017/12/26.
 */

public class SimpleTextHolder extends RecyclerView.ViewHolder {
    public static final String TAG = SimpleTextHolder.class.getSimpleName();

    public TextView tvItem;

    public SimpleTextHolder(View itemView) {
        super(itemView);

        tvItem = (TextView) itemView.findViewById(R.id.tv_item);
    }
}
