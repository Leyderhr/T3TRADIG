package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChartPerspective extends JPanel{


    // * Declaración de variables
    /**
     * <p>index1 ⇒ Es el índice de la perspectiva "Diseño Organizacional"<p/>
     */
    private float index1;

    /**
     * <p>index2 ⇒ Es el índice de la perspectiva "Tecnologías e información estratégicas"<p/>
     */
    private float index2;

    /**
     * <p>index3 ⇒ Es el índice de la perspectiva "Competencias estratégicas"<p/>
     */
    private float index3;

    /**
     * <p>index4 ⇒ Es el índice de la perspectiva "Procesos"<p/>
     */
    private float index4;

    /**
     * <p>index5 ⇒ Es el índice de la perspectiva "Centralidad en el cliente"<p/>
     */
    private float index5;

    /**
     * <p>index6 ⇒ Es el índice de la perspectiva "Finanzas"<p/>
     */
    private float index6;


    private int height;

    private JLabel header;

    private JLabel indicatorIndex1;
    private JLabel indicatorIndex2;
    private JLabel indicatorIndex3;
    private JLabel indicatorIndex4;
    private JLabel indicatorIndex5;
    private JLabel indicatorIndex6;

    private JLabel pieChartDisOrg;
    private JLabel pieChartTecInfEst;
    private JLabel pieChartCompEst;
    private JLabel pieChartProcesos;
    private JLabel pieChartCentClient;
    private JLabel pieChartFinanzas;

    private JLabel imdpChart;

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

    public float getIndex4() {
        return index4;
    }

    public void setIndex4(float index4) {
        this.index4 = index4;
    }

    public float getIndex5() {
        return index5;
    }

    public void setIndex5(float index5) {
        this.index5 = index5;
    }

    public float getIndex6() {
        return index6;
    }

    public void setIndex6(float index6) {
        this.index6 = index6;
    }

    public ChartPerspective(float index1, float index2, float index3, float index4, float index5, float index6){
        setLayout(null);

        setIndex1(index1);
        setIndex2(index2);
        setIndex3(index3);
        setIndex4(index4);
        setIndex5(index5);
        setIndex6(index6);



        add(getHeader()); //0

        add(getIndicatorIndex1()); //1
        add(getPieChartDisOrg()); //2

        add(getIndicatorIndex2()); //3
        add(getPieChartTecInfEst());//4

        add(getIndicatorIndex3());//5
        add(getPieChartCompEst());//6

        add(getIndicatorIndex4());//7
        add(getPieChartProcesos());//8

        add(getIndicatorIndex5());//9
        add(getPieChartCentClient());//10

        add(getIndicatorIndex6());//11
        add(getPieChartFinanzas());//12

        add(getImdpChart());//13

        setSize(1000, 650);
    }

    private JLabel getHeader(){
        if (header == null){
            header = new JLabel("Resultados: Madurez Digital por perspectivas (MDP)");
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
            indicatorIndex1.setBounds(140, 250, 300, 20);
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
            indicatorIndex2.setBounds(325, 250, 300, 20);
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

    private JLabel getIndicatorIndex3() {
        if (indicatorIndex3 == null) {
            indicatorIndex3 = new JLabel();
            indicatorIndex3.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex3.setBounds(140, 445, 300, 20);
            indicatorIndex3.setOpaque(false);
            indicatorIndex3.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex3.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (index3 >= 0 && index3 <= 25) {
                indicatorIndex3.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex3.setIcon(icon);
            } else if (index3 > 25 && index3 <= 50) {
                indicatorIndex3.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex3.setIcon(icon);
            } else if (index3 > 50 && index3 <= 75) {
                indicatorIndex3.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex3.setIcon(icon);
            } else {
                indicatorIndex3.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex3.setIcon(icon);
            }
        }
        return indicatorIndex3;
    }


    private JLabel getIndicatorIndex4() {
        if (indicatorIndex4 == null) {
            indicatorIndex4 = new JLabel();
            indicatorIndex4.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex4.setBounds(325, 445, 300, 20);
            indicatorIndex4.setOpaque(false);
            indicatorIndex4.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex4.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (index4 >= 0 && index4 <= 25) {
                indicatorIndex4.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex4.setIcon(icon);
            } else if (index4 > 25 && index4 <= 50) {
                indicatorIndex4.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex4.setIcon(icon);
            } else if (index4 > 50 && index4 <= 75) {
                indicatorIndex4.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex4.setIcon(icon);
            } else {
                indicatorIndex4.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex4.setIcon(icon);
            }
        }
        return indicatorIndex4;
    }

    private JLabel getIndicatorIndex5() {
        if (indicatorIndex5 == null) {
            indicatorIndex5 = new JLabel();
            indicatorIndex5.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex5.setBounds(140, 620, 300, 20);
            indicatorIndex5.setOpaque(false);
            indicatorIndex5.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex5.setHorizontalTextPosition(SwingConstants.RIGHT);

            if (index5 >= 0 && index5 <= 25) {
                indicatorIndex5.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex5.setIcon(icon);
            } else if (index5 > 25 && index5 <= 50) {
                indicatorIndex5.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex5.setIcon(icon);
            } else if (index5 > 50 && index5 <= 75) {
                indicatorIndex5.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex5.setIcon(icon);
            } else {
                indicatorIndex5.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex5.setIcon(icon);
            }
        }
        return indicatorIndex5;
    }


    private JLabel getIndicatorIndex6() {
        if (indicatorIndex6 == null) {
            indicatorIndex6 = new JLabel();
            indicatorIndex6.setFont(new Font("Arial", Font.PLAIN, 18));
            indicatorIndex6.setBounds(325, 620, 300, 20);
            indicatorIndex6.setOpaque(false);
            indicatorIndex6.setHorizontalAlignment(SwingConstants.LEFT);
            indicatorIndex6.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (index6 >= 0 && index6 <= 25) {
                indicatorIndex6.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicatorIndex6.setIcon(icon);
            } else if (index6 > 25 && index6 <= 50) {
                indicatorIndex6.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicatorIndex6.setIcon(icon);
            } else if (index6 > 50 && index6 <= 75) {
                indicatorIndex6.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicatorIndex6.setIcon(icon);
            } else {
                indicatorIndex6.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicatorIndex6.setIcon(icon);
            }
        }
        return indicatorIndex6;
    }
    // ============================================================================

    // Esta sección de código se encarga de crear las gráficas de anillo para cada PERSPECTIVA
    // ============================================================================
    private JLabel getPieChartDisOrg(){
        if(pieChartDisOrg == null){
            pieChartDisOrg = new JLabel();

            String value = String.valueOf(index1);
            PythonExecutor.pieChart("'IMDP: Diseño \\norganizacional'", value, "1.5", "1.5", "'4'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular4.png")));
            pieChartDisOrg.setIcon(icon);
            pieChartDisOrg.setBounds(40, header.getHeight() + 20 , icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartDisOrg;
    }


    private JLabel getPieChartTecInfEst(){
        if(pieChartTecInfEst == null){
            pieChartTecInfEst = new JLabel();

            String value = String.valueOf(index2);
            PythonExecutor.pieChart("'IMDP: Tecnologías e \\ninformación estratégicas'", value, "1.5", "1.5", "'5'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular5.png")));
            pieChartTecInfEst.setIcon(icon);

            int x = pieChartDisOrg.getWidth() + pieChartDisOrg.getX();
            pieChartTecInfEst.setBounds(x, header.getHeight() + 20 , icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartTecInfEst;
    }

    private JLabel getPieChartCompEst(){
        if(pieChartCompEst == null){
            pieChartCompEst = new JLabel();

            String value = String.valueOf(index3);
            PythonExecutor.pieChart("'IMDP: Competencias \\nestratégicas'", value, "1.5", "1.5", "'6'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular6.png")));
            pieChartCompEst.setIcon(icon);

            int y = pieChartDisOrg.getHeight() + pieChartDisOrg.getY() + 10;
            pieChartCompEst.setBounds(10, y, icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartCompEst;
    }


    private JLabel getPieChartProcesos(){
        if(pieChartProcesos == null){
            pieChartProcesos = new JLabel();

            String value = String.valueOf(index4);
            PythonExecutor.pieChart("'IMDP: Procesos'", value, "1.5", "1.5", "'7'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular7.png")));
            pieChartProcesos.setIcon(icon);

            int x = pieChartCompEst.getWidth() + pieChartCompEst.getX() + 15;
            int y = pieChartTecInfEst.getHeight() + pieChartTecInfEst.getY() + 32;
            pieChartProcesos.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartProcesos;
    }


    private JLabel getPieChartCentClient(){
        if(pieChartCentClient == null){
            pieChartCentClient = new JLabel();

            String value = String.valueOf(index5);
            PythonExecutor.pieChart("'IMDP: Centralidad en el\\n cliente'", value, "1.5", "1.5", "'8'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular8.png")));
            pieChartCentClient.setIcon(icon);

            int y = pieChartCompEst.getHeight() + pieChartCompEst.getY();
            pieChartCentClient.setBounds(1, y , icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartCentClient;
    }


    private JLabel getPieChartFinanzas(){
        if(pieChartFinanzas == null){
            pieChartFinanzas = new JLabel();

            String value = String.valueOf(index6);
            PythonExecutor.pieChart("'IMDP: Finanzas'", value, "1.5", "1.5", "'9'");
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular9.png")));
            pieChartFinanzas.setIcon(icon);

            int x = pieChartCentClient.getWidth() + pieChartCentClient.getX() + 5;
            int y = pieChartProcesos.getHeight() + pieChartProcesos.getY() + 21;
            pieChartFinanzas.setBounds(x, y , icon.getIconWidth(), icon.getIconHeight());
        }
        return pieChartFinanzas;
    }
    // ============================================================================



    private JLabel getImdpChart(){
        if(imdpChart == null){
            imdpChart = new JLabel();

            String categories  = "['Finanzas', 'Centralidad en el cliente', 'Procesos', 'Competencias estratégicas', " +
                    "'Tecnologías e información \\n estratégicas', 'Diseño Organizacional']";

            String values = "[" + index6 + "," + index5 + "," + index4 + "," + index3 + "," + index2 + "," + index1 + "]";
            String title = "'Índices de Madurez Digital por Perspectivas \\n(IMDP) %'";
            PythonExecutor.imdChart(categories , values, "6", "4", "'2'", title);
            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaBarra2.png")));
            imdpChart.setIcon(icon);
            imdpChart.setBounds(400, header.getHeight() + 100, icon.getIconWidth(), icon.getIconHeight());
        }
        return imdpChart;
    }


}
