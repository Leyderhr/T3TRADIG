package Interface.newInterface;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import Interface.newInterface.Chart.ReportPanel;
import logic.Questions;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;


public class QuestionPanel extends JScrollPane {


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
    private Deque<PanelShadow> pilaPanelesVisibles;

    /**
     * colaPanelesNoVisibles ⇒ Cola doble que va a almacenar todos los paneles con las preguntas de cada Dimensión
     */
    private Deque<PanelShadow> pilaPanelesNoVisibles;

    // Paneles de cada una de las Dimensiones, estas contienen las preguntas
    private PanelShadow liderazgoDigitalPanel;
    private PanelShadow culturaClimaDigitalPanel;
    private PanelShadow alineamientoEstrategicoPanel;

    private ButtonMenu btnSavePDF;
    private ButtonMenu btnPreview;
    private PanelShadow panelShadow;
    private JLabel header;
    private final Questions questions = new Questions();
    private final ArrayList<JComboBox<String>> comboBoxSelector;
    // ========================================================================


    public QuestionPanel(Principal1 p) throws Exception {
        reportPanel = new JScrollPane();
        p.getContentPane().add(reportPanel);

        comboBoxSelector = new ArrayList<>();

        reportPanel.setBounds(241, 100, 1039, 620);
        reportPanel.setBorder(null);
        reportPanel.setVerticalScrollBar(new ScrollBarCustom());
        reportPanel.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        reportPanel.setViewportView(getQuestionsPanel(p));
        reportPanel.setViewportBorder(null);
        reportPanel.setVisible(false);

    }


    public JPanel getQuestionsPanel(Principal1 p) {
        if (questionsPanel == null) {
            questionsPanel = new JPanel();
            questionsPanel.setLayout(null);
            questionsPanel.setBounds(241, 100, 1030, 4000);
            questionsPanel.setPreferredSize(new Dimension(1030, 4100));
            questionsPanel.setBackground(Color.WHITE);
            questionsPanel.setOpaque(false);
            questionsPanel.setBorder(null);
            questionsPanel.add(getBtnFinish(p));
            questionsPanel.add(getBtnPreview(p));
            questionsPanel.add(getHeader());

            questionsPanel.add(getLiderazgoDigitalPanel());
            questionsPanel.add(getCultClimaDigitalPanel());
            questionsPanel.add(getAlineamientoEstrategicoPanel());


            //Fragmento donde se agregan todas las preguntas com los ComboBox
            //==================================================================

            //Tenemos que almacenar el último valor de y para poder seguir imprimiendo para abajo


            //Preguntas pertenecientes a la perspectiva de Diseño Organizacional
            //============================================================================
            //int y = getQuestions(130, questions.getLiderazgoDigital(), questionsPanel);
//            int y1 = getQuestions(y, questions.getCulturaClimaDigital(), questionsPanel);
//            int y2 = getQuestions(y1, questions.getAlineamientoEstrategicoIntegracionDigital(), questionsPanel);
//            int y3 = getQuestions(y2, questions.getTrabajoInteligente(), questionsPanel);
            //============================================================================


            //Preguntas pertenecientes a la perspectiva de Tecnologías e información estratégicas
            //============================================================================
//            int y4 = getQuestions(y3, questions.getSistemasAplicacionesDeTI(), questionsPanel);
//            int y5 = getQuestions(y4, questions.getMigracionNube(), questionsPanel);
//            int y6 = getQuestions(y5, questions.getBigDataAnalytics(), questionsPanel);
//            int y7 = getQuestions(y6, questions.getHibridacionMundoFisicoDigital(), questionsPanel);
//            int y8 = getQuestions(y7, questions.getHiperConectividad(), questionsPanel);
//            int y9 = getQuestions(y8, questions.getSeguridadDigital(), questionsPanel);
            //============================================================================


            //Preguntas pertenecientes a la perspectiva de Competencia estratégica
            //============================================================================
//            int y10 = getQuestions(y9, questions.getCompetenciasDigitales(), questionsPanel);
//            int y11 = getQuestions(y10, questions.getFormacionDesarrolloDigital(), questionsPanel);
//            int y12 = getQuestions(y11, questions.getMarcaEmpleadoraTalentoDigital(), questionsPanel);
            //============================================================================


            //Preguntas pertenecientes a la perspectiva de Procesos
            //============================================================================
//            int y13 = getQuestions(y12, questions.getOperacionesDigitales(), questionsPanel);
//            int y14 = getQuestions(y13, questions.getGestionDigitalCliente(), questionsPanel);
//            int y15 = getQuestions(y14, questions.getInnovacionDigital(), questionsPanel);
//            int y16 = getQuestions(y15, questions.getEcosistemaDigital(), questionsPanel);
            //============================================================================


            //Preguntas pertenecientes a la perspectiva de Centralidad en el Cliente
            //============================================================================
//            int y17 = getQuestions(y16, questions.getExperienciaDigitalCliente(), questionsPanel);
//            int y18 = getQuestions(y17, questions.getRelacionDigitalCliente(), questionsPanel);
//            int y19 = getQuestions(y18, questions.getMarcaDigital(), questionsPanel);
            //============================================================================


            //Preguntas pertenecientes a la perspectiva de Finanzas
            //============================================================================
//            int y20 = getQuestions(y19, questions.getProductividadDigital(), questionsPanel);
//            int y21 = getQuestions(y20, questions.getIngresosDigitales(), questionsPanel);
            //============================================================================
            //questionsPanel.add(getShadowPanel());

        }
        return questionsPanel;
    }


