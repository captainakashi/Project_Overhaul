package com.example.projectoverhaul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.projectoverhaul.databinding.ActivityMainBinding;
import com.example.projectoverhaul.databinding.ActivityMechanicBinding;

import java.util.ArrayList;
import java.util.List;

public class Mechanic extends AppCompatActivity {


    ActivityMechanicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMechanicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar,R.drawable.avatar};
        String[] name = {"Mechanic 1       ","Mechanic 2    ","Mechanic 3                       ","Mechanic 4 ","Mechanic 5               ","Mechanic 6            ",
                "Mechanic 7                    ","Mechanic 8                  ","Mechanic 9             ","Mechanic 10      "};
        String[] lastMessage = {"                        FROM : GR Tuning Auto Shop","                            FROM : Jonar's Auto Repair Shop","  FROM : AutoBarts Auto Shop","                                FROM : AutoShack ","             FROM : Landucan Repair Services","                 FROM : Okayam Auto Repair Shop",
                "      FROM : TJ's Auto Care","         FROM : Kris Auto Repair Shop","                FROM : RC Auto Repair Shop","                      FROM : NORCAR Speedworks"};
        String[] lastmsgTime = {"> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <","> Available for Consultation <"};
        String[] phoneNo = {"Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939","Globe: 0927-9961-365 | Smart: 0920-1601-939"};
        String[] country = {"Camp 7","Kennon Road","Upper P. Burgos","Bokawkan/Ferguson Road","Kennon Road","Magsaysay Ave.","Everlasting St. Near Kisad","Hamada Subdiv. Rd.","Ambiong Road","Magsaysay Avenue"};

        ArrayList<User> userArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            User user = new User(name[i],lastMessage[i],lastmsgTime[i],phoneNo[i],country[i],imageId[i]);
            userArrayList.add(user);

        }

        ListAdapter listAdapter = new ListAdapter(Mechanic.this,userArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Mechanic.this,UserActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("phone",phoneNo[position]);
                i.putExtra("country",country[position]);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);

                }
            });





    }
}