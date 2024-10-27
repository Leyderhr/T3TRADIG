package Interface.newInterface.Chart;

import logic.Entitys.Ambito;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class HorizontalBarChart extends JPanel {

    public HorizontalBarChart(ArrayList<Ambito> ambits) {

        setBackground(Color.white);
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ambits.get(0).setNombre_ambito("b");
        ambits.get(1).setNombre_ambito("c");
        for(Ambito a: ambits){
            a.setCant_ptos(50);
            ds.addValue(a.getCant_ptos(), a.getNombre_ambito(), "");
        }
//        //Cargar todas las barras
//        ds.addValue(ambits.get(0), "Diseño Organizacional", "");
//        ds.addValue(colores.get(1), "Tecnologías \n e información estratégicas", "");
//        ds.addValue(colores.get(2), "Competencias estratégicas", "");
//        ds.addValue(colores.get(3), "Procesos", "");
//        ds.addValue(colores.get(4), "Centralidad en el cliente", "");
//        ds.addValue(colores.get(5), "Finanzas", "");

        JFreeChart jf = ChartFactory.createBarChart("Índice de Madurez Digital por Perspectivas \n(IMDP)%",
                "", "",
                ds, PlotOrientation.HORIZONTAL, true, false, false);
        ChartPanel chartPanel = new ChartPanel(jf);

        //Darle tamaño
        chartPanel.setPreferredSize(new Dimension(570, 430));

        CategoryPlot categoryPlot = (CategoryPlot) jf.getPlot();

        //Cambiar color de fondo
        categoryPlot.setBackgroundPaint(Color.white);


        //Poner la leyenda en el lado izquierdo
        jf.getLegend().setPosition(RectangleEdge.LEFT);

        //Espaciar la leyenda para que quede al lado de cada barra
        jf.getLegend().setItemLabelPadding(new RectangleInsets(13, 0, 17, 0));

        //Darle formato
        Font font = new Font("Arial", Font.BOLD, 16);
        jf.getLegend().setItemFont(font);

        //Poner los numeros de las x abajo
        categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        categoryPlot.getRangeAxis().setDefaultAutoRange(new Range(0.00, 100.00));
        categoryPlot.getRangeAxis().setAutoRange(false);
        categoryPlot.getRangeAxis().setAutoRangeMinimumSize(0.1);
        categoryPlot.getRangeAxis().setRange(0.00, 100.00);
        categoryPlot.getRangeAxis().centerRange(50.00);



        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();

        //Cambiar los colores dependiendo del valor
        Color colorUniforme = null;

        for (int i = 0; i < ambits.size(); i++) {
            int c = ambits.get(i).getCant_ptos();
            if (c >= 0 && c <= 25) {
                colorUniforme = new Color(76, 190, 237);
                renderer.setSeriesPaint(i, colorUniforme); // Para la primera serie
            } else if (c > 25 && c <= 50) {
                colorUniforme = new Color(47, 228, 77);
                renderer.setSeriesPaint(i, colorUniforme); // Para la segunda serie

            } else if (c > 50 && c <= 75) {
                colorUniforme = new Color(230, 227, 12);
            } else

                colorUniforme = new Color(225, 34, 34);
            renderer.setSeriesPaint(i, colorUniforme);
        }

        //Cambiar la forma de la barra
        renderer.setBarPainter(new StandardBarPainter());

        //Ponerle sombras
        renderer.setShadowVisible(true);

        setVisible(true);
        add(chartPanel);


    }
}
