package com.icu.yankiinsel.icu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icu.yankiinsel.icu.Interfaces.ListItemClickListener;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.ViewHolders.MatchesRecyclerViewHolder;

import java.util.List;

public class MatchesRecyclerAdapter extends RecyclerView.Adapter<MatchesRecyclerViewHolder>  {

    private List<User> mDataset;
    private final ListItemClickListener mOnClickListener;

    public MatchesRecyclerAdapter(List<User> myDataset, ListItemClickListener listener) {
        mDataset = myDataset;
        mOnClickListener = listener;
    }

    @Override
    public MatchesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.matches_list_element;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MatchesRecyclerViewHolder viewHolder = new MatchesRecyclerViewHolder(view, mOnClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MatchesRecyclerViewHolder holder, int position) {
        holder.bind(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
