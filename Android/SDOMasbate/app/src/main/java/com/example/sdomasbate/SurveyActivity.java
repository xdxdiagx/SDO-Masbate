package com.example.sdomasbate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class SurveyActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_PURPOSE = "purpose";

    private SoundPool soundPool;
    private int introSound;

    String nameInput, positionInput, purposeInput;
    String choose;
    private long backPressedTime;

    private EditText name, position, purpose;
    private Button next;
    private  Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        setTitle("Personal Details");

        Intent intent = getIntent();
        choose = intent.getStringExtra(DivisionActivity.EXTRA_CHOOSE);

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

        Typeface fredoka = Typeface.createFromAsset(getAssets(), "fonts/Fredoka.ttf");
        Typeface mLight = Typeface.createFromAsset(getAssets(), "fonts/MontserratLight.ttf");
        Typeface mMedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        Typeface mRegular = Typeface.createFromAsset(getAssets(), "fonts/MontserratRegular.ttf");

        name = findViewById(R.id.edit_text_name);
        position = findViewById(R.id.edit_text_position);
        purpose = findViewById(R.id.edit_text_purpose);

        name.setTypeface(mLight);
        position.setTypeface(mLight);
        purpose.setTypeface(mLight);

        next = findViewById(R.id.btn_next_1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                if(position.getText().toString().equals("") || purpose.getText().toString().equals("")){
                    fillUp();
                   // Toast.makeText(SurveyActivity.this, "Kindly fill up the position and purpose section", Toast.LENGTH_LONG).show();
                } else {
                    soundPool.play(introSound, 1, 1, 0, 0, 1);
                    nameInput = name.getText().toString();
                    positionInput = position.getText().toString();
                    purposeInput = purpose.getText().toString();

                    Intent intent = new Intent(SurveyActivity.this, RespectActivity.class);
                    intent.putExtra(DivisionActivity.EXTRA_CHOOSE, choose);
                    intent.putExtra(EXTRA_NAME, nameInput);
                    intent.putExtra(EXTRA_POSITION, positionInput);
                    intent.putExtra(EXTRA_PURPOSE, purposeInput);

                    startActivity(intent);
                }
            }
        });
    }

    public void fillUp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Data");
        builder.setMessage("Kindly fill up atleast the position and purpose section.")
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        soundPool.play(introSound, 1, 1, 0, 0, 1);
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

        soundPool.play(introSound, 1, 1, 0, 0, 1);

        Intent intent = new Intent(SurveyActivity.this, DivisionActivity.class);

        startActivity(intent);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Are you sure you want to end the survey?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        soundPool.play(introSound, 1, 1, 0, 0, 1);
//                        Intent intent = new Intent(SurveyActivity.this, DashboardActivity.class);
//                        startActivity(intent);
//                    }
//                })
//
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        soundPool.play(introSound, 1, 1, 0, 0, 1);
//                        dialog.cancel();
//                    }
//                });
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

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
