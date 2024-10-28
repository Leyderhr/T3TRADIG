package Interface.newInterface.Chart;

import logic.useful.Controlador;

import javax.swing.*;
import java.awt.*;

public class ChartDimension extends JPanel {

    private JLabel header;
    private HorizontalBarChart horizontalBarChartDimension;
    private DonutPie2 donutPie2;

    public ChartDimension(){
        setLayout(null);

        add(getHeader());
        add(getHorizontalBarChartDimension());
        add(getDonutPie2());
        setSize(1000,horizontalBarChartDimension.getHeight() + header.getHeight() + header.getY());

    }

    private JLabel getHeader(){
        if (header == null){
            header = new JLabel("Resultados: Madurez Digital por dimensiones (MDD)");
            header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 30));
            header.setBounds(20, 10, 800, 80);
            header.setOpaque(false);
            header.setForeground(new Color(8, 52, 128));
        }
        return header;
    }

    private HorizontalBarChart getHorizontalBarChartDimension(){
        if(horizontalBarChartDimension == null){
            horizontalBarChartDimension = new HorizontalBarChart(null, null, Controlador.getDimension(0));
            horizontalBarChartDimension.setBounds(20, header.getHeight() + 20, 700, 760);
            setOpaque(true);
            horizontalBarChartDimension.setBackground(Color.WHITE);
            setVisible(true);
        }
        return horizontalBarChartDimension;
    }

    private DonutPie2 getDonutPie2(){
        if(donutPie2 == null){
            donutPie2 = new DonutPie2(Controlador.getDimension(0));
            donutPie2.setBounds(57, header.getHeight() + 20, 600, 760);
            setOpaque(true);
            donutPie2.setBackground(Color.ORANGE);
            setVisible(true);
        }
        return donutPie2;
    }
}
