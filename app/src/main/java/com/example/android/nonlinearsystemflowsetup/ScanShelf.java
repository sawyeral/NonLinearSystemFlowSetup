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

public class ScanShelf extends AppCompatActivity {

    int counterAttemptsPhoto = 1; // Counter for Photo Prompts
    int counterAttemptsVideo = 1; // Counter for Video Prompts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_shelf); // Set layout to Scan Shelf Photo

        // Pull item number
        final int itemNumber = getIntent().getIntExtra("item-number",1); // Pull Item Number from Previous Activity

        // Display item Number
        TextView itemNumberView = (TextView) findViewById(R.id.scanshelfphoto_displayitemnumber); // Define - Text view for Item Number on Photo Prompt
        itemNumberView.setText(String.valueOf(itemNumber)); // Set text view to current Item Number on Photo Prompt

        // Play Attempt 1 'Scan Shelf' Audio Prompt
        MediaPlayer mpScanShelfAttempt1 = MediaPlayer.create(this, R.raw.scanshelf_initialprompt); // Define - Audio clip for Scan Shelf Photo initial attempt
        mpScanShelfAttempt1.start(); // Play audio for Scan Shelf Photo initial attempt

//        // If the item number is less than number of items on list (3), then continue with process, otherwise Redirect to Nav to Ship
//        if (itemNumber < 3) {
//            // Switch from 'Scan Item - Photo Prompt' to 'Navigate to Shipping'
//            itemNumber = itemNumber+1; // Increment item number (
//            final int itemNumberCurrent = itemNumber;
//            Button buttonScanShelfToScanItem = (Button) findViewById(R.id.button_scanshelfphoto_correctitem_scanitem);
//            buttonScanShelfToScanItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentScanShelfToScanItem = new Intent(getApplicationContext(), ScanItem.class);
//                    intentScanShelfToScanItem.putExtra("item-number", itemNumberCurrent);
//                    startActivity(intentScanShelfToScanItem);
//                }
//            });
//        }else{
//            // Switch from 'Scan Shelf - Photo Prompt' to 'Scan Item - Photo'
//            Button buttonScanShelfToScanItem = (Button) findViewById(R.id.button_scanshelfphoto_correctitem_scanitem);
//            buttonScanShelfToScanItem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentScanShelfToScanItem = new Intent(getApplicationContext(), ScanItem.class);
//                    startActivity(intentScanShelfToScanItem);
//                }
//            });
//        }

        // Redirect to Scan Item if Scan Shelf Action Correct
        final int itemNumberCurrent = itemNumber; // Create dummy Item Number variable to send to Scan Item
            Button buttonScanShelfToScanItem = (Button) findViewById(R.id.button_scanshelfphoto_correctitem_scanitem); // Define - Correct Button
            buttonScanShelfToScanItem.setOnClickListener(new View.OnClickListener() { // Check for click of Correct Button
                @Override
                public void onClick(View view) {
                    Intent intentScanShelfToScanItem = new Intent(getApplicationContext(), ScanItem.class); // Set Intent - Redirect to Scan Item
                    intentScanShelfToScanItem.putExtra("item-number", itemNumberCurrent); // Transfer data - Item Number TO Scan Item
                    startActivity(intentScanShelfToScanItem); // Implement intent - Redirect to Scan Item
                }
            });

