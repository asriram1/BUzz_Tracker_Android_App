package com.example.karanraj.chauhan.courseplanner;
import android.os.Parcel;
import java.util.Comparator;
import android.os.Parcelable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by karanraj
 */

public class BeverageIntake implements Parcelable {

    private String mName;
    private int mQuantity;
    private Integer mTime;
    private double mBacAdded;

    public BeverageIntake(String name, int quantity, String time,  double bacAdded) {
        mName = name;
        mQuantity = quantity;
        mTime = Integer.parseInt(time.substring(0,2)+time.substring(3,4));
        mBacAdded = bacAdded;
    }

    public BeverageIntake(Parcel in) {
        mName = in.readString();
        mQuantity = in.readInt();
        mTime = in.readInt();
        mBacAdded = in.readDouble();
    }

    public String getName() {
        return mName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public Integer getTime() {
        return mTime;
    }

    public double getBacAdded() {
        return mBacAdded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mQuantity);
        dest.writeInt(mTime);
        dest.writeDouble(mBacAdded);
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

public class BeverageComparator implements Comparator<BeverageIntake>


{

    public int compare(BeverageIntake self, BeverageIntake other)

    {


        return self.getTime().compareTo(other.getTime());
    }


}


//call Collections.sort(ArrayList, new BeverageComparator()) to sort your array list;

