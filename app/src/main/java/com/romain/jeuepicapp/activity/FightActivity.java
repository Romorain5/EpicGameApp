package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.R;

// TODO Intent les characters directement depuis le MainActivity ( parcelable )

// TODO Essayer d'utiliser les même bouton pour le controle des tours

// TODO Recycler view pour faire un historique de se qu'il se passe ( regarder le TP du TopQuiz )


public class FightActivity extends AppCompatActivity {

    public static final  String LOG_TAG =
            FightActivity.class.getSimpleName();
    private TextView InfoPlayerTurns;
    private TextView hpP1;
    private TextView hpP2;
    public Character mJoueur1 = null;
    public Character mJoueur2 = null;
    public static boolean isPlayer1Turn = true;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        Log.d(LOG_TAG, "onCreate: Fight Activity executed successfully ");
        Bundle data = getIntent().getExtras();
        mJoueur1 = data.getParcelable("Player1");
        mJoueur2 = data.getParcelable("Player2");
      // mJoueur1 = data.getExtras().getParcelable("Player1");
      // mJoueur2 = data.getExtras().getParcelable("Player2");
       // Toast.makeText(this,"Du coup la force du joueur 1 est : " + mJoueur1.getStrength(), Toast.LENGTH_LONG).show();
        Log.d("Fight", "onCreate: la force du joueur 1 est :  " + mJoueur1.getStrength());


        InfoPlayerTurns = findViewById(R.id.info_player_turns);
        hpP1 = findViewById(R.id.figth_activity_p1_hp);
        hpP2 = findViewById(R.id.figth_activity_p2_hp);

        hpP1.setText(Integer.toString(mJoueur1.getHealth()));
        hpP2.setText(Integer.toString(mJoueur2.getHealth()));
        Log.d("Fight", "onCreate: le niveau du joueur 1 est " + mJoueur1.getLevel() + " et donc ses point de vie sont de : " + mJoueur1.getHealth());
        Log.d("Fight", "onCreate: le niveau du joueur 2 est " + mJoueur2.getLevel() + " et donc ses point de vie sont de : " + mJoueur2.getHealth());

        if (mJoueur1.getIntelligence() > mJoueur2.getIntelligence()) {
            isPlayer1Turn = true;
            InfoPlayerTurns.setText("Joueur 1, à toi !");
        } else {
            isPlayer1Turn = false;
            InfoPlayerTurns.setText("Joueur 2, à toi !");
        }


    }







    public void isSomeoneDead(){
        if (mJoueur1.getHealth() <= 0) {

            Toast.makeText(this, "Le joueur 1 est mort ! Le joueur 2 a gagné !", Toast.LENGTH_LONG).show();
        } else if (mJoueur2.getHealth() <= 0 ) {

            Toast.makeText(this, "Le joueur 2 est mort ! Le joueur 1 a gagné !", Toast.LENGTH_LONG).show();
        }

    }

    public void BA_p1_to_p2() {
        Log.d("Fight", "BA_p1_to_p2: Clique du bouton attaque basique");
        mJoueur1.basicAttack(mJoueur2);
        Log.d("Fight", "BA_p1_to_p2: temoins apres l'attaque ");
        isPlayer1Turn = false;
        InfoPlayerTurns.setText(R.string.J2_turn);
        Log.d("Fight", "BA_p1_to_p2: temoins apres changement de text - au tour du joueur 2 ");
        Log.d("Fight", "BA_p1_to_p2: Et apres réactualisation de l'affichage des point de vie du joueur 2.");
        hpP2.setText(mJoueur2.getHealth());
    }

    public void BA_p2_to_p1() {
        mJoueur2.basicAttack(mJoueur1);
        isPlayer1Turn = true;
        InfoPlayerTurns.setText(R.string.J1_turn);
        hpP1.setText(mJoueur1.getHealth());
    }



    public void Basic_attack(View view) {
        if (isPlayer1Turn) {
            BA_p1_to_p2();
        } else {
            BA_p2_to_p1();
        }
    }

    public void Special_attack(View view) {
        if (isPlayer1Turn) {
            mJoueur1.specialAttack(mJoueur2);
            isPlayer1Turn = false;
            InfoPlayerTurns.setText(R.string.J2_turn);
            hpP1.setText(mJoueur1.getHealth());
            hpP2.setText(mJoueur2.getHealth());
        } else {
            mJoueur2.specialAttack(mJoueur1);
            isPlayer1Turn = true;
            InfoPlayerTurns.setText(R.string.J1_turn);

            hpP1.setText(mJoueur1.getHealth());
            hpP2.setText(mJoueur2.getHealth());

        }
    }
}