        // Switch from 'Scan Shelf - Photo Prompt' to 'Call for Help'
        Button buttonScanShelfToCallForHelp = (Button) findViewById(R.id.button_scanshelfphoto_callforhelp); // Define - Call for Help Button
        buttonScanShelfToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // Check for Call for Help Button Click
                Intent intentScanShelfToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                startActivity(intentScanShelfToCallForHelp); // Implement intent - Redirect to Call for Help
            }
        });

        // Check for No Action
        Button buttonScanShelfActionNoAction = (Button) findViewById(R.id.button_scanshelfphoto_noaction_scanitemvideo); // Define - No Action Button
        buttonScanShelfActionNoAction.setOnClickListener(new View.OnClickListener() { // Check for Click of No Action Button
            @Override
            public void onClick(View view) { // Check for No Action Button Click

                if(counterAttemptsPhoto < 2) {
                    scanShelfActionNoAction(view);
                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
                }else{
                    scanShelfActionNoActionTransition(itemNumber);

//                    scanShelfVideo(itemNumber);
                }
            }
        });

        // Check for Wrong Shelf
        Button buttonScanShelfActionWrongShelf = (Button) findViewById(R.id.button_scanshelfphoto_wrongitem_scanshelfvideo); // Define - Wrong Shelf Button
        buttonScanShelfActionWrongShelf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(counterAttemptsPhoto < 2){
                    scanShelfActionWrongShelf(view);
                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
                }else{
                    scanShelfActionWrongShelfTransition(itemNumber);
                }
            }
        });
    }


    public void scanShelfActionNoAction(View view){
        MediaPlayer mpScanShelfNoAction = MediaPlayer.create(this, R.raw.scanshelf_noaction);
        mpScanShelfNoAction.start();
    }

    public void scanShelfActionNoActionTransition(int itemNumber){
       final int itemNumberNoActionTransition = itemNumber;
        final MediaPlayer mpScanShelfNoActionTransition = MediaPlayer.create(this, R.raw.scanshelf_noaction_transition);
        //mpScanShelfNoActionTransition.setOnCompletionListener((MediaPlayer.OnCompletionListener) this);
        mpScanShelfNoActionTransition.start();
        mpScanShelfNoActionTransition.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                //finish(); // finish current activity
                scanShelfVideo(itemNumberNoActionTransition);
               // mpScanShelfNoActionTransition.release();
            }

        });
        //mpScanShelfNoActionTransition.setOnCompletionListener(MediaPlayer.OnCompletionListener);
        //mpScanShelfNoActionTransition.release();
        //return;

    }

    public void scanShelfActionWrongShelf(View view){
        MediaPlayer mpScanShelfWrongShelf = MediaPlayer.create(this, R.raw.scanshelf_wrongitem);
        mpScanShelfWrongShelf.start();
    }

    public void scanShelfActionWrongShelfTransition(int itemNumber){
        final int itemNumberWrongShelfTransition = itemNumber;
        final MediaPlayer mpScanShelfWrongShelfTransition = MediaPlayer.create(this, R.raw.scanshelf_wrongitem_transition);
        mpScanShelfWrongShelfTransition.start();
        mpScanShelfWrongShelfTransition.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                scanShelfVideo(itemNumberWrongShelfTransition);
            }
        });
    }

    public void scanShelfVideo(int itemNumber){

        // Setup Video Prompt and Play Video Prompt Attempt 1
            setContentView(R.layout.activity_scan_shelf_video); // Change layout to Scan Shelf Video
            VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
            vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
            vvScanShelfVideo.setMediaController(new MediaController(this));
            vvScanShelfVideo.start();

        if(counterAttemptsVideo < 2){

            // Redirect to Scan Item if Scan Shelf Action Correct
            final int itemNumberCurrent = itemNumber; // Create dummy Item Number variable to send to Scan Item
            Button buttonScanShelfVideoToScanItem = (Button) findViewById(R.id.button_scanshelfvideo_correctitem_scanitem); // Define - Correct Button
            buttonScanShelfVideoToScanItem.setOnClickListener(new View.OnClickListener() { // Check for click of Correct Button
                @Override
                public void onClick(View view) {
                    Intent intentScanShelfVideoToScanItem = new Intent(getApplicationContext(), ScanItem.class); // Set Intent - Redirect to Scan Item
                    intentScanShelfVideoToScanItem.putExtra("item-number", itemNumberCurrent); // Transfer data - Item Number TO Scan Item
                    startActivity(intentScanShelfVideoToScanItem); // Implement intent - Redirect to Scan Item
                }
            });

            // Switch from 'Scan Shelf - Video Prompt' to 'Call for Help'
            Button buttonScanShelfVideoToCallForHelp = (Button) findViewById(R.id.button_scanshelfvideo_callforhelp); // Define - Call for Help Button
            buttonScanShelfVideoToCallForHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Check for Call for Help Button Click
                    Intent intentScanShelfVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                    startActivity(intentScanShelfVideoToCallForHelp); // Implement intent - Redirect to Call for Help
                }
            });

            // Check for No Action
            Button buttonScanShelfVideoActionNoAction = (Button) findViewById(R.id.button_scanshelfvideo_noaction_callforhelp); // Define - No Action Button
            buttonScanShelfVideoActionNoAction.setOnClickListener(new View.OnClickListener() { // Check for Click of No Action Button
                @Override
                public void onClick(View view) { // Check for No Action Button Click

                    if(counterAttemptsVideo < 2) {
                        scanShelfVideoActionNoAction(view);
                        counterAttemptsVideo = counterAttemptsVideo + 1;
                    }else{
                        Intent intentScanShelfVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                        startActivity(intentScanShelfVideoToCallForHelp); // Implement intent - Redirect to Call for Help

                    }
                }
            });

            // Check for Wrong Shelf
            Button buttonScanShelfVideoActionWrongShelf = (Button) findViewById(R.id.button_scanshelfvideo_wrongitem_callforhelp); // Define - Wrong Shelf Button
            buttonScanShelfVideoActionWrongShelf.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    if(counterAttemptsVideo < 2){
                        scanShelfVideoActionWrongShelf(view);
                        counterAttemptsVideo = counterAttemptsVideo + 1;
                    }else{
                        Intent intentScanShelfVideoToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class); // Set intent - Redirect to Call for Help
                        startActivity(intentScanShelfVideoToCallForHelp); // Implement intent - Redirect to Call for Help
                    }
                }
            });
        }

    }

    // Play Scan Shelf - No Action Video
    public void scanShelfVideoActionNoAction(View view){
        VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
        vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanShelfVideo.setMediaController(new MediaController(this));
        vvScanShelfVideo.start();
    }

    // Play Scan Shelf - Wrong Shelf Video
    public void scanShelfVideoActionWrongShelf(View view){
        VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
        vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanShelfVideo.setMediaController(new MediaController(this));
        vvScanShelfVideo.start();
    }




