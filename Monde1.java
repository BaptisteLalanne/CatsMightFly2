/*
 * 1e classe héritant de Monde, niveau basique
 * */

import javax.swing.*;
import java.awt.*;

public class Monde1 extends Monde {

    public Monde1() {
        super();
        photo = new ImageIcon(new ImageIcon("Fondmonde1.png").getImage().getScaledInstance(2158,dim.height, Image.SCALE_DEFAULT));
        photodefilant= new ImageIcon(new ImageIcon("Fondmonde1defilant.png").getImage().getScaledInstance(2158,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original.gif");
        chat = new Chat(photochat,dim.height,400,gravite);
        obstacles = new Obstacle[4];
        for (int i=0; i<obstacles.length-1; i++) {
            obstacles[i]=new Missile(400,400,dim.width,dim.height-80, chat);
            obstacles[i].placement(i);
        }
        obstacles[3] = new Souris(75,60,dim.width,dim.height-80,chat);
        obstacles[3].placement(3);
        largeurimage =2158;
    }
}
