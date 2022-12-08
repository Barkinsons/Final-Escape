import java.io.File;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {

    public Clip clip;
    File[] files = new File[30];

    public Sound( int i ) {

        files[0] = new File( "res/audio/bg.wav" );
        files[1] = new File( "res/audio/button_forward.wav" );
        files[2] = new File( "res/audio/scream.wav" );
        files[3] = new File( "res/audio/item_get.wav" );
        files[4] = new File( "res/audio/knife.wav" );
        files[5] = new File( "res/audio/shoot.wav" );
        files[6] = new File( "res/audio/end.wav" );

        set_file( i );
        clip.start();
    }

    public void set_file(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream( files[i] );
            clip = AudioSystem.getClip();
            clip.open( ais );
        } catch ( Exception e ) {

            e.printStackTrace();
        }

        if( i == 0 ) { clip.loop( Clip.LOOP_CONTINUOUSLY ); }
    }

    public void play() { clip.start(); }
    public void stop() { clip.stop(); }

    public static void playButton() { new Sound( 1 ); }
    public static void playScream() { new Sound( 2 ); }
    public static void playItem()   { new Sound( 3 ); }
    public static void playKnife()  { new Sound( 4 ); }
    public static void playGun()    { new Sound( 5 ); }
    public static void playEnd()    { new Sound( 6 ); }
}