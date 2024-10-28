package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;
import logic.Entitys.Ambito;
import logic.Entitys.Perspectiva;
import logic.useful.Controlador;
import util.table.MyTableCellRendererGeneralPerspective;
import util.table.MyTableCellRendererGeneralTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class MdgMdaMdpTable extends JPanel {

    private JTable resumenGeneralAmbits;
    private JTable resumenGeneralPerspectives;

    private JLabel header;
    private DefaultTableModel model = new DefaultTableModel();

    private JScrollPane scrollPaneResumenGeneralAmbits;
    private JScrollPane scrollPaneResumenGeneralPerspectives;


    public MdgMdaMdpTable() {
        setLayout(null);

        add(getHeader());
        add(getScrollPaneResumenGeneralAmbits());
        add(getScrollPaneResumenGeneralPerspectives());

        // El height del panel se calcula sumando la altura de cada panel + la separación entre ellos
        int height = (header.getHeight() + scrollPaneResumenGeneralAmbits.getHeight() + scrollPaneResumenGeneralPerspectives.getHeight()) + 20;
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


    // Tabla del Resumen de madures digital global y por ámbitos
    // =================================================================================================================
    private JScrollPane getScrollPaneResumenGeneralAmbits() {
        if (scrollPaneResumenGeneralAmbits == null) {
            scrollPaneResumenGeneralAmbits = new JScrollPane();
            scrollPaneResumenGeneralAmbits.setBounds(15, 100, 970, 245);
            scrollPaneResumenGeneralAmbits.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPaneResumenGeneralAmbits.setViewportView(getTableResumenGeneralAmbits());
            scrollPaneResumenGeneralAmbits.setBorder(null);

            model.addColumn("");
            model.addColumn("<html> <p align: center>Madurez Digital<br>(MDm) X̅ máximo</p></html>");
            model.addColumn("<html> <p align: center>Madurez Digital (MDr)<br>X̅ real de autoevaluación</p></html>");
            model.addColumn("<html> <p align: center>Índice de Madurez<br>Digital (IMD)%</p></html>");
            model.addColumn("<html> <p align: center>Nivel de Madurez Digital<br> (NMD)</p></html>");
            actualizarTablaAmbits();

            int heightTable = (50 * (getTableResumenGeneralAmbits().getRowCount())) +
                    getTableResumenGeneralAmbits().getTableHeader().getBounds().height + 45;

            int y = header.getY() + header.getHeight() + 10;
            scrollPaneResumenGeneralAmbits.setBounds(15, y, 970, heightTable);
        }
        return scrollPaneResumenGeneralAmbits;
    }

    private JTable getTableResumenGeneralAmbits() {
        if (resumenGeneralAmbits == null) {
            resumenGeneralAmbits = new JTable() {
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };

            resumenGeneralAmbits.getTableHeader().setReorderingAllowed(false);
            resumenGeneralAmbits.setModel(new DefaultTableModel(
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
            resumenGeneralAmbits.setShowVerticalLines(false);
            resumenGeneralAmbits.getTableHeader().setBackground(new Color(8, 52, 128));
            resumenGeneralAmbits.getTableHeader().setForeground(Color.white);
            resumenGeneralAmbits.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 15));
            resumenGeneralAmbits.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);

            resumenGeneralAmbits.setFont(new Font("Arial", Font.PLAIN, 14));
            resumenGeneralAmbits.setRowHeight(50);

        }
        return resumenGeneralAmbits;
    }


    public void actualizarTablaAmbits() {
        while (model.getRowCount() > 0)
            model.removeRow(0);

        Object[] ob = new Object[5];
        ArrayList<Ambito> ambitos = Controlador.getAmbitos();
        float mdgAutoevaluacion = 0f;
        float mdgQuestionCant = 0f;

        // Valores para el resumen da Madurez Digital Global
        ob[0] = "<html><p><strong>GLOBAL (MDG) </strong></p></html>";
        ob[1] = 4.0f;
        for(Ambito a: ambitos){
            mdgQuestionCant += a.getCant_preguntas();
            mdgAutoevaluacion += a.getCant_ptos();
        }
        ob[2] = mdgAutoevaluacion/mdgQuestionCant;
        ob[3] = "<html> <p><strong>" + ((Float)ob[2] / 4) * 100 + "</strong></p></html>";
        ob[4] = ((Float)ob[2] / 4) * 100;
        model.addRow(ob);

        model.addRow(new String[]{"<html><p><strong>ÁMBITOS (MDA) </strong></p></html>"});

        // Relleno de la tabla con los Ámbitos
        for (Ambito ambito : ambitos) {
            ob[0] = "<html> <p style=\"text-align: left; vertical-align: down;\">" + ambito.getNombre_ambito() + "</p></html>";
            ob[1] = "<html> <p>" + 4.0f +"</p></html>";
            ob[2] = Float.parseFloat(String.valueOf(ambito.calculate_MDr_IMD()[0]));
            ob[3] = "<html> <p><strong>" + Float.parseFloat(String.valueOf(ambito.calculate_MDr_IMD()[1])) + "</strong></p></html>";
            ob[4] = ambito.calculate_MDr_IMD()[1];
            model.addRow(ob);
        }

        resumenGeneralAmbits.setModel(model);
        for (int i = 0; i < resumenGeneralAmbits.getColumnCount(); i++) {
            resumenGeneralAmbits.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRendererGeneralTables());
        }


        resumenGeneralAmbits.getColumnModel().getColumn(0).setMinWidth(204);
        resumenGeneralAmbits.getColumnModel().getColumn(1).setMinWidth(180);
        resumenGeneralAmbits.getColumnModel().getColumn(2).setMinWidth(194);
        resumenGeneralAmbits.getColumnModel().getColumn(3).setMinWidth(194);
        resumenGeneralAmbits.getColumnModel().getColumn(4).setMinWidth(194);
    }
    // =================================================================================================================




    // Tabla del Resumen de madures digital global y por perspectivas
    // =================================================================================================================
    private JScrollPane getScrollPaneResumenGeneralPerspectives() {
        if (scrollPaneResumenGeneralPerspectives == null) {
            scrollPaneResumenGeneralPerspectives = new JScrollPane();
            scrollPaneResumenGeneralPerspectives.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

            scrollPaneResumenGeneralPerspectives.setViewportView(getTableResumenGeneralPerspectives());
            scrollPaneResumenGeneralPerspectives.setBorder(null);

            // Primero vaciamos el table model para que no se dupliquen las columnas
            model = new DefaultTableModel();
            model.addColumn("");
            model.addColumn("<html> <p align: center>Madurez Digital<br>(MDm) X̅ máximo</p></html>");
            model.addColumn("<html> <p align: center>Madurez Digital (MDr)<br>X̅ real de autoevaluación</p></html>");
            model.addColumn("<html> <p align: center>Índice de Madurez<br>Digital (IMD)%</p></html>");
            model.addColumn("<html> <p align: center>Nivel de Madurez Digital<br> (NMD)</p></html>");
            actualizarTablaPerspectives();

            int heightTable = (50 * getTableResumenGeneralPerspectives().getRowCount()) +
                    getTableResumenGeneralPerspectives().getTableHeader().getBounds().height + 55;

            int y = scrollPaneResumenGeneralAmbits.getY() + scrollPaneResumenGeneralAmbits.getHeight() + 10;
            scrollPaneResumenGeneralPerspectives.setBounds(15, y,970, heightTable);
        }
        return scrollPaneResumenGeneralPerspectives;
    }

    private JTable getTableResumenGeneralPerspectives() {
        if (resumenGeneralPerspectives == null) {
            resumenGeneralPerspectives = new JTable() {
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return false;
                }
            };

            resumenGeneralPerspectives.getTableHeader().setReorderingAllowed(false);
            resumenGeneralPerspectives.setModel(new DefaultTableModel(
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
            resumenGeneralPerspectives.setShowVerticalLines(false);
            resumenGeneralPerspectives.getTableHeader().setBackground(new Color(8, 52, 128));
            resumenGeneralPerspectives.getTableHeader().setForeground(Color.white);
            resumenGeneralPerspectives.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 15));
            resumenGeneralPerspectives.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);

            resumenGeneralPerspectives.setFont(new Font("Arial", Font.PLAIN, 14));
            resumenGeneralPerspectives.setRowHeight(50);


        }
        return resumenGeneralPerspectives;
    }


    public void actualizarTablaPerspectives() {
        while (model.getRowCount() > 0)
            model.removeRow(0);

        Object[] ob = new Object[5];
        ArrayList<Perspectiva> perspectivas = Controlador.getPerspectiva(0);

        // Valores para el resumen da Madurez Digital de Perspectivas
        model.addRow(new String[]{"<html><p><strong>PERSPECTIVAS (MDP) </strong></p></html>"});


        for (Perspectiva perspectiva : perspectivas) {
            ob[0] = "<html> <p style=\"text-align: left; vertical-align: down;\">" + perspectiva.getNombre_perspectiva() + "</p></html>";
            ob[1] = "<html> <p>" + 4.0f + "</p></html>";
            ob[2] = Float.parseFloat(String.valueOf(perspectiva.calculate_MDr_IMD()[0]));
            ob[3] = "<html> <p><strong>" + Float.parseFloat(String.valueOf(perspectiva.calculate_MDr_IMD()[1])) + "</strong></p></html>";
            ob[4] = perspectiva.calculate_MDr_IMD()[1];
            model.addRow(ob);
        }

        resumenGeneralPerspectives.setModel(model);
        for (int i = 0; i < resumenGeneralPerspectives.getColumnCount(); i++) {
            resumenGeneralPerspectives.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRendererGeneralPerspective());
        }

        resumenGeneralPerspectives.getColumnModel().getColumn(0).setMinWidth(194);
        resumenGeneralPerspectives.getColumnModel().getColumn(1).setMinWidth(194);
        resumenGeneralPerspectives.getColumnModel().getColumn(2).setMinWidth(194);
        resumenGeneralPerspectives.getColumnModel().getColumn(3).setMinWidth(194);
        resumenGeneralPerspectives.getColumnModel().getColumn(4).setMinWidth(194);


    }
    // =========================================================================

}
