/*
 * 4e classe héritant de Obstacle, utilisée dans tous les mondes
 * */

import javax.swing.*;

public class Souris extends Obstacle {

    public Souris (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("souris.png");
        this.setIcon(icon);
        vitesseinitialobstacle = 4;
    }

	public boolean collision (){
        boolean res;
        if (chat.getX()+chat.getWidth() <= coordX || chat.getX() >= coordX+largeur) { // Joueur pas encore atteint l'obstacle ou dépassé
            res = false;
        } else {  // Joueur au dessus/au dessous de l'obstacle = false
            res = (chat.getY() <= coordY+hauteur) && (chat.getY()+chat.getHeight() >= coordY);
        }
        return res;
    }

    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(4+deltaT) < 0) { // Si l'obstacle sort de l'écran
            this.placementaufond(valeurmax*2);
        } else
            coordX =coordX- vitesseDefilement * (4+deltaT); // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }
    
/// PLACEMENT SPECIFIQUE A LA SOURIS    
    public void placement(int numeroobstacle) {
        coordX=(1+numeroobstacle)*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-100));
        this.setLocation(coordX,coordY);
    }
}
