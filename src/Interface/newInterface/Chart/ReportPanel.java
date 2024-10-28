package Interface.newInterface.Chart;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import Interface.newInterface.ButtonMenu;
import Interface.newInterface.Principal1;
import Interface.newInterface.python.PythonExecutor;

import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Objects;

public class ReportPanel extends JScrollPane  implements Printable {


    public static final int Frame_Value = 1;
    private final JScrollPane reportPanel;
    private JPanel chartPanel;

    private JButton btnSavePDF;
    private PanelShadow panelShadow;

    private ChartMDG chartMDG;
    private ChartAmbits chartAmbits;
    private ChartPerspective chartPerspective;
    private MdgMdaMdpTable mdgMdaMdpTable;
    private ChartDimension chartDimension;
    private MddResumenTable mddResumenTable;


    public ReportPanel(Principal1 p) throws Exception {
        reportPanel = new JScrollPane();
        p.getContentPane().add(reportPanel);
        reportPanel.setBounds(241, 100, 1039, 620);
        reportPanel.setBorder(null);
        reportPanel.setVerticalScrollBar(new ScrollBarCustom());
        reportPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        reportPanel.setViewportView(getChartPanel());
        reportPanel.setViewportBorder(null);
        reportPanel.setVisible(false);

    }



    private JPanel getChartPanel() throws Exception {
        if (chartPanel == null) {
            chartPanel = new JPanel();
            chartPanel.setLayout(null);
            chartPanel.setBounds(241, 100, 1030, 900);
            chartPanel.setBackground(Color.WHITE);
            chartPanel.setOpaque(false);
            chartPanel.setBorder(null);
            chartPanel.add(getShadowPanel());
            chartPanel.add(getBtnSavePDF());
            chartPanel.setPreferredSize(new Dimension(1030, panelShadow.getHeight() + btnSavePDF.getHeight()));


        }
        return chartPanel;
    }

    private PanelShadow getShadowPanel() throws Exception {
        if (panelShadow == null) {
            panelShadow = new PanelShadow();
            panelShadow.setLayout(null);
            panelShadow.add(getChartMDG());
            panelShadow.add(getChartAmbits());
            panelShadow.add(getCharPerspective());
            panelShadow.add(getMdgMdaMdpTable());
            panelShadow.add(getChartDimension());
            panelShadow.add(getMddResumenTable());

            int height = chartMDG.getHeight() + chartAmbits.getHeight() + chartPerspective.getHeight()+ mdgMdaMdpTable.getHeight() + chartDimension.getHeight() + mddResumenTable.getHeight();
            panelShadow.setBounds(3, 3, 1030, height);
//            // Crea la imagen del panel para luego exportarla al pdf y word
//            BufferedImage image = new BufferedImage(panelShadow.getWidth(), panelShadow.getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = image.createGraphics();
//            panelShadow.paint(g2d);
//            g2d.dispose();
//
//            // Guarda la imagen
//            ImageIO.write(image, "png", new File("panel2.png"));
        }
        return panelShadow;
    }


    /** Este método se encarga de construir las gráficas de índices porcentuales dependiendo del campo que se elija
     *  ⇒ Puede ser 1, 2 o 3, para referirse a los campos de Ámbitos, Perspectivas y Dimensiones
     *              respectivamente*/
//    private JLabel getMDChart(int field){
//        if(jlabel == null){
//            jlabel = new JLabel("");
//
//            if(field == 1)
//                // Se crea la gráfica de MDA (Madurez Digital por Ámbitos)
//                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[89.00, 25.00]", "6", "4");
//            else if(field == 2)
//                // Se crea la gráfica de MDP (Madurez Digital por Perspectivas)
//                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
//            else{
//                // Se crea la gráfica de MDD (Madurez Digital por Dimensiones)
//                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
//            }
//            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/grafica.png"), "Imagen no encontrada"));
//            jlabel.setIcon(icon);
//            jlabel.setBounds(10,1, 800,400);
//        }
//        return jlabel;
//    }


//    private JLabel getPieChart(int field){
//        if(jPieLabel == null){
//            jPieLabel = new JLabel("");
//
//            if(field == 1)
//                // Se crea la gráfica de MDA (Madurez Digital por Ámbitos)
//                PythonExecutor.pieChart("'Centralidad en el Cliente'", "25.00", "6", "4");
//            else if(field == 2)
//                // Se crea la gráfica de MDP (Madurez Digital por Perspectivas)
//                PythonExecutor.pieChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
//            else{
//                // Se crea la gráfica de MDD (Madurez Digital por Dimensiones)
//                PythonExecutor.pieChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
//            }
//            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular1.png"), "Imagen no encontrada"));
//            jPieLabel.setIcon(icon);
//            jPieLabel.setBounds(30,300, 800,400);
//        }
//        return jPieLabel;
//    }



    private ChartMDG getChartMDG(){
        if (chartMDG == null){
            chartMDG = new ChartMDG(14.58f, 31.25f);
            chartMDG.setLocation(10,10);
            chartMDG.setBackground(Color.WHITE);

//            // Crea la imagen del panel para luego exportarla al pdf y word
//            BufferedImage image = new BufferedImage(chartMDG.getWidth(), chartMDG.getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = image.createGraphics();
//            chartMDG.paint(g2d);
//            g2d.dispose();
//
//            // Guarda la imagen
//            ImageIO.write(image, "png", new File("panel3.png"));

        }
        return chartMDG;
    }

