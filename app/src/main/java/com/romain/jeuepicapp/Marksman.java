package com.romain.jeuepicapp;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class  Marksman extends Character implements Parcelable {


    public Marksman(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }


    protected Marksman(Parcel in) {
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


        } else {
            int damage = this.getAgility() * 3;
            enemy.setHealth(enemy.getHealth()-damage);

        }


    }

    @Override
    public void specialAttack(Character enemy) {
        int addAgility = this.getAgility() / 2;
        this.setAgility(this.getAgility() + addAgility);


    }

    @Override
    public void getClasse() {

    }


}
