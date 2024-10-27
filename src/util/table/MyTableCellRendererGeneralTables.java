package util.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableCellRendererGeneralTables extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column){
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(SwingConstants.CENTER);

        if(column != 0)
            setHorizontalAlignment(SwingConstants.CENTER);
        else
            setHorizontalAlignment(SwingConstants.LEFT);

        if (value.equals("BÁSICO"))
            setBackground(new Color(1, 176, 239, 255));

        return c;
    }
}
