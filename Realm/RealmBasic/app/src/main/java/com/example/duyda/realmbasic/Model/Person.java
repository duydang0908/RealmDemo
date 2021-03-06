package com.example.duyda.realmbasic.Model;

import io.realm.RealmObject;

public class Person extends RealmObject {
    @Override
    public String toString() {
        return "Person{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }

    String Name;
    int Age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
