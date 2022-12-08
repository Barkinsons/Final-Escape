import javax.swing.JButton;
import java.awt.Cursor;

public class Button extends JButton {

    public Button() {

        setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) );
        setContentAreaFilled( false );
        setBorderPainted( false );
        setFocusPainted( false );
        setOpaque( false );

    }
}
