package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.romain.jeuepicapp.R;

public class MainActivity extends AppCompatActivity {

    public EditText Joueur1;
    public Button PlayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Joueur1 = findViewById(R.id.input_player1);
        PlayButton = findViewById(R.id.activity_main_play_btn);

        PlayButton.setEnabled(false);

        Joueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlayButton.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent = new Intent(MainActivity.this, Main2ActivityPlayer2.class);
                startActivity(newGameIntent);
            }
        });







        //PlayersCreation.init();
    }
}
