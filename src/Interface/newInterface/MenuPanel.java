package Interface.newInterface;


import Interface.newInterface.Chart.ReportPanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class MenuPanel extends JPanel {
    //Declaración de variables

    private JLabel lblConsulting;
    private JPanel menuPanel;
    private ButtonMenu btnSettings;
    private ButtonMenu btnReports;
    private ButtonMenu btnMainPanel;
    private ButtonMenu btnQuestions;



    public MenuPanel(Principal1 p){
        menuPanel = new JPanel();
        p.getContentPane().add(menuPanel);
        menuPanel.setLayout(null);
        menuPanel.setBounds(0, 101, 239, 619);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.add(getBtnSettings(p));
        menuPanel.add(getBtnReports(p));
        menuPanel.add(getBtnMainPanel(p));
        menuPanel.add(getBtnQuestionsPanel(p));
        menuPanel.add(getLblConsulting());



    }

    /*Configuración del botón de settings*/
    //========================================================================

    private ButtonMenu getBtnSettings(Principal1 p) {

        if (btnSettings == null) {
            btnSettings = new ButtonMenu();
            btnSettings.setText("Configuración");
            btnSettings.setIcon(new ImageIcon(getClass().getResource("/util/settings-svgrepo-com(1).png")));
            btnSettings.setBounds(20, 20, 100, 100);
            btnSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnSettings.setHorizontalAlignment(SwingConstants.CENTER);
            btnSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);



            btnSettings.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    panelControl(p, SettingPanel.Frame_Value);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnSettings.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnSettings.setBackground(null);
                }
            });
        }
        return btnSettings;
    }
    //========================================================================

    public ButtonMenu getBtnReports(){
        return btnReports;
    }

    /*Configuración del botón de Reportes*/
    //========================================================================
    private ButtonMenu getBtnReports(Principal1 p) {

        if (btnReports == null) {
            btnReports = new ButtonMenu();
            btnReports.setText("Reportes");
            btnReports.setIcon(new ImageIcon(getClass().getResource("/util/chart.png")));
            btnReports.setBounds(130, 20, 100, 100);
            btnReports.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnReports.setHorizontalAlignment(SwingConstants.CENTER);
            btnReports.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnReports.setHorizontalTextPosition(SwingConstants.CENTER);

            btnReports.setEnabled(false);


            btnReports.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    if(btnReports.isEnabled())
                        panelControl(p, ReportPanel.Frame_Value);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnReports.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnReports.setBackground(null);
                }
            });

        }
        return btnReports;
    }



    //Configuración del botón para la pantalla principal
    //=========================================================

    private ButtonMenu getBtnMainPanel(Principal1 p) {

        if (btnMainPanel == null) {
            btnMainPanel = new ButtonMenu();
            btnMainPanel.setText("Principal");
            btnMainPanel.setIcon(new ImageIcon(getClass().getResource("/util/página-principal-25.png")));
            btnMainPanel.setBounds(20, 140, 100, 100);
            btnMainPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //btnMainPanel.setFont(new Font("Arial", Font.BOLD, 16));
            btnMainPanel.setHorizontalAlignment(SwingConstants.CENTER);
            btnMainPanel.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnMainPanel.setHorizontalTextPosition(SwingConstants.CENTER);

            btnMainPanel.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    panelControl(p,DashboardPanel.Frame_Value);

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnMainPanel.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnMainPanel.setBackground(null);
                }
            });
        }
        return btnMainPanel;
    }

    //Configuración del botón para la pantalla principal
    //=========================================================

    private ButtonMenu getBtnQuestionsPanel(Principal1 p) {

        if (btnQuestions == null) {
            btnQuestions = new ButtonMenu();
            btnQuestions.setText("Cuestionario");
            btnQuestions.setIcon(new ImageIcon(getClass().getResource("/util/cuestionario-25.png")));
            btnQuestions.setBounds(130, 140, 100, 100);
            btnQuestions.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //btnMainPanel.setFont(new Font("Arial", Font.BOLD, 16));
            btnQuestions.setHorizontalAlignment(SwingConstants.CENTER);
            btnQuestions.setVerticalTextPosition(SwingConstants.BOTTOM);
            btnQuestions.setHorizontalTextPosition(SwingConstants.CENTER);


            btnQuestions.addMouseListener(new MouseAdapter()  {
                @Override
                public void mousePressed(MouseEvent e) {
                    panelControl(p,QuestionPanel.Frame_Value);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnQuestions.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnQuestions.setBackground(null);
                }
            });
        }
        return btnQuestions;
    }


    //Método para controlar la visibilidad de los paneles (Ventanas)
    //================================================================
    public void panelControl(Principal1 p, int i){

        switch (i){

            /*0 -> Para poner visible la pantalla principal*/
            //===============================================
            case 0:
                p.getDashboardPanel().setVisible(true);
                if(p.getReportPanel()!= null)
                    p.getReportPanel().setVisible(false);
                p.getQuestionsPanel().setVisible(false);
                p.getSettingPanel().setVisible(false);
                break;

            /*1 -> Para poner visible la pantalla de Reportes*/
            //===============================================
            case 1:
                if(p.getReportPanel()!= null)
                    p.getReportPanel().setVisible(true);
                p.getDashboardPanel().setVisible(false);
                p.getQuestionsPanel().setVisible(false);
                p.getSettingPanel().setVisible(false);
                break;

            /*2 -> Para poner visible la pantalla de Preguntas*/
            //===============================================
            case 2:
                p.getQuestionsPanel().setVisible(true);
                p.getDashboardPanel().setVisible(false);
                if(p.getReportPanel()!= null)
                    p.getReportPanel().setVisible(false);
                p.getSettingPanel().setVisible(false);
                break;

             /*3 -> Para poner visible la pantalla de Configuración*/
            case 3:
                p.getSettingPanel().setVisible(true);
                p.getQuestionsPanel().setVisible(false);
                p.getDashboardPanel().setVisible(false);
                if(p.getReportPanel()!= null)
                    p.getReportPanel().setVisible(false);
                break;
            default:
                break;
        }
    }


    private JLabel getLblConsulting() {

        if (lblConsulting == null) {
            lblConsulting = new JLabel("");
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/Consultoría_logo.png")));
            lblConsulting.setIcon(icon);
//            lblConsulting.setHorizontalAlignment(SwingConstants.CENTER);
//            lblConsulting.setHorizontalTextPosition(SwingConstants.CENTER);
//            lblConsulting.setIconTextGap(1);
            lblConsulting.setBounds(0, 548, icon.getIconWidth(), icon.getIconHeight());
            lblConsulting.setOpaque(false);
            lblConsulting.setVisible(true);


            lblConsulting.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("hola mibida");
                }
            });
        }
        return lblConsulting;
    }


}
