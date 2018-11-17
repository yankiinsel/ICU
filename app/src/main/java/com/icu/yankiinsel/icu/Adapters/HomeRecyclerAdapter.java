package com.icu.yankiinsel.icu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;
import com.icu.yankiinsel.icu.ViewHolders.HomeRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerViewHolder>  {
    private List<User> myDataset;

    public HomeRecyclerAdapter() {
        myDataset = new ArrayList<>();
        for (int i = 1; i < Utils.getExampleUsers().size(); i++) {
            User user = Utils.getExampleUsers().get(i);
            myDataset.add(user);
        }
    }

    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

        holder.bind(myDataset.get(position), position, HomeRecyclerAdapter.this);
    }


    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public void refresh(int position) {
        myDataset.clear();
        for (int i = 1; i < Utils.getExampleUsers().size(); i++) {
            User user = Utils.getExampleUsers().get(i);
            myDataset.add(user);
        }
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, myDataset.size());
        notifyDataSetChanged();
    }
}
