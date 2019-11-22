package com.romain.jeuepicapp;

import java.util.Scanner;

public class PlayersCreation {

    public static Character CreateCaracters(int nb){
        int classe = 0;
        int level = -1;
        int strength = 0;
        int agility = 0;
        int intelligence = 0;
        int luck = 0;

        Scanner scan = new Scanner( System.in);

        System.out.println("Joueur " + nb + ", veuillez choisir la classe de votre personnage : 1- Guerrier  2- Rôdeur  3- Mage");
        classe = scan.nextInt();


        while ( level != (  strength + agility + intelligence + luck )  ) {
            System.out.println("Veuillez saisir les caractéristique de votre personnge, attention la somme des statistiques ne peut pas dépasser le niveau de votre personnage !");
            System.out.println("Niveau du personnage ?");
            level = scan.nextInt();
            System.out.println("Force du personnage ?");
            strength = scan.nextInt();
            System.out.println("Agilité du personnage ?");
            agility = scan.nextInt();
            System.out.println("Intelligence du personnage ?");
            intelligence = scan.nextInt();
            System.out.println("Chance du personnage ?");
            luck = scan.nextInt();
        }

        if (classe == 1) {
            return new Warrior(level, strength, agility, intelligence, nb, luck);
        } else if (classe == 2) {
            return new Marksman(level, strength, agility, intelligence, nb, luck);
        } else {
            return new Wizard(level, strength, agility, intelligence, nb, luck);
        }



    }

    public static void init(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Création du joueur 1");
        Character joueur1 = CreateCaracters(1);
        joueur1.getClasse(); // informe sur la creation du personnage

        System.out.println("Création du joueur 2");
        Character joueur2 = CreateCaracters(2);
        joueur2.getClasse(); // informe sur la création du personnage

        int a;
        System.out.println("Le combat commence !");
        while (true) {                                                                                                      // optimiser le combat
            System.out.println("Vie joueur 1 : " + joueur1.getHealth() + " // Vie joueur 2 : " + joueur2.getHealth());
            System.out.println("Au tour du joueur 1 : ");
            System.out.println("");
            System.out.println("Choisissez une attaque: 1- Attaque basique // 2- Attaque spéciale");
            a = scan.nextInt();
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
            a = scan.nextInt();
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
