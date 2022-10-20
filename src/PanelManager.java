import java.util.*;
import java.awt.*;

public class PanelManager {

    private TreeMap<String, Panel> map = new TreeMap<String, Panel>(); 

    public void addPanel(String str, Panel p) { map.put(str, p); }

    public Panel getPanel(String str) { return map.get(str); }

    public void resize(Dimension d) {
        for (Map.Entry<String, Panel> entry : map.entrySet()) {
            entry.getValue().resize(d);
        }
    }

}
