package Interface.newInterface.Chart;

import logic.Entitys.Ambito;
import logic.Entitys.Perspectiva;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;

import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class HorizontalBarChart extends JPanel {

    public HorizontalBarChart(ArrayList<Ambito> ambits, ArrayList<Perspectiva> perspectivas, ArrayList<logic.Entitys.Dimension> dimensions) {

        if (perspectivas == null && dimensions == null && ambits != null) {
            createChart(ambits, null, null);
        } else if (ambits == null && dimensions == null && perspectivas != null)
            createChart(null, perspectivas, null);
        else
            createChart(null, null, dimensions);


    }


    private void createChart(ArrayList<Ambito> ambitos, ArrayList<Perspectiva> perspectivas, ArrayList<logic.Entitys.Dimension> dimensions) {

        setBackground(Color.white);
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        int classType = 0;

        if (ambitos != null) {
            classType = 1;
        } else if (perspectivas != null)
            classType = 2;
        else if (dimensions != null)
            classType = 3;


        JFreeChart jf = null;
        ChartPanel chartPanel = null;
        CategoryPlot categoryPlot;

        switch (classType) {
            case 1:
                for (Ambito a : ambitos) {
                    String texto = dividirTexto(a.getNombre_ambito(), 27);
                    ds.addValue(a.calculate_MDr_IMD()[1], texto, "");
                    jf = ChartFactory.createBarChart("Índice de Madurez Digital por ámbitos \n(IMDA)%",
                            "", "",
                            ds, PlotOrientation.HORIZONTAL, true, false, false);
                }
                chartPanel = new ChartPanel(jf);

                //Darle tamaño
                chartPanel.setPreferredSize(new Dimension(570, 430));

                //Espaciar la leyenda para que quede al lado de cada barra
                if(jf != null)
                    jf.getLegend().setItemLabelPadding(new RectangleInsets(13, 0, 17, 0));
                break;
            case 2:
                for (Perspectiva p : perspectivas) {
                    String texto = dividirTexto(p.getNombre_perspectiva(), 25);

                    ds.addValue(p.calculate_MDr_IMD()[1], texto, "");
                    jf = ChartFactory.createBarChart("Índice de Madurez Digital por Perspectivas \n(IMDP)%",
                            "", "",
                            ds, PlotOrientation.HORIZONTAL, true, false, false);
                }
                chartPanel = new ChartPanel(jf);

                //Darle tamaño
                chartPanel.setPreferredSize(new Dimension(570, 430));

                //Espaciar la leyenda para que quede al lado de cada barra
                if(jf != null)
                    jf.getLegend().setItemLabelPadding(new RectangleInsets(13, 0, 17, 0));
                break;
            case 3:
                for (logic.Entitys.Dimension d : dimensions) {
                    String texto = dividirTexto(d.getNombre_dimension(), 100);

                    ds.addValue(d.calculate_MDr_IMD()[1], texto, "");
                    jf = ChartFactory.createBarChart("Índice de Madurez Digital por dimensiones \n(IMDD)%",
                            "", "",
                            ds, PlotOrientation.HORIZONTAL, true, false, false);
                }
                chartPanel = new ChartPanel(jf);

                //Darle tamaño
                chartPanel.setPreferredSize(new Dimension(700, 750));

                //Espaciar la leyenda para que quede al lado de cada barra
                if(jf != null) {
                    jf.getLegend().setItemLabelPadding(new RectangleInsets(10, 0, 3, 0));
                    categoryPlot = jf.getCategoryPlot();
                    BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
                    renderer.setItemMargin(0.1);
                }

                break;
            default:
                break;
        }


        if (jf != null) {
            categoryPlot = (CategoryPlot) jf.getPlot();


            //Cambiar color de fondo
            categoryPlot.setBackgroundPaint(Color.white);


            //Poner la leyenda en el lado izquierdo
            jf.getLegend().setPosition(RectangleEdge.LEFT);

            jf.getLegend().setHorizontalAlignment(HorizontalAlignment.RIGHT);

            //Espaciar la leyenda para que quede al lado de cada barra
            //jf.getLegend().setItemLabelPadding(new RectangleInsets(13, 0, 17, 0));

            //Darle formato
            Font font = new Font("Arial", Font.BOLD, 14);
            jf.getLegend().setItemFont(font);


            //Poner los números de las x abajo
            categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
            categoryPlot.getRangeAxis().setDefaultAutoRange(new Range(0.00, 100.00));
            categoryPlot.getRangeAxis().setAutoRange(false);
            categoryPlot.getRangeAxis().setAutoRangeMinimumSize(0.1);
            categoryPlot.getRangeAxis().setRange(0.00, 100.00);
            categoryPlot.getRangeAxis().centerRange(50.00);

            BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());

            NumberAxis rangeAxis = (NumberAxis)categoryPlot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer renderer1 = (BarRenderer)categoryPlot.getRenderer();
            renderer1.setDrawBarOutline(false);
            CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
            categoryAxis.setCategoryMargin(0.2);
            categoryAxis.setUpperMargin(0.02);
            categoryAxis.setLowerMargin(0.02);
//            NumberAxis rangeAxis = (NumberAxis) categoryPlot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//            rangeAxis.setUpperMargin(0.10);

            //Cambiar los colores dependiendo del valor
            Color colorUniforme = null;

            switch (classType) {
                case 1:
                    for (int i = 0; i < ambitos.size(); i++) {
                        float c = ambitos.get(i).calculate_MDr_IMD()[1];
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
                    break;
                case 2:
                    for (int i = 0; i < perspectivas.size(); i++) {
                        float c = perspectivas.get(i).calculate_MDr_IMD()[1];
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
                    break;
                case 3:
                    for (int i = 0; i < dimensions.size(); i++) {
                        float c = dimensions.get(i).calculate_MDr_IMD()[1];
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
                default:
                    break;
            }

            //Cambiar la forma de la barra
            renderer.setBarPainter(new StandardBarPainter());

            //Ponerle sombras
            renderer.setShadowVisible(true);

        }
        setVisible(true);
        add(chartPanel);

    }

    private String dividirTexto(String texto, int anchoMaximo) {
        ArrayList<String> lineas = new ArrayList<>();
        StringBuilder resultado = new StringBuilder();


        // Dividir el texto en palabras
        String[] palabras = texto.split(" ");
        StringBuilder lineaActual = new StringBuilder();

        for (String palabra : palabras) {
            // Verificar si la palabra cabe en la línea actual
            if (lineaActual.length() + palabra.length() + 1 <= anchoMaximo) {
                if (lineaActual.length() > 0) {
                    lineaActual.append(" "); // Agregar espacio si no es la primera palabra
                }
                lineaActual.append(palabra);
            } else {
                // Agregar la línea actual a la lista y comenzar una nueva línea
                resultado.append(lineaActual.toString()).append("\n");
                lineaActual = new StringBuilder(palabra); // Comenzar nueva línea con la palabra actual
            }
        }

        // Agregar la última línea si no está vacía
        if (lineaActual.length() > 0) {
            resultado.append(lineaActual.toString());
        }

        return resultado.toString();
    }

}
