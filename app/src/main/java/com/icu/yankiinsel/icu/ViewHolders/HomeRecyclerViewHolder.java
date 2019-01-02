package com.icu.yankiinsel.icu.ViewHolders;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.icu.yankiinsel.icu.Activities.HomeActivity;
import com.icu.yankiinsel.icu.Adapters.HomeRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.User;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;
import com.jgabrielfreitas.core.BlurImageView;

public class HomeRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mUsernameTextView;
    public BlurImageView mImageView;
    public TextView mLocationTextView;
    public TextView mInterestsTextView;
    public Context context;
    private ImageButton mBtnLike;
    private ImageButton mBtnDidndLike;
    private User mUser;
    private int mPosition;
    private RecyclerView.Adapter mAdapter;

    public HomeRecyclerViewHolder(View v) {
        super(v);
        mUsernameTextView = (TextView)  v.findViewById(R.id.tv_user_name);
        mImageView = (BlurImageView) v.findViewById(R.id.imageView);
        mLocationTextView = (TextView)  v.findViewById(R.id.tv_user_location);
        mInterestsTextView = (TextView)  v.findViewById(R.id.tv_user_interests);
        mInterestsTextView.setMovementMethod(new ScrollingMovementMethod());
        context = v.getContext();


        mBtnLike = v.findViewById(R.id.btn_like);
        mBtnDidndLike = v.findViewById(R.id.btn_didnt_like);

        mBtnLike.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                mBtnLike.startAnimation(pulse);

                for (int i = 0; i < Utils.getLikedUsers().size(); i++) {
                    if (Utils.getLikedUsers().get(i) == mUser) {
                        return;
                    }
                }
                Utils.getLikedUsers().add(mUser);
                Utils.dislikedUserSet.add(mUser);

                ((HomeRecyclerAdapter)mAdapter).refresh(mPosition);

                Toast toast = Toast.makeText(context, mUser.getName() + " " +  context.getResources().getString(R.string.added),
                        Toast.LENGTH_LONG);
                View view = toast.getView();
                view.getBackground().setColorFilter(context.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                TextView text = view.findViewById(android.R.id.message);
                text.setTextColor(context.getResources().getColor(R.color.white));
                toast.show();
            }
        });

        mBtnDidndLike.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Animation pulse = AnimationUtils.loadAnimation(context, R.anim.pulse);
                mBtnDidndLike.startAnimation(pulse);

                Utils.dislikedUserSet.add(mUser);

                ((HomeRecyclerAdapter)mAdapter).refresh(mPosition);


                Toast toast = Toast.makeText(context, mUser.getName() + " " + context.getResources().getString(R.string.removed),
                        Toast.LENGTH_LONG);
                View view = toast.getView();
                view.getBackground().setColorFilter(context.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_IN);
                TextView text = view.findViewById(android.R.id.message);
                text.setTextColor(context.getResources().getColor(R.color.white));
                toast.show();
            }
        });
    }

    public void bind(User user, int position, RecyclerView.Adapter adapter){
        mUser = user;
        mAdapter = adapter;
        mPosition = position;
        mUsernameTextView.setText(user.getNameAge());
        mImageView.setImageResource(android.R.color.transparent);
        mImageView.setImageResource(context.getResources().getIdentifier(mUser.imageName, "drawable", context.getPackageName()));
        mImageView.setBlur(5);
        mLocationTextView.setText(String.valueOf(user.location));
        mInterestsTextView.setText("");
        if (user.interests != null) {
            for (String interest: user.interests) {
                mInterestsTextView.setText(interest + "\n" + mInterestsTextView.getText());
            }
        }
    }

}
