package Interface.newInterface.Chart;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import Interface.newInterface.Principal1;
import Interface.newInterface.python.PythonExecutor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ReportPanel extends JScrollPane {


    public static final int Frame_Value = 1;
    private final JScrollPane reportPanel;
    private JPanel chartPanel;

    private JButton btnSavePDF;
    private PanelShadow panelShadow;
    private ChartAmbits chartAmbits;
    private ChartMDG chartMDG;

    private JLabel jlabel;
    private JLabel jPieLabel;


    public ReportPanel(Principal1 p) throws Exception {
        reportPanel = new JScrollPane();
        p.getContentPane().add(reportPanel);
        reportPanel.setBounds(241, 100, 1039, 620);
        reportPanel.setBorder(null);
        reportPanel.setVerticalScrollBar(new ScrollBarCustom());
        reportPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        reportPanel.setViewportView(getChartPanel(p));
        reportPanel.setViewportBorder(null);
        reportPanel.setVisible(false);

    }



    private JPanel getChartPanel(Principal1 p) throws Exception {
        if (chartPanel == null) {
            chartPanel = new JPanel();
            chartPanel.setLayout(null);
            chartPanel.setBounds(241, 100, 1030, 900);
            chartPanel.setPreferredSize(new Dimension(1030, 5000));
            chartPanel.setBackground(Color.WHITE);
            chartPanel.setOpaque(false);
            chartPanel.setBorder(null);
            chartPanel.add(getBtnSavePDF(p));
            chartPanel.add(getShadowPanel());


        }
        return chartPanel;
    }

    private PanelShadow getShadowPanel() throws Exception {
        if (panelShadow == null) {
            panelShadow = new PanelShadow();
            panelShadow.setBounds(3, 3, 1030, 4000);
            panelShadow.setLayout(null);
            panelShadow.add(getChartMDG());
            panelShadow.add(getChartAmbits());
            //panelShadow.add(getMDChart(1));
            //panelShadow.add(getMDChart(1));

            // Crea la imagen del panel para luego exportarla al pdf y word
            BufferedImage image = new BufferedImage(panelShadow.getWidth(), panelShadow.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            panelShadow.paint(g2d);
            g2d.dispose();

            // Guarda la imagen
            ImageIO.write(image, "png", new File("panel2.png"));
        }
        return panelShadow;
    }


    /** Este método se encarga de construir las gráficas de índices porcentuales dependiendo del campo que se elija
     * @param field ⇒ Puede ser 1, 2 o 3, para referirse a los campos de Ámbitos, Perspectivas y Dimensiones
     *              respectivamente*/
    private JLabel getMDChart(int field){
        if(jlabel == null){
            jlabel = new JLabel("");

            if(field == 1)
                // Se crea la gráfica de MDA (Madurez Digital por Ámbitos)
                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[89.00, 25.00]", "6", "4");
            else if(field == 2)
                // Se crea la gráfica de MDP (Madurez Digital por Perspectivas)
                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
            else{
                // Se crea la gráfica de MDD (Madurez Digital por Dimensiones)
                PythonExecutor.imdChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
            }
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/grafica.png"), "Imagen no encontrada"));
            jlabel.setIcon(icon);
            jlabel.setBounds(10,1, 800,400);
        }
        return jlabel;
    }


    private JLabel getPieChart(int field){
        if(jPieLabel == null){
            jPieLabel = new JLabel("");

            if(field == 1)
                // Se crea la gráfica de MDA (Madurez Digital por Ámbitos)
                PythonExecutor.pieChart("'Centralidad en el Cliente'", "25.00", "6", "4");
            else if(field == 2)
                // Se crea la gráfica de MDP (Madurez Digital por Perspectivas)
                PythonExecutor.pieChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
            else{
                // Se crea la gráfica de MDD (Madurez Digital por Dimensiones)
                PythonExecutor.pieChart("['Centralidad en el Cliente', 'Procesos']", "[50.00, 25.00]", "6", "4");
            }
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular.png"), "Imagen no encontrada"));
            jPieLabel.setIcon(icon);
            jPieLabel.setBounds(30,300, 800,400);
        }
        return jPieLabel;
    }



    private ChartMDG getChartMDG() throws IOException {
        if (chartMDG == null){
            chartMDG = new ChartMDG(14.58f, 31.25f);
            chartMDG.setLocation(10,10);
            chartMDG.setBackground(null);
            chartMDG.setOpaque(false);

        }
        return chartMDG;
    }

    /**Método para crear todas las cosas que tienen ver con los resultados del
      índice de madurez digital por ámbitos*/
    private ChartAmbits getChartAmbits() throws Exception {
        if (chartAmbits == null) {
            chartAmbits = new ChartAmbits(50.58f, 31.25f);
            chartAmbits.setLocation(15, 600);
            chartAmbits.setOpaque(false);
            chartAmbits.setBackground(Color.WHITE);

            //Valores de prueba para la tabla de los Ámbitos

        }
        return chartAmbits;
    }



    private JButton getBtnSavePDF(Principal1 p) {

        if (btnSavePDF == null) {
            btnSavePDF = new JButton();
            ImageIcon icon = new ImageIcon(getClass().getResource("/util/archive-down.png"));
            btnSavePDF.setIcon(icon);
            btnSavePDF.setBackground(null);
            btnSavePDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSavePDF.setHorizontalAlignment(SwingConstants.CENTER);
            btnSavePDF.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSavePDF.setIconTextGap(1);
            btnSavePDF.setBounds(970, 1300, 45, 45);
            btnSavePDF.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnSavePDF.setToolTipText("Guardar información en un pdf");

            btnSavePDF.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String filepath = "";
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    }
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Guardar pdf");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    if(chooser.showOpenDialog(p) == JFileChooser.APPROVE_OPTION)
                        filepath = chooser.getSelectedFile().getAbsolutePath();


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
    public void setVisible(boolean a) {
        this.reportPanel.setVisible(a);
    }


}
