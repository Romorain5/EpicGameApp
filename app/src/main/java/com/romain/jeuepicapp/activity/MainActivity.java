package com.romain.jeuepicapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.R;

// TODO deux activité, une pour chaque création de joueur

public class MainActivity extends AppCompatActivity{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_PLAYER_ID = "PLAYER";
    public static final int PLAYER_CREATION_REQUEST = 1;
    public static final int PLAYER_CREATION_REQUEST2 = 2;

    Character joueur1 = null;
    Character joueur2 = null;


    public int playerIDCheck;


    public static final String EXTRA_ID = "Player1ClassID";

    private Button CreatePlayer1Button;
    private Button CreatePlayer2Button;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreatePlayer1Button = findViewById(R.id.activity_main_create_player1);
        CreatePlayer2Button = findViewById(R.id.activity_main_create_player2);

    }







    public void gotoPlayer1Creation(View view) {
        playerIDCheck = 1;
        Intent intentToP1Creation = new Intent(this, PlayerCreation.class);

        Log.d(LOG_TAG, "Button player1Creation clicked ! ");
        intentToP1Creation.putExtra(EXTRA_PLAYER_ID, playerIDCheck);



        startActivityForResult(intentToP1Creation, PLAYER_CREATION_REQUEST);
    }


    public void gotoPlayer2Creation(View view) {
        playerIDCheck = 2;
        Intent intentToP1Creation = new Intent(this,PlayerCreation.class);
        Log.d(LOG_TAG, "gotoPlayer2Creation: Button clicked ! ");
        intentToP1Creation.putExtra(EXTRA_PLAYER_ID,playerIDCheck);

        startActivityForResult(intentToP1Creation, PLAYER_CREATION_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLAYER_CREATION_REQUEST && resultCode == Activity.RESULT_OK) {
            if (playerIDCheck == 1) {
                Log.d(LOG_TAG, "onActivityResult: back ok :), joueur 1 créé !  ");
                CreatePlayer1Button.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.green));



                joueur1 = data.getParcelableExtra(EXTRA_PLAYER_ID);




            } else if (playerIDCheck == 2) {
                Log.d(LOG_TAG, "onActivityResult: back ok :), jouer 2 créé !! ");
                joueur2 = data.getParcelableExtra(EXTRA_PLAYER_ID);
                CreatePlayer2Button.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.green));

            }


        }

    }

    public void startFight(View view) {

        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra(FightActivity.EXTRA_PLAYER1, joueur1);
        intent.putExtra(FightActivity.EXTRA_PLAYER2, joueur2);

        startActivity(intent);


    }
}



