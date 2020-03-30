import javax.swing.*;

public class Missile extends Obstacle {

    public Missile (int larg, int haut, String type, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,type,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("missile.png");
        this.setIcon(icon);
    }

    public boolean collision (){
        boolean res;
        if (chat.getX() + chat.getWidth() < coordX || chat.getX()+chat.getWidth()>= coordX+largeur/2) { //perso pas encore atteint obstacle ou dépassé
            res=false;
        } else {
            //perso dessus/dessous obstacle
            res= (chat.getY() <= coordY + this.hauteur/2) && (chat.getY() + chat.getHeight() >= coordY+this.hauteur/3);
        }
        return res;
    }


    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(6+deltaT) < 0) { // si obstacle sort de l'écran
            this.placementaufond(valeurmax);
        } else
            coordX =coordX- vitesseDefilement * (6+deltaT); // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }
}
