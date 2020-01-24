package com.romain.jeuepicapp;

import android.os.Parcel;
import android.util.Log;

import com.romain.jeuepicapp.activity.FightActivity;

public class Wizard  extends Character {


    public Wizard(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    protected Wizard(Parcel in) {
        super(in);
    }


    public static final Creator<Wizard> CREATOR = new Creator<Wizard>() {
        @Override
        public Wizard createFromParcel(Parcel in) {
            return new Wizard(in);
        }

        @Override
        public Wizard[] newArray(int size) {
            return new Wizard[size];
        }
    };

    @Override
    public String basicAttack(Character enemy) {

        Log.d("Fight", "basicAttack: entree dans basic attack du mage avant le IF");
        if (!isCritical()) {
            Log.d("Fight", "basicAttack: entree dans basic attack du mage dans si pas de CC");
            int damage = this.getIntelligence();
            Log.d("Fight", "basicAttack: entree dans basic attack et les dmg seront de : " + damage);
            enemy.setHealth(enemy.getHealth()-damage);

            return ("Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        } else {
            Log.d("Fight", "basicAttack: entree dans basic attack du mage dans si il y a CC");
            int damage = this.getIntelligence() * 3;
            enemy.setHealth(enemy.getHealth()-damage);

            return ("COUP CRITIQUE !! Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        }


    }




    @Override
    public String specialAttack(Character enemy) {
        int maxHealth = 5 * this.getLevel();
        int heal = (this.getIntelligence() ) * 2;
        this.setHealth(this.getHealth() + heal);

        if (this.getHealth() >= maxHealth ) {
            this.setHealth( maxHealth );

        }
        return ( "Le joueur " + getNumber() + " se soigne et gagne " + heal + " points de vie !" );
    }

    @Override
    public int getClasse() {


        return 2;
    }





}
