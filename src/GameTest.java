import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class GameTest {
    
    public PanelManager pm;
    public JFrame screen;
    public Panel lastPanel;

    public Sound bg;
    public FloatControl fc;
    public float SFXVolume;

    public GameTest(int x, int y) {
        screen = new JFrame();
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(1280, 800);
        screen.setResizable(false);
        screen.setLocationRelativeTo(null);
        screen.setFocusable(true);

        bg = new Sound();
        bg.set_file(0);
        fc = (FloatControl) bg.clip.getControl(FloatControl.Type.MASTER_GAIN);
        SFXVolume = -20f;
        
        pm = new PanelManager();
        
        PanelBuilder.buildPanels(pm, this);

        screen.setContentPane(pm.getPanel("menu"));

        screen.addKeyListener( new KeyListener() {
            
            public void keyPressed(KeyEvent e) { }

            public void keyReleased(KeyEvent e) { }

            public void keyTyped(KeyEvent e) {
                int key = e.getKeyChar();
                if(key == KeyEvent.VK_ESCAPE) {
                    if (lastPanel == null) {
                        lastPanel = (Panel) screen.getContentPane();
                        changeRoom(pm.getPanel("options"));
                    }
                    else {
                        changeRoom(lastPanel);
                        lastPanel = null;
                    }
                }
            }
        });
    }
    
    public void play() {
        screen.setVisible(true);
        bg.loop();
    }

    public void changeRoom(String room) {
        screen.setContentPane(pm.getPanel(room));
        screen.revalidate();
    }

    public void changeRoom(Container c) {
        screen.setContentPane(c);
        screen.revalidate();
    }

    public void resize(Dimension d) {
        screen.setSize(d);
        pm.resize(d);
        screen.setLocationRelativeTo(null);
    }

    // public void setFullscreen() {
    //     resize(Toolkit.getDefaultToolkit().getScreenSize());
    // }
}
