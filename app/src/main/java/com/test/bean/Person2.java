package com.test.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roger on 2018/4/28.
 */

public class Person2 implements Parcelable{

    private String name;

    private int age;

    private String address;

    protected Person2(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.address = in.readString();
    }

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel in) {
            return new Person2(in);
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(address);
    }
}
