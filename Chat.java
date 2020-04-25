import javax.swing.*;


public class Chat extends JLabel {
    private final int coordx=100;
    private int coordy;
    public double vitesse;
    private final int hauteur;
    private final int ybasduniveau;
    private final double gravite;
    public int piece;

    public Chat (Icon photochat,int hauteur, int ybasduniveau, double gravite){
        this.ybasduniveau = ybasduniveau;
        this.gravite = gravite;
        this.setIcon(photochat);
        this.hauteur=hauteur;
        this.coordy=hauteur/2;
        this.setBounds(coordx,coordy,270,160);
    }

    public void deplace(int choix){
        if(coordy <= 0){
            vitesse = 0;
        }else{
            if(choix == 1){ // Choix 1 = appuie court sur la barre d'espace
                vitesse -= 2;
            }else{ // Choix 2 = appuie prolongé
                vitesse -= 1;
            }
        }
        if (vitesse >4){ // Vitesse max
            vitesse = 4;
        }
        deplacement(); // Appel de la méthode pour afficher le déplacement
    }
    public double get_vitesse() { return vitesse; }

    public void chute(){   // Appelé tous les deltaT du Timer pour faire tomber le joueur en fonction de la gravité
        if(coordy+ybasduniveau >= hauteur){
            vitesse = 0;
        }else{
            vitesse += gravite*0.2/10;
        }
        deplacement();
    }

    public void deplacement(){
        coordy+=vitesse;
        if(coordy <= 0 && vitesse <= 0 ){
            coordy=0;
            vitesse =0;
        }
        this.setLocation(coordx,coordy);
    }


}