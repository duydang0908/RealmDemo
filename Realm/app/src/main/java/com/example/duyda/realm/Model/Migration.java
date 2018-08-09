package com.example.duyda.realm.Model;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }

//        if (oldVersion == 0) {
//            schema.create("Account")
//                    .addField("UserName", String.class)
//                    .addField("PassWord", String.class);
//            oldVersion++;
//        }

        // Migrate to version 2: Add a primary key + object references
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     @PrimaryKey
        //     private int age;
        //     private Dog favoriteDog;
        //     private RealmList<Dog> dogs;
        //     // getters and setters left out for brevity
        // }

        if (oldVersion == 1) {
            schema.get("Account")
                    .addField("id", long.class, FieldAttribute.PRIMARY_KEY);
            oldVersion++;
        }
    }
}