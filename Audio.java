import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio extends Thread
{
    private AudioClip sound;
    private AudioClip bouton;

    public Audio()
    {
        URL url = this.getClass().getClassLoader().getResource("accueil.wav");
        URL url2 = this.getClass().getClassLoader().getResource("bouton.wav");
        sound = Applet.newAudioClip(url);
        bouton = Applet.newAudioClip(url2);
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
}