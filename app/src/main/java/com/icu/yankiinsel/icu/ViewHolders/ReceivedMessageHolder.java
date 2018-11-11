package com.icu.yankiinsel.icu.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText, nameText;
    ImageView profileImage;
    public Context context;

    public ReceivedMessageHolder(View v) {
        super(v);
        messageText = (TextView) v.findViewById(R.id.text_message_body);
        timeText = (TextView) v.findViewById(R.id.text_message_time);
        nameText = (TextView) v.findViewById(R.id.text_message_name);
        profileImage = (ImageView) v.findViewById(R.id.image_message_profile);
        context = v.getContext();
    }

    public void bind(Message message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
        nameText.setText(message.getSender().getName());

        // Insert the profile image from the URL into the ImageView.
        Utils.displayRoundImageFromUrl(context, message.getSender().getProfileUrl(), profileImage);
    }
}
