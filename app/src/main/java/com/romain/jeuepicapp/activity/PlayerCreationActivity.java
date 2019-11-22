package com.romain.jeuepicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.romain.jeuepicapp.PlayersCreation;
import com.romain.jeuepicapp.R;

public class PlayerCreationActivity extends AppCompatActivity {

    private CoordinatorLayout snackbartest;
    private Button buttontest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation);
       // PlayersCreation.init();

              snackbartest = findViewById(R.id.coordinatorLayout);
              buttontest = findViewById(R.id.buttontest);
              showSnackBar();

          }

          public void showSnackBar() {
              Snackbar snackbar = Snackbar.make(snackbartest, "Coucou", Snackbar.LENGTH_INDEFINITE);
              snackbar.show();
          }

    }

    // Comment remplacer le Scanner ?
    // Comment afficher des messages ? Snackbar ou changer un textview à chaque fois ?

