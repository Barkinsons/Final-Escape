import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.io.IOException;
import java.io.File;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Component; 

public class RoomPanel extends JPanel {
    
    private Image img;
    private JPanel p;
    
    public RoomPanel( String imageFilePath, Dimension size ) {

        try {

            this.img = ImageIO.read( new File(imageFilePath) ).getScaledInstance( (int) size.getWidth(), (int) size.getHeight(), Image.SCALE_SMOOTH );
        } catch( IOException e ) { e.printStackTrace(); }

        p = new JPanel();
        p.setLayout( null );
        p.setLocation( 0, 0 );
        p.setPreferredSize( size );
        p.setOpaque( false );
        setSize( size );
        if( imageFilePath != "res/images/menu.jpg" ) { add( p ); }
    }

    public void addC( Component c ) {

        p.add( c );
    }

    @Override
    protected void paintComponent( Graphics g ) {

        super.paintComponent( g );

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage( img, 0, 0, null );
        g2d.dispose();
    }
}