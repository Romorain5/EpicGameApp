package com.romain.jeuepicapp;

public class Wizard  extends Character {


    protected Wizard(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    @Override
    public void basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getIntelligence();
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise boule de feu et fait perdre " + damage + " au joueur " + enemy.getNumber());

        } else {
            int damage = this.getIntelligence() * 3;
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise boule de feu en COUP CRITIQUE !!  Et fait perdre " + damage + " au joueur " + enemy.getNumber());

        }


    }




    @Override
    public void specialAttack(Character enemy) {
        int maxHealth = 5 * this.getHealth();
        int heal = (this.getIntelligence() ) * 2;
        this.setHealth(this.getHealth() + heal);
        if (this.getHealth() >= maxHealth ) {
            this.setHealth( maxHealth );
            System.out.println("Le joueur " + this.getNumber() + " utilise Soin et revient à son maximum en points de vie !");
        } else {
            System.out.println("Le joueur " + this.getNumber() + " utilise Soin et récupére " + heal + " points de vie");

        }
    }

    @Override
    public void getClasse() {


        System.out.println("Bonjour je suis un mâge, je suis de niveau " + this.getLevel() + ", j'ai " + this.getStrength() + " en force, " + this.getAgility() + " en agilité et " + this.getIntelligence() + " en intelligence.");

    }





}
