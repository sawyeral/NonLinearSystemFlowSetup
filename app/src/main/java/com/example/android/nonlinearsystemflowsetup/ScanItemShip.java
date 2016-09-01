package com.example.android.nonlinearsystemflowsetup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ScanItemShip extends AppCompatActivity {

    int counterPhotoAttempts = 1;
    int counterVideoAttempts = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item_ship);

        // Switch from 'Navigate to Shipping - Photo Prompt" to "Call for Help"
        Button buttonScanItemShipToCallForHelp = (Button) findViewById(R.id.button_scanitemshipphoto_callforhelp);
        buttonScanItemShipToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentScanItemShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
                startActivity(intentScanItemShipToCallForHelp);
            }
        });

        // Switch from 'Navigate to Shipping - Photo Prompt" to "Main"
        Button buttonScanItemShipToMain = (Button) findViewById(R.id.button_scanitemshipphoto_arrival_main);
        buttonScanItemShipToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentScanItemShipToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentScanItemShipToMain);
            }
        });

        // Play Audio - First Attempt
        MediaPlayer mpScanItemShipAttempt1 = MediaPlayer.create(this, R.raw.scan_item_ship_attempt1);
        mpScanItemShipAttempt1.start();
    }

    public void scanItemShipPhotoNoArrival(View view){

        if (counterPhotoAttempts < 2 ){
            MediaPlayer mpScanItemShipAttempt2 = MediaPlayer.create(this, R.raw.scan_item_ship_noaction);
            mpScanItemShipAttempt2.start();
            counterPhotoAttempts = counterPhotoAttempts + 1;
        }else{
            setContentView(R.layout.activity_scan_item_ship_video);
            VideoView vvScanItemShipVideo = (VideoView) findViewById(R.id.navtoship_video);
            vvScanItemShipVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvScanItemShipVideo.setMediaController(new MediaController(this));
            vvScanItemShipVideo.start();
        }
    }

    public void scanItemShipVideoNoArrival(View view){
        if (counterVideoAttempts < 2){
            VideoView vvScanItemShipVideo = (VideoView) findViewById(R.id.navtoship_video);
            vvScanItemShipVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvScanItemShipVideo.setMediaController(new MediaController(this));
            vvScanItemShipVideo.start();
            counterVideoAttempts = counterVideoAttempts + 1;
        }else{
            Intent intentScanItemShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
            startActivity(intentScanItemShipToCallForHelp);
        }
    }

    public void scanItemShipVideoToCallForHelp(View view){
        Intent intentScanItemShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
        startActivity(intentScanItemShipToCallForHelp);
    }

    public void scanItemShipVideoToMain(View view){
        Intent intentScanItemShipToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intentScanItemShipToMain);
    }
}

