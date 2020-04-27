/*
 * 3e classe héritant de Obstacle, utilisée dans Monde 3
 * */

import javax.swing.*;

public class TeteChercheuse extends Obstacle {

    private double a; // Coefficient de la droite trajectoire de la tête chercheuse

    public TeteChercheuse (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("requin2.png");
        this.setIcon(icon);
    }

    public boolean collision (){
        boolean res;
        if (chat.getX()+chat.getWidth() <= coordX || chat.getX()+chat.getWidth() >= coordX+largeur/2) { // Joueur pas encore atteint l'obstacle ou dépassé
            res = false;
        } else {  // Joueur au dessus/au dessous de l'obstacle = false
            res = (chat.getY() <= coordY+hauteur/2) && (chat.getY()+chat.getHeight() >= coordY+this.hauteur/2);
        }  // une fois la moitié de l'obstacle passé, on estime que le joueur à éviter l'obstacle donc plus de collision (hitbox généreuse)
        return res;
    }

    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax) {
        if (coordX + largeur - vitesseDefilement * (5 + deltaT) < 0) { // Si l'obstacle sort de l'écran
            this.placementaufond(valeurmax - 1); // Il faut retirer la place prise par les pièces (souris)
        } else {
            coordX = coordX - vitesseDefilement * (5 + deltaT);
            coordY = coordY - (int)(Math.round((vitesseDefilement * (5 + deltaT)*a)));
        }
        this.setLocation(coordX,coordY);
    }
    
/// REDEFINITION METHODES AVEC CALCUL TRAJECTOIRE
    public void placement(int numeroobstacle) {
        coordX = (1 + numeroobstacle) * largeur_fenetre + largeur;
        coordY = (int) (Math.random() * (hauteur_fenetre - this.hauteur));
        this.setLocation(coordX, coordY);
        calcultrajectoire(); // Dès que le missile est à droite de l'écran, en fonction de son placement aléatoire et de celui du joueur, il calcule la trajectoire qu'il va suivre
    }

	public void placementaufond(int valeurmax) {
        coordX=valeurmax*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
        calcultrajectoire();
    }

    public void calcultrajectoire(){
        double numerateur = (chat.getY()+80)-(coordY+140);
        double denominateur = (chat.getX()+135)-(coordX+225);
        a = numerateur/denominateur;  // La variable a représente le coefficient de la trajectoire du missile f(x)=a*x+b
    }
}
