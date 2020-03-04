import javax.swing.*;


public class Chat extends JLabel {
    private int coordx=100;
    private int coordy;
    public double vitesse;
    private int hauteur;
    public Chat (Icon photochat,int hauteur){
        this.setIcon(photochat);
        this.hauteur=hauteur;
        this.coordy=hauteur/2;
        this.setBounds(coordx,coordy,270,160);
    }

    public void deplace(int choix){
        if(coordy <= 0){
            vitesse = 0;
        }else{
            if(choix == 1){
                vitesse -= 2;
            }else{
                vitesse -= 1;
            }
        }
        deplacement();
    }

    public void chute(){
        if(coordy+400 >= hauteur){
            vitesse = 0;
        }else{
            vitesse += 0.1;
        }
        deplacement();
    }

    public void deplacement(){
        coordy+=vitesse;
        this.setLocation(coordx,coordy);
    }


}