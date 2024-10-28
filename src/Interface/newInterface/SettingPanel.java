package Interface.newInterface;

import Interface.export.swing.PanelShadow;
import Interface.export.swing.scrollbar.ScrollBarCustom;
import logic.Entitys.Ambito;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;
import logic.Entitys.Pregunta;
import logic.useful.Controlador;
import logic.useful.Model;
import util.table.MyTableCellRendererCeldas;
import util.table.TableModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;


public class SettingPanel extends PanelShadow {

    // Atributos
    // ========================================================================
    public static final int Frame_Value = 3;

    private ButtonMenu btnInfo;
    private ButtonMenu btnHelp;
    private ButtonMenu btnBack;
    private ButtonMenu btnAdd;
    private ButtonMenu btnDelete;

    private JPanel editQuestionPanel;
    private JPanel panelPrincipal;
    private JLabel lblPointLevel;
    private JScrollPane scrollPaneShowTable;
    private JTable tableExplorer;
    private TableModel tableModel;
    private Model thisModel;
    private ArrayList<Model> models;

    // ========================================================================

    public SettingPanel(Principal1 window){
        setBounds(241, 100, 1039, 620);
        setLayout(null);
        setVisible(false);
        thisModel = new Model(0, -1, 0, "TetraDig System");
        models = new ArrayList<>();

        add(getBtnInfo(window));
        add(getBtnHelp(window));
        add(getEditQuestionPanel());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tableExplorer.getSelectedRow() > -1) {
                    tableExplorer.clearSelection();
                }
            }
        });
    }



    // Configuración del Botón de Información
    // ========================================================================
    private ButtonMenu getBtnInfo(Principal1 window) {
        if (btnInfo == null) {
            btnInfo = new ButtonMenu();
            btnInfo.setText("Información");
            btnInfo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/info-circle-svgrepo-com(1).png"))));
            btnInfo.setBounds(909, 20, 110, 55);
            btnInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnInfo.setHorizontalAlignment(SwingConstants.LEFT);
            btnInfo.setVerticalTextPosition(SwingConstants.CENTER);
            btnInfo.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnInfo.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent me) {
                   try{
                        Information info = new Information(window);
                        info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        info.setVisible(true);
                   }
                   catch (Exception e){
                       e.printStackTrace();
                   }
                }

            });
        }
        return btnInfo;
    }
    // ========================================================================


    // Configuración del Botón de Ayuda
    // ========================================================================
    private ButtonMenu getBtnHelp(Principal1 window) {
        if (btnHelp == null) {
            btnHelp = new ButtonMenu();
            btnHelp.setText("Ayuda");
            btnHelp.setIcon(new ImageIcon(getClass().getResource("/util/help-circle-svgrepo-com(1).png")));
            btnHelp.setBounds(820, 20, 80, 55);
            btnHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnHelp.setHorizontalAlignment(SwingConstants.LEFT);
            btnHelp.setVerticalTextPosition(SwingConstants.CENTER);
            btnHelp.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnHelp.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent me) {
                    try{
                        Help help = new Help(window);
                        help.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        help.setVisible(true);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });
        }
        return btnHelp;
    }
    // ========================================================================




    //Configuración de toda la Parte de Editar las Preguntas
    // ========================================================================

    /* Utilizamos un panel para agregar en él todos los elementos que se necesitan
     * y éste panel a su vez lo agregamos al panel principal, que sería el constructor
     * de la clase*/


    private JPanel getEditQuestionPanel(){
        if(editQuestionPanel == null){
            editQuestionPanel = new JPanel();
            editQuestionPanel.setLayout(null);
            editQuestionPanel.setBounds(10, 255, 1019, 350);
            editQuestionPanel.setBackground(Color.WHITE);

            editQuestionPanel.add(getPanelPrincipal());
            editQuestionPanel.add(getBtnBack());
            editQuestionPanel.add(getBtnAdd());
            editQuestionPanel.add(getBtnDelete());

            editQuestionPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(!scrollPaneShowTable.contains(e.getPoint()))
                        tableExplorer.clearSelection();
                }
            });
        }
        return editQuestionPanel;
    }

    // Configuración del Botón de Atrás
    // ========================================================================
    private ButtonMenu getBtnBack() {
        if (btnBack == null) {
            btnBack = new ButtonMenu();
            btnBack.setText("Atrás");
            btnBack.setIcon(new ImageIcon(getClass().getResource("/util/left-arrow-circle-svgrepo-com(1).png")));
            btnBack.setBounds(909, 20, 90, 55);
            btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnBack.setHorizontalAlignment(SwingConstants.LEFT);
            btnBack.setVerticalTextPosition(SwingConstants.CENTER);
            btnBack.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnBack.setEnabled(false);
            btnBack.setVisible(false);

            btnBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(thisModel.getLevel() > 0){
                        actModel();
                        actTable();
                        actLabels();
                    }
                    if(thisModel.getLevel() == 0) {
                        btnBack.setEnabled(false);
                        btnBack.setVisible(false);
                    }
                }
            });
        }
        return btnBack;
    }


    // Configuración del Botón de Agregar Pregunta
    // ========================================================================
    private ButtonMenu getBtnAdd() {
        if (btnAdd == null) {
            btnAdd = new ButtonMenu();
            btnAdd.setText("Agregar");
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/util/add-circle-svgrepo-com(1).png")));
            btnAdd.setBounds(909, 95, 90, 55);
            btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnAdd.setHorizontalAlignment(SwingConstants.LEFT);
            btnAdd.setVerticalTextPosition(SwingConstants.CENTER);
            btnAdd.setHorizontalTextPosition(SwingConstants.RIGHT);

            btnAdd.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    // panelControl(p, SettingPanel.Frame_Value);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnAdd.setBackground(Color.GREEN);
                }

            });

            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder message = new StringBuilder();

                    switch(thisModel.getLevel()){
                        case 0:
                            message.append("Introduzca el nombre del nuevo ámbito para " + thisModel.getLine());
                            break;

                        case 1:
                            message.append("Introduzca el nombre de la nueva perspectiva para " + thisModel.getLine());
                            break;

                        case 2:
                            message.append("Introduzca el nombre de la nueva dimensión para " + thisModel.getLine());
                            break;

                        case 3:
                            message.append("Introduzca el nombre de la nueva pregunta para " + thisModel.getLine());
                            break;

                        default:break;
                    }

                    String line = JOptionPane.showInputDialog(SettingPanel.this, message);

                    try{
                        if(line != null){
                            Model model = new Model(0, thisModel.getId(), thisModel.getLevel() + 1, line);
                            Controlador.insert(model);
                            actTable();
                        }
                    }catch (IllegalArgumentException exception){
                        JOptionPane.showMessageDialog(SettingPanel.this, exception.getMessage());
                    }
                }
            });
        }
        return btnAdd;
    }

    // Configuración del Botón de Eliminar Pregunta
    // ========================================================================
    private ButtonMenu getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new ButtonMenu();
            btnDelete.setText("Eliminar");
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/util/delete-2-svgrepo-com(1).png")));
            btnDelete.setBounds(909, 170, 90, 55);
            btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDelete.setHorizontalAlignment(SwingConstants.LEFT);
            btnDelete.setVerticalTextPosition(SwingConstants.CENTER);
            btnDelete.setHorizontalTextPosition(SwingConstants.RIGHT);

            btnDelete.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    // panelControl(p, SettingPanel.Frame_Value);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnDelete.setBackground(Color.RED);
                }

            });

            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(tableExplorer.getSelectedRow() > -1){
                        int pos  = tableExplorer.getSelectedRow();
                        Model model = models.get(pos);

                        StringBuilder string = new StringBuilder();

                        switch (thisModel.getLevel()){
                            case 0:
                                string.append("Al eliminar el ámbito " + model.getLine() + " del " + thisModel.getLine() + " eliminara todos los valores relacionados con el.");
                                break;

                            case 1:
                                string.append("Al eliminar la perspectiva " + model.getLine() + " del ámbito " + thisModel.getLine() + " eliminara todos los valores relacionados con el.");
                                break;

                            case 2:
                                string.append("Al eliminar la dimensión " + model.getLine() + " de la perspectiva " + thisModel.getLine() + " eliminara todos los valores relacionados con el.");
                                break;

                            case 3:
                                string.append("Eliminara la pregunta #" + model.getId() + " de la dimensión " + thisModel.getLine());
                                break;

                            default: break;
                        }
                        string.append("\n¿Desea continuar?");

                        //int choise = ;
                        //System.out.println(choise);

                        if(JOptionPane.showConfirmDialog(SettingPanel.this, string, "Confirmación", JOptionPane.YES_NO_OPTION) == 0)
                            Controlador.delete(model);
                        actTable();

                    }else
                        JOptionPane.showMessageDialog(SettingPanel.this, "Para eliminar una linea debe seleccionarla 1ro");
                }
            });
        }
        return btnDelete;
    }

    private JPanel getPanelPrincipal(){
        if (panelPrincipal == null){
            panelPrincipal = new PanelShadow();
            panelPrincipal.setBounds(10, 5, 875, 340);
            panelPrincipal.setLayout(null);

            panelPrincipal.add(getLblPointLevel());
            panelPrincipal.add(getScrollPaneShowTable());
        }
        return panelPrincipal;
    }

    private JLabel getLblPointLevel() {
        if (lblPointLevel == null) {
            lblPointLevel = new JLabel("" );
            lblPointLevel.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 16));
            lblPointLevel.setBounds(20, 15, 600, 20);
            actLabels();
        }
        return lblPointLevel;
    }


    private JScrollPane getScrollPaneShowTable() {
        if (scrollPaneShowTable == null) {
            scrollPaneShowTable = new JScrollPane();
            scrollPaneShowTable.setBounds(10, 40, 855, 290);

            scrollPaneShowTable.setVerticalScrollBar(new ScrollBarCustom());
            scrollPaneShowTable.setViewportView(getTableExplorer());
        }
        return scrollPaneShowTable;
    }

    private JTable getTableExplorer() {
        if (tableExplorer == null) {
            tableExplorer = new JTable();
            tableModel = new TableModel();
            tableExplorer.setModel(tableModel);

            tableExplorer.setRowHeight(60);
            tableExplorer.setBorder(null);
            tableExplorer.getTableHeader().setReorderingAllowed(false);

            tableExplorer.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent event){
                    if(event.getClickCount() == 2)
                        enterNode();
                }
            });
            actTable();
        }
        return tableExplorer;
    }

    private void actTable(){
        tableModel = new TableModel();
        tableExplorer.setModel(tableModel);
        actModelList();
        ((TableModel)tableExplorer.getModel()).actTable(models);

        tableExplorer.getTableHeader().setBackground(new Color(8, 52, 128));
        tableExplorer.getTableHeader().setForeground(Color.white);
        tableExplorer.getTableHeader().setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 16));
        tableExplorer.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 13));

        tableExplorer.getColumnModel().getColumn(0).setMaxWidth(30);
        tableExplorer.getColumnModel().getColumn(0).setMinWidth(30);
        tableExplorer.getColumnModel().getColumn(1).setMaxWidth(825);

        tableExplorer.getColumnModel().getColumn(0).setCellRenderer(new MyTableCellRendererCeldas());
        tableExplorer.getColumnModel().getColumn(1).setCellRenderer(new MyTableCellRendererCeldas());
    }

    private void actModelList(){
        models.clear();

        switch (thisModel.getLevel()){
            case 0:
                for(Ambito a: Controlador.getAmbitos())
                    models.add(new Model(a.getId_ambito(), 0, 1, a.getNombre_ambito()));
                break;
            case 1:
                for(Perspectiva p: Controlador.getPerspectiva(thisModel.getId()))
                    models.add(new Model(p.getId_perspectiva(), p.getId_ambito(), 2, p.getNombre_perspectiva()));
                break;
            case 2:
                for(Dimension d: Controlador.getDimension(thisModel.getId()))
                    models.add(new Model(d.getId_dimension(), d.getId_perspectiva(), 3, d.getNombre_dimension()));
                break;
            case 3:
                for(Pregunta pe: Controlador.getPregunta(thisModel.getId()))
                    models.add(new Model(pe.getId_pregunta(), pe.getId_dimension(), 4, pe.getPregunta()));
                break;
            default: break;
        }

    }

    private void enterNode(){
        int pos = tableExplorer.getSelectedRow();
        if(thisModel.getLevel() < 3){
            thisModel = models.get(pos);
            actTable();
            actLabels();
            btnBack.setEnabled(true);
            btnBack.setVisible(true);
        }
    }

    private void actModel(){
        switch (thisModel.getLevel()){
            case 1:
                thisModel.setId(0);
                thisModel.setSupId(-1);
                thisModel.setLevel(0);
                thisModel.setLine("TetraDig System");
                break;

            case 2:
                Ambito a = Controlador.findAmbito(thisModel.getSupId());
                thisModel.setId(a.getId_ambito());
                thisModel.setSupId(0);
                thisModel.setLevel(1);
                thisModel.setLine(a.getNombre_ambito());
                break;

            case 3:
                Perspectiva p = Controlador.findPerspectiva(thisModel.getSupId());
                thisModel.setId(p.getId_perspectiva());
                thisModel.setSupId(p.getId_ambito());
                thisModel.setLevel(2);
                thisModel.setLine(p.getNombre_perspectiva());
                break;

            default: break;
        }
    }

    private void actLabels(){
        switch (thisModel.getLevel()) {
            case 0:
                lblPointLevel.setText("Ámbitos de: "+ thisModel.getLine());
                break;

            case 1:
                lblPointLevel.setText("Perspectivas de: "+ thisModel.getLine());
                break;

            case 2:
                lblPointLevel.setText("Dimensiones de: "+ thisModel.getLine());
                break;

            case 3:
                lblPointLevel.setText("Preguntas de: "+ thisModel.getLine());
                break;
        }
    }
}
