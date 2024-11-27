package Interface.newInterface;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import Interface.newInterface.Chart.ReportPanel;
import logic.Entitys.Perspectiva;
import logic.Entitys.Pregunta;
import logic.useful.Controlador;
import util.table.MyTableCellRendererCeldas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;


public class QuestionPanel extends JPanel {


    // Atributos
    // ========================================================================
    public static final int Frame_Value = 2;
    private final JScrollPane reportPanel;

    /**
     * questionsPanel ⇒ Es el panel que va a contener todos los demás paneles dentro,
     * es el que está de viewPort en el JScrollPane
     */
    private JPanel questionsPanel;


    /**
     * colaPanelesVisibles ⇒ Cola doble que va a almacenar todos los paneles con las preguntas de cada Dimensión,
     * cuando se presiona el botón {@link #getBtnFinish(Principal1)}, este saca un panel de aquí y lo pone en {@link #getPilaPanelesNoVisibles()}
     */
    private Deque<JPanel> pilaPanelesVisibles;

    /**
     * colaPanelesNoVisibles ⇒ Cola doble que va a almacenar todos los paneles con las preguntas de cada Dimensión
     */
    private Deque<JPanel> pilaPanelesNoVisibles;

    // Paneles de cada una de las Dimensiones, estas contienen las preguntas


    private ButtonMenu btnSavePDF;
    private ButtonMenu btnPreview;
    private JLabel header;
    private ArrayList<Pregunta> preguntas;

    // ========================================================================


    public QuestionPanel(Principal1 p) {
        reportPanel = new JScrollPane();
        p.getContentPane().add(reportPanel);

        reportPanel.setBounds(241, 100, 1039, 620);
        reportPanel.setBorder(null);
        reportPanel.setVerticalScrollBar(new ScrollBarCustom());
        reportPanel.setViewportView(getQuestionsPanel(p));
        reportPanel.setViewportBorder(null);
        reportPanel.setVisible(false);

    }


    public JPanel getQuestionsPanel(Principal1 p) {
        if (questionsPanel == null) {
            questionsPanel = new PanelShadow();
            questionsPanel.setLayout(null);
            questionsPanel.setBounds(241, 100, 1030, 4000);
            questionsPanel.setPreferredSize(new Dimension(1030, 620));
            questionsPanel.setBackground(Color.WHITE);
            questionsPanel.setOpaque(false);
            questionsPanel.setBorder(null);

            getPilaPanelesVisibles();
            getPilaPanelesNoVisibles();

            addQuestions(questionsPanel);

            questionsPanel.add(getBtnFinish(p));
            questionsPanel.add(getBtnPreview(p));
            questionsPanel.add(getHeader());

        }
        return questionsPanel;
    }


