/*
 * 2e classe héritant de Obstacle, utilisée dans Monde 2
 * */

import javax.swing.*;

public class Epine extends Obstacle {

    public Epine (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("epines.png");
        this.setIcon(icon);
    }

    public boolean collision (){
        boolean res;
        if (chat.getX()+chat.getWidth() <= coordX || chat.getX()+chat.getWidth() >= coordX+largeur/2) { // Joueur pas encore atteint l'obstacle ou dépassé
            res = false;
        } else {  // Joueur au dessus/au dessous de l'obstacle = false
            res = (chat.getY() <= coordY+hauteur) && (chat.getY()+chat.getHeight() >= coordY);
        }
        return res;
    }

    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(4+deltaT) < 0) { // Si l'obstacle sort de l'écran
            this.placementaufond(valeurmax - 1); // Il faut retirer la place prise par les pièces (souris)
        } else
            coordX =coordX- vitesseDefilement * (4+deltaT); // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }
}
