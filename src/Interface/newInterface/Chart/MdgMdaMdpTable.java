package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;
import logic.DAO.DAOAmbito;
import logic.DAO.DAODimension;
import logic.DAO.DAOPerspectiva;
import logic.Entitys.Ambito;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;
import logic.useful.Controlador;
import util.table.MyTableCellRendererCeldas;
import util.table.MyTableCellRendererGeneralTables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;

public class MdgMdaMdpTable extends JPanel {

    private JTable resumenGeneralAmbits;
    private float index1;

    private float index2;

    private float index3;

    private JLabel header;
    private JLabel generalIndexTable;

    private final DefaultTableModel model = new DefaultTableModel();
    private ArrayList<Ambito> ambitos;
    private ArrayList<Perspectiva> perspectivas;
    private ArrayList<Dimension> dimensions;


    private JScrollPane scrollPane;

    public float getIndex1() {
        return index1;
    }

    public void setIndex1(float index1) {
        this.index1 = index1;
    }

    public float getIndex2() {
        return index2;
    }

    public void setIndex2(float index2) {
        this.index2 = index2;
    }

    public float getIndex3() {
        return index3;
    }

    public void setIndex3(float index3) {
        this.index3 = index3;
    }

    public MdgMdaMdpTable(float index1, float index3, float index2){
        setLayout(null);
        setIndex1(index1);
        setIndex2(index2);
        setIndex3(index3);

        add(getHeader());
        //add(getGeneralIndexTable());
        //add(getTableResumenGeneralAmbits());
        add(getScrollPane());
        setSize(1000, 500);
    }

    private JLabel getHeader(){
        if (header == null){
            header = new JLabel("Resultados: Resumen madurez digital MDG, MDA y MDP");
            header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 30));
            header.setBounds(20, 10, 850, 80);
            header.setOpaque(false);
            header.setForeground(new Color(8, 52, 128));
        }
        return header;
    }

    private JLabel getGeneralIndexTable(){
        if(generalIndexTable == null){
            generalIndexTable = new JLabel();


            String rows = "[['GLOBAL (MDG)',"+ index1 +"," + index2 +"," + index3 + "," + "'BÁSICO'], " +
                    "['ÁMBITOS (MDA)', '', '', '', 'ẋ']]";
            String columnLables = "['', 'Madurez Digital\\n(MDm) X maximo', 'Madurez Digital (MDr)', 'Indice de Madurez', 'Nivel de Madurez Digital']";

            PythonExecutor.createTable(rows, columnLables);
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/tabla.png")));
            generalIndexTable.setIcon(icon);

            int y = header.getY()+ header.getHeight();
            generalIndexTable.setBounds(1,  y + 20, icon.getIconWidth(), icon.getIconHeight());
        }
        return generalIndexTable;
    }





    // Tabla del Resumen de madures digital global y por ambitos
    // =========================================================================
    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setBounds(15, 100, 970, 329);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            scrollPane.setViewportView(getTableResumenGeneralAmbits());

            model.addColumn("");
            model.addColumn("<html> <p align: center>Madurez Digital<br>(MDm) X̅ máximo</p></html>");
            model.addColumn("<html> <p align: center>Madurez Digital (MDr)<br>X̅ real de autoevaluación</p></html>");
            model.addColumn("<html> <p align: center>Índice de Madurez<br>Digital (IMD)%</p></html>");
            model.addColumn("<html> <p align: center>Nivel de Madurez Digital<br> (NMD)</p></html>");
            actualizarTablaAmbits();

        }
        return scrollPane;
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
                            "", "<html> <p align: center>Madurez Digital (MDm) X máximo</p></html>" ,
                            "<html> <p align: center>Madurez Digital (MDr) X real de autoevaluación</p></html>" ,
                            "<html> <p align: center>Índice de Madurez Digital (IMD)%</p></html>" ,
                            "<html> <p align: center>Nivel de Madurez Digital (NMD)</p></html>" ,
                    }
            ));

            resumenGeneralAmbits.setShowVerticalLines(false);
            resumenGeneralAmbits.getTableHeader().setBackground(new Color(8, 52, 128));
            resumenGeneralAmbits.getTableHeader().setForeground(Color.white);
            resumenGeneralAmbits.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 15));
            resumenGeneralAmbits.getTableHeader().setAlignmentX(CENTER_ALIGNMENT);



        }
        return resumenGeneralAmbits;
    }


    public void actualizarTablaAmbits() {
        while (model.getRowCount() > 0)
            model.removeRow(0);

        ambitos = Controlador.getAmbitos();
        for (Ambito a: ambitos) {

            Object[] ob = new Object[5];
            ob[0] = a.getNombre_ambito();
            ob[1] = (a.getCant_perspectivas() * 4) / a.getCant_perspectivas();
            ob[2] = a.getCant_ptos()  / a.getCant_perspectivas();
            ob[3] = a.getCant_ptos() / a.getCant_perspectivas() * 4;
            ob[4] = (a.getCant_ptos() / a.getCant_perspectivas() * 4) > 25 ? "INICIAL":"BASICO";
            model.addRow(ob);
        }
        resumenGeneralAmbits.setModel(model);
        for(int i = 0; i< resumenGeneralAmbits.getColumnCount(); i++){
            resumenGeneralAmbits.getColumnModel().getColumn(i).setCellRenderer(new MyTableCellRendererGeneralTables());
        }


        resumenGeneralAmbits.getColumnModel().getColumn(0).setMinWidth(200);
    }
    // =========================================================================


}
