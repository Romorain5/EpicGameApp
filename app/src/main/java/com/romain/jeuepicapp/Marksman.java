package com.romain.jeuepicapp;

import android.os.Parcel;

import com.romain.jeuepicapp.activity.FightActivity;

public class  Marksman extends Character {


    public Marksman(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }


    protected Marksman(Parcel in) {
        super(in);
    }


    public static final Creator<Marksman> CREATOR = new Creator<Marksman>() {
        @Override
        public Marksman createFromParcel(Parcel in) {
            return new Marksman(in);
        }

        @Override
        public Marksman[] newArray(int size) {
            return new Marksman[size];
        }
    };

    @Override
    public void basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getAgility();
            enemy.setHealth(enemy.getHealth()-damage);

            FightActivity.addEventInfo("Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        } else {
            int damage = this.getAgility() * 3;
            enemy.setHealth(enemy.getHealth()-damage);

            FightActivity.addEventInfo(" COUP CRITIQUE !! Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");

        }


    }

    @Override
    public void specialAttack(Character enemy) {
        int addAgility = this.getAgility() / 2;
        this.setAgility(this.getAgility() + addAgility);
        FightActivity.addEventInfo("Le joueur " + getNumber() + " se concentre et gagne " + addAgility + " en agilité !");


    }

    @Override
    public void getClasse() {

    }



}
