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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankYouActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int introSound;

    private TextView textView1, textView2;

    String text_rating_respect, text_remarks_respect, text_rating_PD, text_remarks_PD, text_rating_MV, text_remarks_MV, text_rating_RQ, text_remarks_RQ,
            text_rating_smiling, text_remarks_smiling, text_rating_PS, text_remarks_PS, text_rating_wasting_no_time, text_remarks_wasting_no_time,
            text_rating_comfort_rooms, text_remarks_comfort_rooms, text_rating_guards, text_remarks_guards, text_rating_waiting_area, text_remarks_waiting_area,
            text_rating_offices_clean, text_remarks_offices_clean, text_back, text_like, text_improve, text_comment;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

//        setTitle("Client Satisfaction Survey");
        Intent intent = getIntent();
        text_rating_respect = intent.getStringExtra(RespectActivity.EXTRA_RATING_RESPECT);
        text_remarks_respect = intent.getStringExtra(RespectActivity.EXTRA_REMARKS_RESPECT);
        text_rating_PD = intent.getStringExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED);
        text_remarks_PD = intent.getStringExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED);
        text_rating_MV = intent.getStringExtra(MotivatedVoiceActivity.EXTRA_RATING_MOTIVATED_VOICE);
        text_remarks_MV = intent.getStringExtra(MotivatedVoiceActivity.EXTRA_REMARKS_MOTIVATED_VOICE);
        text_rating_RQ = intent.getStringExtra(RespondingQueriesActivity.EXTRA_RATING_RESPONDING_TO_QUERIES);
        text_remarks_RQ = intent.getStringExtra(RespondingQueriesActivity.EXTRA_REMARKS_RESPONDING_TO_QUERIES);
        text_rating_smiling = intent.getStringExtra(SmilingActivity.EXTRA_RATING_SMILING);
        text_remarks_smiling = intent.getStringExtra(SmilingActivity.EXTRA_REMARKS_SMILING);
        text_rating_PS = intent.getStringExtra(ProvidingSolutionsActivity.EXTRA_RATING_PROVIDING_SOLUTIONS);
        text_remarks_PS = intent.getStringExtra(ProvidingSolutionsActivity.EXTRA_REMARKS_PROVIDING_SOLUTIONS);
        text_rating_wasting_no_time = intent.getStringExtra(WastingNoTimeActivity.EXTRA_RATING_WASTING_NO_TIME);
        text_remarks_wasting_no_time = intent.getStringExtra(WastingNoTimeActivity.EXTRA_REMARKS_WASTING_NO_TIME);
        text_rating_comfort_rooms = intent.getStringExtra(ComfortRoomsActivity.EXTRA_RATING_COMFORT_ROOMS);
        text_remarks_comfort_rooms = intent.getStringExtra(ComfortRoomsActivity.EXTRA_REMARKS_COMFORT_ROOMS);
        text_rating_guards = intent.getStringExtra(GuardsActivity.EXTRA_RATING_GUARDS);
        text_remarks_guards = intent.getStringExtra(GuardsActivity.EXTRA_REMARKS_GUARDS);
        text_rating_waiting_area = intent.getStringExtra(WaitingAreaActivity.EXTRA_RATING_WAITING_AREA);
        text_remarks_waiting_area = intent.getStringExtra(WaitingAreaActivity.EXTRA_REMARKS_WAITING_AREA);
        text_rating_offices_clean = intent.getStringExtra(OfficesCleanActivity.EXTRA_RATING_OFFICES_CLEAN);
        text_remarks_offices_clean = intent.getStringExtra(OfficesCleanActivity.EXTRA_REMARKS_OFFICES_CLEAN);
        text_back = intent.getStringExtra(LastQuestionaireActivity.EXTRA_BACK);
        text_like = intent.getStringExtra(LastQuestionaireActivity.EXTRA_LIKE);
        text_improve = intent.getStringExtra(LastQuestionaireActivity.EXTRA_IMPROVE);
        text_comment = intent.getStringExtra(LastQuestionaireActivity.EXTRA_COMMENT);

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

        textView1 = findViewById(R.id.text_view_thank_you);
        textView2 = findViewById(R.id.text_view_thank_you_2);

        textView1.setTypeface(mLight);
        textView2.setTypeface(mLight);

        next = findViewById(R.id.btn_next_1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Are you sure you want to end the survey?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        soundPool.play(introSound, 1, 1, 0, 0, 1);
//                        Intent intent = new Intent(ThankYouActivity.this, DashboardActivity.class);
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
