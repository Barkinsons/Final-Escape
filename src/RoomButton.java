import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomButton extends Button {

    public RoomButton( String roomName, Game g ) {

        addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                if( roomName == "exit" ) { g.stop(); }
                else {
                    g.changeRoom( roomName );
                    Sound.playButton();
                }
            }
        });
    }

    public RoomButton( String roomName, float alignmentX, float alignmentY, Dimension buttonSize, Game g ) {

        this( roomName, g );

        final int maxWidth = (int) g.settings.getSize().getWidth();
        final int maxHeight = (int) g.settings.getSize().getHeight();
        
        final int width = (int) buttonSize.getWidth();
        final int height = (int) buttonSize.getHeight();
        
        final int left = (int) (maxWidth * alignmentX - width / 2);
        final int top = (int) (maxHeight * alignmentY - height / 2);

        setBounds( left, top, width, height );
        setPreferredSize( buttonSize );
    }
}