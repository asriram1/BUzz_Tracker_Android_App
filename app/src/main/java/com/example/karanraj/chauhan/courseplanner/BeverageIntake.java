package com.example.karanraj.chauhan.courseplanner;

/**
 * Created by karanraj
 */

public class BeverageIntake {

    private String mName;
    private int mQuantity;
    private String mTime;

    public BeverageIntake(String name, int quantity, String time) {
        mName = name;
        mQuantity = quantity;
        mTime = time;
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

}
