package Interface.newInterface.Chart;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import Interface.newInterface.ButtonMenu;
import Interface.newInterface.Principal1;
import Interface.newInterface.python.PythonExecutor;
import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;
import logic.useful.Controlador;

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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Objects;

public class ReportPanel extends JScrollPane  implements Printable {


    public static final int Frame_Value = 1;
    private final JScrollPane reportPanel;
    private JPanel chartPanel;

    private ButtonMenu btnSavePDF;
    private ButtonMenu btnSaveWORD;
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
            chartPanel.add(getBtnSaveWORD());
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
            chartMDG = new ChartMDG(Controlador.getAmbitos().get(0), Controlador.getAmbitos().get(1));
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
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/pdf-2-26.png")));
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


    private JButton getBtnSaveWORD() {

        if (btnSaveWORD == null) {
            btnSaveWORD = new ButtonMenu();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/doc-26.png")));
            btnSaveWORD.setIcon(icon);
            btnSaveWORD.setBackground(null);
            btnSaveWORD.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSaveWORD.setHorizontalAlignment(SwingConstants.CENTER);

            btnSaveWORD.setBounds(900, panelShadow.getHeight() + 1, 60, 60);
            btnSaveWORD.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnSaveWORD.setToolTipText("Guardar en un word");

            btnSaveWORD.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException |
                             IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Guardar Imagen");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

//                    FileNameExtensionFilter filter = new FileNameExtensionFilter(ResourceBundleWrapper.getBundle("org.jfree.chart.LocalizationBundle").getString("PNG_Image_Files"), new String[]{"png"});
//                    fileChooser.addChoosableFileFilter(filter);
//                    fileChooser.setFileFilter(filter);


                    // Muestra el JFileChooser
                    int resultado = fileChooser.showSaveDialog(ReportPanel.this);

                    // Si el usuario seleccionó un archivo
                    if (resultado == JFileChooser.APPROVE_OPTION) {
                        String filename = fileChooser.getSelectedFile().getPath();
                        if (!filename.endsWith(".docx")) {
                            filename = filename + ".docx";

                            Cursor cursorEspera = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
                            ReportPanel.this.setCursor(cursorEspera);
                        }
                        saveWord(filename);
                        ReportPanel.this.setCursor(Cursor.getDefaultCursor());
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnSaveWORD.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnSaveWORD.setBackground(null);
                }

            });
        }
        return btnSaveWORD;
    }


    private void saveWord(String path) {

        //Se crean todas las secciones del documento
        Document doc = new Document();

        DocPicture headerPicture = new DocPicture(doc);
        DocPicture header2Picture = new DocPicture(doc);

        Section section = doc.addSection();//H de header, MDG de chartMDG y A chartAmbts

        section.getPageSetup().getMargins().setAll(40f);

        HeaderFooter header = section.getHeadersFooters().getHeader();
        HeaderFooter footer = section.getHeadersFooters().getFooter();

        Paragraph headerParagraph = header.addParagraph();
        Paragraph paragraphMDG = section.addParagraph();


        //Se le da formato a la imagen del header
        String a = System.getProperty("user.dir");
        headerPicture.loadImage(a+"/src/util/header1Word.png");
        header2Picture.loadImage(a+"/src/util/header2Word.png");

        header2Picture.setWidth(75);
        header2Picture.setHeight(40);
        headerPicture.setWidth(80);
        headerPicture.setHeight(35);
        headerPicture.setTextWrappingStyle(TextWrappingStyle.Square);
        header2Picture.setTextWrappingStyle(TextWrappingStyle.Inline);

        //Se inserta en el parrafo
        headerParagraph.getChildObjects().insert(0, headerPicture);
        headerParagraph.getChildObjects().insert(1,header2Picture);

        //Se alinea al centro
        headerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        Paragraph footerParagraph = footer.addParagraph();

        DocPicture footerPicture = new DocPicture(doc);
        footerPicture.loadImage(a+"/src/util/footerWord.png");
        footerPicture.setWidth(250);
        footerPicture.setHeight(100);
        footerPicture.setTextWrappingStyle(TextWrappingStyle.Inline);
        footerParagraph.getChildObjects().insert(0, footerPicture);


        //Se añaden todas las graficas al documento y mas
        addChartMDGToWord(doc);
        addChartAmbitsToWord(doc);
        addChartPerspectiveToWord(doc);
        addChartMDGMDAMDPToWord(doc);
        addChartDimensionToWord(doc);
        addMDDResumeTableToWord(doc);

        doc.saveToFile(path, FileFormat.Docx_2013);

        doc.close();
        JOptionPane.showMessageDialog(null, "Documento guardado exitosamente", "Exportar documento word", JOptionPane.INFORMATION_MESSAGE);
        Toolkit.getDefaultToolkit().beep();

    }

    private void addChartMDGToWord(Document doc){
        BufferedImage image = new BufferedImage(chartMDG.getWidth(), chartMDG.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        chartMDG.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(300);
        picture.setHeight(300);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        //Se agrega al documento
        doc.getSections().get(0).getParagraphs().get(0).getChildObjects().insert(0, picture);
        Body body = doc.getLastSection().getBody();
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);
    }

    private void addChartAmbitsToWord(Document doc){
        BufferedImage image = new BufferedImage(chartAmbits.getWidth(), chartAmbits.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        chartAmbits.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(300);
        picture.setHeight(300);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        Body body = doc.getLastSection().getBody();
        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.getChildObjects().insert(0,picture);
        body.getChildObjects().add(paragraph);

        paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);
    }


    private void addChartPerspectiveToWord(Document doc){
        BufferedImage image = new BufferedImage(chartPerspective.getWidth(), chartPerspective.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        chartPerspective.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }




        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(305);
        picture.setHeight(380);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        Body body = doc.getLastSection().getBody();
        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.getChildObjects().insert(0,picture);
        body.getChildObjects().add(paragraph);

        paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);

    }

    private void addChartMDGMDAMDPToWord(Document doc){
        BufferedImage image = new BufferedImage(mdgMdaMdpTable.getWidth(), mdgMdaMdpTable.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        mdgMdaMdpTable.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(350);
        picture.setHeight(400);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        Body body = doc.getLastSection().getBody();
        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.getChildObjects().insert(0,picture);
        body.getChildObjects().add(paragraph);

        paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);

    }

    private void addChartDimensionToWord(Document doc){
        BufferedImage image = new BufferedImage(chartDimension.getWidth(), chartDimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        chartDimension.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(305);
        picture.setHeight(500);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        Body body = doc.getLastSection().getBody();
        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.getChildObjects().insert(0,picture);
        body.getChildObjects().add(paragraph);

        paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);

    }

    private void addMDDResumeTableToWord(Document doc){
        BufferedImage image = new BufferedImage(mddResumenTable.getWidth(), mddResumenTable.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        // Establece el color de fondo a blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); // Rellena el fondo con color blanco
        mddResumenTable.paint(g2d);
        g2d.dispose();

        //Se transforma la imagen en bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DocPicture picture = new DocPicture(doc);
        //Se le da formato a la imagen
        picture.loadImage(baos.toByteArray());

        picture.setWidth(300);
        picture.setHeight(1200);

        picture.setTextWrappingStyle(TextWrappingStyle.Square);

        Body body = doc.getLastSection().getBody();
        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        Paragraph paragraph = new Paragraph(doc);
        paragraph.getChildObjects().insert(0,picture);
        body.getChildObjects().add(paragraph);

        body.getLastParagraph().appendBreak(BreakType.Page_Break);
        paragraph = new Paragraph(doc);
        paragraph.setText("\n\nObservaciones:");
        ParagraphStyle paragraphStyle = new ParagraphStyle(doc);
        paragraphStyle.getCharacterFormat().setFontName("Arial");
        doc.getStyles().add(paragraphStyle);
        paragraph.applyStyle(paragraphStyle.getName());
        body.getChildObjects().add(paragraph);

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
