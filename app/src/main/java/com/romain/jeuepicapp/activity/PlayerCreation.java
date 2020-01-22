package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.ramotion.fluidslider.FluidSlider;
import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.Marksman;
import com.romain.jeuepicapp.R;
import com.romain.jeuepicapp.Warrior;
import com.romain.jeuepicapp.Wizard;

import java.util.Objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

//TODO comme l'activitée de google ( une activité pour la création des deux joueurs )

// TODO nom des methode et attributs en camelCase

public class PlayerCreation extends AppCompatActivity {

    public static int statsPointsAvailable;
    public static int levelSelected;


    public  String aplayer2Name;
    public  int aplayer2Str;
    public  int aplayer2Int;
    public  int aplayer2Agi;
    public  int aplayer2Chance;
    public  int aplayer2Level;
    public  int aplayer2TotStats;
    public  int aplayer2ClassID;

    private EditText p2Name;
    private RadioGroup p2Class;
    // Joueur 1 Stats
    private EditText p2Level;
    private EditText p2Strength;
    private EditText p2Intel;
    private EditText p2Agility;
    private EditText p2Chance;
    private Button p2TestButton;

    VideoView videoViewo;
    MediaPlayer mediaPlayer;
    int currentVideoPosition;

    private ImageView warrior;
    private ImageView wizard;
    private ImageView marksman;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation);


        final FluidSlider theSliderLevel = findViewById(R.id.fluid_slider_level);
        final FluidSlider theSliderStrength = findViewById(R.id.fluid_slider_strength);
        final FluidSlider theSliderInte = findViewById(R.id.fluid_slider_inte);
        final FluidSlider theSliderAgi = findViewById(R.id.fluid_slider_agility);
        final FluidSlider theSliderLuck = findViewById(R.id.fluid_slider_luck);

        int min = 0;
        int max = 100;


        theSliderLevel.setPosition(0f);
        theSliderLevel.setStartText(String.valueOf(min));
        theSliderLevel.setEndText(String.valueOf(max));

        theSliderStrength.setPosition(0f);
        theSliderStrength.setStartText(String.valueOf(min));
        theSliderStrength.setEndText(String.valueOf(max));
        theSliderStrength.setBubbleText(String.valueOf(0));

        theSliderInte.setPosition(0f);
        theSliderInte.setStartText(String.valueOf(min));
        theSliderInte.setEndText(String.valueOf(max));
        theSliderInte.setBubbleText(String.valueOf(0));

        theSliderAgi.setPosition(0f);
        theSliderAgi.setStartText(String.valueOf(min));
        theSliderAgi.setEndText(String.valueOf(max));
        theSliderAgi.setBubbleText(String.valueOf(0));

        theSliderLuck.setPosition(0f);
        theSliderLuck.setStartText(String.valueOf(min));
        theSliderLuck.setEndText(String.valueOf(max));
        theSliderLuck.setBubbleText(String.valueOf(0));





        warrior = findViewById(R.id.Warrior_choice_head);
        wizard = findViewById(R.id.Wizard_choice_head);
        marksman = findViewById(R.id.Marksman_choice_head);

        //p2Name = findViewById(R.id.input_player2_name);

       //                             p2Level = findViewById(R.id.level_input2);    !!!!!!!!!!!!
       // p2Strength = findViewById(R.id.strength_input2);
       // p2Intel = findViewById(R.id.intel_input2);
       // p2Agility = findViewById(R.id.agility_input2);
       // p2Chance = findViewById(R.id.chance_input2);
        p2TestButton = findViewById(R.id.ok_test_stats_btn2);

        videoViewo = findViewById(R.id.video_view);
        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                +"/"
                + R.raw.waterfall_background);
        videoViewo.setVideoURI(uri);
        videoViewo.start();

        videoViewo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
                mediaPlayer.setLooping(true);
                if (currentVideoPosition != 0) {
                    mediaPlayer.seekTo(currentVideoPosition);
                    mediaPlayer.start();
                }
            }
        });


        theSliderLevel.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                levelSelected = Integer.parseInt(Objects.requireNonNull(theSliderLevel.getBubbleText()));
                Log.d("STATS", "invoke: endTrackListener of level : la valeur de levelSelected est : " + levelSelected);
                aplayer2Level = Integer.parseInt(theSliderLevel.getBubbleText());


                return Unit.INSTANCE;
            }
        });


        theSliderLevel.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos) {
                final String value = String.valueOf( (int) (100 * pos));
                theSliderLevel.setBubbleText(value);
                statsPointsAvailable = Integer.parseInt(value);
                return Unit.INSTANCE;
            }
        });
// FORCE
        theSliderStrength.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                theSliderStrength.setEndText(String.valueOf(statsPointsAvailable));

                return Unit.INSTANCE;
            }
        });

        theSliderStrength.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {

                statsPointsAvailable = ( levelSelected - ( Integer.parseInt((Objects.requireNonNull(theSliderStrength.getBubbleText()))  )
                - Integer.parseInt((Objects.requireNonNull(theSliderInte.getBubbleText())) )
                - Integer.parseInt((Objects.requireNonNull(theSliderAgi.getBubbleText())) )
                - Integer.parseInt((Objects.requireNonNull(theSliderLuck.getBubbleText())) )
                )

                );
                theSliderStrength.setEndText(String.valueOf(statsPointsAvailable));

                aplayer2Str = Integer.parseInt(theSliderStrength.getBubbleText());

                return Unit.INSTANCE;

            }
        });

        theSliderStrength.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos) {
                final String value = String.valueOf( (int) ( statsPointsAvailable * pos));
                theSliderStrength.setBubbleText(value);

                return Unit.INSTANCE;
            }
        });

