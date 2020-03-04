import javax.swing.*;
import java.awt.*;


public class Regles extends JPanel {
    public Dimension dim;
    public Bouton retour;

    public Regles(){
        this.setLayout(null);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        retour = new Bouton (new ImageIcon("retour.png"));
        retour.setBounds(0, 0, 300, 200);
        JLabel rules = new JLabel();
        Icon photo = new ImageIcon(new ImageIcon("rules.png").getImage().getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT));
        rules.setIcon(photo);
        rules.setBounds(0,0,dim.width,dim.height);
        this.add(retour);
        this.add(rules);
    }
}

