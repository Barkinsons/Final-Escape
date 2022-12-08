import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ComboBox extends JComboBox<Dimension> {

    private class MyDimension extends Dimension {

        public MyDimension(int w, int h) {

            super(w, h);
        }

        @Override
        public String toString() {

            return "" + (int) this.getWidth() + " x " + (int) this.getHeight();
        }
    }

    MyDimension resolutions[] = {

        new MyDimension(3840, 2160),
        new MyDimension(3440, 1440),
        new MyDimension(2560, 1600),
        new MyDimension(2560, 1440),
        new MyDimension(2560, 1080),
        new MyDimension(2048, 1536),
        new MyDimension(2048, 1152),
        new MyDimension(1920, 1200),
        new MyDimension(1920, 1080),
        new MyDimension(1600,  900),
        new MyDimension(1440,  900),
        new MyDimension(1280, 1024),
        new MyDimension(1280, 800),
        new MyDimension(800, 600)
    };
    
    public ComboBox( Float alignment, Game g ) {

        setFont( new Font("Courier new", Font.PLAIN, 25) );
        setBackground( Color.BLACK );
        setForeground( Color.WHITE );
        setAlignmentX( alignment );

        int maxWidth = (int) g.settings.getMaxSize().getWidth();
        int maxHeight = (int) g.settings.getMaxSize().getHeight();

        for( MyDimension d : resolutions ) {

            if( d.getWidth() <= maxWidth && d.getHeight() <= maxHeight ) {

                this.addItem( d );
            }
        }

        setMaximumSize(new Dimension(200, this.getHeight()));
        setSelectedItem( g.settings.getSize() );


        addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e ) {

                removeSelf();
                g.changeResolution( (Dimension) getSelectedItem() );
            }
        });

    }

    public void removeSelf() { getParent().remove( this ); }
}

