/*
 * JPanel du magasin
 * */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Magasin extends JPanel {

    public final Dimension dim;
    public final Bouton retour;
    public final JButton achat2;
    public final JButton achat3;
    public final JLabel nombrepiece;
    public int piece;
    public String texte;

    public Magasin(){
        this.setLayout(null);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        achat2 = new JButton(new ImageIcon("boutonachat2.png"));
        achat3 = new JButton(new ImageIcon("boutonachat3.png"));
        retour = new Bouton(new ImageIcon("retour.png"));
        nombrepiece = new JLabel();
        nombrepiece.setText(texte);
        policetexte();
        nombrepiece.setForeground(Color.white);
        JLabel piece = new JLabel(new ImageIcon("souris.png"));
        JLabel cadre = new JLabel();
        Icon photocadre = new ImageIcon(new ImageIcon("cadremagasin.png").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        cadre.setIcon(photocadre);
        JLabel fond = new JLabel();
        Icon shop = new ImageIcon(new ImageIcon("shop2.gif").getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        fond.setIcon(shop);
        
        achat2.setBounds((int)(dim.width/4.5), dim.height - 250, 300, 120);
        achat3.setBounds((int)(dim.width/1.57), dim.height - 250, 300, 120);
        nombrepiece.setBounds(dim.width-300,50,250,75);
        retour.setBounds(0, 0, 300, 200);
        piece.setBounds(dim.width-160,60,75,60);
        cadre.setBounds(0,0,dim.width,dim.height-40);
        fond.setBounds(0,0,dim.width,dim.height-40);
        
        this.add(achat2);
        this.add(achat3);
        this.add(nombrepiece);
        this.add(retour);
        this.add(piece);
        this.add(cadre);
        this.add(fond);
    }


    public void recuperervaleurpiece (String t){
        nombrepiece.setText(t);
    }
    
/// BUFFER READER ET WRITER DES PIECES    
	public void calculpiece() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("piece.txt"));
        while ((texte = in.readLine()) != null)
        {
            piece = Integer.parseInt(texte);
            recuperervaleurpiece(texte);
        }
        in.close();
    }
    public void updatepiece() throws IOException{
        BufferedWriter out = new BufferedWriter (new FileWriter("piece.txt"));
        out.write(String.valueOf(piece));
        out.close();
    }
    
/// POUR PRENDRE NOTRE POLICE    
    public void policetexte() {
        Font font = null;
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, new File("police.ttf"));
            font = font.deriveFont(38.f);
        } catch (Exception a) {
            new Font("Arial",Font.BOLD,12);
        }
        nombrepiece.setFont(font);
    }
}
