package com.romain.jeuepicapp;


import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {

    private int level;
    private int health;
    private int strength;
    private  int agility;
    private int intelligence;
    private int number;
    private int luck;


    protected Character (int level, int strength, int agility, int intelligence, int number, int luck) {
        this.level = level;
        this.health = this.level * 5;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.number = number;
        this.luck = luck;
    }

    public abstract void basicAttack(Character enemy);
    public abstract void specialAttack(Character enemy);
    public abstract void getClasse();


    public boolean isCritical() {
        boolean isCrit = false;
        int randomNumTarget = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            randomNumTarget = ThreadLocalRandom.current().nextInt(0, 100);
        }
        for (int i = 0; i != this.luck && !isCrit; i++) {
            int randomNum = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                randomNum = ThreadLocalRandom.current().nextInt(0, 100);
            }
            if (randomNum == randomNumTarget) {
                isCrit = true;
            }
        }
        return isCrit;
    }

    public int getLevel() {
        return level;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }
}
