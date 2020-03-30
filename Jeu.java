import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;


public class Jeu extends JPanel implements ActionListener, KeyListener {
    public Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Timer temps;
    public JLabel fond;
    public JLabel fond2;
    public JLabel fond3;
    public JLabel fond4;
    public boolean perdu;
    public JLabel distance;
    public int tempspasse;
    public int tempspassedeuxiemefond;
    private Monde monde;
    public int tempsjeu;
    public int cinqseconde;
    public int avance = 1;
    public int distanceparcouru;
    public int distanceparcouruparvitesse;
    private Audio audio;
    public Bouton menu;

    public Jeu(Monde monde, Audio audio){
        this.audio=audio;
        this.setLayout(null);
        this.monde = monde;
        this.add(monde.chat);
        temps = new Timer(10,this);
        temps.start();
        distance = new JLabel();
        policetexte(true);
        distance.setForeground(Color.white);
        fond = new JLabel();
        fond2 = new JLabel();
        fond3= new JLabel();
        fond4 = new JLabel();
        menu = new Bouton (new ImageIcon("retour.png"));
        menu.setBounds(0, 0, 300, 200);

        Icon photo = monde.Imageduniveau();
        Icon photo2= monde.Imageduniveaudiffilant();
        fond.setIcon(photo);
        fond2.setIcon(photo);
        fond3.setIcon(photo2);
        fond4.setIcon(photo2);
        distance.setBounds(100,dim.height-200,2000,100);
        distance.setText("Distance parcourue:");
        fond2.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        fond3.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond4.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        for (int i=0; i<monde.obstacles.length; i++) {
            this.add(monde.obstacles[i]);
        }
        this.add(menu);
        menu.setVisible(false);
        menu.setEnabled(false);
        this.add(distance);
        this.add(fond3);
        this.add(fond4);
        this.add(fond);
        this.add(fond2);
    }
    public Jeu(){

    }

    public void actionPerformed(ActionEvent e) {
        if(monde.collisionobstacles()){
            audio.sonexplosion();
            monde.eloignerlesobstacles();
            perdu = true;
            afficherfin();
            temps.stop();
        }else{
            if (cinqseconde >= 5000) {
                cinqseconde = 0;
                avance += 1;
                distanceparcouruparvitesse += distanceparcouru;
                distanceparcouru = distanceparcouruparvitesse;
            }
            distanceparcouru = cinqseconde*avance/1000;
            cinqseconde += 10;
            tempspasse += monde.vitesseDefilement*(1+avance);
            tempspassedeuxiemefond += monde.vitesseDefilement * (4+avance);
            deplaceimage();
            distance.setText("Distance parcourue:" + (distanceparcouruparvitesse+distanceparcouru) + "m");
            if (avance > 1) {
                deplaceobstacle(avance);
            }
            monde.chat.chute();
        }
        repaint();
    }
    public void deplaceimage(){
        fond.setLocation(-tempspasse,0);
        fond2.setLocation(monde.tailleimage()[2]-tempspasse,0);
        if(monde.tailleimage()[2]-tempspasse<=0){
            tempspasse=0;
        }
        fond3.setLocation(-tempspassedeuxiemefond,0);
        fond4.setLocation(monde.tailleimage()[2]-tempspassedeuxiemefond,0);
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
            if(!perdu){
                monde.chat.deplace(2);
            }
        }
    }
    public void keyReleased(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!perdu){
                monde.chat.deplace(1);
            }
        }
    }
    public void policetexte(boolean petit){ // Pour prendre notre police
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("police.ttf"));
            if(petit){
                font = font.deriveFont(38.f);
            }else{
                font = font.deriveFont(52.f);
            }
        } catch (Exception a) {
            new Font("Arial",Font.BOLD,12);
        }
        distance.setFont(font);
    }
    public void prendrecontrolactionlistener(){
        this.requestFocus();
    }
    public void afficherfin(){
        distance.setBounds(dim.width/2-200,50,2000,100);
        audio.arretermonde1();
        policetexte(false);
        menu.setVisible(true);
        menu.setEnabled(true);
    }
}