import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

import java.io.File;
import java.awt.event.*;
import javax.swing.event.*;

public class Game {
    JFrame screen = new JFrame();
    Sound bg = new Sound();
    TreeMap<String, Panel> panels = new TreeMap<String, Panel>();
    TreeMap<String, AudioInputStream> sounds = new TreeMap<String, AudioInputStream>();
    FloatControl musicVolume;
    int sfxVol;

    public Game(int width, int height) throws Exception {
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setSize(width, height);
        screen.setResizable(false);
        screen.setLocationRelativeTo(null);

        bg.set_file(0);
        bg.loop();
        musicVolume = (FloatControl) bg.clip.getControl(FloatControl.Type.MASTER_GAIN);

        buildPanels();
        // setSounds();

        screen.setContentPane(panels.get("menuPanel"));
    }

    public void play() throws Exception {
        screen.setVisible(true);
    }

    // public class Panel {
    //     JPanel p;
    //     Image img;

    //     public Panel(String filename, int width, int height) throws IOException {
    //         if(filename != null) {img = ImageIO.read(new File(filename)).getScaledInstance(width, height, Image.SCALE_SMOOTH);} 
    //     }

    //     public JPanel getJPanel() {return this.p;}
    //     public  Image  getImage() {return this.img;}
    //     public   void setJPanel(JPanel p) {this.p = p;}

    //     public void resizePanel(int width, int height) {
    //         if(img != null) {
    //             img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    //         }
    //         screen.setLocationRelativeTo(null);
    //     }
    // }

    // public void resize() {
    //     for (Map.Entry<String, Panel> entry : panels.entrySet()) {
    //         entry.getValue().resizePanel(screen.getWidth(), screen.getHeight());
    //     }
    // }

    public void buildPanels() {

        Image temp = null;
        try {
            temp = ImageIO.read(new File("res/images/creepy_girl.jpg")).getScaledInstance(screen.getWidth(), screen.getHeight(), Image.SCALE_SMOOTH);
            make_menu_panel(temp);

            make_options_panel();

            make_room_1();
        } catch (IOException e) { e.printStackTrace(); }

    }

    // public void setSounds() throws Exception {
    //     sounds.put("bg", AudioSystem.getAudioInputStream(new File("res/audio/bg.wav")));
    //     sounds.put("button_clicked", AudioSystem.getAudioInputStream(new File("res/audio/button_click.wav")));
    // }

