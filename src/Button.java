import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Button extends JButton{

    public Button(String text, float ax, float ay, String room, int fontSize, GameTest game) {

        this.setText(text);
        this.setFont(new Font("Courier new", Font.PLAIN, fontSize));

        this.setAlignmentX(ax);
        this.setAlignmentY(ay);

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setForeground(Color.white);
        this.setFocusPainted(false);
        this.setOpaque(false);

        this.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                setBorderPainted(true);
            }
            public void mouseExited(MouseEvent evt) {
                setBorderPainted(false);
            }
        });

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Sound.playButton(game.SFXVolume);
                game.changeRoom(room);
            }
        });

    }

    public Button(ImageIcon img, float ax, float ay, String room, int sx, int sy, GameTest game) {

        this("", ax, ay, room, 0, game);
        this.setIcon(new ImageIcon(img.getImage().getScaledInstance(sx, sy, Image.SCALE_SMOOTH)));

    }




    

}
