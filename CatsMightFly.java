/*
 * Classe principale à exécuter
 * Actionlistener des boutons du Menu, Magasin et Règles
 * */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class CatsMightFly extends JFrame implements ActionListener {
    private int monde; // Variable qui nous permettra de savoir quel monde à choisi le joueur au moment de cliquer sur Jouer
    private final Menu menu = new Menu();
    private final Magasin magasin = new Magasin();
    private final Audio audio = new Audio();
    private final Regles regles = new Regles();
    private Jeu jeu = new Jeu();

    public CatsMightFly() {
        super("Cats might Fly  - Version 2.2");
        audio.jouerEnBoucle(); // On lance la musique d'accueil en boucle
        magasin.retour.addActionListener(this); // On met sur écoute tous les boutons
        menu.gauche.addActionListener(this);
        menu.droite.addActionListener(this);
        menu.jouer.addActionListener(this);
        menu.shop.addActionListener(this);
        menu.regles.addActionListener(this);
        magasin.achat2.addActionListener(this);
        magasin.achat3.addActionListener(this);
        regles.retour.addActionListener(this);
        monde = 1; // Ouvre le menu sur le monde 1 par défaut
        this.setLocationRelativeTo(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // Pour prendre les dimensions de l'écran
        this.add(menu);
        this.setSize(dim.width, dim.height-40);
        this.setLocation(0,0);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new CatsMightFly();
    }


    public void actionPerformed(ActionEvent e){
        audio.sonbouton(); // A chaque clic sur un JButton on déclenche le son
        
/// LANCER UNE PARTIE
        if (e.getSource() == menu.jouer) {
            audio.arreter();
            
            Monde choixmonde = new Monde1(); // Créé le monde et le son correspondant au choix du joueur
            if (monde == 1){
                audio.sonmonde1();
            }
            if (monde == 2){
                choixmonde = new Monde2();
                audio.sonmonde2();
            }if(monde == 3 ) {
                choixmonde = new Monde3();
                audio.sonmonde3();
            }
            jeu = new Jeu(choixmonde,audio); // Créé la fenêtre de jeu
            jeu.addKeyListener(jeu);
            jeu.addMouseListener(jeu);
            jeu.menu.addActionListener(this);
            jeu.setFocusable(true);
            this.setContentPane(jeu);
            this.revalidate();
            jeu.prendrecontrolactionlistener();
        }

/// OUVRIR REGLES DU JEU
        if (e.getSource() == menu.regles) {
            this.afficherRegles();
        }
		
/// OUVRIR MAGASIN
        if(e.getSource()== menu.shop){ 
            try {
                magasin.calculpiece();// On récupère la valeur de piece du .txt pour savoir ce que le joueur peut acheter
                this.afficherMagasin();
            } catch (Exception e2){
            }
        }
        
/// RETOUR AU MENU
        if (e.getSource() == jeu.menu) {
            audio.arreterronron();
            afficherMenu();
            audio.jouerEnBoucle();
        }
        if(e.getSource()== magasin.retour || e.getSource()== regles.retour){
            afficherMenu();
        }
        
/// SELECTION DU MONDE
        if((e.getSource() == menu.droite) && (monde == 2)) {
            monde = 3;
            menu.changemonde3();
        }
        if(e.getSource() == menu.droite && monde == 1){
            monde = 2;
            menu.changemonde2();
        }
        if(e.getSource() == menu.gauche && monde == 2) {
            monde = 1;
            menu.changemonde1();
        }
        if(e.getSource() == menu.gauche && monde == 3) {
            monde = 2;
            menu.changemonde2();
        }
        
/// ACHAT DES MONDES
        if(e.getSource()==magasin.achat2 && magasin.piece>=100 ){ //Si le joueur achète le monde 2, la variable achatmonde2 devient true et on modifie les .txt pour garder cette variable true même si il quitte le jeu.
            menu.achatmonde2=true;
            magasin.piece -= 100;
            try {
                magasin.updatepiece();
                magasin.calculpiece();
                menu.updateachatmonde();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            menu.changemonde1();
            monde=1;
        }
        if(e.getSource()==magasin.achat3 && magasin.piece>=200){ //Si le joueur achete le monde 3, la variable achatmonde3 devient true et on modifie les .txt pour garder cette variable true même si il quitte le jeu.
            menu.achatmonde3=true;
            magasin.piece -= 200;
            menu.changemonde1();
            monde=1;
            try {
                magasin.updatepiece();
                magasin.calculpiece();
                menu.updateachatmonde();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if(menu.achatmonde3){ // Si le joueur a déjà acheté dans des parties précédentes le monde 3 ou 2, il faut qu'il ne puisse plus l'acheter
            magasin.achat3.setEnabled(false);
        }
        if(menu.achatmonde2){
            magasin.achat2.setEnabled(false);
        }
    }

/// AFFICHAGE DES DIFFERENTES FENETRES
    public void afficherMagasin(){  // On change le JPanel principal par celui du Magasin
        this.setContentPane(magasin);
        this.revalidate();
    }
    public void afficherMenu(){  // On change le JPanel principal par celui du Menu
        this.setContentPane(menu);
        this.revalidate();
    }
    public void afficherRegles(){  // On change le JPanel principal par celui des Regles
        this.setContentPane(regles);
        this.revalidate();
    }
}
