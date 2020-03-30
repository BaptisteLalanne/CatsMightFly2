import javax.swing.*;


public abstract class Obstacle extends JLabel{

    protected int largeur;
    protected int hauteur;
    protected int coordX;
    protected int coordY;
    public int hauteur_fenetre;
    public int largeur_fenetre;
    public Chat chat;

    public Obstacle (int larg, int haut, String type, int largeur_fenetre, int hauteur_fenetre, Chat chat) {
        largeur = larg;
        hauteur = haut;
        this.chat = chat;
        this.largeur_fenetre = largeur_fenetre;
        this.hauteur_fenetre= hauteur_fenetre;
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

    public abstract boolean collision();
    public abstract void avanceobstacle(int vitesseDefilement, int deltaT,int valeur,int valeurmax);

    public void placementaufond(int valeurmax) {
        coordX=valeurmax*largeur_fenetre+largeur;
        coordY=(int)(Math.random()*(hauteur_fenetre-this.hauteur));
        this.setLocation(coordX,coordY);
    }
}
