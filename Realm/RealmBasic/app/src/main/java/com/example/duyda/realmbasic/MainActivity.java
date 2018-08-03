package com.example.duyda.realmbasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duyda.realmbasic.Model.Person;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private EditText txtName, txtAge;

    private Button btnView, btnSave, btnDelete;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();

        txtName = (EditText) findViewById(R.id.editTextName);
        txtAge = (EditText) findViewById(R.id.editTextAge);

        btnView = (Button) findViewById(R.id.buttonView);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        textView = (TextView) findViewById(R.id.textView);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_to_database(txtName.getText().toString().trim(), Integer.parseInt(txtAge.getText().toString().trim()));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh_data();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = txtName.getText().toString();
                delete_data(Name);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void save_to_database(final String Name, final int Age) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person person = bgRealm.createObject(Person.class);
                person.setName(Name);
                person.setAge(Age);
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

    private void refresh_data() {
        RealmResults<Person> result = realm.where(Person.class)
                .findAllAsync();
        result.load();
        String output = "";
        for (Person person : result)
            output += person.toString();
        textView.setText(output);
    }

    private void delete_data(String Name) {
        // obtain the results of a query
        final RealmResults<Person> results = realm.where(Person.class).findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
//                results.deleteFirstFromRealm();
//                results.deleteLastFromRealm();

                // remove a single object
//                Person dog = results.get(5);
//                dog.deleteFromRealm();

                // Delete all matches
                results.deleteAllFromRealm();
            }
        });

    }

}
