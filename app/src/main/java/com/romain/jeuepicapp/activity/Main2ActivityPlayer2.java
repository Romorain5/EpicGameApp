package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.romain.jeuepicapp.R;

import com.romain.jeuepicapp.PlayersCreation;

public class Main2ActivityPlayer2 extends AppCompatActivity {

    private EditText Player2Name;
    private Button   PlayButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2_player2);
       Player2Name = findViewById(R.id.input_player2);
       PlayButton2 = findViewById(R.id.activity_main_play_btn2);

        PlayButton2.setEnabled(false);

        Player2Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PlayButton2.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PlayButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent GoToPlayerCreation = new Intent(Main2ActivityPlayer2.this, PlayerCreationActivity.class);
                startActivity(GoToPlayerCreation);
            }
        });


}
}
