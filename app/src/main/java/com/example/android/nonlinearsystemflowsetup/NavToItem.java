package com.example.android.nonlinearsystemflowsetup;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import static com.example.android.nonlinearsystemflowsetup.R.id;
import static com.example.android.nonlinearsystemflowsetup.R.layout;
import static com.example.android.nonlinearsystemflowsetup.R.raw;

public class NavToItem extends AppCompatActivity {

    int counterNavToItemPhoto = 1; // Counter for number of photo prompts given
    int counterNavToItemVideo = 1; // Counter for number of video prompts given
    //public int itemNumber= getIntent().getIntExtra("item-number",1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {// was protected
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_nav_to_item_photo); // Set layout to Nav to Item Photo


        // Pulling Item Number
       final int itemNumber = getIntent().getIntExtra("item-number",1); // Pull item number from previous activity
       final int itemNumberCurrentNavToItemPhoto = itemNumber; // store item number in variable for this activity (photo)

//         Displaying the item number on the screen
        TextView itemNumberView = (TextView) findViewById(id.navtoitemphoto_itemnumberdisplay);
        itemNumberView.setText(String.valueOf(itemNumberCurrentNavToItemPhoto)); // set text on prompt to show current item number

        // On Create we want to play the audio for the initial photo prompt attempt
        MediaPlayer mpNavItemAttempt1 = MediaPlayer.create(this, R.raw.navigate_to_item_attempt1_sample); // Define - Audio for Initial Photo Prompt
        mpNavItemAttempt1.start(); // Execute Void - Play Photo Prompt Audio

        // Switch from 'Navigate to Item - Photo Prompt" to "Call for Help"
        Button buttonNavToItemToCallForHelp = (Button) findViewById(id.button_navtoitemphoto_callforhelp); // Define - Call for Help Button
        buttonNavToItemToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On Click - Call for Help Button
                Intent intentNavToItemToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call For Help
                startActivity(intentNavToItemToCallForHelp); // Implement intent - Redirect to Call for Help
            }
        });


        // Switch from 'Navigate to Item - Photo Prompt' to 'Scan Shelf'
        Button buttonNavToItemToScanShelf = (Button) findViewById(id.button_navtoitemphoto_arrival_scanshelf); // Define - Arrive Button (Nav to Item TO Scan Shelf)
        buttonNavToItemToScanShelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On Click - Arrival Button
//                int itemNumber = itemNumber;
                Intent intentNavToItemToScanShelf = new Intent(getApplicationContext(), ScanShelf.class); // Set intent - Redirect to Scan Shelf
                intentNavToItemToScanShelf.putExtra("item-number", itemNumberCurrentNavToItemPhoto); // Transfer Data - Item Number TO Scan Shelf
                startActivity(intentNavToItemToScanShelf); // Implement Intent - Redirect to Scan Shelf
            }
        });


        // Play second photo prompt for 'Navigate to Item - Photo Prompt'
        // Prompt if No Arrival criteria met
        final Button buttonNavToItemSecondAttempt = (Button) findViewById(id.button_navtoitemphoto_noarrival_navtoitemvideo); // Define - No Arrival Button
        buttonNavToItemSecondAttempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On Click - No Arrival Button

                // Intent intentNavToItemPhotoToNavToItemVideo = new Intent(getApplicationContext(), NavToItemVideo.class);
                //startActivity(intentNavToItemPhotoToNavToItemVideo);
                if (counterNavToItemPhoto < 2) { // If given less than two photo prompts, replay photo prompt
                    navToItemPhotoPlay(); // Execute Void - Play photo prompt audio
                    counterNavToItemPhoto = counterNavToItemPhoto + 1; // Increase Counter for Photo Prompt
                } else { // If given more than two Photo Prompts - Redirect to Video Prompt
                    //Intent intentNavToItemPhotoToNavToItemVideo = new Intent(getApplicationContext(), ScanItem.class);
                    //startActivity(intentNavToItemPhotoToNavToItemVideo);
                    setContentView(layout.activity_nav_to_item_video); // Change Layout - Video Prompt

                    // Displaying the item number on the screen
                    TextView itemNumberView = (TextView) findViewById(id.navtoitemvideo_itemnumberdisplay);
                    itemNumberView.setText(String.valueOf(itemNumberCurrentNavToItemPhoto)); // set text on prompt to show current item number

                    navToItemVideoPrompt(itemNumber); // Execute Void - Play Video Prompts
                }
            }

        });

