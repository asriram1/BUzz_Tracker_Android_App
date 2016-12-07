package com.example.karanraj.chauhan.courseplanner;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.name;

/**
 * Created by karanraj
 */

public class BeverageIntake implements Parcelable {

    private String mName;
    private int mQuantity;
    private String mTime;

    public BeverageIntake(String name, int quantity, String time) {
        mName = name;
        mQuantity = quantity;
        mTime = time;
    }

    public BeverageIntake(Parcel in) {
        mName = in.readString();
        mQuantity = in.readInt();
        mTime = in.readString();
    }

    public String getName() {
        return mName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public String getTime() {
        return mTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mQuantity);
        dest.writeString(mTime);
    }

    public static final Parcelable.Creator<BeverageIntake> CREATOR = new Parcelable.Creator<BeverageIntake>() {
        public BeverageIntake createFromParcel(Parcel in) {
            return new BeverageIntake(in);
        }

        public BeverageIntake[] newArray(int size) {
            return new BeverageIntake[size];
        }
    };


}
