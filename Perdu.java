import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Perdu extends JPanel {
    public Dimension dim;
    public Bouton menu;
    private JLabel distance;

    public Perdu(int distanceparcouru){
        this.setLayout(null);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        menu = new Bouton (new ImageIcon("retour.png"));
        menu.setBounds(0, 0, 300, 200);
        JLabel fin = new JLabel();
        distance = new JLabel("Distance parcourue"+String.valueOf(distanceparcouru));
        policetexte();
        Icon photo = new ImageIcon(new ImageIcon("rules.png").getImage().getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT));
        fin.setIcon(photo);
        fin.setBounds(0,0,dim.width,dim.height);
        this.add(menu);
        this.add(fin);
    }

    public void policetexte(){ // Pour prendre notre police
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("police.ttf"));
            font = font.deriveFont(38.f);
        } catch (Exception a) {
            new Font("Arial",Font.BOLD,18);
        }
        distance.setFont(font);
    }
}

