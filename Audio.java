/*
 * Classe pour créer les musiques de fond et bruitages
 * */

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio extends Thread {
    private final AudioClip sound;
    private final AudioClip bouton;
    private final AudioClip explosion;
    private final AudioClip monde1;
    private final AudioClip monde2;
    private final AudioClip monde3;

    public Audio() {
        URL url = this.getClass().getClassLoader().getResource("accueil.wav");
        URL url2 = this.getClass().getClassLoader().getResource("bouton.wav");
        URL url3 = this.getClass().getClassLoader().getResource("explo.wav");
        URL url4 = this.getClass().getClassLoader().getResource("Monde1.wav");
        URL url5 = this.getClass().getClassLoader().getResource("Monde2.wav");
        URL url6 = this.getClass().getClassLoader().getResource("Monde3.wav");
        sound = Applet.newAudioClip(url);
        bouton = Applet.newAudioClip(url2);
        explosion = Applet.newAudioClip(url3);
        monde1 = Applet.newAudioClip(url4);
        monde2 = Applet.newAudioClip(url5);
        monde3 = Applet.newAudioClip(url6);
    }

    public void jouer() { sound.play(); }
    public void jouerEnBoucle() { sound.loop(); }
    public void arreter() { sound.stop(); }
    
    public void sonbouton(){ bouton.play(); }
    public void sonexplosion() { explosion.play(); }
    public void arreterronron() { explosion.stop(); }
    public void sonmonde1() { monde1.loop(); }
    public void arretermonde1() { monde1.stop(); }
    public void sonmonde2() { monde2.loop(); }
    public void arretermonde2() { monde2.stop(); }
	public void sonmonde3() { monde3.loop(); }
    public void arretermonde3() { monde3.stop(); }
}
