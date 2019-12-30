package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.Marksman;
import com.romain.jeuepicapp.R;
import com.romain.jeuepicapp.Warrior;
import com.romain.jeuepicapp.Wizard;

// TODO deux activité, une pour chaque création de joueur

public class MainActivity extends AppCompatActivity{

    public static final  String LOG_TAG =
            MainActivity.class.getSimpleName();
    public static final String EXTRA_ID = "Player1ClassID";


    private Button PlayButton;

    // --- JOUEUR 1 ---

    public String Aplayer1Name;
    public  int Aplayer1Str;
    public  int Aplayer1Int;
    public  int Aplayer1Agi;
    public  int Aplayer1Chance;
    public  int Aplayer1Level;
    public  int Aplayer1TotStats;
    public  int Aplayer1ClassID;


    // Joueur 1 nom et classe  :
    private EditText P1Name;
    private RadioGroup P1Class;
    // Joueur 1 Stats
    private EditText P1Level;
    private EditText P1Strength;
    private EditText P1Intel;
    private EditText P1Agility;
    private EditText P1Chance;
    private Button P1TestButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- JOUEUR 1 ---

        // Joueur 1 nom et classe :

        P1Name = findViewById(R.id.input_player1_name);
        P1Class = findViewById(R.id.class_choice_group);

        // Joueur 1 stats :
        P1Level = findViewById(R.id.level_input);
        P1Strength = findViewById(R.id.strength_input1);
        P1Intel = findViewById(R.id.intel_input1);
        P1Agility = findViewById(R.id.agility_input1);
        P1Chance = findViewById(R.id.chance_input1);
        P1TestButton = findViewById(R.id.ok_test_stats_btn1);

        // --- JOUEUR 2 ---




       // PlayButton.setEnabled(false);



        P1TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aplayer1Name = P1Name.getText().toString(); //récupére le nom du joueur 1 et le stock dans Aplayer1Name
                // Récupere et stock la force du joueur 1
                String valueS = P1Strength.getText().toString();
                Aplayer1Str = Integer.parseInt(valueS);

                String valueI = P1Intel.getText().toString();
                Aplayer1Int = Integer.parseInt(valueI);

                String valueA = P1Agility.getText().toString();
                Aplayer1Agi = Integer.parseInt(valueA);

                String valueC = P1Chance.getText().toString();
                Aplayer1Chance = Integer.parseInt(valueC);

                String valueL = P1Level.getText().toString();
                Aplayer1Level = Integer.parseInt(valueL);

                Aplayer1TotStats = Aplayer1Agi + Aplayer1Chance + Aplayer1Int + Aplayer1Str;
                if(Aplayer1TotStats > Aplayer1Level || Aplayer1TotStats < 1) {
                    Toast.makeText(MainActivity.this,"La somme des caractèristiques d'un joueur ne peut pas dépasser son niveau ! ",Toast.LENGTH_LONG).show();
                    P1TestButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.red));
                } else {
                    P1TestButton.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.green));


                }
            }
        });




    //    PlayButton.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View v) {
    //           // Intent newGameIntent = new Intent(MainActivity.this, Main2ActivityPlayer2.class);
    //           // startActivity(newGameIntent);
    //        }
    //    });

        //PlayersCreation.init();
    }

    public void checkButton(View v) {
        int radioId = P1Class.getCheckedRadioButtonId();
        switch (radioId) {
            case R.id.class_choice_warrior:
                Aplayer1ClassID = 1;
                break;
            case R.id.class_choice_wizard:
                Aplayer1ClassID = 2;
                break;
            case R.id.class_choice_marksman:
                Aplayer1ClassID = 3;
                break;


        }
    }




    public void GotoPlayer2Creation(View view) {
        Intent intent = new Intent(this, FightActivity.class);
        Intent intentToP2Creation = new Intent(this, Player2Creation.class);
        Log.d(LOG_TAG, "Button next clicked ! ");


        Character player1 = CreateCaracters(1,
            Aplayer1ClassID,
            Aplayer1Level,
            Aplayer1Str,
            Aplayer1Agi,
            Aplayer1Int,
            Aplayer1Chance);
        intent.putExtra("Player1", player1);
        startActivity(intentToP2Creation);
    }


    public static Character CreateCaracters(int pID, int cla, int lvl, int strg, int agi, int inte, int chance) {
        switch (cla) {
            case 1:
                Log.d("Fight", "CreateCaracters: entered warrior creation condition");
                return new Warrior(lvl, strg, agi, inte, pID, chance);
            case 2:
                Log.d("Fight", "CreateCaracters: entered Marksman creation condition");
                return new Marksman(lvl, strg, agi, inte, pID, chance);
            case 3:
                Log.d("Fight", "CreateCaracters: entered Wizard creation condition");
                return new Wizard(lvl, strg, agi, inte, pID, chance);
            default:
                Log.d("Fight", "CreateCaracters: entered null :/ ");
                return null;
    }


   }
}
