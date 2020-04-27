/*
 * Classe pour simplifier la création des boutons
 * */

import javax.swing.*;

public class Bouton extends JButton {


    public Bouton(ImageIcon imageIcon){
        super(imageIcon);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
}

