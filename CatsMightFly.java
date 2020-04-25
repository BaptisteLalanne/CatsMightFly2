import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;

public class CatsMightFly extends JFrame implements ActionListener {
    private int monde; // Variable qui nous permettra de savoir quel monde à choisit le joueur au moment de cliquer sur Jouer
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
        monde = 1;
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
        audio.sonbouton(); // A chaque clique sur un JButton on déclanche le son
        if (e.getSource() == menu.jouer) {
            audio.arreter();
            Monde choixmonde = new Monde1();
            if (monde == 1){
                audio.sonmonde1();
            }
            if (monde == 2){
                choixmonde = new Monde2();
            }if(monde == 3 ) {
                choixmonde = new Monde3();
            }
            jeu = new Jeu(choixmonde,audio);

            jeu.addKeyListener(jeu);
            jeu.menu.addActionListener(this);
            jeu.setFocusable(true);
            this.setContentPane(jeu);
            this.revalidate();
            jeu.prendrecontrolactionlistener();
        }
        if (e.getSource() == jeu.menu) {
            audio.arreterronron();
            afficherMenu();
            audio.jouerEnBoucle();
        }


        if (e.getSource() == menu.regles) {
            this.afficherRegles();
        }

        if(e.getSource()== menu.shop){ // On récupère la valeur de piece du .txt pour savoir ce que le joueur peux acheter et on affiche le magasin.
            try {
                magasin.calculpiece();
                this.afficherMagasin();
            } catch (Exception e2){
            }
        }
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
        if(e.getSource()== magasin.retour || e.getSource()== regles.retour){ // On appel la méthode pour affcher le menu si le joueur sort du magasin
            afficherMenu();
        }
        if(e.getSource()==magasin.achat2 && magasin.piece>=100 ){ //Si le joueur achete le monde3, la variable achatmonde3 devient true et on modifie les .txt pour garder cette variable true même si il quitte le jeu.
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
        if(e.getSource()==magasin.achat3 && magasin.piece>=200){ //Si le joueur achete le monde3, la variable achatmonde3 devient true et on modifie les .txt pour garder cette variable true même si il quitte le jeu.
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

    public void afficherMagasin(){   // On change le JPanel principal par celui du Magasin
        this.setContentPane(magasin);
        this.revalidate();
    }
    public void afficherMenu(){  // // On change le JPanel principal par celui du Menu
        this.setContentPane(menu);
        this.revalidate();
    }

    public void afficherRegles(){  // // On change le JPanel principal par celui des Regles
        this.setContentPane(regles);
        this.revalidate();
    }
}

