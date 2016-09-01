package com.example.android.nonlinearsystemflowsetup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class NavToShip extends AppCompatActivity {

    int counterPhotoAttempts = 1;
    int counterVideoAttempts = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_to_ship);

        // Switch from 'Navigate to Shipping - Photo Prompt" to "Call for Help"
        Button buttonNavToShipToCallForHelp = (Button) findViewById(R.id.button_navtoshipphoto_callforhelp);
        buttonNavToShipToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavToShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
                startActivity(intentNavToShipToCallForHelp);
            }
        });

        // Switch from 'Navigate to Shipping - Photo Prompt" to "Place Box"
        Button buttonNavToShipToPlaceBox = (Button) findViewById(R.id.button_navtoshipphoto_arrival_placebox);
        buttonNavToShipToPlaceBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNavToShipToPlaceBox = new Intent(getApplicationContext(), PlaceBox.class);
                startActivity(intentNavToShipToPlaceBox);
            }
        });

        // Play Audio - First Attempt
        MediaPlayer mpNavShipAttempt1 = MediaPlayer.create(this, R.raw.nav_to_ship_attempt1_sample);
        mpNavShipAttempt1.start();
    }

    public void navToShipPhotoNoArrival(View view){

        if (counterPhotoAttempts < 2 ){
            MediaPlayer mpNavShipAttempt2 = MediaPlayer.create(this, R.raw.nav_to_ship_attempt2_sample);
            mpNavShipAttempt2.start();
            counterPhotoAttempts = counterPhotoAttempts + 1;
        }else{
            setContentView(R.layout.activity_nav_to_ship_video);
            VideoView vvNavToShipVideo = (VideoView) findViewById(R.id.navtoship_video);
            vvNavToShipVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvNavToShipVideo.setMediaController(new MediaController(this));
            vvNavToShipVideo.start();
        }
    }

    public void navToShipVideoNoArrival(View view){
        if (counterVideoAttempts < 2){
            VideoView vvNavToShipVideo = (VideoView) findViewById(R.id.navtoship_video);
            vvNavToShipVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvNavToShipVideo.setMediaController(new MediaController(this));
            vvNavToShipVideo.start();
            counterVideoAttempts = counterVideoAttempts + 1;
        }else{
            Intent intentNavToShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
            startActivity(intentNavToShipToCallForHelp);
        }
    }

    public void navToShipVideoToCallForHelp(View view){
        Intent intentNavToShipToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
        startActivity(intentNavToShipToCallForHelp);
    }

    public void navToShipVideoToPlaceBox(View view){
        Intent intentNavToShipToPlaceBox = new Intent(getApplicationContext(), PlaceBox.class);
        startActivity(intentNavToShipToPlaceBox);
    }
}

