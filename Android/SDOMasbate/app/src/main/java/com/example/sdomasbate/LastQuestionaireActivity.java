package com.example.sdomasbate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LastQuestionaireActivity extends AppCompatActivity {

    public static final String EXTRA_BACK = "back";
    public static final String EXTRA_LIKE = "like";
    public static final String EXTRA_IMPROVE = "improve";
    public static final String EXTRA_COMMENT = "comment";

    private static final String TAG = "Submit";

    private SoundPool soundPool;
    private int introSound;

    //public TextView name, position, purpose;

    String choose, name, position, purpose;
    String rating_Respectful, remarks_Respectful, rating_ProperlyDressed, remarks_ProperlyDressed, rating_UsingMotivatedVoice, remarks_UsingMotivatedVoice,
            rating_RespondingToQueries, remarks_RespondingToQueries, rating_Smiling, remarks_Smiling, rating_ProvidingSolutions, remarks_ProvidingSolutions,
            rating_WastingNoTime, remarks_WastingNoTime, rating_ComformRooms, remarks_ComfortRooms, rating_Guards, remarks_Guards,
            rating_WaitingArea, remarks_WaitingArea, rating_Offices, remarks_Offices;
//    String text_rating_respect, text_remarks_respect, text_rating_PD, text_remarks_PD, text_rating_MV, text_remarks_MV, text_rating_RQ, text_remarks_RQ,
//            text_rating_smiling, text_remarks_smiling, text_rating_PS, text_remarks_PS, text_rating_wasting_no_time, text_remarks_wasting_no_time,
//            text_rating_comfort_rooms, text_remarks_comfort_rooms, text_rating_guards, text_remarks_guards, text_rating_waiting_area, text_remarks_waiting_area,
//            text_rating_offices_clean, text_remarks_offices_clean;

    String goBackToThisOffice, youLikeMostInTheEmployee, wantToImprove, suggestions;
    private EditText back, like, improve, comment;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_questionaire);

        setTitle("Client Satisfaction Survey");

        Intent intent = getIntent();
        choose = intent.getStringExtra(DivisionActivity.EXTRA_CHOOSE);
        name = intent.getStringExtra(SurveyActivity.EXTRA_NAME);
        position= intent.getStringExtra(SurveyActivity.EXTRA_POSITION);
        purpose = intent.getStringExtra(SurveyActivity.EXTRA_PURPOSE);
        rating_Respectful = intent.getStringExtra(RespectActivity.EXTRA_RATING_RESPECT);
        remarks_Respectful = intent.getStringExtra(RespectActivity.EXTRA_REMARKS_RESPECT);
        rating_ProperlyDressed = intent.getStringExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED);
        remarks_ProperlyDressed = intent.getStringExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED);
        rating_UsingMotivatedVoice = intent.getStringExtra(MotivatedVoiceActivity.EXTRA_RATING_MOTIVATED_VOICE);
        remarks_UsingMotivatedVoice = intent.getStringExtra(MotivatedVoiceActivity.EXTRA_REMARKS_MOTIVATED_VOICE);
        rating_RespondingToQueries = intent.getStringExtra(RespondingQueriesActivity.EXTRA_RATING_RESPONDING_TO_QUERIES);
        remarks_RespondingToQueries = intent.getStringExtra(RespondingQueriesActivity.EXTRA_REMARKS_RESPONDING_TO_QUERIES);
        rating_Smiling = intent.getStringExtra(SmilingActivity.EXTRA_RATING_SMILING);
        remarks_Smiling = intent.getStringExtra(SmilingActivity.EXTRA_REMARKS_SMILING);
        rating_ProvidingSolutions = intent.getStringExtra(ProvidingSolutionsActivity.EXTRA_RATING_PROVIDING_SOLUTIONS);
        remarks_ProvidingSolutions = intent.getStringExtra(ProvidingSolutionsActivity.EXTRA_REMARKS_PROVIDING_SOLUTIONS);
        rating_WastingNoTime = intent.getStringExtra(WastingNoTimeActivity.EXTRA_RATING_WASTING_NO_TIME);
        remarks_WastingNoTime = intent.getStringExtra(WastingNoTimeActivity.EXTRA_REMARKS_WASTING_NO_TIME);
        rating_ComformRooms = intent.getStringExtra(ComfortRoomsActivity.EXTRA_RATING_COMFORT_ROOMS);
        remarks_ComfortRooms = intent.getStringExtra(ComfortRoomsActivity.EXTRA_REMARKS_COMFORT_ROOMS);
        rating_Guards = intent.getStringExtra(GuardsActivity.EXTRA_RATING_GUARDS);
        remarks_Guards = intent.getStringExtra(GuardsActivity.EXTRA_REMARKS_GUARDS);
        rating_WaitingArea = intent.getStringExtra(WaitingAreaActivity.EXTRA_RATING_WAITING_AREA);
        remarks_WaitingArea = intent.getStringExtra(WaitingAreaActivity.EXTRA_REMARKS_WAITING_AREA);
        rating_Offices = intent.getStringExtra(OfficesCleanActivity.EXTRA_RATING_OFFICES_CLEAN);
        remarks_Offices = intent.getStringExtra(OfficesCleanActivity.EXTRA_REMARKS_OFFICES_CLEAN);

