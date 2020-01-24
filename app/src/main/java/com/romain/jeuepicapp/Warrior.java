package com.romain.jeuepicapp;

import android.os.Parcel;
import android.util.Log;

import com.romain.jeuepicapp.activity.FightActivity;

public class Warrior extends Character {
    public Warrior(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    protected Warrior(Parcel in) {
        super(in);
    }

    public static final Creator<Warrior> CREATOR = new Creator<Warrior>() {
        @Override
        public Warrior createFromParcel(Parcel in) {
            return new Warrior(in);
        }

        @Override
        public Warrior[] newArray(int size) {
            return new Warrior[size];
        }
    };

    @Override
    public String basicAttack(Character enemy) {
        if (!isCritical()) {
            Log.d("Fight", "basicAttack:  entered in warrior basic attack" );
            int damage = this.getStrength();
            Log.d("Fight", "basicAttack: la force est égal à " + this.getStrength() + " donc les degats subits seront de : " + damage);
            enemy.setHealth(enemy.getHealth()-damage);
            return ("Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        } else {
            int damage = this.getStrength() * 3;
            enemy.setHealth(enemy.getHealth()-damage);
            return ("COUP CRITIQUE !! Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        }


    }

    @Override
    public String specialAttack(Character enemy) {
        int damage = 2 * this.getStrength() ;
        int loss = this.getStrength() / 2;
        enemy.setHealth(enemy.getHealth() - damage);
        this.setHealth(this.getHealth() - loss);

        return ( "Le joueur " + getNumber() + "utilise son attaque spéciale et inflige " + damage + " et se blesse  ( - " + loss + " ) !"   );


    }

    @Override
    public int getClasse() {
        return 1;
    }
}
