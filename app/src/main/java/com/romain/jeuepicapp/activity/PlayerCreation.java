package com.romain.jeuepicapp.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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
public class PlayerCreation extends AppCompatActivity {

    public static int statsPointsAvailable;
    public static int levelSelected;


    public  int mPlayer2Str;
    public  int mPlayer2Int;
    public  int mPlayer2Agi;
    public  int mPlayer2Chance;
    public  int mPlayer2Level;
    public  int mPlayer2ClassID;


    VideoView videoViewo;
    MediaPlayer mediaPlayer;
    int currentVideoPosition;

    private ImageView warrior;
    private ImageView wizard;
    private ImageView marksman;

    int min = 0;
    int max = 100;

    private FluidSlider theSliderLevel;
    private FluidSlider theSliderStrength;
    private FluidSlider theSliderInte;
    private FluidSlider theSliderAgi;
    private FluidSlider theSliderLuck;



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

        theSliderLevel.setPosition(0f);
        theSliderLevel.setStartText(String.valueOf(min));
        theSliderLevel.setEndText(String.valueOf(max));

        asignDefault(theSliderStrength);
        asignDefault(theSliderAgi);
        asignDefault(theSliderInte);
        asignDefault(theSliderLuck);

        warrior = findViewById(R.id.Warrior_choice_head);
        wizard = findViewById(R.id.Wizard_choice_head);
        marksman = findViewById(R.id.Marksman_choice_head);


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
                mPlayer2Level = Integer.parseInt(theSliderLevel.getBubbleText());


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

                updateStatsPointsAvailable();
                theSliderStrength.setEndText(String.valueOf(statsPointsAvailable));

                mPlayer2Str = Integer.parseInt(Objects.requireNonNull(theSliderStrength.getBubbleText()));

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

                updateStatsPointsAvailable();

                theSliderInte.setEndText(String.valueOf(statsPointsAvailable));

                mPlayer2Int = Integer.parseInt(Objects.requireNonNull(theSliderInte.getBubbleText()));
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

                updateStatsPointsAvailable();
                theSliderAgi.setEndText(String.valueOf(statsPointsAvailable));

                mPlayer2Agi = Integer.parseInt(Objects.requireNonNull(theSliderAgi.getBubbleText()));
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

                updateStatsPointsAvailable();
                theSliderLuck.setEndText(String.valueOf(statsPointsAvailable));

                mPlayer2Chance = Integer.parseInt(Objects.requireNonNull(theSliderLuck.getBubbleText()));
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
        int playerID = Objects.requireNonNull(intentToP1Creation.getExtras()).getInt(MainActivity.EXTRA_PLAYER_ID);

        if (mPlayer2ClassID == 1 || mPlayer2ClassID == 2 || mPlayer2ClassID == 3) {
            Intent replyIntent = new Intent();
            Character player1 = CreateCaracters(playerID,
                    mPlayer2ClassID,
                    mPlayer2Level,
                    mPlayer2Str,
                    mPlayer2Agi,
                    mPlayer2Int,
                    mPlayer2Chance);
            replyIntent.putExtra(MainActivity.EXTRA_PLAYER_ID, player1);
            setResult(RESULT_OK,replyIntent);
            finish();


        } else {
            Toast.makeText(this,"Vous devez selectionner un personnage ! " , Toast.LENGTH_LONG).show();
        }

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
        mPlayer2ClassID = 1;
    }

    public void onWizardClicked(View view) {

        wizard.setColorFilter(Color.argb(100, 0, 200, 0));
        warrior.setColorFilter(Color.argb(0, 0, 0, 0));
        marksman.setColorFilter(Color.argb(0, 0, 0, 0));
        mPlayer2ClassID = 2;
    }

    public void onMarksmanClicked(View view) {

        marksman.setColorFilter(Color.argb(100, 0, 200, 0));
        wizard.setColorFilter(Color.argb(0, 0, 0, 0));
        warrior.setColorFilter(Color.argb(0, 0, 0, 0));
        mPlayer2ClassID = 3;
    }


    public void updateStatsPointsAvailable() {

        statsPointsAvailable = ( levelSelected - ( Integer.parseInt((Objects.requireNonNull(theSliderStrength.getBubbleText()))  )
                + Integer.parseInt((Objects.requireNonNull(theSliderInte.getBubbleText())) )
                + Integer.parseInt((Objects.requireNonNull(theSliderAgi.getBubbleText())) )
                + Integer.parseInt((Objects.requireNonNull(theSliderLuck.getBubbleText())) )
        )
        );

    }

    public void asignDefault(FluidSlider theSlider) {
        theSlider.setPosition(0f);
        theSlider.setStartText(String.valueOf(min));
        theSlider.setEndText(String.valueOf(max));
        theSlider.setBubbleText(String.valueOf(0));
    }

}


