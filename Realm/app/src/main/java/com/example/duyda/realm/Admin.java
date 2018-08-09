package com.example.duyda.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duyda.realm.Model.Account;

import io.realm.Realm;
import io.realm.RealmResults;

public class Admin extends AppCompatActivity {

    private EditText txtName;
    private Button btnDelete;
    private TextView lbSignOut, lbShow;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        realm = Realm.getDefaultInstance();

        txtName = (EditText) findViewById(R.id.txtName);

        btnDelete = (Button) findViewById(R.id.btnDelete);

        lbSignOut = (TextView) findViewById(R.id.tvSignOut);
        lbShow = (TextView) findViewById(R.id.textViewShow);

        lbSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, Login.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_data(txtName.getText().toString().trim());
                loadData();
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadData();
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadData();
    }

    private void loadData() {
        RealmResults<Account> result = realm.where(Account.class).findAll();
        result.load();
        String output="";
        for(Account account:result)
            output+=result.toString();
        lbShow.setText(output);
    }

    private void delete_data(String Name)
    {
        // obtain the results of a query
        final RealmResults<Account> results = realm.where(Account.class).equalTo("UserName",Name).findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
//                results.deleteFirstFromRealm();15

                // remove a single object
//                Account account = results.get(5);
//                account.deleteFromRealm();

                // Delete all matches
                results.deleteAllFromRealm();
            }
        });
    }

}
