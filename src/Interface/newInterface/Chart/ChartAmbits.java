package Interface.newInterface.Chart;


import Interface.export.swing.CircleProgressBar;
import Interface.newInterface.python.PythonExecutor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;


public class ChartAmbits extends JPanel {

    /**
     * Declaración de variables
     * <p>index1 ⇒ Es el índice del ámbito "Capacidades Estratégicas y de creación"<p/>
     * <p>index2 ⇒ Es el índice del ámbito "Resultados de Digitalización"<p/>
     */
    private float index1;
    private float index2;

    private JLabel header;

    private JLabel indicatorIndex1;
    private JLabel pieChartCapEst;

    private JLabel indicatorIndex2;
    private JLabel pieChartResultDig;

    private JLabel imdaChart;

    public ChartAmbits(float index1, float index2) {
        setLayout(null);
        setIndex1(index1);
        setIndex2(index2);

        add(getHeader());

        add(getIndicatorIndex1());
        add(getPieChartCapEst());

        add(getIndicatorIndex2());
        add(getPieChartResultDig());
        add(getImdaChart());
        setSize(1000, imdaChart.getY() + imdaChart.getHeight());
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



    /**
     * Este método se encarga de crear el Encabezado para las gráficas
     */
    private JLabel getHeader(){
        if(header == null){
            header = new JLabel("Resultados: Madurez Digital por ámbitos (MDA)");
            header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 30));
            header.setBounds(20, 10, 800, 80);
            header.setOpaque(false);
            header.setForeground(new Color(8, 52, 128));
        }
        return header;
    }

    private JLabel getIndicatorIndex1() {
        if (indicatorIndex1 == null) {
            indicatorIndex1 = new JLabel();
            indicatorIndex1.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex1.setBounds(145, 300, 300, 20);
            indicatorIndex1.setOpaque(false);
            indicatorIndex1.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex1.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (index1 >= 0 && index1 <= 25) {
                indicatorIndex1.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex1.setIcon(icon);
            } else if (index1 > 25 && index1 <= 50) {
                indicatorIndex1.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex1.setIcon(icon);
            } else if (index1 > 50 && index1 <= 75) {
                indicatorIndex1.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex1.setIcon(icon);
            } else {
                indicatorIndex1.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex1.setIcon(icon);
            }
        }
        return indicatorIndex1;
    }


    private JLabel getIndicatorIndex2() {
        if (indicatorIndex2 == null) {
            indicatorIndex2 = new JLabel();
            indicatorIndex2.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex2.setBounds(145, 515, 300, 20);
            indicatorIndex2.setOpaque(false);
            indicatorIndex2.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex2.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (index2 >= 0 && index2 <= 25) {
                indicatorIndex2.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex2.setIcon(icon);
            } else if (index2 > 25 && index2 <= 50) {
                indicatorIndex2.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex2.setIcon(icon);
            } else if (index2 > 50 && index2 <= 75) {
                indicatorIndex2.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex2.setIcon(icon);
            } else {
                indicatorIndex2.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex2.setIcon(icon);
            }
        }
        return indicatorIndex2;
    }

    private JLabel getPieChartCapEst(){
        if(pieChartCapEst == null){
            pieChartCapEst = new JLabel();

            String value = String.valueOf(index1);

            File file = new File("/util/chartsPython/graficaCircular2.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
                PythonExecutor.pieChart("'IMDA: Capacidades estratégicas\\n y de creación de valor sustentable'", value, "", "2", "'2'");

            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular2.png")));
            pieChartCapEst.setIcon(icon);
            pieChartCapEst.setBounds(1, header.getHeight() + 20, icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartCapEst;
    }

    private JLabel getPieChartResultDig(){
        if(pieChartResultDig == null){
            pieChartResultDig = new JLabel();

            String value = String.valueOf(index2);

            File file = new File("/util/chartsPython/graficaCircular3.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
                PythonExecutor.pieChart("'IMDA: Resultados de digitalización'", value, "", "2", "'3'");

            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular3.png")));
            pieChartResultDig.setIcon(icon);
            pieChartResultDig.setBounds(5, pieChartCapEst.getY() + pieChartCapEst.getHeight() + 20 , icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartResultDig;
    }


    private JLabel getImdaChart(){
        if(imdaChart == null){
            imdaChart = new JLabel();

            String values = "[" + index2 + "," + index1 + "]";
            String categories = "['RESULTADOS de Digitalización', 'CAPACIDADES estratégicas\\n y de creación de valor sustentable']";
            String title = "'Índice de madurez digital por ámbitos (IMDA) %'";

            File file = new File("/util/chartsPython/graficaBarra1.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
                PythonExecutor.imdChart(categories, values, "6", "4", "'1'", title);

            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaBarra1.png")));
            imdaChart.setIcon(icon);
            imdaChart.setBounds(pieChartCapEst.getWidth(), header.getHeight() + 70, icon.getIconWidth(), icon.getIconHeight());
        }
        return imdaChart;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        //drawChartAmbits(grphcs);
        //drawChartAmbitsPastel(grphcs);
        //super.paintComponent(grphcs);
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
