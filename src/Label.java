import javax.swing.*;
import java.awt.*;

public class Label extends JLabel{
    
    public Label(String text, int fontSize, float ax, float ay) {
        super(text);
        this.setFont(new Font("Courier new", Font.PLAIN, fontSize));
        this.setAlignmentX(ax);
        this.setAlignmentY(ay);
        this.setForeground(Color.WHITE);
    }

    public Label(ImageIcon img, float ax, float ay) {
        this("", 0, ax, ay);
        this.setIcon(img);
    }
}