    /**Método para crear todas las cosas que tienen ver con los resultados del
      índice de madurez digital por ámbitos*/
    private ChartAmbits getChartAmbits() {
        if (chartAmbits == null) {
            chartAmbits = new ChartAmbits(50.58f, 31.25f);
            int y = chartMDG.getY() + chartMDG.getHeight() + 22;
            chartAmbits.setLocation(15, y);
            chartAmbits.setBackground(Color.WHITE);

//            // Crea la imagen del panel para luego exportarla al pdf y word
//            BufferedImage image = new BufferedImage(chartAmbits.getWidth(), chartAmbits.getHeight(), BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = image.createGraphics();
//            chartAmbits.paint(g2d);
//            g2d.dispose();
//
//            // Guarda la imagen
//            ImageIO.write(image, "png", new File("panel3.png"));
        }
        return chartAmbits;
    }


    /**Método para crear todas las cosas que tienen ver con los resultados del
     índice de madurez digital por perspectivas*/
    private ChartPerspective getCharPerspective() {
        if (chartPerspective == null) {
            chartPerspective = new ChartPerspective();
            int y = chartAmbits.getY() + chartAmbits.getHeight() + 22;
            chartPerspective.setLocation(15, y);//1180
            chartPerspective.setOpaque(false);
            chartPerspective.setBackground(Color.WHITE);
        }
        return chartPerspective;
    }


    private MdgMdaMdpTable getMdgMdaMdpTable(){
        if(mdgMdaMdpTable == null){
            mdgMdaMdpTable = new MdgMdaMdpTable();
            int y = chartPerspective.getY() + chartPerspective.getHeight() + 22;
            mdgMdaMdpTable.setLocation(12, y);
            mdgMdaMdpTable.setOpaque(false);
            mdgMdaMdpTable.setBackground(Color.WHITE);
        }
        return mdgMdaMdpTable;
    }

    private ChartDimension getChartDimension(){
        if(chartDimension == null){
            chartDimension = new ChartDimension();
            int y = mdgMdaMdpTable.getY() + mdgMdaMdpTable.getHeight() + 22;
            chartDimension.setLocation(12, y);
            chartDimension.setOpaque(true);
            chartDimension.setBackground(Color.WHITE);
        }
        return chartDimension;
    }

    private MddResumenTable getMddResumenTable(){
        if(mddResumenTable == null){
            mddResumenTable  = new MddResumenTable();
            int y = chartDimension.getY() + chartDimension.getHeight() + 22;
            mddResumenTable.setLocation(12, y);
            mddResumenTable.setOpaque(false);
            mddResumenTable.setBackground(Color.WHITE);
        }
        return mddResumenTable;
    }


    private JButton getBtnSavePDF() {

        if (btnSavePDF == null) {
            btnSavePDF = new ButtonMenu();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/archive-down.png")));
            btnSavePDF.setIcon(icon);
            btnSavePDF.setBackground(null);
            btnSavePDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSavePDF.setHorizontalAlignment(SwingConstants.CENTER);
            btnSavePDF.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSavePDF.setIconTextGap(1);
            btnSavePDF.setBounds(970, panelShadow.getHeight() + 1, 60, 60);
            btnSavePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnSavePDF.setToolTipText("Guardar en un pdf");

            btnSavePDF.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    try {
                        PrinterJob job = PrinterJob.getPrinterJob();
                        job.setPrintable(ReportPanel.this);

                        if(job.printDialog())
                            job.print();
                    } catch (PrinterException ex) {
                        throw new RuntimeException(ex);
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnSavePDF.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnSavePDF.setBackground(null);
                }

            });
        }
        return btnSavePDF;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {

        Component[] internalPanels = panelShadow.getComponents();

        Graphics2D g2d = (Graphics2D)graphics;

        // Imprime cada JPanel interno en una nueva página
        if (pageIndex < internalPanels.length) {
            JPanel panelToPrint = (JPanel) internalPanels[pageIndex];

            double AlturaPag = pageFormat.getImageableHeight();
            double AnchoPag = pageFormat.getImageableWidth();

            // Calcula el ancho y alto del JPanel
            double AnchoPanel = panelToPrint.getWidth();
            double AlturaPanel = panelToPrint.getHeight();

            // Escala para ajustar el JPanel a la página
            double escala = AnchoPag / AnchoPanel;

            // Ajusta la escala y traslada el origen
            g2d.scale(escala, escala);
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());


            // Imprime el JPanel
            panelToPrint.paint(g2d);

            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }

    }


    //        //El panel no cabria en la hoja, asi que necesitamos reescalarlo:
//        if(AnchoPanel >= AnchoPag) {
//            escala =  AnchoPag / AnchoPanel;
//        }
//        int NumPages = (int) (AlturaPanel / AlturaPag);
//
//        g2d.translate(0f,-pageIndex*AlturaPag);
//        if(pageIndex+1 != NumPages){
//            g2d.setClip(0, (int)(pageIndex*AlturaPag) + 50,(int)AnchoPag,(int)AlturaPag);
//        }
//        else{
//            int RestoPanel = (int) (AlturaPanel - pageIndex*AlturaPag)-5;
//            g2d.setClip(0, (int)(pageIndex*AlturaPag),(int)AnchoPag, RestoPanel);
//        }
//        //Cambiamos la escala para que quepa el panel en la hoja:
//        g2d.scale(escala,escala);
//
//
//        if(pageIndex >=NumPages)
//            return NO_SUCH_PAGE;
//
//        else{
//            panelShadow.paint(g2d);
//            return PAGE_EXISTS;
//        }




    @Override
    public void setVisible(boolean a) {
        this.reportPanel.setVisible(a);
    }

}
