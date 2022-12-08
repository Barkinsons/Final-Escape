import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class Panels {

    public static RoomPanel makeRoom( String roomName, Boolean spawnGhost, Game g ) {

        switch ( roomName ) {

            case "black": return black( g );
            case "menu": return menu( g );
            case "options": return options( g );
            case "jail": return jail(  g );
            case "supply": return supply( g );
            case "hallway": return hallway( g );
            case "dining": return dining( g );
            case "kitchen": return kitchen( g );
            case "stairs": return stairs( spawnGhost, g );
            case "hallway2": return hallway2( spawnGhost, g );
            case "master": return master( spawnGhost, g );
            case "power": return power( spawnGhost, g );
            case "theatre": return theatre( g );
            case "theatre2": return theatre2( g );
            case "supply2": return supply2( g );
            case "jail2": return jail2( g );
            case "study": return study( g );
            case "key": return key( g );
            case "escape": return escape( g );
            case "freedom": return freedom( g );
            case "death": return death( g );
            default: return null;
        }
    }

    private static RoomPanel black( Game g ) {

        RoomPanel black = new RoomPanel("res/images/black.jpg", g.settings.getSize() );

        return black;
    }

    private static RoomPanel menu( Game g ) {

        RoomPanel menu = new RoomPanel( "res/images/menu.jpg", g.settings.getSize() );
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS) );
        menu.setPreferredSize( g.settings.getSize() );

        JLabel title = new JLabel( new ImageIcon("res/images/title.gif") );
        title.setAlignmentX( 0.5f );

        menu.add( Box.createVerticalGlue() );
        menu.add( title );
        menu.add( Box.createVerticalGlue() );
        if( g.getRoom() == "jail") { menu.add( new TextButton("play", g.getRoom(), g) ); }
        else { menu.add( new TextButton("continue", g.getRoom(), g) ); }
        menu.add( Box.createVerticalStrut(30) );
        menu.add( new TextButton( "options", "options", g) );
        menu.add( Box.createVerticalGlue() );
        menu.add( new TextButton( "exit", "exit", g) );
        menu.add( Box.createVerticalGlue() );

        return menu;
    }

    private static RoomPanel options( Game g ) {

        RoomPanel options = new RoomPanel( "res/images/menu.jpg", g.settings.getSize() );
        options.setLayout( new BoxLayout(options, BoxLayout.PAGE_AXIS) );
        options.setPreferredSize( g.settings.getSize() );

        JLabel title = new JLabel( new ImageIcon("res/images/options.png") );
        title.setAlignmentX( 0.5f );

        options.add( Box.createVerticalGlue() );
        options.add( title );
        options.add( Box.createVerticalGlue() );
        options.add( new Label("resolution") );
        options.add( new ComboBox(0.5f, g) );
        if( g.settings.getSize().getWidth() != g.settings.getMaxSize().getWidth() || g.settings.getSize().getHeight() != g.settings.getMaxSize().getHeight() ) { 
            options.add( Box.createVerticalStrut(30) );
            options.add( new Label("fullscreen") );
            options.add( new CheckBox(g) ); 
        }
        options.add( Box.createVerticalGlue() );
        options.add( new TextButton( "back", "menu", g) );
        options.add( Box.createVerticalGlue() );

        return options;
    }

    private static RoomPanel jail( Game g ) {

        RoomPanel jail = new RoomPanel( "res/images/jail.jpg", g.settings.getSize() );
        jail.addC( new HoverButton("supply", 0.5672f, 0.4104f, new Dimension(400, 500), g) );

        return jail;
    }

    private static RoomPanel supply( Game g ) {

        RoomPanel supply = new RoomPanel( "res/images/supply_room.jpg", g.settings.getSize() );
        supply.addC( new HoverButton("hallway", 0.4832f, 0.2917f, new Dimension(400, 250), g) );
        supply.addC( HoverButton.Bottom("jail", g) );

        return supply;
    }

    private static RoomPanel hallway( Game g ) {

        RoomPanel hallway = new RoomPanel( "res/images/hallway.jpg", g.settings.getSize() );
        if( g.player.contains("note") ) { hallway.addC( HoverButton.Bottom( "supply2", g) ); }
        else { hallway.addC( HoverButton.Bottom( "supply", g) ); }
        hallway.addC( new HoverButton("dining", 0.0441f, 0.5194f, new Dimension(300, 400), g) );
        hallway.addC( new HoverButton("kitchen", 0.7742f, 0.5056f, new Dimension(275, 300), g) );
        hallway.addC( HoverButton.Right("study", g) );

        return hallway;
    }

    private static RoomPanel kitchen( Game g ) {

        String filePath;
        if( g.player.contains("knife") ) { filePath = "res/images/kitchen_knife_taken.jpg"; }
        else { filePath = "res/images/kitchen.jpg"; }

        RoomPanel kitchen = new RoomPanel( filePath, g.settings.getSize() );
        kitchen.addC( HoverButton.Bottom( "hallway", g) );
        if( !g.player.contains("knife") ) { kitchen.addC( new ItemButton("knife", 0.4797f, 0.8361f, new Dimension(300, 300), g) ); }

        return kitchen;
    }

    private static RoomPanel dining( Game g ) {

        String filePath;
        if( g.zombies.contains("dining") ) { filePath = "res/images/dining_boss.jpg"; }
        else { filePath = "res/images/dining.jpg"; }

        RoomPanel dining = new RoomPanel( filePath, g.settings.getSize() );
        dining.addC( HoverButton.Bottom( "hallway", g) );
        if( g.zombies.contains("dining") ) { dining.addC( new ZombieButton("knife", "dining", 0.6383f, 0.4514f, new Dimension(200, 400), g) ); }
        else { dining.addC( new HoverButton("stairs", 0.6590f, 0.4465f, new Dimension(300, 400), g) ); }

        return dining;
    }

    private static RoomPanel stairs( Boolean spawnGhost, Game g ) {

        String filePath;
        if( g.player.contains("power") ) { filePath = "res/images/stairs_bright.jpg"; }
        else { filePath = "res/images/stairs_dark.jpg"; }

        RoomPanel stairs = new RoomPanel( filePath, g.settings.getSize() );
        stairs.addC( HoverButton.Bottom( "dining", g) );
        stairs.addC( new HoverButton("hallway2", 0.4445f, 0.5479f, new Dimension(400, 500), g) );
        
        if( spawnGhost ) {

            stairs.addC( ImageLabel.ghost( g ) );
            g.startGhostLoop();
        }

        return stairs;
    }

    private static RoomPanel hallway2( Boolean spawnGhost, Game g ) {

        String filePath;
        if( g.player.contains("power") ) { filePath = "res/images/stairhallways_bright.jpg"; }
        else { filePath = "res/images/stairhallways_dark.jpg"; }

        RoomPanel hallway2 = new RoomPanel( filePath, g.settings.getSize() );
        hallway2.addC( HoverButton.Bottom( "stairs", g) );
        if( !g.player.contains("knife") ) { hallway2.addC( new ItemButton("knife", 0.4797f, 0.8361f, new Dimension(300, 300), g) ); }
        hallway2.addC( new HoverButton("master", 0.4113f, 0.4701f, new Dimension(250, 300), g) );
        hallway2.addC( new HoverButton("power", 0.5805f, 0.4632f, new Dimension(250, 300), g) );
        if( g.player.contains("power") ) { hallway2.addC( new HoverButton("theatre", 0.2293f, 0.4632f, new Dimension(200, 500), g) ); }

        if( spawnGhost ) {

            hallway2.addC( ImageLabel.ghost( g ) );
            g.startGhostLoop();
        }

        return hallway2;
    }

    private static RoomPanel master( Boolean spawnGhost, Game g ) {

        String filePath;
        if( g.player.contains("power") ) { 
            if( g.zombies.contains("master") ) { filePath = "res/images/master_light_boss.jpg"; }
            else { filePath = "res/images/master_light.jpg"; }
        }
        else { filePath = "res/images/master_dark.jpg"; }

        RoomPanel master = new RoomPanel( filePath, g.settings.getSize() );
        master.addC( HoverButton.Bottom( "hallway2", g) );
        if( g.player.contains("power")) { 
            if( g.zombies.contains("master") ) { master.addC( new ZombieButton("gun", "master", 0.7332f, 0.5910f, new Dimension(300, 450), g) ); }
            else if( !g.player.contains("note") ) { master.addC( new ItemButton("note", 0.7570f, 0.5694f, new Dimension(200, 400), g) ); }
        }

        if( spawnGhost ) {

            master.addC( ImageLabel.ghost( g ) );
            g.startGhostLoop();
        }

        return master;
    }

    private static RoomPanel power( Boolean spawnGhost, Game g ) {

        String filePath;
        if( g.player.contains("power") ) { filePath = "res/images/powerroom_bright.jpg"; }
        else { filePath = "res/images/powerroom_dark.jpg"; }

        RoomPanel power = new RoomPanel( filePath, g.settings.getSize() );
        power.addC( HoverButton.Bottom( "hallway2", g) );
        if( g.player.contains("key") ) { power.addC( new HoverButton("escape", 0.9066f, 0.4639f, new Dimension(200, 500), g) ); }
        if( !g.player.contains("power")) { power.addC( new ItemButton("power", 0.4906f, 0.4451f, new Dimension(300, 300), g) ); }

        if( spawnGhost ) {

            power.addC( ImageLabel.ghost( g ) );
            g.startGhostLoop();
        }

        return power;
    }

    private static RoomPanel theatre( Game g ) {

        RoomPanel theatre = new RoomPanel( "res/images/theatre_room.jpg", g.settings.getSize() );
        theatre.addC( HoverButton.Bottom( "hallway2", g) );
        theatre.addC( new HoverButton("theatre2", 0.8477f, 0.6076f, new Dimension(300, 300), g) );

        return theatre;
    }

    private static RoomPanel theatre2( Game g ) {

        String filePath;
        if( g.player.contains("gun") ) { filePath = "res/images/theatre_gun_taken.jpg"; }
        else { filePath = "res/images/theatre_gun.jpg"; }

        RoomPanel theatre2 = new RoomPanel( filePath, g.settings.getSize() );
        theatre2.addC( HoverButton.Bottom( "theatre", g) );

        if( !g.player.contains("gun") ) { theatre2.addC( new ItemButton("gun", 0.5352f, 0.5125f, new Dimension(200, 200), g) ); }

        return theatre2;
    }

    private static RoomPanel supply2( Game g ) {

        String filePath;
        if( g.zombies.contains("jail") ) { filePath = "res/images/jail_boss.jpg"; }
        else { filePath = "res/images/jail_boss_killed.jpg"; }

        RoomPanel supply2 = new RoomPanel( filePath, g.settings.getSize() );
        supply2.addC( HoverButton.Bottom( "hallway", g ) );

        if( g.zombies.contains("jail") ) { supply2.addC( new ZombieButton("gun", "jail", 0.4813f, 0.4785f, new Dimension(400, 400), g) ); }
        else { supply2.addC( new HoverButton("jail2", 0.4633f, 0.4778f, new Dimension(300, 425), g) ); }

        return supply2;
    }

    private static RoomPanel jail2( Game g ) {

        String filePath;
        if( g.player.contains("note2") ) { filePath = "res/images/brick_code_taken.jpg"; }
        else { filePath = "res/images/brick_code.jpg"; }

        RoomPanel jail2 = new RoomPanel( filePath, g.settings.getSize() );
        jail2.addC( HoverButton.Bottom( "supply2", g ) );

        if( !g.player.contains("note2") ) { jail2.addC( new ItemButton("note2", 0.5262f, 0.4597f, new Dimension(200, 200), g) ); }

        return jail2;
    }

    private static RoomPanel study( Game g ) {

        RoomPanel study = new RoomPanel( "res/images/study.jpg", g.settings.getSize() );
        study.addC( HoverButton.Bottom( "hallway", g ) );
        if( g.player.contains("note2") ) { study.addC( new HoverButton("key", 0.5273f, 0.5340f, new Dimension(200, 200), g) ); }

        return study;
    }

    private static RoomPanel escape( Game g ) {

        String filePath;
        if( g.player.contains("power") ) { filePath = "res/images/freedom_door.jpg"; }
        else { filePath = "res/images/attic_dark.jpg"; }

        RoomPanel escape = new RoomPanel( filePath, g.settings.getSize() );
        escape.addC( HoverButton.Bottom( "power", g ) );
        escape.addC( new HoverButton("freedom", 0.5f, 0.5f, new Dimension(200, 400), g) );

        return escape;
    }

    private static RoomPanel freedom( Game g ) {

        RoomPanel escape = new RoomPanel( "res/images/freedom.jpg", g.settings.getSize() );
        escape.addC( HoverButton.Bottom( "menu", g ) );

        g.bg.stop();
        Sound.playEnd();

        return escape;
    }

    private static RoomPanel key( Game g ) {

        String filePath;
        if( g.player.contains("key") ) { filePath = "res/images/cashier_no_key.jpg"; }
        else { filePath = "res/images/cashier_key.jpg"; }

        RoomPanel key = new RoomPanel( filePath, g.settings.getSize() );
        key.addC( HoverButton.Bottom( "study", g ) );

        if( !g.player.contains("key") ) { key.addC( new ItemButton("key", 0.2832f, 0.4306f, new Dimension(400, 400), g) ); }

        return key;
    }

    private static RoomPanel death( Game g ) {

        RoomPanel death = new RoomPanel( "res/images/black.jpg", g.settings.getSize() );
        death.addC( new ImageLabel("res/images/jumpscare.gif", 0.5f, 0.5f, g.settings.getSize(), g) );

        return death;
    }
}
