import javax.swing.*;
import java.awt.*;

public abstract class Monde {

    protected  Dimension dim;
    protected Obstacle[] obstacles;
    protected double gravite;
    protected Chat chat;
    protected int vitesseDefilement;
    protected Icon photo;

    public Monde(){
        gravite=9.81;
        vitesseDefilement=1;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
    }
    public abstract Icon Imageduniveau();
    public abstract Icon Imageduniveaudiffilant();
    public abstract  int [] tailleimage();


    public void obstacleAvance(int deltaT) {
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i].avanceobstacle(vitesseDefilement,deltaT,i,obstacles.length);
        }
    } // Acceleration du défilement ?


    /// Les méthodes abstraites

    public abstract double vitesseChute();
    public abstract double accelerationChute();

}
