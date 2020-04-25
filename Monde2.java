import javax.swing.*;
import java.awt.*;

public class Monde2 extends Monde {
    public int temp;
    public final JProgressBar thermo;
    public final JLabel decorationthermo;


    public Monde2 (){
        super();
        photo = new ImageIcon(new ImageIcon("Fondmonde2.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
        photodiffilant= new ImageIcon(new ImageIcon("Fondmonde2deffilant.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original2.gif");
        vitesseDefilement=2;
        gravite= 13;
        obstacles = new Obstacle[4];
        chat = new Chat(photochat,dim.height,350,gravite);
        for (int i=0; i<obstacles.length-1; i++) {
            obstacles[i]=new Epine(118,229,"feu",dim.width,dim.height-80, chat);
            obstacles[i].placement(i);
        }
        obstacles[3] = new Souris(75,60,"souris",dim.width,dim.height-80,chat);
        obstacles[3].placement(3);
        temp=0;
        int minimum = 0;
        int maximum = 1000;
        thermo = new JProgressBar(JProgressBar.VERTICAL,minimum, maximum);
        thermo.setBounds(dim.width-100,20,50,200);
        thermo.setStringPainted(true);
        thermo.setForeground(Color.orange);
        decorationthermo = new JLabel();
        Icon deco = new ImageIcon("thermo.png");
        decorationthermo.setIcon(deco);
        decorationthermo.setBounds(dim.width-110,0,68,250);

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
        deplacementjauge();
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
            temp+=2;
        } else if (temp>1) {
            temp-=2; // Energie thermique = m*Cp*dT, flux thermique = E/temps
        } else {
            temp=0;
        }
        System.out.println(temp);
        return temp;
    }
    public void deplacementjauge(){
        thermo.setValue(temp);
        if(temp>800){
            thermo.setForeground(Color.red);
        }else{
            thermo.setForeground(Color.orange);
        }
    }
}