//        name = findViewById(R.id.name);
//        position = findViewById(R.id.position);
//        purpose = findViewById(R.id.purpose);
//
//        name.setText(text_name);
//        position.setText(text_position);
//        purpose.setText(text_purpose);

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

        back = findViewById(R.id.edit_text_back);
        like = findViewById(R.id.edit_text_like);
        improve = findViewById(R.id.edit_text_improve);
        comment = findViewById(R.id.edit_text_comment);

        back.setTypeface(mLight);
        like.setTypeface(mLight);
        improve.setTypeface(mLight);
        comment.setTypeface(mLight);

        next = findViewById(R.id.btn_next_1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(introSound, 1, 1, 0, 0, 1);
//                if(position.getText().toString().equals("") || purpose.getText().toString().equals("")){
//                    Toast.makeText(SurveyActivity.this, "Kindly fill up the position and purpose section", Toast.LENGTH_LONG).show();
//                } else {

                isNetworkConnected();

                    //Intent intent = new Intent(LastQuestionaireActivity.this, ThankYouActivity.class);
                    //Submit to firebase code here

                    //startActivity(intent);
//                }
            }
        });
    }


    public boolean isNetworkConnected() {
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                       // Toast.makeText(this, "Wifi Enabled", Toast.LENGTH_LONG).show();
                        Log.i("Connectivity", "WIFI");
                        result = true;
                        submit();
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        //Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_LONG).show();
                        Log.i("Connectivity", "DATA");
                        result = true;
                        submit();
                    }
                } else {
                    noConnection();
                    //Toast.makeText(this, "No Connection", Toast.LENGTH_LONG).show();
                    Log.i("Connectivity", "No Connection");
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        //Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_LONG).show();
                        Log.i("Connectivity", "DATA");
                        result = true;
                        submit();
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        //Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_LONG).show();
                        Log.i("Connectivity", "DATA");
                        result = true;
                        submit();
                    }
                } else {
                    noConnection();
                   // Toast.makeText(this, "No Connection", Toast.LENGTH_LONG).show();
                    Log.i("Connectivity", "No Connection");
                }
            }
        }
        return result;
    }

    public void submit(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference placeholder1 = database.getReference("Placeholder 1");
        DatabaseReference placeholder2 = database.getReference("Placeholder 2");
        DatabaseReference placeholder3 = database.getReference("Placeholder 3");
        DatabaseReference placeholder4 = database.getReference("Placeholder 4");

        goBackToThisOffice = back.getText().toString();
        youLikeMostInTheEmployee = like.getText().toString();
        wantToImprove = improve.getText().toString();
        suggestions = comment.getText().toString();
        Intent intent = new Intent(LastQuestionaireActivity.this, ThankYouActivity.class);
        intent.putExtra(DivisionActivity.EXTRA_CHOOSE, choose);
        intent.putExtra(SurveyActivity.EXTRA_NAME, name);
        intent.putExtra(SurveyActivity.EXTRA_POSITION, position);
        intent.putExtra(SurveyActivity.EXTRA_PURPOSE, purpose);
        intent.putExtra(RespectActivity.EXTRA_RATING_RESPECT, rating_Respectful);
        intent.putExtra(RespectActivity.EXTRA_REMARKS_RESPECT, remarks_Respectful);
        intent.putExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED, rating_ProperlyDressed);
        intent.putExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED, remarks_ProperlyDressed);
        intent.putExtra(MotivatedVoiceActivity.EXTRA_RATING_MOTIVATED_VOICE, rating_UsingMotivatedVoice);
        intent.putExtra(MotivatedVoiceActivity.EXTRA_REMARKS_MOTIVATED_VOICE, remarks_UsingMotivatedVoice);
        intent.putExtra(RespondingQueriesActivity.EXTRA_RATING_RESPONDING_TO_QUERIES, rating_RespondingToQueries);
        intent.putExtra(RespondingQueriesActivity.EXTRA_REMARKS_RESPONDING_TO_QUERIES, remarks_RespondingToQueries);
        intent.putExtra(SmilingActivity.EXTRA_RATING_SMILING, rating_Smiling);
        intent.putExtra(SmilingActivity.EXTRA_REMARKS_SMILING, remarks_Smiling);
        intent.putExtra(ProvidingSolutionsActivity.EXTRA_RATING_PROVIDING_SOLUTIONS, rating_ProvidingSolutions);
        intent.putExtra(ProvidingSolutionsActivity.EXTRA_REMARKS_PROVIDING_SOLUTIONS, remarks_ProvidingSolutions);
        intent.putExtra(WastingNoTimeActivity.EXTRA_RATING_WASTING_NO_TIME, rating_WastingNoTime);
        intent.putExtra(WastingNoTimeActivity.EXTRA_REMARKS_WASTING_NO_TIME, remarks_WastingNoTime);
        intent.putExtra(ComfortRoomsActivity.EXTRA_RATING_COMFORT_ROOMS, rating_ComformRooms);
        intent.putExtra(ComfortRoomsActivity.EXTRA_REMARKS_COMFORT_ROOMS, remarks_ComfortRooms);
        intent.putExtra(GuardsActivity.EXTRA_RATING_GUARDS, rating_Guards);
        intent.putExtra(GuardsActivity.EXTRA_REMARKS_GUARDS, remarks_Guards);
        intent.putExtra(WaitingAreaActivity.EXTRA_RATING_WAITING_AREA, rating_WaitingArea);
        intent.putExtra(WaitingAreaActivity.EXTRA_REMARKS_WAITING_AREA, remarks_WaitingArea);
        intent.putExtra(OfficesCleanActivity.EXTRA_RATING_OFFICES_CLEAN, rating_Offices);
        intent.putExtra(OfficesCleanActivity.EXTRA_REMARKS_OFFICES_CLEAN, remarks_Offices);
        intent.putExtra(EXTRA_BACK, goBackToThisOffice);
        intent.putExtra(EXTRA_LIKE, youLikeMostInTheEmployee);
        intent.putExtra(EXTRA_IMPROVE, wantToImprove);
        intent.putExtra(EXTRA_COMMENT, suggestions);
        //Submit to firebase code here

        UserHelperClass helperClass = new UserHelperClass(name, position, purpose, rating_Respectful, remarks_Respectful, rating_ProperlyDressed, remarks_ProperlyDressed, rating_UsingMotivatedVoice, remarks_UsingMotivatedVoice,
                rating_RespondingToQueries, remarks_RespondingToQueries, rating_Smiling, remarks_Smiling, rating_ProvidingSolutions, remarks_ProvidingSolutions,
                rating_WastingNoTime, remarks_WastingNoTime, rating_ComformRooms, remarks_ComfortRooms, rating_Guards, remarks_Guards,
                rating_WaitingArea, remarks_WaitingArea, rating_Offices, remarks_Offices, goBackToThisOffice, youLikeMostInTheEmployee, wantToImprove, suggestions);

        if(choose.equals("Placeholder 1")){
            Log.i(TAG, choose);
            placeholder1.push().setValue(helperClass);
        }
        if(choose.equals("Placeholder 2")){
            Log.i(TAG, choose);
            placeholder2.push().setValue(helperClass);
        }
        if(choose.equals("Placeholder 3")){
            Log.i(TAG, choose);
            placeholder3.push().setValue(helperClass);
        }
        if(choose.equals( "Placeholder 4")){
            Log.i(TAG, choose);
            placeholder4.push().setValue(helperClass);
        }


        //Log.i(TAG, choose);

