package com.example.duyda.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duyda.realm.Model.Account;

import io.realm.Realm;

public class SignUp extends AppCompatActivity {

    private Realm realm;
    private TextView lbSignUp, lbSwitchSignIn;
    private EditText txtName, txtPass;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        realm = Realm.getDefaultInstance();

        lbSignUp = (TextView) findViewById(R.id.tvSignUp);
        lbSwitchSignIn = (TextView) findViewById(R.id.tvSignIn);

        txtName = (EditText) findViewById(R.id.txtName);
        txtPass = (EditText) findViewById(R.id.txtPassword);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        lbSwitchSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtName.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty())
                    Toast.makeText(SignUp.this, "Vui lòng điền đẩy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else if (txtName.getText().toString().equals("Admin") || txtName.getText().toString().equals("admin"))
                    Toast.makeText(SignUp.this, "Tên đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                else if(checkExist(txtName.getText().toString().trim()))
                    Toast.makeText(SignUp.this,"Tên đăng nhập đã tồn tại",Toast.LENGTH_SHORT).show();
                else {
                    save_to_database(txtName.getText().toString().trim(), txtPass.getText().toString().trim());
                    Toast.makeText(SignUp.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void save_to_database(final String Name, final String Pass) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Account account = bgRealm.createObject(Account.class);
                account.setUserName(Name);
                account.setPassWord(Pass);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.v("Success", "OK");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("Failed", error.getMessage());
            }
        });
    }

    private Boolean checkExist(String findName) {
        Account user = realm.where(Account.class).equalTo("UserName", findName).findFirst();

        if (user != null)
            // Exists
            return true;
        else
            // Not exist
            return false;
    }
}
