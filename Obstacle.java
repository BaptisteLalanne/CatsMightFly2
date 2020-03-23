import javax.swing.*;


public class Obstacle extends JLabel{

    private int largeur;
    private int hauteur;
    private int coordX;
    private int coordY;
    public int hauteur_fenetre;
    public int largeur_fenetre;
    public Chat chat;

    public Obstacle (int larg, int haut, String type, int largeur_fenetre, int hauteur_fenetre, Chat chat) {
        largeur = larg;
        hauteur = haut;
        this.chat = chat;
        this.largeur_fenetre = largeur_fenetre;
        this.hauteur_fenetre= hauteur_fenetre;
        Icon icon = new ImageIcon("missile.png");
        this.setIcon(icon);
        this.setBounds(coordX,coordY,larg,haut);
    }

    public int get_coordX() { return coordX; }
    public int get_largeur() { return largeur; }
    public void set_coordX(int valeur){
        coordX=valeur;
    }

    /// Attributs à adapter au reste du code


    /// Placement aléatoire des obstacles (extremité droite de l'écran)

    public void placement(int numeroobstacle) {
        coordX=(1+numeroobstacle)*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
    } // Faisabilité du parcours ?

    /// Parcourir le tableau d'obstacles à tout instant pr chercher collision

    public boolean collision() {
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
            if (coordX + largeur - vitesseDefilement*deltaT < 0) { // si obstacle sort de l'écran
                this.placementaufond(valeurmax);
            } else
                coordX =coordX- vitesseDefilement * deltaT; // deltaT le délai entre chaque appel à la méthode
        this.setLocation(coordX,coordY);
    }

    public void placementaufond(int valeurmax) {
        coordX=valeurmax*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
    }
}
