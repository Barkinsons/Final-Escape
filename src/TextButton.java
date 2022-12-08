import java.awt.Font;
import java.awt.Color;

public class TextButton extends RoomButton {
    
    public TextButton( String text, String roomName, Game g ) {

        super( roomName, g );

        setText( text );
        setFont( new Font("Courier new", Font.PLAIN, 40) );
        setForeground( Color.WHITE );
        setAlignmentX( 0.5f );
    }

}
