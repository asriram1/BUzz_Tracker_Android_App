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

    public static ArrayList<Double> soberalcoholcalculator(double genderConstant, double weight, int lightbeer, int shotOfVodka, int wine, int liquor)

    {

        ArrayList<Double> BAClevelsArray = new ArrayList<>();
        double baclevel=1;
        double total;

        double lightbeeramount = lightbeer * 0.48; //in ounces
        double vodkashotamount = shotOfVodka * 0.60;  //in ounces
        double wineamount = wine * 0.60; //inounces
        double liquoramount = liquor * 0.50; //inounces

        total = lightbeeramount + vodkashotamount + wineamount + liquoramount;

        int hour = 0;
        while (true){

            baclevel = ((total * multiconstant) / (weight * genderConstant) - 0.15*hour);
            baclevel =Double.parseDouble(new DecimalFormat("##.####").format(baclevel));
            if(baclevel<=0.08){
                break;
            }
            Log.d(TAG, "soberalcoholcalculator: "+ baclevel );
            BAClevelsArray.add(baclevel);
            hour++;


        }




        return BAClevelsArray;
    }

    public static double pacerAlcoholCalculator(double genderConstant, double weight, int amount, String type )

    {
        double baclevel;
        double total = 0.0;



        switch (type){
            case  "Regular Beer (5%, 12oz)":
                            total = amount * 0.60;
                            break;
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

        baclevel = (total * multiconstant) / (weight * genderConstant);
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
