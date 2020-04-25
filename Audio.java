import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio extends Thread
{
    private final AudioClip sound;
    private final AudioClip bouton;
    private final AudioClip explosion;
    private final AudioClip monde1;

    public Audio()
    {
        URL url = this.getClass().getClassLoader().getResource("accueil.wav");
        URL url2 = this.getClass().getClassLoader().getResource("bouton.wav");
        URL url3 = this.getClass().getClassLoader().getResource("explo.wav");
        URL url4 = this.getClass().getClassLoader().getResource("Monde1.wav");
        sound = Applet.newAudioClip(url);
        bouton = Applet.newAudioClip(url2);
        explosion = Applet.newAudioClip(url3);
        monde1 = Applet.newAudioClip(url4);
    }

    public void jouer()
    {
        sound.play();
    }

    public void jouerEnBoucle()
    {
        sound.loop();
    }

    public void arreter()
    {
        sound.stop();
    }
    public void sonbouton(){
        bouton.play();
    }
    public void sonexplosion(){
        explosion.play();
    }
    public void sonmonde1(){
        monde1.loop();
    }
    public void arretermonde1()
    {
        monde1.stop();
    }
    public void arreterronron(){ explosion.stop();}

}