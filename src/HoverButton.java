import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;

public class HoverButton extends RoomButton {
    
    public HoverButton( String roomName, float alignmentX, float alignmentY, Dimension size, Game g ) {

        super( roomName, alignmentX, alignmentY, size,  g );

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

    public static HoverButton Bottom( String roomName, Game g ) { return new HoverButton( roomName, 0.5f, 1f, new Dimension((int)  g.settings.getMaxSize().getWidth(), 200), g ); }
    public static HoverButton Top( String roomName, Game g )    { return new HoverButton( roomName, 0.5f, 0f, new Dimension((int)  g.settings.getMaxSize().getWidth(), 200), g ); }
    public static HoverButton Left( String roomName, Game g )   { return new HoverButton( roomName, 0f, 0.5f, new Dimension(200, (int) g.settings.getMaxSize().getHeight()), g ); }
    public static HoverButton Right( String roomName, Game g )  { return new HoverButton( roomName, 1f, 0.5f, new Dimension(200, (int) g.settings.getMaxSize().getHeight()), g ); }

}
