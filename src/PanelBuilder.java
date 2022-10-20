import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;

public class PanelBuilder {
    

    public static void buildPanels(PanelManager pm, GameTest g) {
        pm.addPanel("menu", make_menu_panel(g));
        pm.addPanel("options", make_options_panel(g));
        pm.addPanel("room1", make_room1(g));
        pm.addPanel("room2", make_room2(g));
        pm.addPanel("room3", make_room3(g));
        pm.addPanel("room4", make_room4(g));
        pm.addPanel("room5", make_room5(g));
        pm.addPanel("room6", make_room6(g));
    }

    public static Panel make_menu_panel(GameTest g) {

        Panel menu;

        try {
            menu = new Panel( ImageIO.read(new File("res/images/creepy_girl.jpg")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            menu = new Panel();
        }

        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));

        menu.add(Box.createVerticalGlue());

        Label title = new Label(new ImageIcon("res/images/title.gif"), .5f, .5f);
        menu.add(title);

        menu.add(Box.createVerticalGlue());

        Panel buttons = new Panel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        Button play = new Button("play", .5f, .5f, "room1", 25, g);
        Button options = new Button("options", .5f, .5f, "options", 25, g);
        Button exit = new Button("exit", .5f, .5f, "exit", 25, g);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.screen.dispatchEvent(new WindowEvent(g.screen, WindowEvent.WINDOW_CLOSING));
            }
        });

        buttons.add(play);
        buttons.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 20), new Dimension(0, 20)));
        buttons.add(options);
        buttons.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 20), new Dimension(0, 20)));
        buttons.add(exit);

        menu.add(buttons);
        menu.add(Box.createVerticalGlue());

        return menu;

    }

    public static Panel make_options_panel(GameTest g) {

        Panel options = new Panel(Color.BLACK);
        options.setLayout(new BoxLayout(options, BoxLayout.PAGE_AXIS));

        options.add(Box.createVerticalGlue());

        Panel titleBar = new Panel();
        titleBar.setLayout(new BoxLayout(titleBar, BoxLayout.LINE_AXIS));

        Label title = new Label(new ImageIcon("res/images/options.png"), .5f, .5f);

        titleBar.add(Box.createHorizontalGlue());
        titleBar.add(title);
        titleBar.add(Box.createHorizontalGlue());

        options.add(titleBar);
        options.add(Box.createVerticalGlue());

        Panel resBar = new Panel();
        // resBar.add(Box.createHorizontalGlue());
        
        Label res = new Label("resolution: ", 25, 1, .5f);
        Panel resolutions = new Panel(new ComboBox(g));
        
        resBar.setMaximumSize(new Dimension(Short.MAX_VALUE, res.getHeight()));
        resBar.add(res);
        resBar.add(resolutions);
        options.add(resBar);
        
        Panel musBar = new Panel();
        // musBar.add(Box.createHorizontalGlue());
        
        Label mus = new Label("music volume: ", 25, 1, .5f);
        JSlider musVol = new JSlider(JSlider.HORIZONTAL, (int)g.fc.getMinimum(), (int)g.fc.getMaximum(), (int)g.fc.getValue());
        musVol.setBackground(Color.black);
        musVol.setForeground(Color.white);
        musVol.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                g.fc.setValue(musVol.getValue());
            }
        });
        
        musBar.setMaximumSize(new Dimension(Short.MAX_VALUE, mus.getHeight()));
        musBar.add(mus);
        musBar.add(musVol);
        options.add(musBar);

        Panel sfxBar = new Panel();

        Label sfx = new Label("sfx volume: ", 25, 1, .5f);
        JSlider sfxVol = new JSlider(JSlider.HORIZONTAL, (int)g.fc.getMinimum(), (int)g.fc.getMaximum(), (int)g.fc.getValue());
        sfxVol.setBackground(Color.black);
        sfxVol.setForeground(Color.white);
        sfxVol.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                g.SFXVolume = sfxVol.getValue();
            }
        });
        
        sfxBar.setMaximumSize(new Dimension(Short.MAX_VALUE, mus.getHeight()));
        sfxBar.add(sfx);
        sfxBar.add(sfxVol);
        options.add(sfxBar);

        // Panel fullscreenBar = new Panel();

        // Label fullscreen = new Label("fullscreen: ", 25, 1, .5f);
        // JCheckBox fullScreenCheckBox = new JCheckBox();
        // fullScreenCheckBox.setBackground(Color.black);
        // fullScreenCheckBox.setForeground(Color.white);
        // fullScreenCheckBox.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         g.setFullscreen();
        //     }
        // });
        
        
        // fullscreenBar.setMaximumSize(new Dimension(Short.MAX_VALUE, mus.getHeight()));
        // fullscreenBar.add(fullscreen);
        // fullscreenBar.add(fullScreenCheckBox);
        // options.add(fullscreenBar);

        options.add(Box.createVerticalGlue());

        
        Button exit = new Button("exit to menu", .5f, 0, "menu", 25, g);
        options.add(exit);
        

        return options;
    }

    public static Panel make_room1(GameTest g) {
        Panel room1;

        try {
            room1 = new Panel( ImageIO.read(new File("res/images/room1.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room1 = new Panel();
        }
        room1.setLayout(new BoxLayout(room1, BoxLayout.PAGE_AXIS));

        Panel room2button = new Panel(new Button("room 2", 0, 0, "room2", 25, g), 0, 0);

        room1.add(Box.createVerticalGlue());
        room1.add(room2button);
        room1.add(Box.createVerticalGlue());

        return room1;

    }

    public static Panel make_room2(GameTest g) {

        Panel room2;

        try {
            room2 = new Panel( ImageIO.read(new File("res/images/room2.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room2 = new Panel();
        }

        room2.setLayout(new BoxLayout(room2, BoxLayout.PAGE_AXIS));

        Panel room2button = new Panel(new Button("room 3", 0, 0, "room3", 25, g), 0, 0);
        room2button.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        Panel backButton = new Panel(new Button("back", 0, 0, "room1", 25, g));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        room2.add(Box.createVerticalGlue());
        room2.add(room2button);
        room2.add(Box.createVerticalGlue());
        room2.add(backButton);

        return room2;

    }

    public static Panel make_room3(GameTest g) {

        Panel room3;

        try {
            room3 = new Panel( ImageIO.read(new File("res/images/room3.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room3 = new Panel();
        }

        room3.setLayout(new BoxLayout(room3, BoxLayout.PAGE_AXIS));

        Panel room2button = new Panel(new Button("room 4", 0, 0, "room4", 25, g), 0, 0);
        room2button.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        Panel backButton = new Panel(new Button("back", 0, 0, "room2", 25, g));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        room3.add(Box.createVerticalGlue());
        room3.add(room2button);
        room3.add(Box.createVerticalGlue());
        room3.add(backButton);

        return room3;

    }

    public static Panel make_room4(GameTest g) {

        Panel room4;

        try {
            room4 = new Panel( ImageIO.read(new File("res/images/room4.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room4 = new Panel();
        }

        room4.setLayout(new BoxLayout(room4, BoxLayout.PAGE_AXIS));

        Panel room2button = new Panel(new Button("room 5", 0, 0, "room5", 25, g), 0, 0);
        room2button.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        Panel backButton = new Panel(new Button("back", 0, 0, "room3", 25, g));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        room4.add(Box.createVerticalGlue());
        room4.add(room2button);
        room4.add(Box.createVerticalGlue());
        room4.add(backButton);

        return room4;

    }

    public static Panel make_room5(GameTest g) {

        Panel room5;

        try {
            room5 = new Panel( ImageIO.read(new File("res/images/room5.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room5 = new Panel();
        }

        room5.setLayout(new BoxLayout(room5, BoxLayout.PAGE_AXIS));

        Panel room2button = new Panel(new Button("room 6", 0, 0, "room6", 25, g), 0, 0);
        room2button.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        Panel backButton = new Panel(new Button("back", 0, 0, "room4", 25, g));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        room5.add(Box.createVerticalGlue());
        room5.add(room2button);
        room5.add(Box.createVerticalGlue());
        room5.add(backButton);

        return room5;

    }

    public static Panel make_room6(GameTest g) {

        Panel room6;

        try {
            room6 = new Panel( ImageIO.read(new File("res/images/room6.png")).getScaledInstance(1280, 800, Image.SCALE_SMOOTH) );
        } catch (IOException e) {
            room6 = new Panel();
        }

        room6.setLayout(new BoxLayout(room6, BoxLayout.PAGE_AXIS));

        Panel backButton = new Panel(new Button("back", 0, 0, "room5", 25, g));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 20));

        room6.add(Box.createVerticalGlue());
        room6.add(backButton);

        return room6;

    }

    









}
