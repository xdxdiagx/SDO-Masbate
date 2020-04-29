package com.example.sdomasbate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int introSound;

    TextView rate, logo, subtitle;
    Button btn_survey;
    //ImageView splash;
    Animation smallToBig, fromLeftToRight, fromLeftToRightDelay1, fromLeftToRightDelay2, fromLeftToRightDelay3, fleft, fhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        introSound = soundPool.load(this, R.raw.button, 1);

        smallToBig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        fromLeftToRight = AnimationUtils.loadAnimation(this, R.anim.fromlefttoright);
        fromLeftToRightDelay1 = AnimationUtils.loadAnimation(this, R.anim.fromlefttorightdelay1);
        fromLeftToRightDelay2 = AnimationUtils.loadAnimation(this, R.anim.fromlefttorightdelay2);
        fromLeftToRightDelay3 = AnimationUtils.loadAnimation(this, R.anim.fromlefttorightdelay3);
        fleft = AnimationUtils.loadAnimation(this, R.anim.fleft);
        fhelper = AnimationUtils.loadAnimation(this, R.anim.fhelper);

        Typeface logox = Typeface.createFromAsset(getAssets(), "fonts/Fredoka.ttf");
        Typeface mLight = Typeface.createFromAsset(getAssets(), "fonts/MontserratLight.ttf");
        Typeface mMedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        Typeface mRegular = Typeface.createFromAsset(getAssets(), "fonts/MontserratRegular.ttf");


        logo = (TextView) findViewById(R.id.logo);
        rate = (TextView) findViewById(R.id.rate);
        subtitle = (TextView) findViewById(R.id.subtitle);
        btn_survey = (Button) findViewById(R.id.btn_survey);
        //btn_exit = (Button) findViewById(R.id.btn_exit);

        //splash = (ImageView) findViewById(R.id.splash);

        logo.setTypeface(logox);
        rate.setTypeface(logox);
        subtitle.setTypeface(mLight);
        btn_survey.setTypeface(mMedium);
        //btn_exit.setTypeface(mMedium);

        //splash.startAnimation(smallToBig);
        logo.startAnimation(fromLeftToRight);
        rate.startAnimation(fromLeftToRight);
        subtitle.startAnimation(fromLeftToRightDelay1);
        btn_survey.startAnimation(fromLeftToRightDelay2);
        //btn_exit.startAnimation(fromLeftToRightDelay3);

        btn_survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                Intent i = new Intent(DashboardActivity.this, DivisionActivity.class);
                startActivity(i);
                //overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
        });

//        logo.setTranslationX(400);
//        subtitle.setTranslationX(400);
//        btn_survey.setTranslationX(400);
//        btn_exit.setTranslationX(400);
//
//        logo.animate().translationX(0).setDuration(800).setStartDelay(500).start();
//        subtitle.animate().translationX(0).setDuration(800).setStartDelay(700).start();
//        btn_survey.animate().translationX(0).setDuration(800).setStartDelay(900).start();
//        btn_exit.animate().translationX(0).setDuration(800).setStartDelay(900).start();

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        soundPool.play(introSound, 1, 1, 0, 0, 1);
                        finish();
                        //DashboardActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        soundPool.play(introSound, 1, 1, 0, 0, 1);
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        if(backPressedTime + 2000 > System.currentTimeMillis()){
//            backToast.cancel();
//            Intent intent = new Intent(SurveyActivity.this, DashboardActivity.class);
//            startActivity(intent);
//            //super.onBackPressed();
//            return;
//        } else {
//           backToast = Toast.makeText(getBaseContext(), "Press back again to quit the survey!", Toast.LENGTH_LONG);
//           backToast.show();
//        }
//
//        backPressedTime = System.currentTimeMillis();
    }

}
