import javax.swing.*;

public class TeteChercheuse extends Obstacle {

    private double a; // Coefficient de la trajectoire de la tête chercheuse

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
            res= (chat.getY() <= coordY + this.hauteur/2) && (chat.getY() + chat.getHeight() >= coordY+this.hauteur/2);
        } // une fois la moitié de l'obstacle passé, on estime que le joueur à éviter l'obstacle donc plus de collision (hitbox généreuse)
        return res;
    }


    public void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax) {
        if (coordX + largeur - vitesseDefilement * (5 + deltaT) < 0) { // si obstacle sort de l'écran
            this.placementaufond(valeurmax - 1);
        } else {
            coordX = coordX - vitesseDefilement * (5 + deltaT);
            coordY = coordY - (int)(Math.round((vitesseDefilement * (5 + deltaT)*a)));
        }
        this.setLocation(coordX,coordY);

    }

    public void placementaufond(int valeurmax) { // Renvoie le missile à droite
        coordX=valeurmax*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
        calcultrajectoire();// des que le missile est à droite de l'écran, en fonction de son placement aléatoire et de celui du joueur, il calcul la trajectoire qu'il va suivre
    }
    public void placement(int numeroobstacle) { // Appeler à la création du niveau pour placer la souris
        coordX = (1 + numeroobstacle) * largeur_fenetre + largeur;
        coordY = (int) (Math.random() * (hauteur_fenetre - this.hauteur));
        this.setLocation(coordX, coordY);
        calcultrajectoire();
    }

    public void calcultrajectoire(){
        double numerateur = (chat.getY()+80)-(coordY+140);
        double denominateur = (chat.getX()+135)-(coordX+225);
        a = numerateur/denominateur;  // La variable a représente le coefficient de la trajectoire du missile f(x)=a*x+b

    }


}
