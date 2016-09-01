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

public class ScanItem extends AppCompatActivity {

    int counterAttemptsPhoto = 1;
    int counterAttemptsVideo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_item);

        // Pull item number
        final int itemNumber = getIntent().getIntExtra("item-number",1);

        // Display item Number
        TextView itemNumberView = (TextView) findViewById(R.id.scanitemphoto_displayitemnumber);
        itemNumberView.setText(String.valueOf(itemNumber));

        // Play Attempt 1 'Scan Item' Audio Prompt
        final MediaPlayer mpScanItemAttempt1 = MediaPlayer.create(this, R.raw.scan_item_attempt1_sample);
        mpScanItemAttempt1.start();

        // Redirect from 'Scan Item - Photo Prompt' to 'Nav to Item' or 'Nav to Ship'
        final int itemNumberCurrent = itemNumber;
        Button buttonScanItemCorrectAction = (Button) findViewById(R.id.button_scanitemphoto_correctnumber_navtoship);
        buttonScanItemCorrectAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemNumberCurrent<3){
                    int itemNumberNew = itemNumberCurrent + 1;
                    Intent intentScanItemToNavToItem = new Intent(getApplicationContext(), NavToItem.class);
                    intentScanItemToNavToItem.putExtra("item-number", itemNumberNew);
                    startActivity(intentScanItemToNavToItem);

                }else{
                    Intent intentScanItemToNavToShip = new Intent(getApplicationContext(), NavToShip.class);
                    startActivity(intentScanItemToNavToShip);

                }

            }
        });

//        if (itemNumber < 3) {
//            // Switch from 'Scan Item - Photo Prompt' to 'Navigate to Shipping'
//            itemNumber = itemNumber+1;
//            final int itemNumberCurrent = itemNumber;
//            Button buttonScanItemToNavToShip = (Button) findViewById(R.id.button_scanitemphoto_correctnumber_navtoship);
//            buttonScanItemToNavToShip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentScanItemToNavToShip = new Intent(getApplicationContext(), NavToItem.class);
//                    intentScanItemToNavToShip.putExtra("item-number", itemNumberCurrent);
//                    startActivity(intentScanItemToNavToShip);
//                }
//            });
//        }else{
//            // Switch from 'Scan Item - Photo Prompt' to 'Navigate to Item - Photo'
//            Button buttonScanItemToNavToShip = (Button) findViewById(R.id.button_scanitemphoto_correctnumber_navtoship);
//            buttonScanItemToNavToShip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intentScanItemToNavToItem = new Intent(getApplicationContext(), NavToShip.class);
//                    startActivity(intentScanItemToNavToItem);
//                }
//            });
//        };


        // Switch from 'Scan Item - Photo Prompt' to 'Call for Help'
        Button buttonScanItemToCallForHelp = (Button) findViewById(R.id.button_scanitemphoto_callforhelp);
        buttonScanItemToCallForHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentScanItemToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                startActivity(intentScanItemToCallForHelp);
            }
        });

