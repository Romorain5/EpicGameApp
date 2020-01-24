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

    public int getClassID(){
        return 3;
    }

    @Override
    public String basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getAgility();
            enemy.setHealth(enemy.getHealth()-damage);

            return ("Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");

            //FightActivity.addEventInfo("Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");


        } else {
            int damage = this.getAgility() * 3;
            enemy.setHealth(enemy.getHealth()-damage);

            return ("COUP CRITIQUE !! Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");

            //FightActivity.addEventInfo(" COUP CRITIQUE !! Le joueur " + getNumber() + " attaque et inflige " + damage + " points de dégats ! ");

        }


    }

    @Override
    public String specialAttack(Character enemy) {
        int addAgility = this.getAgility() / 2;
        this.setAgility(this.getAgility() + addAgility);
        return ( "Le joueur " + getNumber() + " se concentre et gagne " + addAgility + " en agilité !" );



    }

    @Override
    public int getClasse() {

        return 3;

    }



}
