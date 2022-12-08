import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ImageLabel extends JLabel {
    
    public ImageLabel( String imgFilePath, float alignmentX, float alignmentY, Dimension labelSize, Game g ) {

        final int maxWidth = (int) g.settings.getSize().getWidth();
        final int maxHeight = (int) g.settings.getSize().getHeight();
        
        final int width = (int) labelSize.getWidth();
        final int height = (int) labelSize.getHeight();
        
        final int left = (int) Math.max( maxWidth * alignmentX - width / 2, 0 );
        final int top = (int) Math.max( maxHeight * alignmentY - height / 2, 0 );

        ImageIcon i = new ImageIcon( imgFilePath );
        i.setImage( i.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT) );

        setIcon( i );
        setOpaque( false );
        setBounds( left, top, width, height );
        setPreferredSize( labelSize );
    }

    public static ImageLabel ghost( Game g ) { return new ImageLabel( "res/images/ghost_girl.png", 0.5f, 0.5f, new Dimension(500, 350), g ); }
}