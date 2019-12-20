package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.Marksman;
import com.romain.jeuepicapp.R;
import com.romain.jeuepicapp.Warrior;
import com.romain.jeuepicapp.Wizard;

public class FightActivity extends AppCompatActivity {

    public static final  String LOG_TAG =
            FightActivity.class.getSimpleName();

    private TextView hpP1;
    private TextView hpP2;
    public static Character Ajoueur1 = null;
    public static Character Ajoueur2 = null;
    public static boolean isPlayer1Turn = true;
    private LinearLayout Player1Control;
    private LinearLayout Player2Control;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        Log.d(LOG_TAG, "onCreate: Fight Activity executed successfully ");
        //Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        // int classeP1;
        // classeP1 = extras.getInt("PlayerClassID");
         Ajoueur1 = CreateCaracters (
                1,
                extras.getInt("Player1ClassID"),
                extras.getInt("Player1Level"),
                extras.getInt("Player1Strength"),
                extras.getInt("Player1Agility"),
                extras.getInt("Player1Intel"),
                extras.getInt("Player1Chance"));


        Ajoueur2 = CreateCaracters (
                2,
                extras.getInt("Player2ClassID"),
                extras.getInt("Player2Level"),
                extras.getInt("Player2Strength"),
                extras.getInt("Player2Agility"),
                extras.getInt("Player2Intel"),
                extras.getInt("Player2Chance"));

        Player1Control = findViewById(R.id.linear_layout_control_p1);
        Player2Control = findViewById(R.id.linear_layout_control_p2);
        hpP1 = findViewById(R.id.figth_activity_p1_hp);
        hpP2 = findViewById(R.id.figth_activity_p2_hp);

        hpP1.setText(Integer.toString(Ajoueur1.getHealth()));
        hpP2.setText(Integer.toString(Ajoueur2.getHealth()));

        if (Ajoueur1.getIntelligence() < Ajoueur2.getIntelligence()) {
            Player1Control.setVisibility(View.VISIBLE);
        } else {
            Player2Control.setVisibility(View.VISIBLE);
        }


    }





    public static Character CreateCaracters(int pID, int cla, int lvl, int strg, int agi, int inte, int chance){



        if (cla == 1) {
            return new Warrior(lvl, strg, agi, inte, pID, chance);
        } else if (cla == 2) {
            return new Marksman(lvl, strg, agi, inte, pID, chance);
        } else {
            return new Wizard(lvl, strg, agi, inte, pID, chance);
        }
    }

    public void isSomeoneDead(){
        if (Ajoueur1.getHealth() <= 0) {
            Player1Control.setVisibility(View.INVISIBLE);
            Player2Control.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Le joueur 1 est mort ! Le joueur 2 a gagné !", Toast.LENGTH_LONG);
        } else if (Ajoueur2.getHealth() <= 0 ) {
            Player1Control.setVisibility(View.INVISIBLE);
            Player2Control.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Le joueur 2 est mort ! Le joueur 1 a gagné !", Toast.LENGTH_LONG);
        }

    }

    public void P1_basic_attack_to_P2(View view) {
        Log.d(LOG_TAG, "P1_basic_attack_to_P2:  entered method");
        Ajoueur1.basicAttack(Ajoueur2);
        Log.d(LOG_TAG, "P1_basic_attack_to_P2:  executed joueur1.basicAttack");
        hpP2.setText(Integer.toString(Ajoueur2.getHealth())); // refresh player 2 health
        Player1Control.setVisibility(View.INVISIBLE);
        Player2Control.setVisibility(View.VISIBLE);
        isSomeoneDead();
    }

    public void P1_special_attack_to_P2(View view) {
        Ajoueur1.specialAttack(Ajoueur2);
        hpP1.setText(Integer.toString(Ajoueur1.getHealth()));
        hpP2.setText(Integer.toString(Ajoueur2.getHealth()));
        Player1Control.setVisibility(View.INVISIBLE);
        Player2Control.setVisibility(View.VISIBLE);
        isSomeoneDead();
    }

    public void P2_basic_attack_to_P1(View view) {
        Ajoueur2.basicAttack(Ajoueur1);
        hpP1.setText(Integer.toString(Ajoueur1.getHealth())); // refresh player 1 health
        Player2Control.setVisibility(View.INVISIBLE);
        Player1Control.setVisibility(View.VISIBLE);
        isSomeoneDead();
    }

    public void P2_special_attack_to_P1(View view) {
        Ajoueur2.specialAttack(Ajoueur1);
        hpP1.setText(Integer.toString(Ajoueur1.getHealth()));
        hpP2.setText(Integer.toString(Ajoueur2.getHealth()));
        Player2Control.setVisibility(View.INVISIBLE);
        Player1Control.setVisibility(View.VISIBLE);
        isSomeoneDead();
    }
}
