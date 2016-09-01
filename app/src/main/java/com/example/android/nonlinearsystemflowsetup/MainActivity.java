package com.example.android.nonlinearsystemflowsetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int itemNumber = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonMainToNavItemPhoto = (Button) findViewById(R.id.button_main_begin_navtoitemphoto);
        buttonMainToNavItemPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMainToNavItemPhoto = new Intent(getApplicationContext(),NavToItem.class);
                intentMainToNavItemPhoto.putExtra("item-number", itemNumber);
                startActivity(intentMainToNavItemPhoto);
            }
        });

        Button buttonMainToCallForHelp = (Button) findViewById(R.id.button_main_callforhelp);
        buttonMainToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMainToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                intentMainToCallForHelp.putExtra("item-number", itemNumber);
                startActivity(intentMainToCallForHelp);
            }
        });
    }
}
