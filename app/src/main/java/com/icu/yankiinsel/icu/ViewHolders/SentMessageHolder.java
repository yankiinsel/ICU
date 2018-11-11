package com.icu.yankiinsel.icu.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;

public class SentMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText;

    public SentMessageHolder(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
    }

    public void bind(Message message) {
        messageText.setText(message.getMessage());

        // Format the stored timestamp into a readable String using method.
        timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
    }
}
