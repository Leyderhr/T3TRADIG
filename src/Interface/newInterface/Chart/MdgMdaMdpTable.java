package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MdgMdaMdpTable extends JPanel {

    private float index1;

    private float index2;

    private float index3;

    private JLabel header;
    private JLabel generalIndexTable;

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
        add(getGeneralIndexTable());
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
}
