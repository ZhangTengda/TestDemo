package com.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.R;

/**
 * Created by roger on 2018/1/21.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] indexArray;
    private Context context;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public MainActivityAdapter(Context context, String[] indexArray) {
        this.context = context;
        this.indexArray = indexArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(context).inflate(R.layout.activity_main_recyclerview_item_layout, parent, false);

        return new MainActivityViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainActivityViewHolder mainActivityViewHolder = (MainActivityViewHolder) holder;
        mainActivityViewHolder.textView.setText(indexArray[position]);
    }

    @Override
    public int getItemCount() {
        return indexArray == null ? 0 : indexArray.length;
    }

    private class MainActivityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        MainActivityViewHolder(View contactView) {
            super(contactView);
            textView = (TextView) contactView.findViewById(R.id.activity_main_recyclerview_item_textview);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int layoutPosition = getLayoutPosition();
            if (recyclerViewItemClickListener != null) {
                recyclerViewItemClickListener.onItemClick(v, layoutPosition);
            }
        }
    }

    /**
     * recyclerview click method
     *
     * @param recyclerViewItemClickListener recyclerViewItemClickListener
     */
    public void addRecyclerViewClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    /**
     * RecyclerView Itme Click interface
     */
    public interface RecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
}