    private PanelShadow getLiderazgoDigitalPanel() {
        if (liderazgoDigitalPanel == null) {
            liderazgoDigitalPanel = new PanelShadow();
            liderazgoDigitalPanel.setLayout(null);
            liderazgoDigitalPanel.setVisible(true);
            liderazgoDigitalPanel.setBounds(10, 100, 1010, 500);

            liderazgoDigitalPanel.add(
                    new JLabel("<html> <p align: left> PP- Diseño Organizacional <br> " +
                            "DD- Liderazgo digital </p></html>"));

            getQuestions(100, questions.getLiderazgoDigital(), liderazgoDigitalPanel);
            liderazgoDigitalPanel.getComponent(0).setBounds(50, 10, 600, 50);
        }
        return liderazgoDigitalPanel;
    }

    private PanelShadow getCultClimaDigitalPanel() {
        if (culturaClimaDigitalPanel == null) {
            culturaClimaDigitalPanel = new PanelShadow();
            culturaClimaDigitalPanel.setLayout(null);
            culturaClimaDigitalPanel.setVisible(false);
            culturaClimaDigitalPanel.setBounds(10, 100, 1010, 500);

            culturaClimaDigitalPanel.add(
                    new JLabel("<html> <p align: left> PP- Diseño Organizacional <br> " +
                            "DD- Cultura y clima digital </p></html>"));
            getQuestions(100, questions.getCulturaClimaDigital(), culturaClimaDigitalPanel);
            culturaClimaDigitalPanel.getComponent(0).setBounds(50, 10, 600, 50);
        }
        return culturaClimaDigitalPanel;
    }

    private PanelShadow getAlineamientoEstrategicoPanel() {
        if (alineamientoEstrategicoPanel == null) {
            alineamientoEstrategicoPanel = new PanelShadow();
            alineamientoEstrategicoPanel.setLayout(null);
            alineamientoEstrategicoPanel.setVisible(false);
            alineamientoEstrategicoPanel.setBounds(10, 100, 1010, 500);

            alineamientoEstrategicoPanel.add(
                    new JLabel("<html> <p align: left> PP- Diseño Organizacional <br> " +
                            "DD- Alineamiento estratégico e integración digital </p></html>"));
            getQuestions(100, questions.getAlineamientoEstrategicoIntegracionDigital(), alineamientoEstrategicoPanel);
            alineamientoEstrategicoPanel.getComponent(0).setBounds(50, 10, 600, 50);
        }
        return alineamientoEstrategicoPanel;
    }

