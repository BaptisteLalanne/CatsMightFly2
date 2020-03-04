import javax.swing.*;
import java.awt.*;

public class Monde3 extends Monde {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Icon photo = new ImageIcon(new ImageIcon("Fondmonde3.png").getImage().getScaledInstance(1758,dim.height, Image.SCALE_DEFAULT));
    public Icon photodiffilant= new ImageIcon(new ImageIcon("Fondmonde3deffilant.png").getImage().getScaledInstance(1758,dim.height,Image.SCALE_DEFAULT));

    public Monde3 (){



    }

    public Icon Imageduniveau(){
        return photo;
    }
    public Icon Imageduniveaudiffilant() {
        return photodiffilant;
    }

    public int[] tailleimage() {
        int[] tableau;
        tableau = new int[]{dim.width,0 ,1758,dim.height};
        return tableau;

    }

    public double vitesseChute() {
        return 0;
    }

    public double accelerationChute() {
        return 0;
    }
}
