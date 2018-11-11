package com.icu.yankiinsel.icu.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.jgabrielfreitas.core.BlurImageView;

public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mUsernameTextView;
    public BlurImageView mImageView;
    public TextView mLocationTextView;
    public TextView mInterestsTextView;
    public Context context;

    public HomeRecyclerViewHolder(View v) {
        super(v);
        mUsernameTextView = (TextView)  v.findViewById(R.id.tv_user_name);
        mImageView = (BlurImageView) v.findViewById(R.id.imageView);
        mLocationTextView = (TextView)  v.findViewById(R.id.tv_user_location);
        mInterestsTextView = (TextView)  v.findViewById(R.id.tv_user_interests);
        mInterestsTextView.setMovementMethod(new ScrollingMovementMethod());
        context = v.getContext();
    }

    public void bind(User user){
        mUsernameTextView.setText(user.getNameAge());
        mImageView.setImageResource(context.getResources().getIdentifier(user.imageName, "drawable", context.getPackageName()));
        mImageView.setBlur(25);
        mLocationTextView.setText(String.valueOf(user.location));

        for (String interest: user.interests) {
            mInterestsTextView.setText(interest + "\n"+ mInterestsTextView.getText());
        }
    }

}
