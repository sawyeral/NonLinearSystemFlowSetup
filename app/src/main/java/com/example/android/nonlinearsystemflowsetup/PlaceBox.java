package com.example.android.nonlinearsystemflowsetup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlaceBox extends AppCompatActivity {

    int counterAttemptsPhoto = 1;
    int counterAttemptsVideo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_box);

        // Switch from 'Scan Item - Photo Prompt' to 'Navigate to Shipping'
        Button buttonPlaceBoxToScanToShip = (Button) findViewById(R.id.button_placeboxphoto_correctnumber_scantoship);
        buttonPlaceBoxToScanToShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlaceBoxToScanToShip = new Intent(getApplicationContext(),ScanItemShip.class);
                startActivity(intentPlaceBoxToScanToShip);
            }
        });

        // Switch from 'Place Box - Photo Prompt' to 'Call for Help'
        Button buttonPlaceBoxToCallForHelp = (Button) findViewById(R.id.button_placeboxphoto_callforhelp);
        buttonPlaceBoxToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPlaceBoxToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                startActivity(intentPlaceBoxToCallForHelp);
            }
        });

        // Play Attempt 1 'Place Box' Audio Prompt
        MediaPlayer mpPlaceBoxAttempt1 = MediaPlayer.create(this, R.raw.place_box_attempt1_sample);
        mpPlaceBoxAttempt1.start();
    }

    public void placeBoxNoAction(View view){
        if (counterAttemptsPhoto < 2){
            MediaPlayer mpPlaceBoxNoAction = MediaPlayer.create(this, R.raw.place_box_noresponse_sample);
            mpPlaceBoxNoAction.start();
            counterAttemptsPhoto = counterAttemptsPhoto + 1;
        } else{
            setContentView(R.layout.activity_place_box_video);
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
        }
    }

    public void placeBoxTooMany(View view){
        if (counterAttemptsPhoto < 2){
            MediaPlayer mpPlaceBoxTooMany = MediaPlayer.create(this, R.raw.place_box_toomany_sample);
            mpPlaceBoxTooMany.start();
            counterAttemptsPhoto = counterAttemptsPhoto + 1;
        } else{
            setContentView(R.layout.activity_place_box_video);
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
        }
    }

    public void placeBoxWrongItem(View view){
        if (counterAttemptsPhoto < 2){
            MediaPlayer mpPlaceBoxWrongItem = MediaPlayer.create(this, R.raw.place_box_wrongitem_sample);
            mpPlaceBoxWrongItem.start();
            counterAttemptsPhoto = counterAttemptsPhoto + 1;
        } else{
            setContentView(R.layout.activity_place_box_video);
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
        }
    }

    public void placeBoxVideoNoAction(View view){
        if (counterAttemptsVideo < 2){
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
            counterAttemptsVideo = counterAttemptsVideo + 1;
        } else{
            Intent intentPlaceBoxToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
            startActivity(intentPlaceBoxToCallForHelp);
        }
    }

    public void placeBoxVideoTooMany(View view){
        if (counterAttemptsVideo < 2){
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
            counterAttemptsVideo = counterAttemptsVideo + 1;
        } else{
            Intent intentPlaceBoxToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
            startActivity(intentPlaceBoxToCallForHelp);
        }
    }

    public void placeBoxVideoWrongItem(View view){
        if (counterAttemptsVideo < 2){
            VideoView vvPlaceBoxVideo = (VideoView) findViewById(R.id.placebox_video);
            vvPlaceBoxVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvPlaceBoxVideo.setMediaController(new MediaController(this));
            vvPlaceBoxVideo.start();
            counterAttemptsVideo = counterAttemptsVideo + 1;
        } else{
            Intent intentPlaceBoxToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
            startActivity(intentPlaceBoxToCallForHelp);
        }
    }

    public void placeBoxVideoCorrectNumber(View view){
        Intent intentPlaceBoxToScanToShip = new Intent(getApplicationContext(),ScanItemShip.class);
        startActivity(intentPlaceBoxToScanToShip);
    }

    public void placeBoxVideoToCallForHelp(View view){
        Intent intentPlaceBoxToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
        startActivity(intentPlaceBoxToCallForHelp);
    }

}

