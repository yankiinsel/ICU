package com.icu.yankiinsel.icu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder>  {
    private List<User> mDataset;

    public HomeRecyclerAdapter(List<User> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_element;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        HomeRecyclerViewHolder viewHolder = new HomeRecyclerViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(HomeRecyclerViewHolder holder, int position) {

        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
