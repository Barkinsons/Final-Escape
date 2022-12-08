import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    
    private Game g;

    public KeyHandler( Game g ) {

        this.g = g;
    }

    public void keyPressed( KeyEvent e ) {

        int key = e.getKeyChar();

        if( key == KeyEvent.VK_SPACE ) { g.flipView(); }
        if( key == KeyEvent.VK_ESCAPE) { g.changeRoom( "menu" ); }
    }

    public void keyReleased( KeyEvent e ) { }
    public void keyTyped( KeyEvent e ) { }
}
