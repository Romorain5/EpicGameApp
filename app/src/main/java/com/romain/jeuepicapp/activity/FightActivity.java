package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.R;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    public static final  String LOG_TAG =
            FightActivity.class.getSimpleName();
    private TextView InfoPlayerTurns;
    public Character mJoueur1 = null;
    public Character mJoueur2 = null;
    public static boolean isPlayer1Turn = true;
    private ProgressView player1HealthBar;
    private ProgressView player2HealthBar;

    public static RecyclerView recyclerView;

    public static final String EXTRA_PLAYER1 = "Player1";
    public static final String EXTRA_PLAYER2 = "Player2";
    public static ArrayList<String> eventMessagesArray = new ArrayList<>();
    static MyRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        player1HealthBar = findViewById(R.id.player1_healthbar);
        player2HealthBar = findViewById(R.id.player2_healthbar);
        ImageView player1Body = findViewById(R.id.imageViewBodyPlayer1);
        ImageView player2Body = findViewById(R.id.imageViewBodyPlayer2);



        player1HealthBar.setAutoAnimate(false);
        player2HealthBar.setAutoAnimate(false);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this,eventMessagesArray);
        recyclerView.setAdapter(adapter);

        Log.d(LOG_TAG, "onCreate: Fight Activity executed successfully ");
        Bundle data = getIntent().getExtras();
        if (data != null) {
            mJoueur1 = data.getParcelable(EXTRA_PLAYER1);
        }
        mJoueur2 = data.getParcelable(EXTRA_PLAYER2);


        player1HealthBar.setMax(mJoueur1.getHealth());
        player2HealthBar.setMax(mJoueur2.getHealth());

        player1HealthBar.setProgress(mJoueur1.getHealth());
        player2HealthBar.setProgress(mJoueur2.getHealth());

        defineCharactersBody(mJoueur1, player1Body);
        defineCharactersBody(mJoueur2, player2Body);




        InfoPlayerTurns = findViewById(R.id.info_player_turns);


        if (mJoueur1.getIntelligence() > mJoueur2.getIntelligence()) {
            isPlayer1Turn = true;
            InfoPlayerTurns.setText(R.string.J1_turn);
        } else {
            isPlayer1Turn = false;
            InfoPlayerTurns.setText(R.string.J2_turn);
        }


    }



    public void isSomeoneDead(){
        if (mJoueur1.getHealth() <= 0) {

            Toast.makeText(this, "Le joueur 1 est mort ! Le joueur 2 a gagné !", Toast.LENGTH_LONG).show();
            finish();
        } else if (mJoueur2.getHealth() <= 0 ) {

            Toast.makeText(this, "Le joueur 2 est mort ! Le joueur 1 a gagné !", Toast.LENGTH_LONG).show();
            finish();
        }

    }


    public void Basic_attack(View view) {
        if (isPlayer1Turn) {
            addEventInfo(mJoueur1.basicAttack(mJoueur2));
        } else {
            addEventInfo(mJoueur2.basicAttack(mJoueur1));
        }
        updateHealthBars();
        switchTurn();
        isSomeoneDead();
    }

    public void Special_attack(View view) {
        if (isPlayer1Turn) {
            addEventInfo(mJoueur1.specialAttack(mJoueur2));
        } else {
            addEventInfo(mJoueur2.specialAttack(mJoueur1));
        }
        updateHealthBars();
        switchTurn();
        isSomeoneDead();

    }

    public static void addEventInfo(String msg) {
        eventMessagesArray.add(msg);
        adapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(eventMessagesArray.size() -1 );
    }

    public static void defineCharactersBody(Character player, ImageView img) {

        if (player.getNumber() == 1) {
            switch (player.getClasse()) {
                case 1:
                    img.setImageResource(R.drawable.warrior_full_body_facing_right);
                    break;
                case 2:
                    img.setImageResource(R.drawable.wizard_full_body_facing_right);
                    break;
                case 3:
                    img.setImageResource(R.drawable.marksman_full_body_facing_right);
                    break;
            }

        } else if (player.getNumber() == 2) {
            switch (player.getClasse()) {
                case 1:
                    img.setImageResource(R.drawable.warrior_full_body_facing_left);
                    break;
                case 2:
                    img.setImageResource(R.drawable.wizard_full_body_facing_left);
                    break;
                case 3:
                    img.setImageResource(R.drawable.marksman_full_body_facing_left);
                    break;
            }
        }

    }

    public void updateHealthBars() {
        player2HealthBar.setProgress(mJoueur2.getHealth());
        player1HealthBar.setProgress(mJoueur1.getHealth());
    }

    public void switchTurn() {
        if (isPlayer1Turn) {
            isPlayer1Turn = false;
            InfoPlayerTurns.setText(R.string.J2_turn);

        } else {
            isPlayer1Turn = true;
            InfoPlayerTurns.setText(R.string.J1_turn);

        }


    }


}
