package com.id.jenius.jenius.recylerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.id.jenius.jenius.R;

public class RecylerViewHolder extends RecyclerView.ViewHolder {

    public TextView ivName,ivAge,buttonViewOption;
    public ImageView ivPhoto;

    public RecylerViewHolder(View v) {
        super(v);
        ivName = (TextView) v.findViewById(R.id.ivName);
        ivAge = (TextView) v.findViewById(R.id.ivAge);
        ivPhoto = (ImageView) v.findViewById(R.id.ivPhoto);
        buttonViewOption = (TextView) itemView.findViewById(R.id.itemTextView);
    }
}