    public void make_menu_panel(Image img) throws IOException {

        Panel menuPanel = new Panel(img);

        // menuPanel.setJPanel(new JPanel() {
        //     @Override
        //     protected void paintComponent(Graphics g) {
        //         super.paintComponent(g);
        //         g.drawImage(menuPanel.getImage(), 0, 0, null);
        //     }
        // });
  
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel(new ImageIcon("res/images/title.gif"));
        title.setBorder(BorderFactory.createEmptyBorder(100, 0, 125, 0));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JButton playButton = makeButton("play", true);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setContentPane(panels.get("room1"));
                screen.revalidate();
                screen.repaint();
                playButton.setBorderPainted(false);
            }
        });
        playButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        playButton.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 0, 20, 0), new LineBorder(Color.white)));

        JButton optionsButton = makeButton("options", true);
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setContentPane(panels.get("optionsPanel"));
                screen.revalidate();
                optionsButton.setBorderPainted(false);
            }
        });
        optionsButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        optionsButton.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 0, 20, 0), new LineBorder(Color.white)));

        JButton exitButton = makeButton("exit", true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.dispatchEvent(new WindowEvent(screen, WindowEvent.WINDOW_CLOSING));
                exitButton.setBorderPainted(false);
            }
        });
        exitButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        exitButton.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(20, 0, 20, 0), new LineBorder(Color.white)));

        menuPanel.add(title);
        menuPanel.add(playButton);
        menuPanel.add(optionsButton);
        menuPanel.add(exitButton);

        panels.put("menuPanel", menuPanel);
    }

    public void make_options_panel() throws IOException {

        Panel optionsPanel = new Panel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
        optionsPanel.setBackground(Color.black);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        titlePanel.setOpaque(false);

        JLabel title = new JLabel(new ImageIcon(new ImageIcon("res/images/options.png").getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH)));
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(100, 0, 100, 0));

        JButton exitButton = makeButton(new ImageIcon(new ImageIcon("res/images/options_x.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)), true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setContentPane(panels.get("menuPanel"));
                screen.revalidate();
                exitButton.setBorderPainted(false);
            }
        });

        titlePanel.add(title);
        titlePanel.add(exitButton);

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        row1.setOpaque(false);
        row1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel res1 = new JLabel("resolution: ");
        res1.setFont(new Font("Courier new", Font.PLAIN, 25));
        res1.setForeground(Color.white);
        JComboBox<String> res2 = new JComboBox<String>();
        res2.addItem("1920 x 1080");
        res2.addItem("1600 x 900");
        res2.addItem("1440 x 900");
        res2.addItem("1280 x 1024");
        res2.addItem("1280 x 800");
        res2.addItem("800 x 600");
        res2.setSelectedIndex(4);
        res2.setFont(new Font("Courier new", Font.PLAIN, 25));
        res2.setBackground(Color.black);
        res2.setForeground(Color.white);
        res2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (res2.getSelectedItem().toString()) {
                    case "1920 x 1080":
                        screen.setSize(new Dimension(1920, 1080));
                        // resize();
                        break;
                    case "1600 x 900":
                        screen.setSize(new Dimension(1600, 900));
                        // resize();
                        break;
                    case "1440 x 900":
                        screen.setSize(new Dimension(1440, 900));
                        // resize();
                        break;    
                    case "1280 x 1024":
                        screen.setSize(new Dimension(1280, 1024));
                        // resize();
                        break; 
                    case "1280 x 800":
                        screen.setSize(new Dimension(1280, 800));
                        // resize();
                        break; 
                    case "800 x 600":
                        screen.setSize(new Dimension(800, 600));
                        // resize();
                        break; 
                }
            }
        });

        row1.add(res1);
        row1.add(res2);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        row2.setOpaque(false);
        row2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel mus1 = new JLabel("music: ");
        mus1.setAlignmentX(.75f);
        mus1.setFont(new Font("Courier new", Font.PLAIN, 25));
        mus1.setForeground(Color.white);

        JSlider mus2 = new JSlider(JSlider.HORIZONTAL, -20, 6, 0);
        mus2.setBackground(Color.black);
        mus2.setForeground(Color.white);
        mus2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                musicVolume.setValue(mus2.getValue());
            }
        });

        row2.add(mus1);
        row2.add(mus2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        row3.setOpaque(false);
        row3.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JLabel sfx1 = new JLabel("sfx: ");
        sfx1.setAlignmentX(.75f);
        sfx1.setFont(new Font("Courier new", Font.PLAIN, 25));
        sfx1.setForeground(Color.white);

        JSlider sfx2 = new JSlider(JSlider.HORIZONTAL);
        sfx2.setBackground(Color.black);
        sfx2.setForeground(Color.white);

        JLabel bottomSpace = new JLabel();
        bottomSpace.setBorder(BorderFactory.createEmptyBorder(175, 0, 0, 0));

        row3.add(sfx1);
        row3.add(sfx2);
    
        optionsPanel.add(titlePanel);
        optionsPanel.add(row1);
        optionsPanel.add(row2);
        optionsPanel.add(row3);
        optionsPanel.add(bottomSpace);

        panels.put("optionsPanel", optionsPanel);
        
    }

    public void make_room_1() throws IOException {
        Panel room1_panel = new Panel();

        room1_panel.setLayout(null);
        room1_panel.setBackground(Color.black);

        JPanel p = new JPanel();
        p.setBounds(0, 0, screen.getWidth(), 40);
        p.setOpaque(false);
        JLabel label = new JLabel("Room 1");
        label.setFont(new Font("Courier new", Font.PLAIN, 30));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setForeground(Color.white);

        JButton button1 = makeButton("Room2", true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setContentPane(panels.get("room2"));
                screen.revalidate();
                button1.setBorderPainted(false);
            }
        });

        JButton button2 = makeButton("Room3", true);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setContentPane(panels.get("room3"));
                screen.revalidate();
                button2.setBorderPainted(false);
            }
        });
        JPanel p1 = new JPanel();
        p1.setBounds(0, 600, screen.getWidth(), 60);
        p1.setOpaque(false);


        p.add(label);
        p1.add(button1);
        p1.add(button2);
        room1_panel.add(p);
        room1_panel.add(p1);


        panels.put("room1", room1_panel);

    }

    public void make_room_2() throws IOException {
        Panel room2_panel = new Panel();
        room2_panel.setLayout(null);
        room2_panel.setBackground(Color.black);

        JPanel p = new JPanel();
        p.setBounds(0, 0, screen.getWidth(), 40);
        p.setOpaque(false);
        JLabel room2 = new JLabel("Room2");
        room2.setFont(new Font("Courier new", Font.PLAIN, 30));
        room2.setForeground(Color.white);
        p.add(room2);
        room2_panel.add(p);

        panels.put("room2", room2_panel);
    }

    public void make_room_3() throws IOException {
        Panel room3_panel = new Panel();
        room3_panel.setLayout(null);
        room3_panel.setBackground(Color.BLACK);

        JPanel p = new JPanel();
        p.setBounds(0, 0, screen.getWidth(), 40);
        p.setOpaque(false);
        JLabel room3 = new JLabel("Room3");
        room3.setForeground(Color.white);
        room3.setFont(new Font("Courier new", Font.PLAIN, 30));
        p.add(room3);
        room3_panel.add(p);

        panels.put("room3", room3_panel);
    }

    public JButton makeButton(String text, Boolean highlight) {
        JButton b = new JButton(text);
        b.setFont(new Font("Courier new", Font.PLAIN, 25));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setForeground(Color.white);
        b.setFocusPainted(false);
        if(highlight) {
            b.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    b.setBorderPainted(true);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    b.setBorderPainted(false);
                }
            });
        }       

        return b;
    }

    public JButton makeButton(ImageIcon i, Boolean highlight) {
        JButton b = new JButton(i);
        b.setFont(new Font("Courier new", Font.PLAIN, 25));
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setForeground(Color.white);
        b.setFocusPainted(false);
        if(highlight) {
            b.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    b.setBorderPainted(true);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    b.setBorderPainted(false);
                }
            });
        }       
        return b;
    }

}
