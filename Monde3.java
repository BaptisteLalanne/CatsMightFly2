import javax.swing.*;
import java.awt.*;

public class Monde3 extends Monde {



    public Monde3 (){
        super();
        photo = new ImageIcon(new ImageIcon("Fondmonde3.png").getImage().getScaledInstance(2000,dim.height, Image.SCALE_DEFAULT));
        photodiffilant= new ImageIcon(new ImageIcon("Fondmonde3deffilant.png").getImage().getScaledInstance(2000,dim.height,Image.SCALE_DEFAULT));
        photochat = new ImageIcon("original3.gif");
        obstacles = new Obstacle[2];
        chat = new Chat(photochat,dim.height,350,gravite);
        obstacles[0]=new TeteChercheuse(450,280,"Tetechercheuse",dim.width,dim.height-80, chat);
        obstacles[0].placement(0);
        obstacles[1] = new Souris(75,60,"souris",dim.width,dim.height-80,chat);
        obstacles[1].placement(1);
    }

    public Icon Imageduniveau(){
        return photo;
    }
    public Icon Imageduniveaudiffilant() {
        return photodiffilant;
    }

    public int[] tailleimage() {
        int[] tableau;
        tableau = new int[]{dim.width, 0, 2000, dim.height};
        return tableau;

    }
}
