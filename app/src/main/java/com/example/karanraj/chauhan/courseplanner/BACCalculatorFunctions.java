package com.example.karanraj.chauhan.courseplanner;

/**
 * Created by rijish on 12/6/16.
 * Functions to be used by app for calculation of BAC at different time intervals
 */

public class BACCalculatorFunctions {


    final static double multiconstant = 5.14;

    public double soberalcoholcalculator(double genderConstant, double weight, int lightbeer, int regbeer, int wine, int liquor)

    {
        double baclevel;
        double total;

        double lightbeeramount = lightbeer * 0.48; //in ounces
        double regbeeramount = regbeer * 0.60;  //in ounces
        double wineamount = wine * 0.60; //inounces
        double liquoramount = liquor * 0.50; //inounces

        total = lightbeeramount + regbeeramount + wineamount + liquoramount;

        baclevel = (total * multiconstant) / (weight * genderConstant);
        return baclevel;

    }


    public int[] time(double baclevel)

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


    public double[] level(double baclevel)


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
