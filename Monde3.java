/*
 * 3e classe héritant de Monde, niveau avec obstacles plus difficiles à éviter
 * */

import javax.swing.*;
import java.awt.*;

public class Monde3 extends Monde {

    public Monde3 (){
        super();
        photo = new ImageIcon(new ImageIcon("Fondmonde3.png").getImage().getScaledInstance(2000,dim.height, Image.SCALE_DEFAULT));
        photodefilant= new ImageIcon(new ImageIcon("Fondmonde3deffilant.png").getImage().getScaledInstance(2000,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original3.gif");
        chat = new Chat(photochat,dim.height,350,gravite);
        obstacles = new Obstacle[2];
        obstacles[0]=new TeteChercheuse(450,280,dim.width,dim.height-80, chat);
        obstacles[0].placement(0);
        obstacles[1] = new Souris(75,60,dim.width,dim.height-80,chat);
        obstacles[1].placement(1);
    }

    public Icon Imageduniveau() { return photo; }
    
    public Icon Imageduniveaudefilant() { return photodefilant; }

    public int[] tailleimage() {
        int[] tableau;
        tableau = new int[]{dim.width, 0, 2000, dim.height};
        return tableau;
    }
}