//        // Check for No Action
//        Button buttonScanItemActionNoAction = (Button) findViewById(R.id.button_scanshelfphoto_noaction_scanitemvideo);
//        buttonScanItemActionNoAction.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(counterAttemptsPhoto<2){
//                    scanItemActionNoAction(view);
//                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
//                }else{
//                    scanItemActionNoActionTransition(itemNumber);
//                    scanItemVideo(itemNumber);
//                }
//            }
//        });


        // Check for No Action
        Button buttonScanItemActionNoAction = (Button) findViewById(R.id.button_scanitemphoto_noaction_scanitemvideo);
        buttonScanItemActionNoAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsPhoto < 2){
                    scanItemActionNoAction(view);
                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
                }else{
                    scanItemActionNoActionTransition(itemNumber);
                }
            }
        });

        // Check for Wrong Item
        Button buttonScanItemActionWrongItem = (Button) findViewById(R.id.button_scanitemphoto_wrongitem_scanitemvideo);
        buttonScanItemActionWrongItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsPhoto < 2){
                    scanItemActionWrongItem(view);
                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
                }else{
                    scanItemActionWrongItemTransition(itemNumber);
                }
            }
        });

        // Check for Too Many Items Scanned
        Button buttonScanItemActionTooMany = (Button) findViewById(R.id.button_scanitemphoto_toomany_scanitemvideo);
        buttonScanItemActionTooMany.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsPhoto < 2){
                    scanItemActionTooMany(view);
                    counterAttemptsPhoto = counterAttemptsPhoto + 1;
                }else{
                    scanItemActionTooManyTransition(itemNumber);
                }
            }
        });

    }

    public void scanItemActionNoAction(View view){
        MediaPlayer mpScanItemActionNoAction = MediaPlayer.create(this, R.raw.scan_item_noresponse_sample);
        mpScanItemActionNoAction.start();
    }

    public void scanItemActionNoActionTransition(int itemNumber){
        final int itemNumberCurrent = itemNumber;
        final MediaPlayer mpScanItemActionNoActionTransition = MediaPlayer.create(this, R.raw.scanitem_noaction_transition);
        mpScanItemActionNoActionTransition.start();
        mpScanItemActionNoActionTransition.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
              scanItemVideo(itemNumberCurrent);
            }
        });
    }

    public void scanItemActionWrongItem(View view){
        MediaPlayer mpScanItemActionWrongItem = MediaPlayer.create(this, R.raw.scan_item_wrongitem_sample);
        mpScanItemActionWrongItem.start();
    }

    public void scanItemActionWrongItemTransition(int itemNumber){
        final int itemNumberCurrent = itemNumber;
        final MediaPlayer mpScanItemActionWrongItemTransition = MediaPlayer.create(this, R.raw.scanitem_wrongitem_transition);
        mpScanItemActionWrongItemTransition.start();
        mpScanItemActionWrongItemTransition.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                scanItemVideo(itemNumberCurrent);
            }
        });
    }

    public void scanItemActionTooMany(View view){
        MediaPlayer mpScanItemActionTooMany = MediaPlayer.create(this, R.raw.scan_item_toomany_sample);
        mpScanItemActionTooMany.start();
    }

    public void scanItemActionTooManyTransition(int itemNumber){
        final int itemNumberCurrent = itemNumber;
        final MediaPlayer mpScanItemActionTooManyTransition = MediaPlayer.create(this, R.raw.scanitem_toomany_transition);
        mpScanItemActionTooManyTransition.start();
        mpScanItemActionTooManyTransition.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                scanItemVideo(itemNumberCurrent);
            }
        });
    }

    public void scanItemVideo(final int itemNumber){
        setContentView(R.layout.activity_scan_item_video); // Change layout to Scan Item - Video
        VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
        vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanItemVideo.setMediaController(new MediaController(this));
        vvScanItemVideo.start();

        final int itemNumberCurrent  = itemNumber;

        Button buttonScanItemVideoActionCorrectAction = (Button) findViewById(R.id.button_scanitemvideo_correctnumber_navtoship);
        buttonScanItemVideoActionCorrectAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(itemNumberCurrent < 3 ){
                    int itemNumberNew = itemNumberCurrent + 1;
                    Intent intentScanItemVideoToNavToItem = new Intent(getApplicationContext(),NavToItem.class);
                    intentScanItemVideoToNavToItem.putExtra("item-number", itemNumberNew);
                    startActivity(intentScanItemVideoToNavToItem);
                }  else{
                    Intent intentScanItemVideoToNavToShip = new Intent(getApplicationContext(),NavToShip.class);
                    intentScanItemVideoToNavToShip.putExtra("item-number", itemNumberCurrent);
                    startActivity(intentScanItemVideoToNavToShip);
                }
            }

        });

        Button buttonScanItemVideoActionCallForHelp = (Button) findViewById(R.id.button_scanitemvideo_callforhelp);
        buttonScanItemVideoActionCallForHelp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentScanItemVideoToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                intentScanItemVideoToCallForHelp.putExtra("item-number", itemNumberCurrent);
                startActivity(intentScanItemVideoToCallForHelp);
            }
        });


        Button buttonScanItemVideoActionNoAction = (Button) findViewById(R.id.button_scanitemvideo_noaction_callforhelp);
        buttonScanItemVideoActionNoAction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsVideo < 2){
                    scanItemVideoActionNoAction(view);
                    counterAttemptsVideo = counterAttemptsVideo + 1;
                }else{
                    Intent intentScanItemVideoToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                    intentScanItemVideoToCallForHelp.putExtra("item-number", itemNumberCurrent);
                    startActivity(intentScanItemVideoToCallForHelp);
                }
            }
        });

        Button buttonScanItemVideoActionWrongItem = (Button) findViewById(R.id.button_scanitemvideo_wrongitem_callforhelp);
        buttonScanItemVideoActionWrongItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsVideo < 2){
                    scanItemVideoActionWrongItem(view);
                    counterAttemptsVideo = counterAttemptsVideo + 1;
                }else{
                    Intent intentScanItemVideoToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                    intentScanItemVideoToCallForHelp.putExtra("item-number",itemNumberCurrent);
                    startActivity(intentScanItemVideoToCallForHelp);
                }
            }
        });

        Button buttonScanItemVideoActionTooMany = (Button) findViewById(R.id.button_scanitemvideo_toomany_callforhelp);
        buttonScanItemVideoActionTooMany.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(counterAttemptsVideo < 2 ){
                    scanItemVideoActionTooMany(view);
                    counterAttemptsVideo = counterAttemptsVideo + 1;
                }else{
                    Intent intentScanItemVideoToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
                    intentScanItemVideoToCallForHelp.putExtra("item-number",itemNumberCurrent);
                    startActivity(intentScanItemVideoToCallForHelp);
                }
            }
        });
    }

    //  Play Scan Item Video - No Action
    public void scanItemVideoActionNoAction(View view){
        VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
        vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanItemVideo.setMediaController(new MediaController(this));
        vvScanItemVideo.start();
    }

    // Play Scan Item Video - Wrong Item
    public void scanItemVideoActionWrongItem(View view){
        VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
        vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanItemVideo.setMediaController(new MediaController(this));
        vvScanItemVideo.start();
    }

    // Play Scan Item Video - Too Many
    public void scanItemVideoActionTooMany(View view){
        VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
        vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
        vvScanItemVideo.setMediaController(new MediaController(this));
        vvScanItemVideo.start();
    }


