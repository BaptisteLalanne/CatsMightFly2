import javax.swing.*;
import java.awt.*;

public class Monde2 extends Monde {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Icon photo = new ImageIcon(new ImageIcon("Fondmonde2.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
    public Icon photodiffilant= new ImageIcon(new ImageIcon("Fondmonde2deffilant.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
    public Icon photochat = new ImageIcon("original2.gif");
    
    public int temp;

    public Monde2 (){
        super();
        vitesseDefilement=2;
        gravite= 16;
        obstacles = new Obstacle[3];
        chat = new Chat(photochat,dim.height,350,gravite);
        for (int i=0; i<obstacles.length; i++) {
            obstacles[i]=new Epine(118,229,"feu",dim.width,dim.height-80, chat);
            obstacles[i].placement(i);
        }
        temp=0;

    }


    public Icon Imageduniveau(){
        return photo;
    }

    public Icon Imageduniveaudiffilant() {
        return photodiffilant;
    }

    public int [] tailleimage(){
        int[] tableau;
        tableau = new int[]{dim.width,0 ,3055,dim.height};
        return tableau;
    }
    
    public boolean collisionobstacles (){
        boolean perdu = false;
        temp = temperature();
        if (temp>1000){
			perdu = true;
		}
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
    }
    
    public int temperature() {
		if (chat.get_vitesse() == 0){
			temp+=1;
		} else if (temp>0) {
			temp-=2; // Energie thermique = m*Cp*dT, flux thermique = E/temps ?
			} else {
				temp=0;
				System.out.println("coucou");
			}
		return temp;
	}

    public double vitesseChute() {
        return 0;
    }

    public double accelerationChute() {
        return 0;
    }
}
