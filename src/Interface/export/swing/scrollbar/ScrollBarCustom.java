package Interface.export.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 5));
        setForeground(new Color(0,51,153,255));
        setUnitIncrement(40);
        setOpaque(false);
    }
}
