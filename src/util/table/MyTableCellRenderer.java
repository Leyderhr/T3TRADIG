package util.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class MyTableCellRenderer extends DefaultTableCellRenderer {

    public MyTableCellRenderer() {
        super();
        setHorizontalAlignment(JLabel.CENTER); // Alineación al centro
        // O, puedes usar JLabel.LEFT, JLabel.RIGHT según tus necesidades
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        return c;
    }
}

