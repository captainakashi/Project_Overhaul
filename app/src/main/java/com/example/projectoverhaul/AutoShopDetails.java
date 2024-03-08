/*
 * %W% %E% Zain-Ul-Abedin
 *
 * Copyright (c) 2017-2018. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of ZainMustafaaa.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * for learning purposes.
 *
 */
package com.example.projectoverhaul;

import java.util.Arrays;



public class AutoShopDetails {


    private String AutoShopName;



    private double[] geometry;


    public String getAutoShopName() {
        return AutoShopName;
    }

    public void setAutoShopName(String AutoShopName) {
        this.AutoShopName = AutoShopName;
    }

    public double[] getGeometry() {
        return geometry;
    }

    public void setGeometry(double[] geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "AutoShopDetails{" +
                ", AutoShopName='" + AutoShopName + '\'' +
                ", geometry=" + Arrays.toString(geometry) +
                '}';
    }
}
