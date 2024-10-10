package Interface.newInterface.Chart;


import Interface.export.swing.CircleProgressBar;

import javax.swing.*;
import java.awt.*;


public class ChartAmbits extends JPanel {

    /**
     * Declaración de variables
     * <p>index1 ⇒ Es el índice del ámbito "Capacidades Estratégicas y de creación"<p/>
     * <p>index2 ⇒ Es el índice del ámbito "Resultados de Digitalización"<p/>
     */
    private float index1;
    private float index2;

    public ChartAmbits() {
        setLayout(null);
    }


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

    @Override
    protected void paintComponent(Graphics grphcs) {
        drawChartAmbits(grphcs);
        drawChartAmbitsPastel(grphcs);
        super.paintComponent(grphcs);
    }

    /*Métodos para dibujar las gráficas*/
    //==========================================================

    //Método para dibujar Madurez Digital por Ámbitos (Gráfica de barras horizontales)
    private void drawChartAmbits(Graphics grphcs) {
        Graphics2D g2D = (Graphics2D) grphcs;

        //Dibujamos el encabezado de las tablas
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setFont(new Font("Arial", Font.PLAIN, 20));
        g2D.setColor(new Color(106, 109, 110));
        g2D.drawString("Índice de Madurez digital por ámbitos (IMDA) %", 520, 120);
        g2D.setFont(new Font("Arial", Font.BOLD, 20));
        g2D.setColor(new Color(11, 52, 128, 255));
        g2D.drawString("Resultados: Madurez Digital por ámbitos (MDA)", 30, 50);

        //Establecemos el ancho de las líneas
        BasicStroke basicStroke = new BasicStroke(2);
        g2D.setStroke(basicStroke);


        /*Dibujamos el rectángulo con los valores
        (las líneas tienen una separación de 69 píxeles de ancho)
        (los valores tienen una separación de 65 píxeles de ancho)*/
        g2D.setColor(new Color(1, 176, 239, 255));
        g2D.drawLine(739, 160, 739, 470);
        g2D.drawLine(808, 160, 808, 470);
        g2D.drawLine(877, 160, 877, 470);
        g2D.drawRect(670, 160, 275, 310);

        g2D.setColor(Color.black);
        g2D.setFont(new Font("Arial", Font.BOLD, 14));
        g2D.drawString("0,00", 660, 487);
        g2D.drawString("25,00", 725, 487);
        g2D.drawString("50,00", 790, 487);
        g2D.drawString("75,00", 855, 487);
        g2D.drawString("100,00", 920, 487);


        //Dibujamos la sombra de las barras
        g2D.setColor(new Color(220, 220, 220, 215));
        g2D.fillRoundRect(670, 191, calcBarWidth(this.index1) + 2, 101, 3, 3);
        g2D.fillRoundRect(670, 351, calcBarWidth(this.index2) + 2, 101, 3, 3);

        /*Dibujamos las barras*/
        //Barra indicadora de CAPACIDADES estratégicas y de creación de valor sustentable
        g2D.setColor(new Color(1, 176, 239, 255));
        g2D.fillRoundRect(670, 190, calcBarWidth(this.index1), 100, 3, 3);

        //Barra indicadora de Resultados de digitalización
        g2D.setColor(new Color(146, 209, 79, 255));
        g2D.fillRoundRect(670, 350, calcBarWidth(this.index2), 100, 3, 3);

        /*Dibujamos los indicadores*/
        g2D.setFont(new Font("Arial", Font.BOLD, 16));
        g2D.setColor(Color.black);
        g2D.drawString("CAPACIDADES estratégicas y de", 400, 240);
        g2D.drawString("creación de valor sustentable", 400, 260);
        g2D.drawString("RESULTADOS de digitalización", 400, 400);
        g2D.drawString(""+this.index1, 670 + calcBarWidth(this.index1 + 2), 250);
        g2D.drawString(""+this.index2, 670 + calcBarWidth(this.index2 + 2), 400);

    }

    //Metodo para calcular el tamaño de las barras, para distribuirlas entre los valores
    private int calcBarWidth(float index1) {
        float a;
        float b = 0.0f;
        if (index1 <= 25.0f) {
            a = (index1 / 25.0f) * 100;
            b = (a * 69.0f) / 100;
        } else if (index1 > 25.0f && index1 <= 50.0f) {
            a = (index1 / 50.0f) * 100;
            b = (a * (69.0f * 2.0f)) / 100;
        } else if (index1 > 50.0f && index1 <= 75.0f) {
            a = (index1 / 75.0f) * 100;
            b = (a * (69.0f * 3.0f)) / 100;
        } else {
            a = (index1 / 100.0f) * 100;
            b = (a * (69.0f * 4.0f)) / 100;
        }

        return Math.round(b);
    }


    //Método para dibujar Madurez Digital por Ámbitos (Gráfica de pastel)
    private void drawChartAmbitsPastel(Graphics gr) {
        Graphics2D g2D = (Graphics2D) gr;


        /*Gráfica de pastel que muestra el IMD: CAPACIDADES Estratégicas*/
        //=================================================================================
        /* Se le pasa por el constructor el porciento que debe mostrar, en este caso (index1)*/
        CircleProgressBar a = new CircleProgressBar(index1);
        a.setForeground(new Color(1, 176, 239, 255));
        a.setBounds(100, 150, 200, 200);
        a.setBorder(null);

        ChartAmbits.this.add(a);

        g2D.setFont(new Font("Arial", Font.BOLD, 30));
        g2D.drawString(this.index1 + "%", a.getX() + 50, a.getY() + 110);
        g2D.setFont(new Font("Arial", Font.BOLD, 15));
        g2D.setColor(new Color(106, 109, 110));
        g2D.drawString("IMDA: Capacidades estratégicas y de ", a.getX() - 60, a.getY() - 40);
        g2D.drawString("creación de valor sustentable", a.getX() - 40, a.getY() - 20);
        g2D.setColor(Color.black);

        /*Después de crear el gráfico de pastel, se agrega al panel, no se hace que el
         * método retorne al gráfico xq entonces no actualiza los valores del porciento*/
        //=================================================================================


        /*Gráfica de pastel que muestra el IMD: Resultados de Digitalización*/
        //=================================================================================
        /* Se le pasa por el constructor el porciento que debe mostrar, en este caso (index1)*/
        CircleProgressBar b = new CircleProgressBar(index2);
        b.setForeground(new Color(146, 209, 79, 255));
        b.setBounds(100, 400, 200, 200);
        b.setBorder(null);


        g2D.setFont(new Font("Arial", Font.BOLD, 30));
        g2D.drawString(this.index2 + "%", b.getX() + 50, b.getY() + 110);
        g2D.setFont(new Font("Arial", Font.BOLD, 15));
        g2D.setColor(new Color(106, 109, 110));
        g2D.drawString("IMDA: Resultados de digitalización", b.getX() - 60, b.getY() - 20);
        /*Después de crear el gráfico de pastel, se agrega al panel, no se hace que el
         * método retorne al gráfico xq entonces no actualiza los valores del porciento*/
        ChartAmbits.this.add(b);


    }



}
