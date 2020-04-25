import javax.swing.*;
import java.awt.*;

public class Monde1 extends Monde {

    public Monde1 (){
        super();
        photo = new ImageIcon(new ImageIcon("Fondmonde1v2.png").getImage().getScaledInstance(2158,dim.height, Image.SCALE_DEFAULT));
        photodiffilant= new ImageIcon(new ImageIcon("Fondmonde1deffilantv2.png").getImage().getScaledInstance(2158,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original.gif");
        obstacles = new Obstacle[4];
        chat = new Chat(photochat,dim.height,400,gravite);
        for (int i=0; i<obstacles.length-1; i++) {
            obstacles[i]=new Missile(400,400,"missile",dim.width,dim.height-80, chat);
            obstacles[i].placement(i);
        }
        obstacles[3] = new Souris(75,60,"souris",dim.width,dim.height-80,chat);
        obstacles[3].placement(3);


    }

    public Icon Imageduniveau(){
        return photo;
    }

    public Icon Imageduniveaudiffilant() {
        return photodiffilant;
    }

    public int[] tailleimage() {
        int[] tableau;
        tableau = new int[]{dim.width,0 ,2158,dim.height};
        return tableau;

    }
}
