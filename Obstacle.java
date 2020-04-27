/*
 * Classe abstraite mère des obstacles et souris
 * */

import javax.swing.*;

public abstract class Obstacle extends JLabel{

    protected final int largeur;
    protected final int hauteur;
    protected int coordX;
    protected int coordY;
    public final int hauteur_fenetre;
    public final int largeur_fenetre;
    public final Chat chat;

    public Obstacle (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat) {
        largeur = larg;
        hauteur = haut;
        this.chat = chat;
        this.largeur_fenetre = largeur_fenetre;
        this.hauteur_fenetre= hauteur_fenetre;
        this.setBounds(coordX,coordY,larg,haut);
    }


    public abstract boolean collision(); // Méthodes abstraites à redéfinir car chaque obstacle a sa hitbox et vitesse déplacement
    public abstract void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax);
    
    public void placement(int numeroobstacle) {  // Placement aléatoire des obstacles (extremité droite de l'écran)
        coordX=(1+numeroobstacle)*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
    }

    public void placementaufond(int valeurmax) {  // Replacement des obstacles à droite une fois sortis de l'écran
        coordX=valeurmax*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
    }
}
