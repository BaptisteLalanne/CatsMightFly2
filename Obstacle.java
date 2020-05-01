/*
 * Classe abstraite mère des obstacles et souris
 * */

import javax.swing.*;

public abstract class Obstacle extends JLabel{

    protected final int largeur;
    protected final int hauteur;
    protected int coordX;
    protected int coordY;
    protected final int hauteur_fenetre;
    protected final int largeur_fenetre;
    public final Chat chat;
    protected int vitesseinitialobstacle;

    public Obstacle (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat) {
        largeur = larg;
        hauteur = haut;
        this.chat = chat;
        this.largeur_fenetre = largeur_fenetre;
        this.hauteur_fenetre= hauteur_fenetre;
        this.setBounds(coordX,coordY,larg,haut);
    }


    public abstract boolean collision(); // Méthodes abstraites à redéfinir car chaque obstacle a sa hitbox et vitesse déplacement
    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(vitesseinitialobstacle+deltaT) < 0) { // Si l'obstacle sort de l'écran
            this.placementaufond(valeurmax - 1); // Pour qu'il n'y ait pas d'espace entre missiles, on enlève la place prise par la souris
        } else
            coordX =coordX- vitesseDefilement * (vitesseinitialobstacle+deltaT); // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }
    
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
