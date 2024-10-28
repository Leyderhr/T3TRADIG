package util.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableCellRendererGeneralPerspective extends DefaultTableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(SwingConstants.CENTER);

        if (column != 0)
            setHorizontalAlignment(SwingConstants.CENTER);
        else {
            setHorizontalAlignment(SwingConstants.LEFT);
            setVerticalAlignment(CENTER);
        }
        // Pinta la fila 0 con el fondo azul claro
        if (row == 0) {
            this.setBackground(new Color(211, 243, 254, 255));
            if (column == 0) {
                // Solo escribe en la columna 0 de la fila 0
                setText(value.toString());
                setFont(new Font("Arial", Font.BOLD, 15));
            }
            else {
                // Limpia las otras columnas de la fila 0
                setText("");
            }
        } else {
            // Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
            this.setBackground(Color.white);

            // Permite escribir en las demás filas
            setText(value != null ? value.toString() : "");
        }


        if (column == 4 && row != 0) {
            if (value != null) {
                try {
                    float valorFloat = Float.parseFloat(value.toString()); // Convierte el valor a float

                    if (valorFloat >= 0 && valorFloat <= 25) {
                        setText("<html><p><strong>BÁSICO</strong></p></html>");
                        setBackground(new Color(1, 176, 239, 255));
                    }
                    else if (valorFloat > 25 && valorFloat <= 50) {
                        setText("<html><p><strong>INICIAL</strong></p></html>");
                        setBackground(new Color(146, 209, 79, 255));
                    }
                    else if (valorFloat > 50 && valorFloat <= 75) {
                        setText("<html><p><strong>ESTRATÉGICO</strong></p></html>");
                        setBackground(new Color(255, 193, 0, 255));
                    }
                    else {
                        setText("<html><p><strong>INNOVADOR - DISRUPTIVO</strong></p></html>");
                        setBackground(new Color(254, 0, 0, 255));
                    }
                } catch (NumberFormatException e) {
                    // Maneja el caso en el que el valor no se pueda convertir a flotante
                    setText("Error");
                    setBackground(Color.RED);
                }
            }
        }


        return c;
    }

}
