import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {
    Image img;

    public Panel(Image img) {
        this.img = img;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.img != null) {
            g.drawImage(this.img, 0, 0, null);
        }
    }
}
