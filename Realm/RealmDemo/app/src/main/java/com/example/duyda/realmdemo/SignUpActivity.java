package com.example.duyda.realmdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duyda.realmdemo.Model.Account;

import io.realm.Realm;
import io.realm.RealmResults;

public class SignUpActivity extends AppCompatActivity {

    private TextView signIn;
    private EditText txtName, txtPass;
    private Button btnSignUp;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        realm = Realm.getDefaultInstance();


        signIn = (TextView) findViewById(R.id.textViewSwitchLogin);

        txtName = (EditText) findViewById(R.id.editTextUserName);
        txtPass = (EditText) findViewById(R.id.editTextPassword);

        btnSignUp = (Button) findViewById(R.id.buttonSignUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (txtName.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty())
//                    Toast.makeText(SignUpActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
//                else if (txtName.getText().toString().equals("admin"))
//                    Toast.makeText(SignUpActivity.this, "Tên đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
//                else {
//                    save_to_database(txtName.getText().toString().trim(), txtPass.getText().toString().trim());
//                    Toast.makeText(SignUpActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SignUpActivity.this, AdminActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
//
//    private void save_to_database(final String name, final String pass) {
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                Account account = bgRealm.createObject(Account.class);
//                account.setUserName(name);
//                account.setPassWord(pass);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                // Transaction was a success.
//                Log.v("Success", "OK");
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                // Transaction failed and was automatically canceled.
//                Log.e("Failed", error.getMessage());
//            }
//        });
//    }
//
//    private Boolean checkExist(String name) {
//        RealmResults<Account> result = realm.where(Account.class)
//                .equalTo("UserName", name)
//                .findAllAsync();
//        if (result != null)
//            return true;
//        else
//            return false;
//    }

}
