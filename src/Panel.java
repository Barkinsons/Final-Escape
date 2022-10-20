import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {
    Image img;

    public Panel() {
        img = null;
        this.setOpaque(false);
    }

    public Panel(Image img) {
        this.img = img;
    }

    public Panel(Button b) {
        this();
        this.setBounds(b.getBounds());
        this.add(b);
    }

    public Panel(Button b, int x, int y) {
        this(b);
        this.setBounds(x, y, this.getHeight(), this.getWidth());
    }

    public Panel(ComboBox cb) {
        this();
        this.setBounds(cb.getBounds());
        this.add(cb);
    }

    public Panel(Color c) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, c.getRGB());

        this.img = img.getScaledInstance(1280, 800, Image.SCALE_SMOOTH);
        this.setOpaque(false);
    }

    public void resize(Dimension d) {
        this.img = this.img.getScaledInstance((int) d.getWidth(), (int) d.getHeight(), Image.SCALE_SMOOTH);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.img != null) {
            g.drawImage(this.img, 0, 0, null);
        }
    }
}
