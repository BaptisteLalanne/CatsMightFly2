/*
 * Classe abstraite mère des mondes
 * */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public abstract class Monde {

    protected final Dimension dim;
    protected Obstacle[] obstacles;
    protected Chat chat;
    protected double gravite;
    protected int vitesseDefilement;
    protected int piece;
    protected Icon photochat;
    protected Icon photo;
    protected Icon photodefilant;

    public Monde() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        gravite=9.81;
        vitesseDefilement=1;
        try {
            calculpiece();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public abstract Icon Imageduniveau();
    public abstract Icon Imageduniveaudefilant();
    public abstract  int [] tailleimage();  // Tableau avec dimensions de l'image pour placer les fonds

/// GESTION OBSTACLES ET COLLISIONS
    public void obstacleAvance(int deltaT) {
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i].avanceobstacle(vitesseDefilement,deltaT,i,obstacles.length);
        }
    }
    public boolean collisionobstacles (){
        boolean perdu = false;
        for (Obstacle obstacle : obstacles) {
                if (obstacle.collision()) {
                    if (obstacle instanceof Souris) { // Si le joueur a touché une souris, il gagne une pièce et on place une nouvelle souris
                        piece +=1;
                        int hasard = (int)(2+ Math.random()*3);
                        obstacle.placementaufond(hasard);
                    } else { // Le joueur a touché autre chose qu'une souris
                        perdu = true;
                    }
                }
        }
        return perdu;
    }
    public void eloignerlesobstacles(){ // Visuellement pour ne plus voir l'objet touché (explosé)
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i].placement(i);
        }
    }
    
/// BUFFER READER ET WRITER DES PIECES
    public void calculpiece() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("piece.txt"));
        String texte;
        while ((texte = in.readLine()) != null)
        {
            piece = Integer.parseInt(texte);
        }
        in.close();
    }
    public void updatepiece() throws IOException{
        BufferedWriter out = new BufferedWriter (new FileWriter("piece.txt"));
        out.write(String.valueOf(piece));
        out.close();
    }
}
