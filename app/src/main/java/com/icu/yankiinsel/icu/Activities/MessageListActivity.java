package com.icu.yankiinsel.icu.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.icu.yankiinsel.icu.Adapters.MessagesRecyclerAdapter;
import com.icu.yankiinsel.icu.Model.Message;
import com.icu.yankiinsel.icu.R;
import com.icu.yankiinsel.icu.Utils;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private RecyclerView.Adapter mMessageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userID= getIntent().getStringExtra("USER_ID");

        setContentView(R.layout.activity_message_list);
        mMessageRecycler = (RecyclerView) findViewById(R.id.recyclerview_message_list);

        mMessageRecycler.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mMessageRecycler.setLayoutManager(mLayoutManager);

        messageList = new ArrayList<>();
        messageList = Utils.getExampleMessages();

        mMessageAdapter = new MessagesRecyclerAdapter(messageList, userID);
        mMessageRecycler.setAdapter(mMessageAdapter);
    }
}