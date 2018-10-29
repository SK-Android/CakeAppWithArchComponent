package com.androidapp.mcs.cakeappwitharchcomp.model;

import java.util.ArrayList;
import java.util.List;

public class SampleDataProvider {

    public static List<Cakes> getCakes()
    {
        List<Cakes> cakes = new ArrayList<>();
        cakes.add(new Cakes("Title1","Desc1",""));
        cakes.add(new Cakes("Title2","Desc2",""));
        return cakes;
    }

    public static int getCount()
    {
        return getCakes().size();
    }




    }

