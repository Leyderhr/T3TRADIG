package Interface.newInterface;

import Interface.Export.swing.PanelShadow;
import Util.table.TableTreeModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JTable tableTree;
    private TableTreeModel tableTreeModel;

    // ========================================================================

    public SettingPanel(Principal1 window){
        setBounds(241, 100, 1039, 620);
        setLayout(null);
        setVisible(false);
        add(getBtnInfo(window));
        add(getBtnHelp(window));
        add(getEditQuestionPanel());
    }



    // Configuración del Botón de Información
    // ========================================================================
    private ButtonMenu getBtnInfo(Principal1 window) {
        if (btnInfo == null) {
            btnInfo = new ButtonMenu();
            btnInfo.setText("Información");
            btnInfo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Util/info-circle-svgrepo-com(1).png"))));
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
            btnHelp.setIcon(new ImageIcon(getClass().getResource("/Util/help-circle-svgrepo-com(1).png")));
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
        }
        return editQuestionPanel;
    }

    // Configuración del Botón de Atrás
    // ========================================================================
    private ButtonMenu getBtnBack() {
        if (btnBack == null) {
            btnBack = new ButtonMenu();
            btnBack.setText("Atrás");
            btnBack.setIcon(new ImageIcon(getClass().getResource("/Util/left-arrow-circle-svgrepo-com(1).png")));
            btnBack.setBounds(909, 20, 90, 55);
            btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnBack.setHorizontalAlignment(SwingConstants.LEFT);
            btnBack.setVerticalTextPosition(SwingConstants.CENTER);
            btnBack.setHorizontalTextPosition(SwingConstants.RIGHT);

            btnBack.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    // panelControl(p, SettingPanel.Frame_Value);

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
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/Util/add-circle-svgrepo-com(1).png")));
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
        }
        return btnAdd;
    }


    // Configuración del Botón de Eliminar Pregunta
    // ========================================================================
    private ButtonMenu getBtnDelete() {
        if (btnDelete == null) {
            btnDelete = new ButtonMenu();
            btnDelete.setText("Eliminar");
            btnDelete.setIcon(new ImageIcon(getClass().getResource("/Util/delete-2-svgrepo-com(1).png")));
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
            //panelPrincipal.add(getLblActualNode());
        }
        return panelPrincipal;
    }


    private JLabel getLblPointLevel() {
        if (lblPointLevel == null) {
            lblPointLevel = new JLabel("Ámbitos de: ");
            lblPointLevel.setBounds(20, 10, 160, 20);
        }
        return lblPointLevel;
    }


    private JScrollPane getScrollPaneShowTable() {
        if (scrollPaneShowTable == null) {
            scrollPaneShowTable = new JScrollPane();
            scrollPaneShowTable.setBounds(10, 40, 855, 290);
            //scrollPaneShowTable.setColumnHeaderView(getTableTree());
        }
        return scrollPaneShowTable;
    }

//    private JTable getTableTree() {
//        if (tableTree == null) {
//            tableTree = new JTable();
//            tableTreeModel = new TableTreeModel();
//            tableTree.setModel(tableTreeModel);
//            tableTree.addMouseListener(new java.awt.event.MouseAdapter() {
//                public void mouseClicked(java.awt.event.MouseEvent event){
//                    if(event.getClickCount() == 2)
//                        enterNode();
//                }
//            });
//            actTable(thisNode);
//        }
//        return tableTree;
//    }
//
//    private void actTable(BinaryTreeNode<Model> node){
//        tableTreeModel = new TableTreeModel();
//        tableTree.setModel(tableTreeModel);
//        ((TableTreeModel)tableTree.getModel()).actTable(tetra.getTree().getSonsInfo(node));
//    }





}
