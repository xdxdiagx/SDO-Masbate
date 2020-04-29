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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class MotivatedVoiceActivity extends AppCompatActivity implements SmileRating.OnSmileySelectionListener, SmileRating.OnRatingSelectedListener {

    public static final String EXTRA_RATING_MOTIVATED_VOICE = "motivated_voice";
    public static final String EXTRA_REMARKS_MOTIVATED_VOICE = "motivated_voice_remarks";

    private SoundPool soundPool;
    private int introSound;

    private TextView mainQuestion, question, remarks;
    private EditText suggestion;
    //public TextView name, position, purpose;

    String suggestionInput;
    String choose, text_name, text_position, text_purpose;
    String text_rating_respect, text_remarks_respect, text_rating_PD, text_remarks_PD;
    String rating = "None";

    private static final String TAG = "SurveyActivity";

    private SmileRating mSmileRating;

    Button next;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivated_voice);

        setTitle("Client Satisfaction Survey");

        Intent intent = getIntent();
        choose = intent.getStringExtra(DivisionActivity.EXTRA_CHOOSE);
        text_name = intent.getStringExtra(SurveyActivity.EXTRA_NAME);
        text_position= intent.getStringExtra(SurveyActivity.EXTRA_POSITION);
        text_purpose = intent.getStringExtra(SurveyActivity.EXTRA_PURPOSE);
        text_rating_respect = intent.getStringExtra(RespectActivity.EXTRA_RATING_RESPECT);
        text_remarks_respect = intent.getStringExtra(RespectActivity.EXTRA_REMARKS_RESPECT);
        text_rating_PD = intent.getStringExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED);
        text_remarks_PD = intent.getStringExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED);

//        name = findViewById(R.id.name);
//        position = findViewById(R.id.position);
//        purpose = findViewById(R.id.purpose);
//
//        name.setText(text_name);
//        position.setText(text_position);
//        purpose.setText(text_purpose);

        mSmileRating = (SmileRating) findViewById(R.id.ratingView);
        mSmileRating.setOnSmileySelectionListener(this);
        mSmileRating.setOnRatingSelectedListener(this);
        /*Typeface typeface = Typeface.createFromAsset(getAssets(), "MetalMacabre.ttf");
        mSmileRating.setTypeface(typeface);*/

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

        mainQuestion = findViewById(R.id.question);
        question = findViewById(R.id.text_view);
        remarks = findViewById(R.id.text_view_remarks);
        suggestion = findViewById(R.id.edit_text_suggestion);

        mainQuestion.setTypeface(fredoka);
        question.setTypeface(fredoka);
        remarks.setTypeface(mLight);
        suggestion.setTypeface(mLight);

        next = findViewById(R.id.btn_next_1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                if(rating == "None"){
                    rate();
                } else {
                    suggestionInput = suggestion.getText().toString();
                    Intent intent = new Intent(MotivatedVoiceActivity.this, RespondingQueriesActivity.class );
                    intent.putExtra(DivisionActivity.EXTRA_CHOOSE, choose);
                    intent.putExtra(SurveyActivity.EXTRA_NAME, text_name);
                    intent.putExtra(SurveyActivity.EXTRA_POSITION, text_position);
                    intent.putExtra(SurveyActivity.EXTRA_PURPOSE, text_purpose);
                    intent.putExtra(RespectActivity.EXTRA_RATING_RESPECT, text_rating_respect);
                    intent.putExtra(RespectActivity.EXTRA_REMARKS_RESPECT, text_remarks_respect);
                    intent.putExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED, text_rating_PD);
                    intent.putExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED, text_remarks_PD);
                    intent.putExtra(EXTRA_RATING_MOTIVATED_VOICE, rating);
                    intent.putExtra(EXTRA_REMARKS_MOTIVATED_VOICE, suggestionInput);
                    startActivity(intent);
                }

            }
        });

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
            }
        });
    }

    public void rate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Rating");
        builder.setMessage("Kindly rate this section before you proceed.")
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
    public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
        switch (smiley) {
            case SmileRating.BAD:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "Bad";
                Log.i(TAG, "Bad");
                break;
            case SmileRating.GOOD:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "Good";
                Log.i(TAG, "Good");
                break;
            case SmileRating.GREAT:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "Great";
                Log.i(TAG, "Great");
                break;
            case SmileRating.OKAY:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "Okay";
                Log.i(TAG, "Okay");
                break;
            case SmileRating.TERRIBLE:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "Terrible";
                Log.i(TAG, "Terrible");
                break;
            case SmileRating.NONE:
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                rating = "None";
                Log.i(TAG, "None");
                break;
        }
    }

    @Override
    public void onRatingSelected(int level, boolean reselected) {
        Log.i(TAG, "Rated as: " + level + " - " + reselected);
    }

    @Override
    public void onBackPressed() {

        soundPool.play(introSound, 1, 1, 0, 0, 1);
        Intent intent = new Intent(MotivatedVoiceActivity.this, ProperlyDressedActivity.class);
        intent.putExtra(DivisionActivity.EXTRA_CHOOSE, choose);
        intent.putExtra(SurveyActivity.EXTRA_NAME, text_name);
        intent.putExtra(SurveyActivity.EXTRA_POSITION, text_position);
        intent.putExtra(SurveyActivity.EXTRA_PURPOSE, text_purpose);
        intent.putExtra(RespectActivity.EXTRA_RATING_RESPECT, text_rating_respect);
        intent.putExtra(RespectActivity.EXTRA_REMARKS_RESPECT, text_remarks_respect);
        startActivity(intent);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Are you sure you want to end the survey?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        soundPool.play(introSound, 1, 1, 0, 0, 1);
//                        Intent intent = new Intent(MotivatedVoiceActivity.this, DashboardActivity.class);
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
