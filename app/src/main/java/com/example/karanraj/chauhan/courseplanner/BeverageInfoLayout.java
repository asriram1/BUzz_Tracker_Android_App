package com.example.karanraj.chauhan.courseplanner;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

/**
 * Created by karanraj on 12/4/16.
 */

public class BeverageInfoLayout extends RelativeLayout {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private EditText mQuantityField;
    private EditText mTimeField;
    private Spinner mBeverageOptionsSpinner;


    public BeverageInfoLayout(Context context){
        super(context);
        this.mContext = context;
        inflate();
    }

    public BeverageInfoLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
        inflate();
    }

    public BeverageInfoLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        inflate();
    }

    // specific to api 21
    @TargetApi(21)
    public BeverageInfoLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        inflate();
    }

    private void inflate(){
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.beverage_info_layout,this,true);

        //try removing this. on each call?
        mBeverageOptionsSpinner = (Spinner) this.findViewById(R.id.beverage_options_spinner);

        mQuantityField = (EditText) this.findViewById(R.id.quantity_field);

        mTimeField = (EditText) this.findViewById(R.id.time_field);
        mTimeField.setInputType(InputType.TYPE_CLASS_PHONE);

    }

    protected Spinner getBeverageOptionsSpinner(){return mBeverageOptionsSpinner;}

    protected EditText getQuantityField(){ return mQuantityField;}

    protected EditText getTimeField(){return mTimeField;}
    
    /*  returns 2 if there is sufficient class information
        returns 1 if there is insufficient information
     */
    protected int amountOfClassInfo(){
        if (mBeverageOptionsSpinner.getSelectedItemPosition()!=0 && !mTimeField.getText().toString().equals("") && !mQuantityField.getText().toString().equals("")){
            return 2;
        } else {
            return 1;
        }
    }


}
