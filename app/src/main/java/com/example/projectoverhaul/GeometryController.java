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

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class GeometryController {


    public static boolean loading;

    public static ArrayList<AutoShopDetails> detailArrayList = new ArrayList();

    public static void manipulateData(StringBuffer buffer){


        loading = true;
        try {

            detailArrayList.clear();


            JSONObject jsonpObject = new JSONObject(buffer.toString());

            JSONArray array = jsonpObject.getJSONArray("results");


            for(int i=0; i<array.length(); i++){
                try {
                    JSONObject jsonObject = array.getJSONObject(i);
                    AutoShopDetails autoShopDetails = new AutoShopDetails();

                    if(jsonObject.getString("name")!=null)  autoShopDetails.setAutoShopName(jsonObject.getString("name"));
                    else  autoShopDetails.setAutoShopName("Not Available");

                    autoShopDetails.setGeometry(new double[]{jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                            jsonObject.getJSONObject("geometry").getJSONObject("location").getDouble("lng")});

                    detailArrayList.add(autoShopDetails);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        loading = false;
        Log.d("Array Loaded with size ", "Size of "+detailArrayList.size());
    }
}
