package com.romain.jeuepicapp;

public class Wizard  extends Character {


    public Wizard(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

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
