import javax.swing.*;


public class Chat extends JLabel {
    private int coordx=100;
    private int coordy;
    public double vitesse;
    private int hauteur;
    private int ybasduniveau;
    private double gravite;

    public Chat (Icon photochat,int hauteur, int ybasduniveau, double gravite){
        this.ybasduniveau = ybasduniveau;
        this.gravite = gravite;
        this.setIcon(photochat);
        this.hauteur=hauteur;
        this.coordy=hauteur/2;
        this.setBounds(coordx,coordy,270,160);
    }
    
    public double get_vitesse() { return vitesse; }

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
        if (vitesse >4){
            vitesse = 4;
        }
        deplacement();
    }

    public void chute(){
        if(coordy+ybasduniveau >= hauteur){
            vitesse = 0;
        }else{
            vitesse += gravite*0.2/10;
        }
        deplacement();
    }

    public void deplacement(){
        coordy+=vitesse;
        if(coordy <= 0 && vitesse <= 0 ){
            coordy=0;
            vitesse =0;
        }
        this.setLocation(coordx,coordy);
    }


}
