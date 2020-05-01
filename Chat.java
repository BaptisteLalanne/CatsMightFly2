/*
 * JLabel du chat (modélisation gravité)
 * */

import javax.swing.*;

public class Chat extends JLabel {
    private int coordy;
    private final int coordx=100;
    private final int hauteur;
    private final int ybasduniveau;
    private final double gravite;
    private double vitesse;

    public Chat (Icon photochat, int hauteur, int ybasduniveau, double gravite){
        this.ybasduniveau = ybasduniveau;
        this.gravite = gravite;
        this.setIcon(photochat);
        this.hauteur=hauteur;
        this.coordy=hauteur/2;
        this.setBounds(coordx,coordy,270,160);
    }
    
    public double get_vitesse() { return vitesse; }

    public void deplace(int choix) {
        if(coordy <= 0){
            vitesse = 0;
        }else{
            if(choix == 1){  // Choix 1 = appui court sur la barre d'espace
                vitesse -= 2;
            }else if (choix ==2){  // Choix 2 = appui prolongé
                vitesse -= 1;
            }else{
                vitesse -= 3; // Souris
            }
        }
        if (vitesse > 4){  // Vitesse max
            vitesse = 4;
        }
        deplacement();  // Appel de la méthode pour afficher le déplacement
    }
    
    public void chute() {  // Appelé tous les deltaT du Timer pour faire tomber le joueur en fonction de la gravité
        if(coordy+ybasduniveau >= hauteur){
            vitesse = 0;
        }else{
            vitesse += gravite*0.2/10;
        }
        deplacement();
    }
    
	public void deplacement() {
		coordy+=vitesse;
		if(coordy <= 0 && vitesse <= 0 ){
			coordy = 0;
			vitesse = 0;
		}
		this.setLocation(coordx,coordy);
	}
}
