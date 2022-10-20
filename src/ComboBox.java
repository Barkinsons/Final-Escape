import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBox extends JComboBox<String> {
    int resolutions[][] = {
        {1920, 1080}, 
        {1600, 900},
        {1440, 900},
        {1280, 1024},
        {1280, 800},
        {800, 600}
    };

    public ComboBox(GameTest g) {
   
        this.setFont(new Font("Courier new", Font.PLAIN, 25));
        this.setBackground(Color.black);
        this.setForeground(Color.white);
  
        for(int[] res : resolutions) {
            this.addItem("" + res[0] + " x " + res[1]);
        }
        this.setSelectedIndex(4);

        this.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int res[] = resolutions[getSelectedIndex()]; 
                g.resize(new Dimension(res[0], res[1]));
            }
        });

        this.setMaximumSize(new Dimension(240, this.getHeight()));

    }
}