// INTELLIGENCE
        theSliderInte.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                theSliderInte.setEndText(String.valueOf(statsPointsAvailable));

                return Unit.INSTANCE;
            }
        });

        theSliderInte.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {

                Log.d("STATS", "invoke: valeur de bubble text de stength: " + theSliderStrength.getBubbleText());

                //TODO à mettre dans une fonction
                statsPointsAvailable = ( levelSelected - (Integer.parseInt(Objects.requireNonNull(theSliderStrength.getBubbleText()))
                        + Integer.parseInt(Objects.requireNonNull(theSliderInte.getBubbleText()))
                        + Integer.parseInt(Objects.requireNonNull(theSliderAgi.getBubbleText()))
                        + Integer.parseInt(Objects.requireNonNull(theSliderLuck.getBubbleText()))
                )
                );
                Log.d("STATS", "invoke: val de statPointsAvailable = " + statsPointsAvailable);
                theSliderInte.setEndText(String.valueOf(statsPointsAvailable));

                aplayer2Int = Integer.parseInt(theSliderInte.getBubbleText());
                return Unit.INSTANCE;

            }
        });

        theSliderInte.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos) {
                final String value = String.valueOf( (int) ( statsPointsAvailable * pos));
                theSliderInte.setBubbleText(value);
                return Unit.INSTANCE;
            }
        });

// AGILITE
        theSliderAgi.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                theSliderAgi.setEndText(String.valueOf(statsPointsAvailable));

                return Unit.INSTANCE;
            }
        });

        theSliderAgi.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {

                statsPointsAvailable = ( levelSelected - (Integer.parseInt((theSliderStrength.getBubbleText() )  )
                        + Integer.parseInt((theSliderInte.getBubbleText() ) )
                        + Integer.parseInt((theSliderAgi.getBubbleText() ) )
                        + Integer.parseInt((theSliderLuck.getBubbleText() ) )
                )
                );
                theSliderAgi.setEndText(String.valueOf(statsPointsAvailable));

                aplayer2Agi = Integer.parseInt(theSliderAgi.getBubbleText());
                return Unit.INSTANCE;

            }
        });

        theSliderAgi.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos) {
                final String value = String.valueOf( (int) ( statsPointsAvailable * pos));
                theSliderAgi.setBubbleText(value);
                return Unit.INSTANCE;
            }
        });

// CHANCE
        theSliderLuck.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                theSliderLuck.setEndText(String.valueOf(statsPointsAvailable));

                return Unit.INSTANCE;
            }
        });

        theSliderLuck.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {

                statsPointsAvailable = ( levelSelected - ( Integer.parseInt((theSliderStrength.getBubbleText() )  )
                        + Integer.parseInt((theSliderInte.getBubbleText() ) )
                        + Integer.parseInt((theSliderAgi.getBubbleText() ) )
                        + Integer.parseInt((theSliderLuck.getBubbleText() ) )
                )
                );
                theSliderLuck.setEndText(String.valueOf(statsPointsAvailable));

                aplayer2Chance = Integer.parseInt(theSliderLuck.getBubbleText());
                return Unit.INSTANCE;

            }
        });

        theSliderLuck.setPositionListener(new Function1<Float, Unit>() {
            @Override
            public Unit invoke(Float pos) {
                final String value = String.valueOf( (int) ( statsPointsAvailable * pos));
                theSliderLuck.setBubbleText(value);
                return Unit.INSTANCE;
            }
        });
    }


    public void createCharacter(View view) {

        Intent intentToP1Creation = getIntent();
        int playerID = intentToP1Creation.getExtras().getInt(MainActivity.EXTRA_PLAYER_ID);


        //Intent intentb = new Intent(this, FightActivity.class);
        Intent replyIntent = new Intent();
        Character player1 = CreateCaracters(playerID,
                aplayer2ClassID,
                aplayer2Level,
                aplayer2Str,
                aplayer2Agi,
                aplayer2Int,
                aplayer2Chance);
        replyIntent.putExtra(MainActivity.EXTRA_PLAYER_ID, player1);
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    public static Character CreateCaracters(int pID, int cla, int lvl, int strg, int agi, int inte, int chance) {
        switch (cla) {
            case 1:
                Log.d("Fight", "CreateCaracters: entered warrior creation condition");
                return new Warrior(lvl, strg, agi, inte, pID, chance);
            case 2:
                Log.d("Fight", "CreateCaracters: entered Wizard creation condition");
                return new Wizard(lvl, strg, agi, inte, pID, chance);
            case 3:
                Log.d("Fight", "CreateCaracters: entered Marksman creation condition");
                return new Marksman(lvl, strg, agi, inte, pID, chance);
            default:
                Log.d("Fight", "CreateCaracters: entered null :/ ");
                return null;
        }


    }

    public void onWarriorClicked(View view) {
        warrior.setColorFilter(Color.argb(100, 0, 200, 0));
        wizard.setColorFilter(Color.argb(0, 0, 0, 0));
        marksman.setColorFilter(Color.argb(0, 0, 0, 0));
        aplayer2ClassID = 1;
    }

    public void onWizardClicked(View view) {

        wizard.setColorFilter(Color.argb(100, 0, 200, 0));
        warrior.setColorFilter(Color.argb(0, 0, 0, 0));
        marksman.setColorFilter(Color.argb(0, 0, 0, 0));
        aplayer2ClassID = 2;
    }

    public void onMarksmanClicked(View view) {

        marksman.setColorFilter(Color.argb(100, 0, 200, 0));
        wizard.setColorFilter(Color.argb(0, 0, 0, 0));
        warrior.setColorFilter(Color.argb(0, 0, 0, 0));
        aplayer2ClassID = 3;
    }
}
