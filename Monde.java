import javax.swing.*;
import java.awt.*;
import java.io.*;

public abstract class Monde {

    protected  Dimension dim;
    protected Obstacle[] obstacles;
    protected double gravite;
    protected Chat chat;
    protected int vitesseDefilement;
    protected Icon photo;
    protected int piece;
    
    protected int temp;

    public Monde() {
        gravite=9.81;
        vitesseDefilement=1;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            calculpiece();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public abstract Icon Imageduniveau();
    public abstract Icon Imageduniveaudiffilant();
    public abstract  int [] tailleimage();


    public void obstacleAvance(int deltaT) {
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i].avanceobstacle(vitesseDefilement,deltaT,i,obstacles.length);
        }
    }
    public boolean collisionobstacles (){
        boolean perdu = false;
        for (Obstacle obstacle : obstacles) {
                if (obstacle.collision()) {
                    if (obstacle instanceof Souris) {
                        piece +=1;
                        int hasard = (int)(2+ Math.random()*3);
                        obstacle.placementaufond(hasard);
                    }else{
                        perdu = true;
                    }
                }

        }
        return perdu;
    }// Acceleration du défilement ?


    /// Les méthodes abstraites

    public abstract double vitesseChute();
    public abstract double accelerationChute();

    public void eloignerlesobstacles(){		//Eviter de détecter la collision toutes les millisecondes
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i].placement(i);
        }
    }
    public void calculpiece() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("piece.txt"));
        String texte;
        while ((texte = in.readLine()) != null)
        {
            piece = Integer.parseInt(texte);
        }
        in.close();
    }

    public void updatepiece() throws IOException{ // Pour écrire dans le .txt le nouveau nombre de piece
        BufferedWriter out = new BufferedWriter (new FileWriter("piece.txt"));
        out.write(String.valueOf(piece));
        out.close();
    }
}
