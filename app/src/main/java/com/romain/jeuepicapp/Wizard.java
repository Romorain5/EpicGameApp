package com.romain.jeuepicapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Wizard  extends Character implements Parcelable {


    public Wizard(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    protected Wizard(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
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
    public void basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getIntelligence();
            enemy.setHealth(enemy.getHealth()-damage);


        } else {
            int damage = this.getIntelligence() * 3;
            enemy.setHealth(enemy.getHealth()-damage);


        }


    }




    @Override
    public void specialAttack(Character enemy) {
        int maxHealth = 5 * this.getHealth();
        int heal = (this.getIntelligence() ) * 2;
        this.setHealth(this.getHealth() + heal);
        if (this.getHealth() >= maxHealth ) {
            this.setHealth( maxHealth );

        } else {

        }
    }

    @Override
    public void getClasse() {



    }





}
