package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;
import logic.DAO.DAOPerspectiva;
import logic.Entitys.Perspectiva;
import logic.useful.Controlador;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
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


    private int donutChartWidth;
    private int donutCartHeight;

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

    private HorizontalBarChart imdpChart;


    public int getDonutCartHeight() {
        return donutCartHeight;
    }

    public void setDonutCartHeight(int donutCartHeight) {
        this.donutCartHeight = donutCartHeight;
    }

    public int getDonutChartWidth() {
        return donutChartWidth;
    }

    public void setDonutChartWidth(int donutChartWidth) {
        this.donutChartWidth = donutChartWidth;
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

    public ChartPerspective(){
        setLayout(null);

        add(getHeader()); //0

//        add(getIndicatorIndex1()); //1
//        add(getPieChartDisOrg()); //2
//
//        add(getIndicatorIndex2()); //3
//        add(getPieChartTecInfEst());//4
//
//        add(getIndicatorIndex3());//5
//        add(getPieChartCompEst());//6
//
//        add(getIndicatorIndex4());//7
//        add(getPieChartProcesos());//8
//
//        add(getIndicatorIndex5());//9
//        add(getPieChartCentClient());//10
//
//        add(getIndicatorIndex6());//11
//        add(getPieChartFinanzas());//12

        //add(getImdpChart());//13

        getPerspectivesDonutsCharts();
        add(getImdpChart());

    }

    private void getPerspectivesDonutsCharts(){
        ArrayList<Perspectiva> perspectivas = Controlador.getPerspectiva(0);
        String nombrePersp;
        float pnts;
        int x = 1;
        int y = header.getHeight() + 20;
        int width = 0;
        int index = 0;

        for(int i = 0; i < perspectivas.size() / 2; i++){
            for(int j = 0; j < 2; j++){
                index += j;
                nombrePersp = perspectivas.get(index).getNombre_perspectiva();
                pnts = perspectivas.get(index).calculate_MDr_IMD()[1];

                DonutPie dp = new DonutPie("IMDP: "+nombrePersp, pnts, 50, 100, 100, 100);

                dp.setLocation(x, y);
                this.add(dp);

                x += dp.getWidth() - 120;
                setDonutCartHeight(dp.getHeight());
                setSize(1000, y + dp.getHeight());
            }
            y += donutCartHeight;
            x = 1;
            index++;
        }

        setDonutChartWidth(width);
    }

    private HorizontalBarChart getImdpChart(){
        if(imdpChart == null){
            imdpChart = new HorizontalBarChart(null, Controlador.getPerspectiva(0), null);
            imdpChart.setBounds(400, header.getHeight() + 80, 600, 430);
            imdpChart.setBackground(Color.white);
            setVisible(true);
        }
        return imdpChart;
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

    // ============================================================================

    // Esta sección de código se encarga de crear las gráficas de anillo para cada PERSPECTIVA
    // ============================================================================
    private JLabel getPieChartDisOrg(){
        if(pieChartDisOrg == null){
            pieChartDisOrg = new JLabel();

            String value = String.valueOf(index1);

            File file = new File("/util/chartsPython/graficaCircular4.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
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

            File file = new File("/util/chartsPython/graficaCircular5.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
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

            File file = new File("/util/chartsPython/graficaCircular6.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
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

            File file = new File("/util/chartsPython/graficaCircular7.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
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

            File file = new File("/util/chartsPython/graficaCircular9.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
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


//    // Esta seccion de codigo se encarga de crear la grafica de barra con las perspectivas
//    private JLabel getImdpChart(){
//        if(imdpChart == null){
//            imdpChart = new JLabel();
//
//            String categories  = "['Finanzas', 'Centralidad en el cliente', 'Procesos', 'Competencias estratégicas', " +
//                    "'Tecnologías e información \\n estratégicas', 'Diseño Organizacional']";
//
//            String values = "[" + index6 + "," + index5 + "," + index4 + "," + index3 + "," + index2 + "," + index1 + "]";
//            String title = "'Índices de Madurez Digital por Perspectivas \\n(IMDP) %'";
//
//            File file = new File("/util/chartsPython/graficaBarra2.png");
//            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
//                PythonExecutor.imdChart(categories , values, "6", "4", "'2'", title);
//
//            Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaBarra2.png")));
//            imdpChart.setIcon(icon);
//            imdpChart.setBounds(400, header.getHeight() + 100, icon.getIconWidth(), icon.getIconHeight());
//        }
//        return imdpChart;
//    }


}