    private void addQuestions(JPanel superPanel) {

        ArrayList<Perspectiva> perspectivas = Controlador.getPerspectiva(0);
        ArrayList<logic.Entitys.Dimension> dimensiones;
        int dimensionesAnteriores = 0;


        /* Se utiliza un triple for anidado para englobar a las perspectivas, las dimensiones y a las preguntas*/
        // Este for recorre todas las perspectivas
        for (int i = 0; i < perspectivas.size(); i++) {
            dimensiones = Controlador.getDimension(i + 1);
            // Este for recorre todas las dimensiones pertenecientes a la perspectiva del for de afuera
            for (int j = 0; j < dimensiones.size(); j++) {

                // Se crea un header con el nombre de la perspectiva y la dimension a la que pertenecen las preguntas
                JLabel header = new JLabel("<html> <p align: left> PP- " + perspectivas.get(i).getNombre_perspectiva() + "<br>  " +
                        "DD-" + dimensiones.get(j).getNombre_dimension() + "</p></html>");
                header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 19));
                header.setForeground(new Color(8, 52, 128));
                header.setBounds(50, 20, 600, 50);


                preguntas = Controlador.getPregunta(j + 1 + dimensionesAnteriores);

                JPanel panel = new JPanel();
                panel.setLayout(null);
                panel.setBackground(null);
                panel.setVisible(j == 0 && i == 0);
                panel.add(header);
                panel.add(getScrollPane(preguntas, getQuestionsTable()));
                panel.setBounds(10, 10, 1010, 550);

                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (panel.contains(e.getPoint()))
                            getQuestionsTable().clearSelection();
                    }
                });

                superPanel.add(panel);
                pilaPanelesVisibles.offerLast(panel);
            }
            dimensionesAnteriores += dimensiones.size();
        }
    }


    // Método con todas las propiedades del encabezado del panel
    private JLabel getHeader() {
        if (header == null) {
            header = new JLabel();
            header.setFont(new Font("Arial", Font.BOLD, 20));
            header.setForeground(new Color(11, 52, 128, 255));
            header.setBounds(50, 20, 800, 80);
            header.setText("Requisitos, iniciativas, y buenas prácticas de transformación digital");
        }
        return header;
    }


    private ButtonMenu getBtnFinish(Principal1 p) {

        if (btnSavePDF == null) {
            btnSavePDF = new ButtonMenu();
            btnSavePDF.setText("Siguiente");
            btnSavePDF.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
            btnSavePDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnSavePDF.setVerticalAlignment(SwingConstants.CENTER);
            btnSavePDF.setHorizontalAlignment(SwingConstants.CENTER);
            btnSavePDF.setVerticalTextPosition(SwingConstants.CENTER);
            btnSavePDF.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSavePDF.setToolTipText("Pasar a la siguiente dimensión");

            int posY = getPilaPanelesVisibles().peekFirst().getHeight() + getPilaPanelesVisibles().peekFirst().getY();
            btnSavePDF.setBounds(850, posY, 100, 45);

            btnSavePDF.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    assert pilaPanelesVisibles.peek() != null;
                    // Obtenemos el panel que se encuentra visible en el momento y cogemos de él el JScrollPane
                    JScrollPane a = (JScrollPane) pilaPanelesVisibles.peek().getComponent(1);
                    // Obtenemos la tabla del JScrollPane
                    JTable table = (JTable) a.getViewport().getView();
                    boolean isfull = false;

                    /* Verificamos si la columna 2 (3) de la tabla tiene un valor diferente a ""
                    Necesitamos esta comprobación para el caso en que el usuario pase de tabla sin haber rellenado
                    todas las preguntas no guarde los valors porque va a dar error a la hora de convertir de "" a int*/
                    int rowCant = table.getRowCount();
                    int cantFilasVacias = 0;
                    for(int i = 0; i < rowCant; i++){
                        if(table.getValueAt(i, 2) != "") {
                            cantFilasVacias++;
                        }
                    }

                    if(cantFilasVacias == rowCant){
                        isfull = true;
                    }


                    if (isfull) {
                        int idPregunta;
                        int ptos;
                        preguntas = Controlador.getPregunta(pilaPanelesNoVisibles.size()+1);

                        // Recorremos toda la tabla guardando los valores de los puntos de las preguntas
                        for (int i = 0; i < table.getRowCount(); i++) {
                            // Obtenemos el id de la dimension a la que pertenecen las preguntas
                            // Dependiendo de si hay algun panel no visible, significa que tu eres la posicion = size
                            // de la pila de paneles no visibles + 1 xq los id comienzan en 1
                            idPregunta = (int) table.getValueAt(i, 0);
                            ptos = (int) table.getValueAt(i, 2);

                            System.out.println(idPregunta);

                            if(idPregunta == preguntas.get(i).getId_pregunta()) {
                                preguntas.get(i).setPtos(ptos);
                            }
                        }
                        // Aqui se deberia llamar al metodo para actualizar los valores de la pregunta.
                        Controlador.savePreguntas(preguntas);

                    }
                    if (btnSavePDF.getText().equals("Finalizar") && getPilaPanelesVisibles().size() == 1 && isfull) {

                        Controlador.calculatePoints();
                        try {
                            p.addReportPanel();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        p.getMenuPanel().panelControl(p, ReportPanel.Frame_Value);
                        p.getMenuPanel().getBtnReports().setEnabled(true);
                    }
                    setVisibilityDimensionsPanels(1, p);
                }
            });

        }
        return btnSavePDF;
    }

    private ButtonMenu getBtnPreview(Principal1 p) {

        if (btnPreview == null) {
            btnPreview = new ButtonMenu();
            btnPreview.setText("Anterior");
            btnPreview.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
            btnPreview.setVisible(false);
            btnPreview.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnPreview.setVerticalAlignment(SwingConstants.CENTER);
            btnPreview.setHorizontalAlignment(SwingConstants.CENTER);
            btnPreview.setVerticalTextPosition(SwingConstants.CENTER);
            btnPreview.setHorizontalTextPosition(SwingConstants.CENTER);
            btnPreview.setToolTipText("Pasar a la dimensión anterior");

            int posY = getPilaPanelesVisibles().peekFirst().getHeight() + getPilaPanelesVisibles().peekFirst().getY();
            btnPreview.setBounds(100, posY, 100, 45);

            btnPreview.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setVisibilityDimensionsPanels(2, p);
                }
            });
        }
        return btnPreview;
    }


    private Deque<JPanel> getPilaPanelesVisibles() {
        if (pilaPanelesVisibles == null) {
            pilaPanelesVisibles = new ArrayDeque<>();
        }
        return pilaPanelesVisibles;
    }

    private Deque<JPanel> getPilaPanelesNoVisibles() {
        if (pilaPanelesNoVisibles == null) {
            pilaPanelesNoVisibles = new ArrayDeque<>();
        }
        return pilaPanelesNoVisibles;
    }

    public void resizeQuestionsPanel(Principal1 p) {
        getQuestionsPanel(p).setPreferredSize(new Dimension(pilaPanelesVisibles.peek().getWidth(), pilaPanelesVisibles.peek().getHeight()));
    }


    public void setVisibilityDimensionsPanels(int value, Principal1 p) {

        // Si el value = 1, significa que queremos avanzar, si es 2 queremos retroceder
        if (!(getPilaPanelesVisibles().isEmpty()) && value == 1) {

            if (getPilaPanelesVisibles().size() > 1) {
                getPilaPanelesNoVisibles().offerFirst(getPilaPanelesVisibles().pollFirst());
                Objects.requireNonNull(getPilaPanelesNoVisibles().peekFirst()).setVisible(false);
                Objects.requireNonNull(getPilaPanelesVisibles().peek()).setVisible(true);
                if (!getBtnPreview(p).isVisible())
                    getBtnPreview(p).setVisible(true);
                resizeQuestionsPanel(p);

            }

            if (getPilaPanelesVisibles().size() == 1) {
                getBtnFinish(p).setText("Finalizar");
                getBtnFinish(p).setToolTipText("Terminar encuesta");
                resizeQuestionsPanel(p);
            }


        } else if (value == 2) {

            if (!getPilaPanelesNoVisibles().isEmpty()) {
                getPilaPanelesVisibles().offerFirst(getPilaPanelesNoVisibles().pollFirst());
                Objects.requireNonNull(getPilaPanelesVisibles().peekFirst()).setVisible(true);
                getBtnFinish(p).setText("Siguiente");
                resizeQuestionsPanel(p);

                if (getPilaPanelesNoVisibles().isEmpty())
                    getBtnPreview(p).setVisible(false);

            }

        }
    }


    @Override
    public void setVisible(boolean a) {
        this.reportPanel.setVisible(a);
    }


    private JTable getQuestionsTable() {

        JTable questionsTable = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return colIndex == 2;
            }
        };
        questionsTable.setRowHeight(60);
        questionsTable.getTableHeader().setReorderingAllowed(false);


        questionsTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null},
                        {null, null, null},
                },
                new String[]{
                        "No.", "Requisitos, iniciativas y buenas prácticas de transformación digital (DO/LD)", "Puntos"
                }));

        questionsTable.getTableHeader().setBackground(new Color(8, 52, 128));
        questionsTable.getTableHeader().setForeground(Color.white);
        questionsTable.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 16));
        questionsTable.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 13));

        return questionsTable;
    }

    private JScrollPane getScrollPane(ArrayList<Pregunta> lista, JTable questionsTable) {

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 100, 930, 430);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        final DefaultTableModel model = new DefaultTableModel();
        scrollPane.setViewportView(questionsTable);
        model.addColumn("No.");
        model.addColumn("Requisitos, iniciativas y buenas prácticas de transformación digital (DO/LD)");
        model.addColumn("Valor");

        actualizarTabla(lista, model, questionsTable);

        return scrollPane;
    }

    public void actualizarTabla(ArrayList<Pregunta> lista, DefaultTableModel model, JTable questionsTable) {

        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        for (int i = 0; i < lista.size(); i++) {
            Object[] ob = new Object[3];
            ob[0] = lista.get(i).getId_pregunta();
            ob[1] = "<html> <p style=\"text-align: left; vertical-align: top;\">" + lista.get(i).getPregunta() + "</p></html>";
            ob[2] = 0;

            model.addRow(ob);
        }
        questionsTable.setModel(model);

        questionsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (questionsTable.getSelectedRow() != -1) {
                    if (questionsTable.isColumnSelected(1) && e.getClickCount() == 2) {
                        JOptionPane.showMessageDialog(null, "<html>" +
                                "<head>\n" +
                                "    <meta charset=\"UTF-8\">\n" +
                                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                                "    <title>Tooltip Personalizado</title>\n" +
                                "    <style>\n" +
                                "        /* Estilo del tooltip */\n" +
                                "        .tooltip {\n" +
                                "            position: relative;\n" +
                                "            display: inline-block;\n" +
                                "            cursor: pointer;\n" +
                                "        }\n" +
                                "\n" +
                                "        .tooltip .tooltiptext {\n" +
                                "            visibility: hidden;\n" +
                                "            width: 500px;\n" +
                                "            height: 50px;\n" +
                                "            /*background-color: #555;*/\n" +
                                "            color: black;\n" +
                                "            text-align: left;\n" +
                                "            border-radius: 5px;\n" +
                                "            padding: 10px;\n" +
                                "            position: absolute;\n" +
                                "            z-index: 1;\n" +
                                "            bottom: 125%; /* Posición del tooltip */\n" +
                                "            left: 50%;\n" +
                                "            margin-left: -250px; /* Centrar el tooltip */\n" +
                                "            opacity: 0;\n" +
                                "            transition: opacity 0.3s;\n" +
                                "        }\n" +
                                "\n" +
                                "        .tooltip:hover .tooltiptext {\n" +
                                "            visibility: visible;\n" +
                                "            opacity: 1;\n" +
                                "        }\n" +
                                "    </style>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "\n" +
                                "<div class=\"tooltip\">\n" +
                                "    <div class=\"tooltiptext\"> <p align: center>" + lista.get(questionsTable.getSelectedRow()).getPregunta() +
                                "</p> </div>\n" +
                                "</div>\n" +
                                "\n" +
                                "</body>" +
                                "</html>", "Requisitos, iniciativas y buenas prácticas de transformación digital (DO/LD)", JOptionPane.INFORMATION_MESSAGE);
                    }
                }


            }
        });
        ToolTipManager.sharedInstance().setInitialDelay(750);

        Integer[] points = {0, 1, 2, 3, 4};
        JComboBox<Integer> pointsComboBox = new JComboBox<>();
        for (Integer p : points) {
            pointsComboBox.addItem(p);
        }
        pointsComboBox.setToolTipText("<html> <p>0 = NO EXISTENTE<br>1 = INICIANDO<br>2 = HABILITANDO<br>3 = OPERACIONAL" +
                "<br>4 = OPTIMIZADO</html>");
        pointsComboBox.setBackground(Color.WHITE);
        questionsTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(pointsComboBox));

        for (int i = 0; i < questionsTable.getRowCount(); i++)
            questionsTable.setValueAt("", i, 2);

        questionsTable.getColumnModel().getColumn(2).setMaxWidth(50);
        questionsTable.getColumnModel().getColumn(0).setMaxWidth(50);

        questionsTable.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRendererCeldas());
        questionsTable.getColumnModel().getColumn(1).setCellRenderer(new MyTableCellRendererCeldas());
        questionsTable.getColumnModel().getColumn(2).setCellRenderer(new MyTableCellRendererCeldas());

    }
}
