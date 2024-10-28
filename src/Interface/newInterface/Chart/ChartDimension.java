package Interface.newInterface.Chart;

import logic.useful.Controlador;

import javax.swing.*;
import java.awt.*;

public class ChartDimension extends JPanel {

    private JLabel header;
    private HorizontalBarChart horizontalBarChartDimension;
    private DonutChartPanel donutPie2;

    public ChartDimension(){
        setLayout(null);

        add(getHeader());
        add(getHorizontalBarChartDimension());
        add(getDonutChart());
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
            horizontalBarChartDimension.setBounds(1, header.getHeight() + 20, 700, 760);
            setOpaque(true);
            horizontalBarChartDimension.setBackground(Color.WHITE);
            setVisible(true);
        }
        return horizontalBarChartDimension;
    }

    private DonutChartPanel getDonutChart(){
        if(donutPie2 == null){
            donutPie2 = new DonutChartPanel(Controlador.getDimension(0));
            donutPie2.setBounds(700, header.getHeight() + 200, 300, 500);
            setOpaque(true);
            donutPie2.setBackground(Color.WHITE);
            setVisible(true);
        }
        return donutPie2;
    }
}
