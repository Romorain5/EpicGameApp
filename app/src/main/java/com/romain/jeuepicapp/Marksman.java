package com.romain.jeuepicapp;

public class Marksman extends Character {


    protected Marksman(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    @Override
    public void basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getAgility();
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise son arc et fait perdre " + damage + " au joueur " + enemy.getNumber());

        } else {
            int damage = this.getAgility() * 3;
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise son arc en COUP CRITIQUE !!  Et fait perdre " + damage + " au joueur " + enemy.getNumber());

        }


    }

    @Override
    public void specialAttack(Character enemy) {
        int addAgility = this.getAgility() / 2;
        this.setAgility(this.getAgility() + addAgility);
        System.out.println("Le joueur " + this.getNumber() + " utilise concentration et gagne " + addAgility + " en agilité");


    }

    @Override
    public void getClasse() {
        System.out.println("Bonjour je suis un rodeur, je suis de niveau " + this.getLevel() + ", j'ai " + this.getStrength() + " en force, " + this.getAgility() + " en agilité et " + this.getIntelligence() + " en intelligence.");

    }


}
