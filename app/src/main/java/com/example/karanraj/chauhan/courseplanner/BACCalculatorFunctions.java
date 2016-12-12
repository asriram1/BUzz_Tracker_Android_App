package com.example.karanraj.chauhan.courseplanner;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.karanraj.chauhan.courseplanner.R.string.weight;

/**
 * Created by rijish on 12/6/16.
 * Functions to be used by app for calculation of BAC at different time intervals
 */

public class BACCalculatorFunctions {

    private static final String TAG = "askc";
    final static double multiconstant = 5.14;

    public static ArrayList<Double> soberalcoholcalculator(double genderConstant, double weight, int beer, int shotOfVodka, int wine, int liquor)

    {

        ArrayList<Double> BAClevelsArray = new ArrayList<>();       //Create an array list to add multiple BACs
        double baclevel=1;
        double total;                       //total is the total amount of alcohol consumed

        double beeramount = beer * 0.60; //in ounces
        double vodkashotamount = shotOfVodka * 0.60;  //in ounces
        double wineamount = wine * 0.60; //inounces
        double liquoramount = liquor * 0.60; //inounces

        total = beeramount + vodkashotamount + wineamount + liquoramount;  //alcohol total

        int hour = 0;
        while (true){

            baclevel = ((total * multiconstant) / (weight * genderConstant) - 0.015*hour);   //using bac formula to calculate bac levels with different  h (time)
            baclevel =Double.parseDouble(new DecimalFormat("##.###").format(baclevel));    //changing the double to display only two digits after the decimal
            BAClevelsArray.add(baclevel);           // add bac value to the array list

            if(baclevel<=0.05){
                break;                          //break condition
            }
            Log.d(TAG, "soberalcoholcalculator: "+ baclevel );
            hour++;                                 // time iterator (hour)
        }
        return BAClevelsArray;          //returns the array list including all bac levels
    }

    public static double pacerAlcoholCalculator(double genderConstant, double weight, int amount, String type ) //bac calculator for each drink at any given time

    {
        double baclevel;
        double total = 0.0;



        switch (type){
            case  "Regular Beer (5%, 12oz)":
                            total = amount * 0.60;
                            break;                                                          //drinks switch case
            case  "Light Beer (4%, 12oz)":
                            total = amount *0.48;
                            break;
            case  "Table Wine (12%, 5oz)":
                            total = amount*0.60;
                            break;
            case  "Wine Cooler (5%, 12oz)":
                            total = amount *0.60;
                            break;
            case  "Vodka (40%, 1.25oz)":
                            total = amount *0.50;
                            break;
            case  "Gin (40%, 1.25oz)":
                            total = amount *0.50;
                            break;
            case  "Rum (40%, 1.25oz)":
                            total = amount* 0.50;
                            break;
            case  "Tequila (40%, 1.25oz)":
                            total = amount* 0.5;
                            break;
            case   "Bourbon (40%, 1.25oz)":
                            total = amount*0.5;
                            break;
            case  "Scotch (40%, 1.25oz)":
                            total = amount * 0.5;
                            break;
        }

        baclevel = (total * multiconstant) / (weight * genderConstant);                         //bac value return
        return baclevel;

    }


    public static int[] time(double baclevel)

    {

        double thresh = 0.08;
        int hrs;
        hrs = (int) ((baclevel - thresh) / 0.15) + 1;

        int[] time = new int[hrs];

        for (int i = 0; i <= hrs; i++) {

            time[i] = i;

        }

        return time;

    }


    public static double[] level(double baclevel)


    {


        double thresh = 0.08;
        int hrs;
        hrs = (int) ((baclevel - thresh) / 0.15);


        double[] levels = new double[hrs];

        for (int j = 0; j <= hrs; j++)

        {

            levels[j] = baclevel;
            baclevel -= 0.15;
            if (baclevel < 0)
                break;

        }
        return levels;

    }

}
