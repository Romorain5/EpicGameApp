package com.romain.jeuepicapp;

public class Warrior extends Character {
    public Warrior(int level, int strength, int agility, int intelligence, int number, int luck) {
        super(level, strength, agility, intelligence, number, luck);
    }

    @Override
    public void basicAttack(Character enemy) {
        if (!isCritical()) {
            int damage = this.getStrength();
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise coup d'épée et fait perdre " + damage + " au joueur " + enemy.getNumber());

        } else {
            int damage = this.getStrength() * 3;
            enemy.setHealth(enemy.getHealth()-damage);
            System.out.println("Le joueur " + this.getNumber() + " utilise coup d'épée en COUP CRITIQUE !!  Et fait perdre " + damage + " au joueur " + enemy.getNumber());

        }


    }

    @Override
    public void specialAttack(Character enemy) {
        int damage = 2 * this.getStrength() ;
        int loss = this.getStrength() / 2;
        enemy.setHealth(enemy.getHealth() - damage);
        this.setHealth(this.getHealth() - loss);
        System.out.println("Le joueur " + this.getNumber() + " utilise coup de rage et fait perdre " + damage + "  au joueur " + enemy.getNumber() );
        System.out.println("Le joueur " + this.getNumber() + " perd " + loss + " dans son excès de colère");
    }

    @Override
    public void getClasse() {
        System.out.println("Bonjour je suis un guerrier, je suis de niveau " + this.getLevel() + ", j'ai " + this.getStrength() + " en force, " + this.getAgility() + " en agilité et " + this.getIntelligence() + " en intelligence.");

    }


}
