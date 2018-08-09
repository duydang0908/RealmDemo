package com.example.duyda.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.duyda.realm.Model.Account;

import io.realm.Realm;
import io.realm.RealmResults;

public class Login extends AppCompatActivity {

    private Realm realm;
    private TextView lbSignIn, lbSwitchSignUp;
    private EditText txtName, txtPass;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();

        lbSignIn = (TextView) findViewById(R.id.lbSignIn);
        lbSwitchSignUp = (TextView) findViewById(R.id.tvSignUp);

        txtName = (EditText) findViewById(R.id.txtName);
        txtPass = (EditText) findViewById(R.id.txtPassword);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        lbSwitchSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameLogin = txtName.getText().toString().trim();
                String passLogin = txtPass.getText().toString().trim();
                if (txtName.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty())
                    Toast.makeText(Login.this, "Vui lòng điền đẩy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else if ((txtName.getText().toString().equals("Admin") && txtPass.getText().toString().equals("Admin")) ||
                        (txtName.getText().toString().equals("admin") && txtPass.getText().toString().equals("admin"))) {
                    Intent intent = new Intent(Login.this, Admin.class);
                    startActivity(intent);
                } else if (checkLogin(nameLogin, passLogin)) {
                    Intent intent = new Intent(Login.this, Main.class);
                    startActivity(intent);
                    Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(Login.this, "Lỗi đăng nhập!", Toast.LENGTH_SHORT).show();
            }
        });

//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                Crashlytics.getInstance().crash(); // Force a crash
//            }
//        });

    }

    private Boolean checkLogin(String userName, String passWord) {
        Account login = realm.where(Account.class).equalTo("UserName", userName).and().equalTo("PassWord", passWord).findFirst();
        if (login != null)
            return true;
        else
            return false;
    }

}
