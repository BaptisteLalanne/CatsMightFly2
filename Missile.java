/*
 * 1e classe héritant de Obstacle, utilisée dans Monde 1
 * */

import javax.swing.*;

public class Missile extends Obstacle {

    public Missile (int larg, int haut, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("missile.png");
        this.setIcon(icon);
        vitesseinitialobstacle = 6;
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
}
