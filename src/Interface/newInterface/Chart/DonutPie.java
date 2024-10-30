package Interface.newInterface.Chart;

import Interface.export.swing.CircleProgressBar;
import org.jfree.chart.util.ResourceBundleWrapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.PopupMenuUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DonutPie extends JPanel implements Printable {


    private JLabel header;

    private JLabel indicator;
    private JLabel valueLabel;
    //Atributos de la gráfica de anillo
    private CircleProgressBar donutChart;
    private float value;
    private int xDonutchart;
    private int yDonutChart;
    private int widthDonutChart;
    private int heigthDonutChart;
    private String nameDonutChart;

    private JPopupMenu popupMenu;
    private JMenuItem print;
    private JMenuItem savePNG;


    public DonutPie(String name, float value, int x, int y, int width, int height) {
        setLayout(null);
        setOpaque(false);


        setValue(value);
        setxDonutchart(x);
        setyDonutChart(y);
        setWidthDonutChart(width);
        setHeigthDonutChart(height);
        setNameDonutChart(name);

        add(getHeader());
        add(drawChartAmbitsPastel());
        add(getIndicator());
        add(getPopupMenu());

        setComponentPopupMenu(getPopupMenu());

        int heigth = header.getHeight() + donutChart.getHeight() + indicator.getHeight() + 10;
        this.setSize(indicator.getX() + indicator.getWidth(), heigth);

    }

    public CircleProgressBar getDonutChart() {
        return donutChart;
    }

    public void setDonutChart(CircleProgressBar donutChart) {
        this.donutChart = donutChart;
    }

    public String getNameDonutChart() {
        return nameDonutChart;
    }


    public void setNameDonutChart(String name) {
        this.nameDonutChart = name;
    }


    public int getxDonutchart() {
        return xDonutchart;
    }

    public void setxDonutchart(int x) {
        this.xDonutchart = x;
    }


    public int getyDonutChart() {
        return yDonutChart;
    }

    public void setyDonutChart(int y) {
        this.yDonutChart = y;
    }


    public int getWidthDonutChart() {
        return widthDonutChart;
    }

    public void setWidthDonutChart(int width) {
        this.widthDonutChart = width;
    }

    public int getHeigthDonutChart() {
        return heigthDonutChart;
    }

    public void setHeigthDonutChart(int heigth) {
        this.heigthDonutChart = heigth;
    }


    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public void setHeader(JLabel header) {
        this.header = header;
    }


    /**
     * Este método se encarga de crear el Encabezado para las gráficas
     */
    private JLabel getHeader() {
        if (header == null) {
            header = new JLabel("<html><p align: center>" + nameDonutChart + "</p></html>");
            header.setHorizontalAlignment(SwingConstants.CENTER);
            header.setFont(new Font("Arial", Font.PLAIN, 18));
            header.setBounds(1, 1, widthDonutChart + 100, 55);
            header.setOpaque(false);
        }
        return header;
    }

    public JLabel getIndicator() {
        if (indicator == null) {
            indicator = new JLabel();
            indicator.setFont(new Font("Arial", Font.BOLD, 15));
            indicator.setOpaque(false);
            indicator.setHorizontalAlignment(SwingConstants.LEFT);
            indicator.setHorizontalTextPosition(SwingConstants.RIGHT);


            if (value >= 0 && value <= 25) {
                indicator.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicator.setIcon(icon);
            } else if (value > 25 && value <= 50) {
                indicator.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicator.setIcon(icon);
            } else if (value > 50 && value <= 75) {
                indicator.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicator.setIcon(icon);
            } else {
                indicator.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicator.setIcon(icon);
            }

            int indicatorX = donutChart.getX() + donutChart.getWidth() - 65;
            int indicatorY = donutChart.getY() + donutChart.getHeight();
            indicator.setBounds(indicatorX, indicatorY, 250, indicator.getFont().getSize()+ 1);
        }
        return indicator;
    }


//    public JLabel getValueLabel() {
//        if (valueLabel == null) {
//            valueLabel = new JLabel("<html><p align: center>" + value + "%</p></html>");
//            valueLabel.setFont(new Font("Arial", Font.BOLD, 30));
//            valueLabel.setBounds(donutChart.getX() + 55, donutChart.getY() + (donutChart.getHeight() / 2) - 20, 120, valueLabel.getFont().getSize());
//            valueLabel.setOpaque(false);
//            valueLabel.setBackground(Color.ORANGE);
//        }
//        return valueLabel;
//    }

    //Método para dibujar Madurez Digital por Ámbitos (Gráfica de pastel)
    private CircleProgressBar drawChartAmbitsPastel() {


        //=================================================================================
        /* Se le pasa por el constructor el porciento que debe mostrar, en este caso (value)*/
        donutChart = new CircleProgressBar(value);

        if (value >= 0 && value <= 25) {
            donutChart.setForeground(new Color(1, 176, 239, 255));
        } else if (value > 25 && value <= 50) {
            donutChart.setForeground(new Color(146, 209, 79, 255));
        } else if (value > 50 && value <= 75) {
            donutChart.setForeground(new Color(255, 193, 0, 255));
        } else {
            donutChart.setForeground(new Color(254, 0, 0, 255));
        }

        donutChart.setBounds(xDonutchart, header.getHeight() + 10, widthDonutChart, heigthDonutChart);
        donutChart.setBorder(null);


//        g2D.setFont(new Font("Arial", Font.BOLD, 15));
//        g2D.setColor(new Color(106, 109, 110));
        //g2D.drawString("IMDA: Capacidades estratégicas y de ", a.getX() - 60, a.getY() - 40);
        //g2D.drawString("creación de valor sustentable", a.getX() - 40, a.getY() - 20);

        /*Después de crear el gráfico de pastel, se agrega al panel, no se hace que el
         * método retorne al gráfico xq entonces no actualiza los valores del porciento*/
        //=================================================================================


        /*Gráfica de pastel que muestra el IMD: Resultados de Digitalización*/
        //=================================================================================
        /* Se le pasa por el constructor el porciento que debe mostrar, en este caso (index1)
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
        //this.add(b);*/

        return donutChart;
    }

    private JMenuItem getPrint() {
        if (print == null) {
            print = new JMenuItem("Imprimir");

            print.addActionListener(e -> {
                // Implementa la acción de impresión
                savePDF();

            });
        }
        return print;
    }


    private JMenuItem getSavePNG() {
        if (savePNG == null) {
            savePNG = new JMenuItem("Guardar imagen..");

            savePNG.addActionListener(e -> savePNG());
        }
        return savePNG;
    }


    private JPopupMenu getPopupMenu() {
        if (popupMenu == null) {

            popupMenu = new JPopupMenu();

            // Agrega elementos al menú contextual
            popupMenu.add(getPrint());
            popupMenu.addSeparator();
            popupMenu.add(getSavePNG());
        }
        return popupMenu;
    }

    private void savePNG() {
        // Crea la imagen del panel para luego exportarla al pdf y word
        BufferedImage image = new BufferedImage(DonutPie.this.getWidth(), DonutPie.this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco

        // Ahora pinta el panel
        paint(g2d); // Llama al método paint para renderizar el panel en la imagen
        g2d.dispose();

        // Guarda la imagen
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter filter = new FileNameExtensionFilter(ResourceBundleWrapper.getBundle("org.jfree.chart.LocalizationBundle").getString("PNG_Image_Files"), new String[]{"png"});
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);


        // Muestra el JFileChooser
        int resultado = fileChooser.showSaveDialog(DonutPie.this);

        // Si el usuario seleccionó un archivo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getPath();
            if (!filename.endsWith(".png")) {
                filename = filename + ".png";
            }
            File selectedFile = new File(filename);

            // Guarda la imagen
            try {
                ImageIO.write(image, "png", selectedFile);
                JOptionPane.showMessageDialog(null, "Imagen guardada en: " + selectedFile.getAbsolutePath(), "Error", JOptionPane.INFORMATION_MESSAGE);
                Toolkit.getDefaultToolkit().beep();
            } catch (IOException ex) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Error al guardar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


    private void savePDF() {

        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            if(job.printDialog())
                job.print();
        } catch (PrinterException  e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex){
        double alturaPag = pageFormat.getImageableHeight();
        double anchoPag = pageFormat.getImageableWidth();
        double anchoPanel = (double) this.getWidth();
        double alturaPanel = (double) this.getHeight();
        double escala = 1;

        //El panel no cabria en la hoja, asi que necesitamos reescalarlo:
        if (anchoPanel >= anchoPag) {
            escala = anchoPag / anchoPanel;
        }

        if (pageIndex > 0) return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) graphics;

        // Punto donde empezará a imprimir dentro la página (100, 50)
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY() + 50);

        // Reducción de la impresión al 25%
        g2d.scale(escala, escala); // Cambia el valor para ajustar la escala

        this.printAll(graphics);

        this.paint(g2d);
        return PAGE_EXISTS;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {

    }

}
