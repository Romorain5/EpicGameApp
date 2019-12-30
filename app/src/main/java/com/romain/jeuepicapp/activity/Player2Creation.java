package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
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

public class Player2Creation extends AppCompatActivity {


    public  String Aplayer2Name;
    public  int Aplayer2Str;
    public  int Aplayer2Int;
    public  int Aplayer2Agi;
    public  int Aplayer2Chance;
    public  int Aplayer2Level;
    public  int Aplayer2TotStats;
    public  int Aplayer2ClassID;

    private EditText P2Name;
    private RadioGroup P2Class;
    // Joueur 1 Stats
    private EditText P2Level;
    private EditText P2Strength;
    private EditText P2Intel;
    private EditText P2Agility;
    private EditText P2Chance;
    private Button P2TestButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2_creation);

        P2Name = findViewById(R.id.input_player2_name);
        P2Class = findViewById(R.id.class_choice_group2);

        P2Level = findViewById(R.id.level_input2);
        P2Strength = findViewById(R.id.strength_input2);
        P2Intel = findViewById(R.id.intel_input2);
        P2Agility = findViewById(R.id.agility_input2);
        P2Chance = findViewById(R.id.chance_input2);
        P2TestButton = findViewById(R.id.ok_test_stats_btn2);

        P2TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aplayer2Name = P2Name.getText().toString(); //récupére le nom du joueur 1 et le stock dans Aplayer1Name
                // Récupere et stock la force du joueur 1
                String valueS = P2Strength.getText().toString();
                Aplayer2Str = Integer.parseInt(valueS);

                String valueI = P2Intel.getText().toString();
                Aplayer2Int = Integer.parseInt(valueI);

                String valueA = P2Agility.getText().toString();
                Aplayer2Agi = Integer.parseInt(valueA);

                String valueC = P2Chance.getText().toString();
                Aplayer2Chance = Integer.parseInt(valueC);

                String valueL = P2Level.getText().toString();
                Aplayer2Level = Integer.parseInt(valueL);

                Aplayer2TotStats = Aplayer2Agi + Aplayer2Chance + Aplayer2Int + Aplayer2Str;
                if(Aplayer2TotStats > Aplayer2Level || Aplayer2TotStats < 1) {
                    Toast.makeText(Player2Creation.this,"La somme des caractèristiques d'un joueur ne peut pas dépasser son niveau ! ",Toast.LENGTH_LONG).show();
                    P2TestButton.setBackgroundColor(ContextCompat.getColor(Player2Creation.this,R.color.red));
                } else {
                    P2TestButton.setBackgroundColor(ContextCompat.getColor(Player2Creation.this,R.color.green));


                }
            }
        });
    }




    public void checkButton2(View v) {
        int radioId2 = P2Class.getCheckedRadioButtonId();
        switch (radioId2) {
            case R.id.class_choice_warrior2:
                Aplayer2ClassID = 1;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + Aplayer2ClassID);
                break;
            case R.id.class_choice_wizard2:
                Aplayer2ClassID = 2;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + Aplayer2ClassID);

                break;
            case R.id.class_choice_marksman2:
                Aplayer2ClassID = 3;
                Log.d("Fight", "checkButton2: maintenant la classID du joueur 2 est : " + Aplayer2ClassID);

                break;


        }
    }

    public void StartFightActivity(View view) {
        Intent intentb = new Intent(this, FightActivity.class);
        Character player2 = CreateCaracters(2,
                Aplayer2ClassID,
                Aplayer2Level,
                Aplayer2Str,
                Aplayer2Agi,
                Aplayer2Int,
                Aplayer2Chance);
        intentb.putExtra("Player2", player2);
        startActivity(intentb);
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
