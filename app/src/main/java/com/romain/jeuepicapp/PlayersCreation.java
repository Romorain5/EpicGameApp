package com.romain.jeuepicapp;

import com.romain.jeuepicapp.activity.MainActivity;

import java.util.Scanner;

public class PlayersCreation {




    public static Character CreateCaracters(int pID, int cla, int lvl, int strg, int agi, int inte, int chance){



        if (cla == 1) {
            return new Warrior(lvl, strg, agi, inte, pID, chance);
        } else if (cla == 2) {
            return new Marksman(lvl, strg, agi, inte, pID, chance);
        } else {
            return new Wizard(lvl, strg, agi, inte, pID, chance);
        }



    }

    public static void init(){


        Character joueur1 = CreateCaracters (1, MainActivity.Aplayer1ClassID, MainActivity.Aplayer1Level, MainActivity.Aplayer1Str, MainActivity.Aplayer1Agi, MainActivity.Aplayer1Int, MainActivity.Aplayer1Chance);
       // joueur1.getClasse(); // informe sur la creation du personnage

        System.out.println("Création du joueur 2");
        Character joueur2 = CreateCaracters(2, MainActivity.Aplayer2ClassID, MainActivity.Aplayer2Level, MainActivity.Aplayer2Str, MainActivity.Aplayer2Agi, MainActivity.Aplayer2Int, MainActivity.Aplayer2Chance);
        joueur2.getClasse(); // informe sur la création du personnage

        int a;
        System.out.println("Le combat commence !");
        while (true) {
            System.out.println("Vie joueur 1 : " + joueur1.getHealth() + " // Vie joueur 2 : " + joueur2.getHealth());
            System.out.println("Au tour du joueur 1 : ");
            System.out.println("");
            System.out.println("Choisissez une attaque: 1- Attaque basique // 2- Attaque spéciale");
            a = 1; // attention j'ai changé ça
            if (a == 1) {
                joueur1.basicAttack(joueur2);
            } else {
                joueur1.specialAttack(joueur2);
            }

            if (joueur1.getHealth() <= 0) {
                System.out.println("Le joueur 1 est mort !");
                break;
            } else if (joueur2.getHealth() <= 0) {
                System.out.println("Le joueur 2 est mort !");
                break;
            }

            System.out.println("Vie joueur 1 : " + joueur1.getHealth() + " // Vie joueur 2 : " + joueur2.getHealth());
            System.out.println("Au tour du joueur 2 : ");
            System.out.println("");
            System.out.println("Choisissez une attaque: 1- Attaque basique // 2- Attaque spéciale");
            a = 1; // Attention j'ai changé ça
            if (a == 1) {
                joueur2.basicAttack(joueur1);
            } else {
                joueur2.specialAttack(joueur1);
            }

            if (joueur1.getHealth() <= 0) {
                System.out.println("Le joueur 1 est mort !");
                break;
            } else if (joueur2.getHealth() <= 0) {
                System.out.println("Le joueur 2 est mort !");
                break;
            }



        }
    }


}