//        Log.i(TAG, ""+ text_name + " " + text_position + " " + text_purpose);
//        Log.i(TAG, ""+ text_rating_respect + " " + text_remarks_respect);
//        Log.i(TAG, ""+ text_rating_PD + " " + text_remarks_PD);
//        Log.i(TAG, ""+ text_rating_MV + " " + text_remarks_MV);
//        Log.i(TAG, ""+ text_rating_RQ + " " + text_remarks_RQ);
//        Log.i(TAG, ""+ text_rating_smiling + " " + text_remarks_smiling);
//        Log.i(TAG, ""+ text_rating_PS + " " + text_remarks_PS);
//        Log.i(TAG, ""+ text_rating_wasting_no_time + " " + text_remarks_wasting_no_time);
//        Log.i(TAG, ""+ text_rating_comfort_rooms + " " + text_remarks_comfort_rooms);
//        Log.i(TAG, ""+ text_rating_guards + " " + text_remarks_guards);
//        Log.i(TAG, ""+ text_rating_waiting_area + " " + text_remarks_waiting_area);
//        Log.i(TAG, ""+ text_rating_offices_clean + " " + text_remarks_offices_clean);
//        Log.i(TAG, ""+ backInput + " " + likeInput + " " + improveInput + " " + commentInput);

        startActivity(intent);
    }

    public void noConnection(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Connection");
        builder.setMessage("Kindly connect to the internet to submit your survey.")
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
        Intent intent = new Intent(LastQuestionaireActivity.this, OfficesCleanActivity.class );
        intent.putExtra(DivisionActivity.EXTRA_CHOOSE, choose);
        intent.putExtra(SurveyActivity.EXTRA_NAME, name);
        intent.putExtra(SurveyActivity.EXTRA_POSITION, position);
        intent.putExtra(SurveyActivity.EXTRA_PURPOSE, purpose);
        intent.putExtra(RespectActivity.EXTRA_RATING_RESPECT, rating_Respectful);
        intent.putExtra(RespectActivity.EXTRA_REMARKS_RESPECT, remarks_Respectful);
        intent.putExtra(ProperlyDressedActivity.EXTRA_RATING_PROPERLY_DRESSED, rating_ProperlyDressed);
        intent.putExtra(ProperlyDressedActivity.EXTRA_REMARKS_PROPERLY_DRESSED, remarks_ProperlyDressed);
        intent.putExtra(MotivatedVoiceActivity.EXTRA_RATING_MOTIVATED_VOICE, rating_UsingMotivatedVoice);
        intent.putExtra(MotivatedVoiceActivity.EXTRA_REMARKS_MOTIVATED_VOICE, remarks_UsingMotivatedVoice);
        intent.putExtra(RespondingQueriesActivity.EXTRA_RATING_RESPONDING_TO_QUERIES, rating_RespondingToQueries);
        intent.putExtra(RespondingQueriesActivity.EXTRA_REMARKS_RESPONDING_TO_QUERIES, remarks_RespondingToQueries);
        intent.putExtra(SmilingActivity.EXTRA_RATING_SMILING, rating_Smiling);
        intent.putExtra(SmilingActivity.EXTRA_REMARKS_SMILING, remarks_Smiling);
        intent.putExtra(ProvidingSolutionsActivity.EXTRA_RATING_PROVIDING_SOLUTIONS, rating_ProvidingSolutions);
        intent.putExtra(ProvidingSolutionsActivity.EXTRA_REMARKS_PROVIDING_SOLUTIONS, remarks_ProvidingSolutions);
        intent.putExtra(WastingNoTimeActivity.EXTRA_RATING_WASTING_NO_TIME, rating_WastingNoTime);
        intent.putExtra(WastingNoTimeActivity.EXTRA_REMARKS_WASTING_NO_TIME, remarks_WastingNoTime);
        intent.putExtra(ComfortRoomsActivity.EXTRA_RATING_COMFORT_ROOMS, rating_ComformRooms);
        intent.putExtra(ComfortRoomsActivity.EXTRA_REMARKS_COMFORT_ROOMS, remarks_ComfortRooms);
        intent.putExtra(GuardsActivity.EXTRA_RATING_GUARDS, rating_Guards);
        intent.putExtra(GuardsActivity.EXTRA_REMARKS_GUARDS, remarks_Guards);
        intent.putExtra(WaitingAreaActivity.EXTRA_RATING_WAITING_AREA, rating_WaitingArea);
        intent.putExtra(WaitingAreaActivity.EXTRA_REMARKS_WAITING_AREA, remarks_WaitingArea);
        startActivity(intent);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setMessage("Are you sure you want to end the survey?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        soundPool.play(introSound, 1, 1, 0, 0, 1);
//                        Intent intent = new Intent(LastQuestionaireActivity.this, DashboardActivity.class);
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
