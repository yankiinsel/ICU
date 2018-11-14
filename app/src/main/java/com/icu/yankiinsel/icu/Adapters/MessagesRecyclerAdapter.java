package com.icu.yankiinsel.icu.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.icu.yankiinsel.icu.Model.*;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;
import com.icu.yankiinsel.icu.ViewHolders.ReceivedMessageHolder;
import com.icu.yankiinsel.icu.ViewHolders.SentMessageHolder;


import java.util.ArrayList;
import java.util.List;

public class MessagesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private List<Message> mMessageList;
    private List<Message> thisMessageList;

    private String oppositeUserID;
    private String userID = Utils.getExampleUsers().get(0).getUserId();

    public MessagesRecyclerAdapter(List<Message> messageList, String userID) {
        this.mMessageList = messageList;
        this.oppositeUserID = userID;
    }

    @Override
    public int getItemCount() {
        thisMessageList = new ArrayList<>();
        for (int i = 0; i<mMessageList.size(); i++) {
            if (((mMessageList.get(i).getSender().getUserId().equals(oppositeUserID) ) &&
                    (mMessageList.get(i).getReciever().getUserId().equals(userID))) ||
                    ((mMessageList.get(i).getReciever().getUserId().equals(oppositeUserID)) &&
                            (mMessageList.get(i).getSender().getUserId().equals(userID)))) {
                thisMessageList.add(mMessageList.get(i));
            }
        }
        return thisMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = (Message) thisMessageList.get(position);

        if ((message.getSender().getUserId().equals(Utils.getExampleUsers().get(0).getUserId()))
            && message.getReciever().getUserId().equals(oppositeUserID)) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else if ((message.getReciever().getUserId().equals(Utils.getExampleUsers().get(0).getUserId()))
                && message.getSender().getUserId().equals(oppositeUserID)){
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
        return VIEW_TYPE_MESSAGE_SENT;
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) thisMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }
}