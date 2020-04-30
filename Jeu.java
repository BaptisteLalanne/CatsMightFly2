/*
 * Fenêtre (JPanel) de jeu
 * Listener des actions barre espace et clic souris
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class Jeu extends JPanel implements ActionListener, KeyListener,MouseListener {
    public final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public Timer temps;
    public JLabel fond; // Arrière plan du niveau, 1e image
    public JLabel fond2; // Arrière plan du niveau, 2e image
    public JLabel fond3; // Premier plan du niveau, 1e image
    public JLabel fond4; // Premier plan du niveau, 2e image
    public JLabel distance; // Affichage de la distance parcourue
    public int tempspasse;  // Variables pour gérer position des fonds (selon temps écoulé & vitesse de défilement)
    public int tempspassedeuxiemefond;
    public int avance = 1;  // Facteur pour augmenter progressivement la vitesse de défilement
    public int cinqseconde;  // Vitesse de défilement augmente toutes les 5 secondes
    public int distanceparcourue;  // Distance parcourue depuis dernier changement de vitesse
    public int distanceparcourueparvitesse;  // Distance additionnée des précédentes vitesses (total sauf vitesse courante)
    public boolean perdu;
    public Bouton menu;
    private Monde monde;
    private Audio audio;

    public Jeu(Monde monde, Audio audio){
        this.audio = audio;
        this.monde = monde;
        this.setLayout(null);
        temps = new Timer(10,this);
        temps.start();
        
        menu = new Bouton (new ImageIcon("retour.png"));
        menu.setBounds(0, 0, 300, 200);
        distance = new JLabel();
        policetexte(true);
        distance.setForeground(Color.white);
        distance.setText("Distance parcourue:");
        distance.setBounds(100,dim.height-200,2000,100);
        
        fond = new JLabel();
        fond2 = new JLabel();
        fond3= new JLabel();
        fond4 = new JLabel();
        Icon photo = monde.Imageduniveau();
        Icon photo2= monde.Imageduniveaudefilant();
        fond.setIcon(photo);
        fond2.setIcon(photo);
        fond3.setIcon(photo2);
        fond4.setIcon(photo2);
        fond.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        fond2.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond3.setBounds(monde.tailleimage()[0],monde.tailleimage()[1],monde.tailleimage()[2],monde.tailleimage()[3]);
        fond4.setBounds(0,0,monde.tailleimage()[2],monde.tailleimage()[3]);
        
        for (int i=0; i<monde.obstacles.length; i++) {
            this.add(monde.obstacles[i]);
        }
        if(monde instanceof Monde2){ // Si le monde est le monde2 alors ajout du thermomètre
            this.add(((Monde2) monde).decorationthermo);
            this.add(((Monde2) monde).thermo);
        }
        this.add(menu);
        menu.setVisible(false); // On désactive le bouton retour, il sera visible et utilisable quand le joueur perd
        menu.setEnabled(false);
        this.add(monde.chat);
        this.add(distance);
        this.add(fond3);
        this.add(fond4);
        this.add(fond);
        this.add(fond2);
    }
    
    public Jeu(){
    }

	public void prendrecontrolactionlistener(){
        this.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
/// QUAND LE JOUEUR TOUCHE UN OBSTACLE (SOURIS OU OBSTACLE MORTEL)
        if(monde.collisionobstacles()){
            audio.sonexplosion();
            monde.eloignerlesobstacles(); // Visuellement pour ne plus voir l'objet touché (explosé)
            perdu = true;
            afficherfin(); // Appel de la méthode pour afficher le score
            temps.stop();
            try {
                monde.updatepiece(); // Ajout dans le piece.txt des pièces récoltés par le joueur
            } catch (IOException ex) {
                ex.printStackTrace();
            }
/// PROGRESSION NORMALE DE LA PARTIE
        } else {
            if (cinqseconde >= 5000) {  // Toutes les 5 secondes on accélère le niveau
				cinqseconde = 0;
                avance += 1;
                distanceparcourueparvitesse += distanceparcourue;
            }
            distanceparcourue = cinqseconde*avance/1000;
            cinqseconde += 10;
            tempspasse += monde.vitesseDefilement*(1+avance);
            tempspassedeuxiemefond += monde.vitesseDefilement*(4+avance);
            deplaceimage();
            distance.setText("Distance parcourue:" + (distanceparcourueparvitesse+distanceparcourue) + "m");
            if (avance > 1) { // Les 5 premières secondes (avance = 1) le joueur n'a pas d'obstacle pour se préparer à sa run
                deplaceobstacle(avance);
            }
            monde.chat.chute();
        }
        repaint();
    }
    
/// DEFILEMENT DU FOND DU NIVEAU ET OBSTACLES
    public void deplaceimage(){ // Le fond est en deux images qui se bouclent entre elles pour faire un fond infini (2 fonds par image avec 2 vitesses différentes)
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

/// GESTION BARRE ESPACE
    public void keyTyped(KeyEvent k) {
    }
    public void keyPressed(KeyEvent k) {  // Si la touche est maintenue enfoncée
        if(k.getKeyCode() == KeyEvent.VK_SPACE){
            if(!perdu){
                monde.chat.deplace(2);
            }
        }
    }
    public void keyReleased(KeyEvent k) {  // Si on appuie rapidement sur la touche
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            if(!perdu){
                monde.chat.deplace(1);
            }
        }
    }
/// GESTION SOURIS
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
        if(!perdu){
            monde.chat.deplace(3);
        }
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
        if(!perdu){
            monde.chat.deplace(3);
        }
    }
/// FIN DE PARTIE
    public void afficherfin(){
        distance.setBounds(dim.width/2-200,50,2000,100);
        audio.arretermonde1();
        audio.arretermonde2();
        audio.arretermonde3();
        policetexte(false); // false = grande police
        menu.setVisible(true);  // On dévoile/active le bouton retour (instancié dans le constructeur au début)
        menu.setEnabled(true);
    }
    
/// POUR PRENDRE NOTRE POLICE
    public void policetexte(boolean petit){
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
}
