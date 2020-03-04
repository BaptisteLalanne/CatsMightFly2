import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


public class Jeu extends JPanel implements ActionListener, KeyListener {
    public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Timer temps;
    public JLabel fond;
    public JLabel fond2;
    public JLabel fond3;
    public JLabel fond4;
    public int tempspasse;
    public int tempspassedeuxiemefond;
    private Monde monde;
    public int tempsjeu;
    public int dixseconde;
    public int avance;

    public Jeu(Monde monde){
        this.setLayout(null);
        this.addKeyListener(this);
        this.monde = monde;
        this.add(monde.chat);
        temps = new Timer(1,this);
        temps.start();
        fond = new JLabel();
        fond2 = new JLabel();
        fond3= new JLabel();
        fond4 = new JLabel();
        Icon photo = monde.Imageduniveau();
        Icon photo2= monde.Imageduniveaudiffilant();
        fond.setIcon(photo);
        fond2.setIcon(photo);
        fond3.setIcon(photo2);
        fond4.setIcon(photo2);
        fond2.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        fond3.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond4.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        for (int i=0; i<monde.obstacles.length; i++) {
            this.add(monde.obstacles[i]);
        }
        this.add(fond3);
        this.add(fond4);
        this.add(fond);
        this.add(fond2);
    }

    public void actionPerformed(ActionEvent e) {
        if(dixseconde >= 100000){
            dixseconde=0;
            avance +=1;
        }
        tempsjeu +=10;
        dixseconde +=tempsjeu;
        tempspasse += monde.vitesseDefilement+avance;
        tempspassedeuxiemefond +=monde.vitesseDefilement*4+avance;
        deplaceimage();
        if (tempsjeu>5000){
            deplaceobstacle(6+avance);
        }
        monde.chat.chute();
    }
    public void deplaceimage(){
        fond.setBounds(-tempspasse,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        fond2.setBounds(monde.tailleimage()[2]-tempspasse,0,monde.tailleimage()[2],dim.height);
        if(monde.tailleimage()[2]-tempspasse<=0){
            tempspasse=0;
        }
        fond3.setBounds(-tempspassedeuxiemefond,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        fond4.setBounds(monde.tailleimage()[2]-tempspassedeuxiemefond,0,monde.tailleimage()[2],dim.height);
        if(monde.tailleimage()[2]-tempspassedeuxiemefond<=0){
            tempspassedeuxiemefond=0;
        }
    }
    public void deplaceobstacle(int vitesse){

        monde.obstacleAvance(vitesse);
    }

    public void keyTyped(KeyEvent k) {
    }
    public void keyPressed(KeyEvent k) {

        if(k.getKeyCode() == KeyEvent.VK_SPACE){
            monde.chat.deplace(2);
        }
    }
    public void keyReleased(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            monde.chat.deplace(1);
        }
    }

}