import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class CheckBox extends JCheckBox {

    public CheckBox( Game g ) {

        setBackground( Color.BLACK );
        setFocusPainted( false );
        setAlignmentX( 0.5f );

        if( g.settings.getSize().getWidth() == g.settings.getMaxSize().getWidth() && g.settings.getSize().getHeight() == g.settings.getMaxSize().getHeight() ) { setEnabled( false ); }
        else { setEnabled( true );}

        addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                g.fullscreen();
            }
        });

        addMouseListener( new MouseAdapter() {

            public void mouseEntered( MouseEvent e ) {

                setFocusPainted( true );
            }

            public void mouseExited( MouseEvent e ) {

                setFocusPainted( false );
            }
        });
    }
}