import javax.swing.*;
import java.awt.*;


public class Thermometre extends JPanel {
    public Dimension dim;
    public JLabel thermo;
    public JLabel jauge;

    public Thermometre(){
        this.setLayout(null);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        Icon fondThermo = new ImageIcon(new ImageIcon("thermo.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT));
        thermo.setIcon(fondThermo);
        thermo.setBounds(0,0,200,200);
        
        // Jauge: rectangle rouge dont hauteur proportionnelle à température
        
        this.add(thermo);
        //this.add(jauge);
    }
}