//    public void shelfScanNoAction(View view){
//        if (counterAttemptsPhoto < 2){
//            MediaPlayer mpScanShelfNoAction = MediaPlayer.create(this, R.raw.scanshelf_noaction);
//            mpScanShelfNoAction.start();
//            counterAttemptsPhoto = counterAttemptsPhoto + 1;
//        } else{
//            setContentView(R.layout.activity_scan_shelf_video);
//            VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
//            vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanShelfVideo.setMediaController(new MediaController(this));
//            vvScanShelfVideo.start();
//        }
//    }
//
//    public void shelfScanWrongItem(View view){
//        if (counterAttemptsPhoto < 2){
//            MediaPlayer mpScanShelfWrongItem = MediaPlayer.create(this, R.raw.scanshelf_wrongitem);
//            mpScanShelfWrongItem.start();
//            counterAttemptsPhoto = counterAttemptsPhoto + 1;
//        } else{
//            setContentView(R.layout.activity_scan_shelf_video);
//            VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
//            vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanShelfVideo.setMediaController(new MediaController(this));
//            vvScanShelfVideo.start();
//        }
//    }
//
//    public void shelfScanVideoNoAction(View view){
//        if (counterAttemptsVideo < 2){
//            VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
//            vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanShelfVideo.setMediaController(new MediaController(this));
//            vvScanShelfVideo.start();
//            counterAttemptsVideo = counterAttemptsVideo + 1;
//        } else{
//            Intent intentScanShelfToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
//            startActivity(intentScanShelfToCallForHelp);
//        }
//    }
//
//    public void shelfScanVideoWrongItem(View view){
//        if (counterAttemptsVideo < 2){
//            VideoView vvScanShelfVideo = (VideoView) findViewById(R.id.scanshelf_video);
//            vvScanShelfVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanShelfVideo.setMediaController(new MediaController(this));
//            vvScanShelfVideo.start();
//            counterAttemptsVideo = counterAttemptsVideo + 1;
//        } else{
//            Intent intentScanShelfToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
//            startActivity(intentScanShelfToCallForHelp);
//        }
//    }
//
//    public void shelfScanVideoCorrectItem(View view){
//        Intent intentScanShelfToScanItem = new Intent(getApplicationContext(), ScanItem.class);
//        startActivity(intentScanShelfToScanItem);
//    }
//
//    public void shelfScanVideoToCallForHelp(View view){
//        Intent intentScanShelfToCallForHelp = new Intent(getApplicationContext(), CallForHelp.class);
//        startActivity(intentScanShelfToCallForHelp);
//    }

}

