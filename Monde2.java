/*
 * 2e classe héritant de Monde, niveau avec température du chat
 * */

import javax.swing.*;
import java.awt.*;

public class Monde2 extends Monde {
    private int temp;
    public final JProgressBar thermo;
    public final JLabel decorationthermo;


    public Monde2 (){
        super();
        vitesseDefilement = 2;
        gravite = 13;
        photo = new ImageIcon(new ImageIcon("Fondmonde2.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
        photodefilant= new ImageIcon(new ImageIcon("Fondmonde2defilant.png").getImage().getScaledInstance(3055,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original2.gif");
        chat = new Chat(photochat,dim.height,350,gravite);
        obstacles = new Obstacle[4];
        for (int i=0; i<obstacles.length-1; i++) {
            obstacles[i]=new Epine(118,229,dim.width,dim.height-80, chat);
            obstacles[i].placement(i);
        }
        obstacles[3] = new Souris(75,60,dim.width,dim.height-80,chat);
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
        largeurimage =3055;
    }




/// REDEFINITION METHODE AVEC PRISE EN COMPTE TEMPERATURE
    public boolean collisionobstacles() {
        boolean perdu = super.collisionobstacles();
        deplacementjauge();
        temp = temperature();
        if (temp>1000){
            perdu = true;
        }
        return perdu;
    }

    public int temperature() {
        if (chat.get_vitesse() == 0){
            temp+=2;
        } else if (temp>1) {
            temp-=2;
        } else {
            temp=0;
        }
        return temp;
    }
    
    public void deplacementjauge(){
        thermo.setValue(temp);
        if(temp>800) {
            thermo.setForeground(Color.red);
        } else {
            thermo.setForeground(Color.orange);
        }
    }
}
