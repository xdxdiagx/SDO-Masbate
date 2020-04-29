package com.example.sdomasbate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DivisionActivity extends AppCompatActivity {

    public static final String EXTRA_CHOOSE = "choose";

    private SoundPool soundPool;
    private int introSound;

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    String item, itemhead;
    String choose;

    ImageView splash;
    Animation smallToBig, fromLeftToRight, fleft, fhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        setTitle("Section");

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

        //smallToBig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        fromLeftToRight = AnimationUtils.loadAnimation(this, R.anim.from_left_to_right);

        //splash = (ImageView) findViewById(R.id.splash2);
        //splash.startAnimation(smallToBig);

        listView = (ExpandableListView)findViewById(R.id.expand);
        listView.startAnimation(fromLeftToRight);
        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHash);
        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

//                itemhead = listDataHeader.get(groupPosition).toString();
//                Log.i("item type",""+itemhead);
//                if(itemhead.equals("Office of Schools Division Superintendent (OSDS)")){
//                    Log.i("item type","0");
//                } else if(itemhead.equals("Curriculum Implementation Division (CID)")) {
//                    Log.i("item type","1");
//                } else if(itemhead.equals("School Governance and Operations Division (SGOD)")) {
//                    Log.i("item type","2");
//                }

                item =listHash.get(listDataHeader.get(groupPosition)).get(childPosition).toString();
                Log.i("item type",""+item);
                if(item.equals("Placeholder 1")){
                    soundPool.play(introSound, 1, 1, 0, 0, 1);
                    choose = "Placeholder 1";
                    Log.i("item type","New Activity 1");
                    Intent i = new Intent(DivisionActivity.this, SurveyActivity.class);
                    i.putExtra(EXTRA_CHOOSE, choose);
                    startActivity(i);
                    //Toast.makeText(DivisionActivity.this, "New Activity 1", Toast.LENGTH_LONG).show();
                } else if (item.equals("Placeholder 2")){
                    soundPool.play(introSound, 1, 1, 0, 0, 1);
                    choose = "Placeholder 2";
                    Log.i("item type","New Activity 2");
                    Intent i = new Intent(DivisionActivity.this, SurveyActivity.class);
                    i.putExtra(EXTRA_CHOOSE, choose);
                    startActivity(i);
                    //Toast.makeText(DivisionActivity.this, "New Activity 2", Toast.LENGTH_LONG).show();
                } else if (item.equals("Placeholder 3")){
                    soundPool.play(introSound, 1, 1, 0, 0, 1);
                    choose = "Placeholder 3";
                    Log.i("item type","New Activity 3");
                    Intent i = new Intent(DivisionActivity.this, SurveyActivity.class);
                    i.putExtra(EXTRA_CHOOSE, choose);
                    startActivity(i);
                   //Toast.makeText(DivisionActivity.this, "New Activity 3", Toast.LENGTH_LONG).show();
                } else if (item.equals("Placeholder 4")){
                    soundPool.play(introSound, 1, 1, 0, 0, 1);
                    choose = "Placeholder 4";
                    Log.i("item type","New Activity 4");
                    Intent i = new Intent(DivisionActivity.this, SurveyActivity.class);
                    i.putExtra(EXTRA_CHOOSE, choose);
                    startActivity(i);
                    //Toast.makeText(DivisionActivity.this, "New Activity 4", Toast.LENGTH_LONG).show();
                }

                //Toast.makeText(DivisionActivity.this, item, Toast.LENGTH_LONG);
                //Toast.makeText(DivisionActivity.this, listDataHeader.get(groupPosition) + " : " + listHash.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

//    public void playSound(View v){
//        switch (v.getId()){
//            case R.id.list_header:
//                soundPool.play(introSound, 1, 1, 0, 0, 1);
//                break;
//            case R.id.list_item:
//                soundPool.play(introSound, 1, 1, 0, 0, 1);
//                break;
//        }
//
//    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Office of Schools Division Superintendent (OSDS)");
        listDataHeader.add("Curriculum Implementation Division (CID)");
        listDataHeader.add("School Governance and Operations Division (SGOD)");
        listDataHeader.add("Youth Formation Division (YFD)");
        listDataHeader.add("Human Resource Management");
        listDataHeader.add("Records Section");
        listDataHeader.add("Legal Office");
        listDataHeader.add("Supply Office");
        listDataHeader.add("Accounting Section");
        listDataHeader.add("Planning Office");

        List<String> OSDS = new ArrayList<>();
        OSDS.add("Placeholder 1");

        List<String> CID = new ArrayList<>();
        CID.add("Placeholder 1");
        CID.add("Placeholder 2");
        CID.add("Placeholder 3");

        List<String> SGOD = new ArrayList<>();
        SGOD.add("Placeholder 4");
        SGOD.add("Placeholder 5");

        List<String> YFD = new ArrayList<>();
        YFD.add("Placeholder 6");
        YFD.add("Placeholder 7");

        List<String> HRM = new ArrayList<>();
        HRM.add("Placeholder 8");

        List<String> RS = new ArrayList<>();
        RS.add("Placeholder 9");
        RS.add("Placeholder 10");
        RS.add("Placeholder 11");

        List<String> LO = new ArrayList<>();
        LO.add("Placeholder 1");
        LO.add("Placeholder 2");
        LO.add("Placeholder 3");

        List<String> SO = new ArrayList<>();
        SO.add("Placeholder 12");

        List<String> AS = new ArrayList<>();
        AS.add("Placeholder 13");
        AS.add("Placeholder 14");

        List<String> PO = new ArrayList<>();
        PO.add("Placeholder 15");


        listHash.put(listDataHeader.get(0),OSDS);
        listHash.put(listDataHeader.get(1),CID);
        listHash.put(listDataHeader.get(2),SGOD);
        listHash.put(listDataHeader.get(3),YFD);
        listHash.put(listDataHeader.get(4),HRM);
        listHash.put(listDataHeader.get(5),RS);
        listHash.put(listDataHeader.get(6),LO);
        listHash.put(listDataHeader.get(7),SO);
        listHash.put(listDataHeader.get(8),AS);
        listHash.put(listDataHeader.get(9),PO);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
