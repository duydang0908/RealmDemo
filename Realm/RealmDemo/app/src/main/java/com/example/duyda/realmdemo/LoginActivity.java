package com.example.duyda.realmdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView lbLogin, signUp;
    private EditText txtName, txtPass;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lbLogin = (TextView) findViewById(R.id.tvlbLogin);
        signUp = (TextView) findViewById(R.id.textViewSwitchSignUp);

        txtName = (EditText) findViewById(R.id.etNL);
        txtPass = (EditText) findViewById(R.id.etPL);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