    // Método para crear la sombra del panel
    private PanelShadow getShadowPanel() {

        if (panelShadow == null) {
            panelShadow = new PanelShadow();
            panelShadow.setBounds(3, 3, 1030, 4000);
            panelShadow.setLayout(null);
        }
        return panelShadow;
    }


    /**
     * Método para imprimir todas las preguntas.
     *
     * @param dimensions: el arrayList de las dimensiones a agregar
     * @param panel:      panel donde se van a agregar las preguntas
     * @return y: El último valor de la posición y en el panel donde se agregó
     */
    private int getQuestions(int y, ArrayList<String> dimensions, JPanel panel) {
        int x = 50;
        int width = 880; // Largo
        int height = 70; // Ancho

        if (!dimensions.isEmpty()) {

            // Por cada pregunta se va a crear un jLabel
            for (String s : dimensions) {
                JLabel questionLabel = new JLabel();
                questionLabel.setBounds(x, y, width, height);
                questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
                questionLabel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.controlShadow, SystemColor.controlShadow, SystemColor.controlShadow, new Color(160, 160, 160)));
                questionLabel.setBackground(new Color(240, 240, 240));

                // Se utilizan inyecciones html para poner los saltos de línea en el jLabel
                questionLabel.setText("<html> <p align: left>" + s + "</p></html>");

                // Llamamos al método que agrega el comboBox a la pregunta
                getComboBoxSelector(y + 70, panel);

                y += height + 100;
                panel.setSize(1010, y);
                panel.add(questionLabel);
            }
        }
        return y;
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


    /**
     * Método para colocar un ComboBox a cada pregunta.
     * <p>
     * Este método va a colocar un ComboBox a cada pregunta y lo agregará al
     * arraylist comboBoxSelecto que se encuentra como atributo en la clase
     *
     * @param y:     el valor de posición y dentro del panel
     * @param panel: el panel donde se va a agregar el comboBox
     */
    private void getComboBoxSelector(int y, JPanel panel) {
        JComboBox<String> comboBox = new JComboBox<>();

        comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        comboBox.setBackground(Color.WHITE);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"0 = NO EXISTENTE", "1 = INICIANDO", "2 = HABILITANDO", "3 = OPERACIONAL", "4 = OPTIMIZADO"}));
        comboBox.setSelectedIndex(-1);
        comboBox.setBounds(50, y + 10, 170, 30);

        // Agregamos el ComboBox al arrayList
        comboBoxSelector.add(comboBox);

        // Agregamos el ComboBox al panel
        panel.add(comboBox);

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
            btnSavePDF.setToolTipText("Pasar al siguiente ámbito");

            int posY = getPilaPanelesVisibles().peekFirst().getHeight() + getPilaPanelesVisibles().peekFirst().getY();
            btnSavePDF.setBounds(850, posY, 100, 45);

            btnSavePDF.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(btnSavePDF.getText().equals("Finalizar") && getPilaPanelesVisibles().size() == 1)
                        p.getMenuPanel().panelControl(p, ReportPanel.Frame_Value);
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
            btnPreview.setToolTipText("Pasar al ámbito anterior");

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


    private Deque<PanelShadow> getPilaPanelesVisibles() {
        if (pilaPanelesVisibles == null) {
            pilaPanelesVisibles = new ArrayDeque<>();

            pilaPanelesVisibles.offerLast(getLiderazgoDigitalPanel());
            pilaPanelesVisibles.offerLast(getCultClimaDigitalPanel());
            pilaPanelesVisibles.offerLast(getAlineamientoEstrategicoPanel());
        }
        return pilaPanelesVisibles;
    }

    private Deque<PanelShadow> getPilaPanelesNoVisibles() {
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

            // Técnicamente aquí, cuando la pila esté vacía, debería pasar al ReportPanel
            //if(getColaPanelesVisibles().isEmpty())


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

    public ArrayList<JComboBox<String>> getComboBoxSelector() {
        return comboBoxSelector;
    }


}
