package com.example.duyda.realmdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {

    private EditText txtDelete;
    private TextView tvShowData, tvSignOut;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txtDelete = (EditText) findViewById(R.id.etND);

        tvShowData = (TextView) findViewById(R.id.tvShow);
        tvSignOut = (TextView) findViewById(R.id.tvLogOut);

        btnDelete = (Button) findViewById(R.id.btnDelete);

        tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
