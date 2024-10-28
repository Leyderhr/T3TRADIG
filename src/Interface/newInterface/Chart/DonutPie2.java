package Interface.newInterface.Chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.util.ArrayList;

public class DonutPie2  extends JPanel {

    @SuppressWarnings("unchecked")
    public DonutPie2(ArrayList<logic.Entitys.Dimension> dimensions) {
        super();

        Color color = null;

        // Crea el conjunto de datos para el gráfico de anillo
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (logic.Entitys.Dimension d: dimensions){
            dataset.setValue(d.getNombre_dimension(), d.calculate_MDr_IMD()[1]);
            // Determina el color basado en el porcentaje
            color = getColor((int) (d.calculate_MDr_IMD()[1]));
        }

        // Crea el gráfico de anillo
        JFreeChart chart = ChartFactory.createRingChart(
                "<html><p>Cantidad de dimensiones por<br>niveles de madurez digital</p></html>", // Título del gráfico
                dataset, // Conjunto de datos
                false, // No mostrar leyenda
                false, // No mostrar consejos de herramientas
                false // No mostrar URL
        );



        // Configura el gráfico de anillo
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setCircular(false); // Crea un gráfico de anillo
        //plot.setSimpleLabels(false); // No muestra etiquetas
        plot.setLabelGenerator(null); // Elimina el generador de etiquetas
        plot.setLabelFont(new Font("SansSerif", Font.BOLD, 16)); // Configura el estilo del texto central
        plot.setNoDataMessage("Valor no disponible"); // Mensaje para datos vacíos
        plot.setShadowPaint(null); // Elimina la sombra gris


        plot.setSectionPaint(0, color); // Color para la categoría
        plot.setSectionPaint(1, Color.LIGHT_GRAY); // Color para el resto

        // Agrega el valor al centro del gráfico
        //plot.setSimpleLabelOffset(0.1);
        plot.setLabelPaint(Color.BLACK);
        //plot.setSimpleLabelFont(new Font("SansSerif", Font.BOLD, 20));
        plot.setLabelGenerator(new org.jfree.chart.labels.StandardPieSectionLabelGenerator("{0}"));

        // Ajusta el tamaño del círculo central
        //plot.setInteriorGap(1.2); // Ajusta el tamaño del hueco interior
        plot.setCircular(true); // Para que el gráfico sea completamente circular

        // Crea un panel para el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(200, 300));


        setLayout(null);
        setVisible(true);
        add(chartPanel);

    }

    // Determina el color basado en el porcentaje
    private Color getColor(int porcentaje) {
        if (0 <= porcentaje && porcentaje <= 25) {
            return Color.BLUE;
        } else if (25 < porcentaje && porcentaje <= 50) {
            return Color.GREEN;
        } else if (50 < porcentaje && porcentaje <= 75) {
            return Color.YELLOW;
        } else if (75 < porcentaje && porcentaje <= 100) {
            return Color.RED;
        } else {
            return Color.GRAY; // Color por defecto si el porcentaje no está dentro del rango
        }
    }
}