//        // On Create we want to play the audio for the initial photo prompt attempt
//        MediaPlayer mpNavItemAttempt1 = MediaPlayer.create(this, R.raw.navigate_to_item_attempt1_sample); // Define - Audio for Initial Photo Prompt
//        mpNavItemAttempt1.start(); // Execute Void - Play Photo Prompt Audio
    }

    // Void plays audio for Photo Prompt
    public void navToItemPhotoPlay() {
        MediaPlayer mpNavItemAttempt2 = MediaPlayer.create(this, R.raw.navigate_to_item_attempt2_sample); // Define - Audio for Photo Prompt Attempt 2 (No/Incorrect Arrival)
        mpNavItemAttempt2.start(); // Play Audio - Photo Prompt Attempt 2 (No/Incorrect Arrival)
        counterNavToItemPhoto = counterNavToItemPhoto + 1; // Increase Counter for Photo Prompt
    }

    // Void plays Video Prompt
    public void navToItemVideoPrompt(final int itemNumber) {

        // Play Video Prompt Initial Attempt
        navToItemVideoPlay();
        counterNavToItemVideo = counterNavToItemVideo + 1; // Increase Counter for Video Prompt

        // Button Click - Arrival - Redirect to Scan Shelf
        Button buttonNavToItemVideoActionArrival = (Button) findViewById(id.button_navtoitemvideo_scanshelf); // Define - Arrive Button (Nav to Item TO Scan Shelf)
        buttonNavToItemVideoActionArrival.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) { // On Click - Arrival Button
              Intent intentNavToItemToScanShelf = new Intent(getApplicationContext(), ScanShelf.class); // Set intent - Redirect to Scan Shelf
              intentNavToItemToScanShelf.putExtra("item-number", itemNumber); // Transfer Data - Item Number TO Scan Shelf
              startActivity(intentNavToItemToScanShelf); // Implement Intent - Redirect to Scan Shelf
          }
        });

        // Button Click - No Arrival - Replay Video Prompt or Redirect to Call for Help
        Button buttonNavToItemVideoActionNoArrival= (Button) findViewById(id.button_navtoitemvideo_noarrival_callforhelp); // Define - Arrive Button (Nav to Item TO Scan Shelf)
        buttonNavToItemVideoActionNoArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On Click - No Arrival Button

                if (counterNavToItemVideo < 2) { // If given less than two video prompts, play video prompt
//                    VideoView vvNavToItemVideo = (VideoView) findViewById(id.navtoitemvideo_video); // Define - Video View in layout used for playing Video Prompt
//                    vvNavToItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + raw.sample_video_prompt); // Set Video Prompt
//                    vvNavToItemVideo.setMediaController(new MediaController(this)); // Set media controller
//                    vvNavToItemVideo.start(); // Play Video Prompt
                    navToItemVideoPlay();
                    counterNavToItemVideo = counterNavToItemVideo + 1; // Increase Counter for Video Prompt
                } else { // If given more than two Video Prompts, Redirect to Call For Help
                    Intent intentNavToItemVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                    startActivity(intentNavToItemVideoToCallForHelp); // Implement intent - Redirect to Call for Help
                }
            }

        });

        // Button Click - No Arrival - Replay Video Prompt or Redirect to Call for Help
        Button buttonNavToItemVideoActionCallForHelp= (Button) findViewById(id.button_navtoitemvideo_callforhelp); // Define - Arrive Button (Nav to Item TO Scan Shelf)
        buttonNavToItemVideoActionCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // On Click - No Arrival Button
                Intent intentNavToItemVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                startActivity(intentNavToItemVideoToCallForHelp); // Implement intent - Redirect to Call for Help
            }
        });
    }


    // Void plays Video Prompt
    public void navToItemVideoPlay() {
                    VideoView vvNavToItemVideo = (VideoView) findViewById(id.navtoitemvideo_video); // Define - Video View in layout used for playing Video Prompt
                    vvNavToItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + raw.sample_video_prompt); // Set Video Prompt
                    vvNavToItemVideo.setMediaController(new MediaController(this)); // Set media controller
                    vvNavToItemVideo.start(); // Play Video Prompt
    }

//    // Void Redirects to Call for Help - On Button (Call for Help) Click from Nav To Item Video layout
//    public void navToItemVideoToCallForHelp(View view) {
//        Intent intentNavToItemVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
//        startActivity(intentNavToItemVideoToCallForHelp); // Implement intent - Redirect to Call for Help
//    }
//
//    // Void Redirects to Scan Item - On Button (Arrival) Click from Nav to Item Video Layout
//    public void navToItemVideoToScanShelf(int itemNumber) {
//        Intent intentNavToItemVideoToScanShelf = new Intent(getApplicationContext(), ScanShelf.class); // Set intent - Redirect to Scan Shelf
//        startActivity(intentNavToItemVideoToScanShelf); // Implement intent - Redirect to Scan Shelf
//    }

}

        /*public void navToItemPhotoPlay(View view) {
        if (counterNavToItemPhoto < 2) {
            MediaPlayer mpNavItemAttempt2 = MediaPlayer.create(this, R.raw.navigate_to_item_attempt2_sample);
            mpNavItemAttempt2.start();
            counterNavToItemPhoto = counterNavToItemPhoto + 1;
        } else {
            counterNavToItemVideo = counterNavToItemVideo + 1;
            navToItemPhotoToNavToItemVideo();
        }

    }

    public void navToItemPhotoToNavToItemVideo() {
        if (counterNavToItemVideo < 2) {
            setContentView(layout.activity_nav_to_item_video);
            VideoView vvNavToItemVideo = (VideoView) findViewById(id.navtoitemvideo_video);
            vvNavToItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + raw.sample_video_prompt);
            ;
            vvNavToItemVideo.setMediaController(new MediaController(this));
            vvNavToItemVideo.start();

            counterNavToItemVideo = counterNavToItemVideo + 1;
        }
    }*/

// }
//}