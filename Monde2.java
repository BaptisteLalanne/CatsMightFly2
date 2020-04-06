import javax.swing.*;
import java.awt.*;

public class Monde2 extends Monde {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Icon photo = new ImageIcon(new ImageIcon("Fondmonde2.png").getImage().getScaledInstance(3049,dim.height,Image.SCALE_DEFAULT));
    public Icon photodiffilant= new ImageIcon(new ImageIcon("Fondmonde2deffilant.png").getImage().getScaledInstance(3049,dim.height,Image.SCALE_DEFAULT));

    public Monde2 (){



    }


    public Icon Imageduniveau(){
        return photo;
    }

    public Icon Imageduniveaudiffilant() {
        return photodiffilant;
    }

    public int [] tailleimage(){
        int[] tableau;
        tableau = new int[]{dim.width,0 ,3049,dim.height};
        return tableau;
    }

    public double vitesseChute() {
        return 0;
    }

    public double accelerationChute() {
        return 0;
    }
}
