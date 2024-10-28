package Interface.newInterface.Chart;

import logic.Entitys.Perspectiva;
import logic.useful.Controlador;
import util.table.MyTableCellRendererGeneralPerspective;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MddResumenTable extends JPanel {

    private JLabel header;

    private JTable resumenGeneralDimensions;
    private JScrollPane  scrollPaneResumenGeneralDimensions;
    private DefaultTableModel model = new DefaultTableModel();


    public MddResumenTable(){
        setLayout(null);

        add(getHeader());
        add(getScrollPaneResumenGeneralDimensions());


        // El height del panel se calcula sumando la altura de cada panel + la separación entre ellos
        int height = (header.getHeight() + scrollPaneResumenGeneralDimensions.getHeight() + scrollPaneResumenGeneralDimensions.getHeight()) + 20;
        setSize(1000, height);
    }


    private JLabel getHeader() {
        if (header == null) {
            header = new JLabel("Resultados: Resumen madurez digital MDG, MDA y MDP");
            header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 30));
            header.setBounds(20, 10, 850, 80);
            header.setOpaque(false);
            header.setForeground(new Color(8, 52, 128));
        }
        return header;
    }


    // Tabla del Resumen de madures digital global y por perspectivas
    // =================================================================================================================
    private JScrollPane getScrollPaneResumenGeneralDimensions() {
        if (scrollPaneResumenGeneralDimensions == null) {
            scrollPaneResumenGeneralDimensions = new JScrollPane();
            scrollPaneResumenGeneralDimensions.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

            scrollPaneResumenGeneralDimensions.setViewportView(getTableResumenGeneralDimensions());
            scrollPaneResumenGeneralDimensions.setBorder(null);

            // Primero vaciamos el table model para que no se dupliquen las columnas
            model = new DefaultTableModel();
            model.addColumn("");
            model.addColumn("<html> <p align: center>Madurez Digital<br>(MDm) X̅ máximo</p></html>");
            model.addColumn("<html> <p align: center>Madurez Digital (MDr)<br>X̅ real de autoevaluación</p></html>");
            model.addColumn("<html> <p align: center>Índice de Madurez<br>Digital (IMD)%</p></html>");
            model.addColumn("<html> <p align: center>Nivel de Madurez Digital<br> (NMD)</p></html>");
            actualizarTablaPerspectives();

            int heightTable = (50 * getTableResumenGeneralDimensions().getRowCount()) +
                    getTableResumenGeneralDimensions().getTableHeader().getBounds().height + 55;

            int y = header.getY() + header.getHeight() + 10;
            scrollPaneResumenGeneralDimensions.setBounds(15, y,970, heightTable);
        }
        return scrollPaneResumenGeneralDimensions;
    }

    private JTable getTableResumenGeneralDimensions() {
        if (resumenGeneralDimensions == null) {
            resumenGeneralDimensions = new JTable() {
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };

            resumenGeneralDimensions.getTableHeader().setReorderingAllowed(false);
            resumenGeneralDimensions.setModel(new DefaultTableModel(
                    new Object[][]{
                            {null, null, null, null, null, null},
                            {null, null, null, null, null, null},
                    },
                    new String[]{
                            "", "<html> <p align: center>Madurez Digital (MDm) X máximo</p></html>",
                            "<html> <p align: center>Madurez Digital (MDr) X real de autoevaluación</p></html>",
                            "<html> <p align: center>Índice de Madurez Digital (IMD)%</p></html>",
                            "<html> <p align: center>Nivel de Madurez Digital (NMD)</p></html>",
                    }
            ));

            // Customización del encabezado de la tabla
            resumenGeneralDimensions.setShowVerticalLines(false);
            resumenGeneralDimensions.getTableHeader().setBackground(new Color(8, 52, 128));
            resumenGeneralDimensions.getTableHeader().setForeground(Color.white);
            resumenGeneralDimensions.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 15));
            resumenGeneralDimensions.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);

            resumenGeneralDimensions.setFont(new Font("Arial", Font.PLAIN, 14));
            resumenGeneralDimensions.setRowHeight(50);


        }
        return resumenGeneralDimensions;
    }


    public void actualizarTablaPerspectives() {
        while (model.getRowCount() > 0)
            model.removeRow(0);

        Object[] ob = new Object[5];
        ArrayList<logic.Entitys.Dimension> dimensions = Controlador.getDimension(0);

        // Valores para el resumen da Madurez Digital de Perspectivas
        model.addRow(new String[]{"<html><p><strong>PERSPECTIVAS (MDP) </strong></p></html>"});


        for (logic.Entitys.Dimension dimension: dimensions) {
            ob[0] = "<html> <p style=\"text-align: left; vertical-align: down;\">" + dimension.getNombre_dimension() + "</p></html>";
            ob[1] = "<html> <p>" + 4.0f + "</p></html>";
            ob[2] = Float.parseFloat(String.valueOf(dimension.calculate_MDr_IMD()[0]));
            ob[3] = "<html> <p><strong>" + Float.parseFloat(String.valueOf(dimension.calculate_MDr_IMD()[1])) + "</strong></p></html>";
            ob[4] = dimension.calculate_MDr_IMD()[1];
            model.addRow(ob);
        }

        resumenGeneralDimensions.setModel(model);
        for (int i = 0; i < resumenGeneralDimensions.getColumnCount(); i++) {
            resumenGeneralDimensions.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRendererGeneralPerspective());
        }

        resumenGeneralDimensions.getColumnModel().getColumn(0).setMinWidth(194);
        resumenGeneralDimensions.getColumnModel().getColumn(1).setMinWidth(194);
        resumenGeneralDimensions.getColumnModel().getColumn(2).setMinWidth(194);
        resumenGeneralDimensions.getColumnModel().getColumn(3).setMinWidth(194);
        resumenGeneralDimensions.getColumnModel().getColumn(4).setMinWidth(194);


    }
    // =========================================================================
}
