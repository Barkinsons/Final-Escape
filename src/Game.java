import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Random;


public class Game {

    public Settings settings;
    public Sound bg;
    public ArrayList<String> player;
    public ArrayList<String> zombies;
    public JFrame screen;
    
    private String curRoomName;
    private RoomPanel curPanel;
    private RoomPanel blackPanel;
    private String lastRoomName;
    
    final private Random r;
    private boolean eyesClosed;
    
    private Timer ghostLoop;
    public Timer deathLoop;
    private long startTime;
    private long screenTime;

    public Game() {

        settings = new Settings();
        bg = new Sound( 0 );
        player = new ArrayList<String>();
        zombies = new ArrayList<String>();
        zombies.add("master");
        zombies.add("dining");
        zombies.add("jail");
        
        screen = new JFrame();
        screen.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        screen.setUndecorated( true );
        screen.setResizable( false );
        screen.setFocusable( true );
        screen.addKeyListener( new KeyHandler( this ));
        
        lastRoomName = "jail";

        r = new Random();
        eyesClosed = false;

        startTime = -1;
        
        ghostLoop = new Timer( 40, new ActionListener() {

            public void actionPerformed( ActionEvent e ) {
                
                if( startTime < 0 ) { 

                    startTime = System.currentTimeMillis(); 
                    screenTime = 0;
                    bg.stop();
                }
                else {

                    long duration = System.currentTimeMillis() - startTime;
                    
                    if( screenTime > settings.getGhostTime() ) { 

                        die(); 
                        startTime = -1;
                        ghostLoop.stop();
                    }
                    else {
                        
                        if( duration > settings.getGhostDelay() ) {
                            
                            curPanel = Panels.makeRoom( curRoomName, false, getSelf() );
                            startTime = -1;
                            ghostLoop.stop();
                        } 
                        else if( !eyesClosed ) { screenTime += ghostLoop.getDelay(); }
                    }
                    System.out.println( "screenTime = " + screenTime + " duration = " + duration );
                }

            }
        });

        deathLoop = new Timer( 3000, new ActionListener() { public void actionPerformed( ActionEvent e ) { stop(); } } );

        changeRoom( "menu" );
        screen.pack();
    }

    public void play() { screen.setVisible( true ); }
    public void stop() { screen.dispatchEvent(new WindowEvent(screen, WindowEvent.WINDOW_CLOSING)); }

    public void changeRoom( String roomName ) {

        if( ghostLoop.isRunning() ) { die(); } 
        else {
            Boolean spawnGhost;
            if( !player.contains("power") ) { spawnGhost = r.nextInt(100) < settings.getGhostChance(); }
            else { spawnGhost = false; }

            eyesClosed = false;
            curRoomName = roomName;
            if( roomName != "options" && roomName != "menu" ) { lastRoomName = roomName; }
            curPanel = Panels.makeRoom( roomName, spawnGhost, this );
            blackPanel = Panels.makeRoom( "black", spawnGhost, this );
            screen.setContentPane( curPanel );
        }
    }

    public void startGhostLoop() { ghostLoop.start(); }

    public void die() { 
        screen.setContentPane( Panels.makeRoom( "death", false, this )); 
        deathLoop.start();
        Sound.playScream();
    }

    public void changeResolution( Dimension newSize ) {

        settings.setSize( newSize );
        screen.getComponent(0);
        changeRoom( curRoomName );
        screen.setSize( newSize );
        screen.dispose();

        if( newSize.getWidth() == settings.getMaxSize().getWidth() && newSize.getHeight() == settings.getMaxSize().getHeight() ) { screen.setUndecorated( true ); }
        else { screen.setUndecorated( false ); }

        screen.setLocationRelativeTo(null);
        screen.setVisible( true );
        screen.revalidate();
    }

    public void resetRoom() { changeRoom( curRoomName ); }

    public String getRoom() { return lastRoomName; }

    public void fullscreen() { changeResolution( settings.getMaxSize() ); }

    public void flipView() {

        if( curRoomName == "menu" || curRoomName == "options" || curRoomName == "death" ) { return; }

        if( eyesClosed ) { 

            screen.setContentPane( curPanel ); 
            if( !ghostLoop.isRunning() && !bg.clip.isRunning() ) { bg.loop(); }
        }
        else { screen.setContentPane( blackPanel ); }

        eyesClosed = !eyesClosed;
    }

    private Game getSelf() { return this; }

    public static void main(String args[]) {

        Game g = new Game();
        g.play();
    }
}