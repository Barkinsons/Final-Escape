import java.awt.Dimension;
import java.awt.Toolkit;


public class Settings {

    final private Dimension maxSize;
    private Dimension screenSize;
    final private long ghostDelay; // Total time ghost will stay on screen before being removed
    final private long ghostTime; // Time ghost allowed to be on screen before dying
    final private int ghostChance;

    public Dimension getSize() { return screenSize; }
    public Dimension getMaxSize() { return maxSize; }
    public long getGhostDelay() { return ghostDelay; }
    public long getGhostTime() { return ghostTime; }
    public int getGhostChance() { return ghostChance; }

    public void setSize( Dimension newSize ) { screenSize = newSize; }

    public Settings() {

        maxSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize = maxSize;
        ghostDelay = 4000;
        ghostTime = 600;
        ghostChance = 33;
    }

    
}
