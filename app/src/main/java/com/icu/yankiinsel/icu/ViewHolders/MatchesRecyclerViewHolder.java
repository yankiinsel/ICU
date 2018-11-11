package com.icu.yankiinsel.icu.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.icu.yankiinsel.icu.Interfaces.ListItemClickListener;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.jgabrielfreitas.core.BlurImageView;

public class MatchesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public BlurImageView mImageView;
    public TextView mTextView;
    public Context context;
    private ListItemClickListener mListener;

    public MatchesRecyclerViewHolder(View v,  ListItemClickListener listener) {
        super(v);
        mImageView = (BlurImageView) v.findViewById(R.id.imageView);
        mTextView = (TextView) v.findViewById(R.id.tv_matches_user_name);
        mListener = listener;
        v.setOnClickListener(this);
        context = v.getContext();
    }

    public void bind(User user){
        mImageView.setImageResource(context.getResources().getIdentifier(user.imageName, "drawable", context.getPackageName()));
        mImageView.setBlur(25);
        mTextView.setText(String.valueOf(user.getNameAge()));
    }

    @Override
    public void onClick(View v) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }
}
