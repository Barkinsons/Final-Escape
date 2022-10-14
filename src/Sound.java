import java.io.File;
import javax.sound.sampled.*;

public class Sound {
    public Clip clip;
    File[] files = new File[30];

    public Sound() {
        files[0] = new File("res/audio/bg.wav");
        files[1] = new File("res/audio/button_forward.wav");
    }

    public void set_file(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(files[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
