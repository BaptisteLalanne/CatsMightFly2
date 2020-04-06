import javax.swing.*;
import java.awt.*;

public class TeteChercheuse extends Obstacle {
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public TeteChercheuse (int larg, int haut, String type, int largeur_fenetre, int hauteur_fenetre, Chat chat){
        super(larg,haut,type,largeur_fenetre,hauteur_fenetre,chat);
        Icon icon = new ImageIcon("requin2.png");
        this.setIcon(icon);
    }

    public boolean collision (){
        boolean res;
        if (chat.getX() + chat.getWidth() < coordX || chat.getX()+chat.getWidth()>= coordX+largeur/2) { //perso pas encore atteint obstacle ou dépassé
            res=false;
        } else {
            //perso dessus/dessous obstacle
            res= (chat.getY() <= coordY + this.hauteur) && (chat.getY() + chat.getHeight() >= coordY);
        }
        return res;
    }


    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax){
        if (coordX + largeur - vitesseDefilement*(4+deltaT) < 0) { // si obstacle sort de l'écran
            this.placementaufond(valeurmax);
        } else
            coordX = coordX - vitesseDefilement*(4+deltaT); // deltaT le délai entre chaque appel à la méthode
            
            if(coordX<dim.width){
                if (coordY<dim.height/2){
                    coordY = coordY + chat.getY()/200;
                }
                if(coordY>dim.height/2){
                    coordY = coordY - chat.getY()/200;  
                }
            }
        this.setLocation(coordX,coordY);
    }
    
   
}