//    // Redirect from 'Scan Item - Photo Prompt' to 'Scan Item - Video Prompt'
//    public void itemScanNoAction(View view){
//        if (counterAttemptsPhoto < 2){
//            MediaPlayer mpScanItemNoAction = MediaPlayer.create(this, R.raw.scan_item_noresponse_sample);
//            mpScanItemNoAction.start();
//            counterAttemptsPhoto = counterAttemptsPhoto + 1;
//        } else{
//            setContentView(R.layout.activity_scan_item_video);
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//        }
//    }



//    public void itemScanTooMany(View view){
//        if (counterAttemptsPhoto < 2){
//            MediaPlayer mpScanItemTooMany = MediaPlayer.create(this, R.raw.scan_item_toomany_sample);
//            mpScanItemTooMany.start();
//            counterAttemptsPhoto = counterAttemptsPhoto + 1;
//        } else{
//            setContentView(R.layout.activity_scan_item_video);
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//        }
//    }
//
//    public void itemScanWrongItem(View view){
//        if (counterAttemptsPhoto < 2){
//            MediaPlayer mpScanItemWrongItem = MediaPlayer.create(this, R.raw.scan_item_wrongitem_sample);
//            mpScanItemWrongItem.start();
//            counterAttemptsPhoto = counterAttemptsPhoto + 1;
//        } else{
//            setContentView(R.layout.activity_scan_item_video);
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//        }
//    }
//
//    public void itemScanVideoNoAction(View view){
//        if (counterAttemptsVideo < 2){
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//            counterAttemptsVideo = counterAttemptsVideo + 1;
//        } else{
//            Intent intentScanItemToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
//            startActivity(intentScanItemToCallForHelp);
//        }
//    }
//
//    public void itemScanVideoTooMany(View view){
//        if (counterAttemptsVideo < 2){
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//            counterAttemptsVideo = counterAttemptsVideo + 1;
//        } else{
//            Intent intentScanItemToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
//            startActivity(intentScanItemToCallForHelp);
//        }
//    }
//
//    public void itemScanVideoWrongItem(View view){
//        if (counterAttemptsVideo < 2){
//            VideoView vvScanItemVideo = (VideoView) findViewById(R.id.scanitem_video);
//            vvScanItemVideo.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_video_prompt);
//            vvScanItemVideo.setMediaController(new MediaController(this));
//            vvScanItemVideo.start();
//            counterAttemptsVideo = counterAttemptsVideo + 1;
//        } else{
//            Intent intentScanItemToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
//            startActivity(intentScanItemToCallForHelp);
//        }
//    }
//
//    public void itemScanVideoCorrectNumber(View view){
//        Intent intentScanItemToNavToShip = new Intent(getApplicationContext(),NavToShip.class);
//        startActivity(intentScanItemToNavToShip);
//    }
//
//    public void itemScanVideoToCallForHelp(View view){
//        Intent intentScanItemToCallForHelp = new Intent(getApplicationContext(),CallForHelp.class);
//        startActivity(intentScanItemToCallForHelp);
//    }

}

