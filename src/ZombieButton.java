import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ZombieButton extends Button {

    public ZombieButton( String itemKillName, String roomName, float alignmentX, float alignmentY, Dimension buttonSize, Game g ) {

        final int maxWidth = (int) g.settings.getSize().getWidth();
        final int maxHeight = (int) g.settings.getSize().getHeight();
        
        final int width = (int) buttonSize.getWidth();
        final int height = (int) buttonSize.getHeight();
        
        final int left = (int) (maxWidth * alignmentX - width / 2);
        final int top = (int) (maxHeight * alignmentY - height / 2);

        setBounds( left, top, width, height );
        setPreferredSize( buttonSize );

        addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                if( g.player.contains( itemKillName ) ) { 

                    g.zombies.remove( roomName );
                    g.resetRoom();
                    if( roomName == "dining" ) { Sound.playKnife(); }
                    else { Sound.playGun(); }
                }
            }
        });

        setBorder( new LineBorder(Color.RED) );
        
        addMouseListener( new MouseAdapter() {

            public void mouseEntered( MouseEvent e ) {

                setBorderPainted( true );
            }

            public void mouseExited( MouseEvent e ) {

                setBorderPainted( false );
            }
        });
    }
    
}
