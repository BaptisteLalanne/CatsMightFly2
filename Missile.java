/*
 * 1e classe héritant de Obstacle, utilisée dans Monde 1
 * */

import javax.swing.*;

public class Missile extends Obstacle {

    public Missile (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("missile.png");
        this.setIcon(icon);
    }

    public boolean collision (){
        boolean res;
        if (chat.getX()+chat.getWidth() <= coordX || chat.getX()+chat.getWidth() >= coordX+largeur/2) { // Joueur pas encore atteint l'obstacle ou dépassé
            res = false;
        } else {  // Joueur au dessus/au dessous de l'obstacle = false
            res = (chat.getY() <= coordY+hauteur/2) && (chat.getY()+chat.getHeight() >= coordY+this.hauteur/3);
        }
        return res;
    }

    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(6+deltaT) < 0) { // Si l'obstacle sort de l'écran
            this.placementaufond(valeurmax - 1); // Pour qu'il n'y ait pas d'espace entre missiles, on enlève la place prise par la souris
        } else
            coordX =coordX- vitesseDefilement * (6+deltaT); // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }
}
