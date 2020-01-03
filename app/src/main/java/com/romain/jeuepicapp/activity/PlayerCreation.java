package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.romain.jeuepicapp.Character;
import com.romain.jeuepicapp.Marksman;
import com.romain.jeuepicapp.R;
import com.romain.jeuepicapp.Warrior;
import com.romain.jeuepicapp.Wizard;

import java.util.Objects;

//TODO comme l'activitée de google ( une activité pour la création des deux joueurs )

// TODO nom des methode et attributs en camelCase

public class PlayerCreation extends AppCompatActivity {


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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation);

        p2Name = findViewById(R.id.input_player2_name);
        p2Class = findViewById(R.id.class_choice_group2);

        p2Level = findViewById(R.id.level_input2);
        p2Strength = findViewById(R.id.strength_input2);
        p2Intel = findViewById(R.id.intel_input2);
        p2Agility = findViewById(R.id.agility_input2);
        p2Chance = findViewById(R.id.chance_input2);
        p2TestButton = findViewById(R.id.ok_test_stats_btn2);

        p2TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aplayer2Name = p2Name.getText().toString(); //récupére le nom du joueur 1 et le stock dans Aplayer1Name
                // Récupere et stock la force du joueur 1
                String valueS = p2Strength.getText().toString();
                aplayer2Str = Integer.parseInt(valueS);

                String valueI = p2Intel.getText().toString();
                aplayer2Int = Integer.parseInt(valueI);

                String valueA = p2Agility.getText().toString();
                aplayer2Agi = Integer.parseInt(valueA);

                String valueC = p2Chance.getText().toString();
                aplayer2Chance = Integer.parseInt(valueC);

                String valueL = p2Level.getText().toString();
                aplayer2Level = Integer.parseInt(valueL);

                aplayer2TotStats = aplayer2Agi + aplayer2Chance + aplayer2Int + aplayer2Str;
                if(aplayer2TotStats > aplayer2Level || aplayer2TotStats < 1) {
                    Toast.makeText(PlayerCreation.this,"La somme des caractèristiques d'un joueur ne peut pas dépasser son niveau ! ",Toast.LENGTH_LONG).show();
                    p2TestButton.setBackgroundColor(ContextCompat.getColor(PlayerCreation.this,R.color.red));
                } else {
                    p2TestButton.setBackgroundColor(ContextCompat.getColor(PlayerCreation.this,R.color.green));


                }
            }
        });
    }




    public void checkButton2(View v) {
        int radioId2 = p2Class.getCheckedRadioButtonId();
        switch (radioId2) {
            case R.id.class_choice_warrior2:
                aplayer2ClassID = 1;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + aplayer2ClassID);
                break;
            case R.id.class_choice_wizard2:
                aplayer2ClassID = 2;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + aplayer2ClassID);
                break;
            case R.id.class_choice_marksman2:
                aplayer2ClassID = 3;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + aplayer2ClassID);
                break;

        }
    }

    public void createCharacter(View view) {

        Intent intentToP1Creation = getIntent();
        int playerID = intentToP1Creation.getExtras().getInt("Player");

        //Intent intentb = new Intent(this, FightActivity.class);
        Intent replyIntent = new Intent();
        if (playerID == 1) {
            Character player1 = CreateCaracters(1,
                    aplayer2ClassID,
                    aplayer2Level,
                    aplayer2Str,
                    aplayer2Agi,
                    aplayer2Int,
                    aplayer2Chance);
            replyIntent.putExtra("Player11", player1);
            setResult(RESULT_OK,replyIntent);
            finish();

        } else if (playerID == 2) {
            Character player2 = CreateCaracters(1,
                    aplayer2ClassID,
                    aplayer2Level,
                    aplayer2Str,
                    aplayer2Agi,
                    aplayer2Int,
                    aplayer2Chance);
            replyIntent.putExtra("Player22", player2);
            setResult(RESULT_OK,replyIntent);
            finish();
        }




        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        // intentb.putExtra("Player1", Objects.requireNonNull(getIntent().getExtras()).getParcelable("Player1"));
        //}
        //startActivity(intentb);
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
}
