package com.icu.yankiinsel.icu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.icu.yankiinsel.icu.NotificationUtils;
import com.icu.yankiinsel.icu.R;

public class LoginActivity  extends AppCompatActivity {

    Button mSignInButton;
    EditText mEmailEditText;
    EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSignInButton = findViewById(R.id.btn_login);
        mEmailEditText = findViewById(R.id.edit_email);
        mPasswordEditText = findViewById(R.id.edit_password);

        mSignInButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent k = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(k);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void testMessageNotification(View view) {
        NotificationUtils.remindUserNewMessages(this);
    }
    public void testMatchesNotification(View view) {
        NotificationUtils.remindUserNewMatches(this);
    }


}
