import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Menu extends JPanel {

    public Bouton jouer; // Tout les boutons sont en public car c'est le main qui a l'Action Listener dessus
    public Bouton shop;
    public Bouton droite;
    public Bouton gauche;
    public Bouton regles;
    public JLabel image;
    public JLabel verouiller;
    public Dimension dim;
    public boolean achatmonde2;
    public boolean achatmonde3;
    private JLabel choixniveau;
    private Icon gif;
    private Icon photo;
    private Icon choixmonde;


    public Menu(){
        this.setLayout(null);
        try { // On regarde dans les .txt si le joueur a déjà acheté ou non un monde.
            checkersiachat();
        } catch (IOException e) {
            achatmonde2=false;
            achatmonde3=false;
        }
        dim = Toolkit.getDefaultToolkit().getScreenSize(); // Permet de récuperer la dimensions de l'écran qui execute le jeu
        jouer = new Bouton(new ImageIcon("jouer.png"));
        shop = new Bouton(new ImageIcon("magasin.png"));
        regles = new Bouton(new ImageIcon("règles.png"));
        droite = new Bouton(new ImageIcon("droite.png"));
        gauche = new Bouton(new ImageIcon("gauche.png"));
        choixniveau = new JLabel(new ImageIcon("Monde1.png"));
        verouiller = new JLabel();
        JLabel titre = new JLabel();
        image = new JLabel();
        jouer.setBounds(100, dim.height / 2 - 150, 300, 75);
        shop.setBounds(100, dim.height / 2 + 20, 300, 75);
        regles.setBounds(100, dim.height / 2+200, 300, 75);
        droite.setBounds(dim.width / 2 + 175, dim.height - 200, 75, 75);
        gauche.setBounds(dim.width / 2 - 250, dim.height - 200, 75, 75);
        choixniveau.setBounds(dim.width / 2 - 150, dim.height - 200, 300, 75);
        image.setBounds(0, 0, dim.width, dim.height - 40);
        verouiller.setBounds(0, 0, dim.width, dim.height - 40);
        Icon title = new ImageIcon(new ImageIcon("Titre.png").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        titre.setIcon(title);
        titre.setBounds(0, 0, dim.width, dim.height - 40);
        changemonde1();
        this.add(titre);
        this.add(shop);
        this.add(regles);
        this.add(jouer);
        this.add(droite);
        this.add(gauche);
        this.add(choixniveau);
        this.add(verouiller);
        this.add(image);
    }

    public void changemonde1(){
        gif = new ImageIcon(new ImageIcon("giphy.gif").getImage().getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT));
        image.setIcon(gif);
        choixmonde = new ImageIcon("Monde1.png");
        choixniveau.setIcon(choixmonde);
        gauche.setEnabled(false);
        droite.setEnabled(true);
        jouer.setEnabled(true);
        verouiller.setIcon(null);
    }
    public void changemonde2(){
        gif = new ImageIcon(new ImageIcon("giphy2.gif").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        choixmonde = new ImageIcon("Monde2.png");
        image.setIcon(gif);
        choixniveau.setIcon(choixmonde);
        gauche.setEnabled(true);
        droite.setEnabled(true);
        if(achatmonde2) { // Si le joueur a acheté le monde2 on setEnabled le bouton jouer et on enlève le png avec le cadenas
            jouer.setEnabled(true);
            verouiller.setIcon(null);
        }else{
            photo = new ImageIcon(new ImageIcon("verrouiller.png").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
            verouiller.setIcon(photo);
            jouer.setEnabled(false);
        }
    }
    public void changemonde3(){
        gif = new ImageIcon(new ImageIcon("giphy3.gif").getImage().getScaledInstance(dim.width,dim.height, Image.SCALE_DEFAULT));
        image.setIcon(gif);
        choixmonde = new ImageIcon("Monde3.png");
        droite.setEnabled(false);
        gauche.setEnabled(true);
        choixniveau.setIcon(choixmonde);
        if(achatmonde3) {
            jouer.setEnabled(true);
            verouiller.setIcon(null);
        }else{
            photo = new ImageIcon(new ImageIcon("verrouiller.png").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
            verouiller.setIcon(photo);
            jouer.setEnabled(false);
        }
    }

    public void checkersiachat() throws IOException { // Permet de voir si dans les fichiers txt si le joueur a déjà acheté le monde 2 ou 3
        BufferedReader in = new BufferedReader(new FileReader("monde2.txt"));
        String texte;
        while ((texte = in.readLine()) != null) {
            achatmonde2 = Boolean.parseBoolean(texte);
        }
        in.close();
        BufferedReader in2 = new BufferedReader(new FileReader("monde3.txt"));
        while ((texte = in2.readLine()) != null) {
            achatmonde3 = Boolean.parseBoolean(texte);
        }
        in2.close();
    }

    public void updateachatmonde() throws IOException{
        BufferedWriter out = new BufferedWriter (new FileWriter("monde2.txt"));
        out.write(String.valueOf(achatmonde2));
        out.close();
        BufferedWriter outt = new BufferedWriter (new FileWriter("monde3.txt"));
        outt.write(String.valueOf(achatmonde3));
        outt.close();
    }
}
