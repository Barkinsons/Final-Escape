import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;

public class Label extends JLabel {
    
    public Label( String text ) {

        super(text);

        setFont( new Font("Courier new", Font.PLAIN, 40) );
        setForeground( Color.WHITE );
        setAlignmentX( 0.5f );
    }
}
