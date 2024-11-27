package Interface.newInterface;

import Interface.newInterface.Chart.ReportPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Principal1 extends JFrame {
    private  WindPanel windPanel;
    private DashboardPanel dashboardPanel;
    private ReportPanel reportPanel;
    private QuestionPanel questionsPanel;
    private MenuPanel menuPanel;
    private SettingPanel settingPanel;

    private Point point;




    public Principal1() throws Exception{
        setBounds(0, 0, 1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("TEST DE TRANSFORMACIÓN DIGITAL (TETR4DIG)");
        setLocationRelativeTo(null);
        setContentPane(new JPanel());
        setBackground(Color.BLACK);
        setIconImage(Toolkit.getDefaultToolkit().getImage(Principal1.class.getResource("/util/Imagen1-removebg-preview.png")));

        add(windPanel = new WindPanel(Principal1.this));
        add(dashboardPanel = new DashboardPanel(Principal1.this));
        //add(reportPanel = new ReportPanel(Principal1.this));
        add(menuPanel = new MenuPanel(Principal1.this));
        add(questionsPanel = new QuestionPanel(Principal1.this));
        add(settingPanel = new SettingPanel(Principal1.this));
        setLayout(null);
        setUndecorated(true);

        /*Evento para hacer que se pueda mover la ventana*/
        //====================================================
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                point = e.getPoint();
                getComponentAt(point);
            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int CurrentX = getLocation().x;
                int CurrentY = getLocation().y;

                int MoveX = (CurrentX + e.getX()) - (CurrentX + point.x);
                int MoveY = (CurrentY + e.getY()) - (CurrentY + point.y);

                int x = CurrentX + MoveX;
                int y = CurrentY + MoveY;

                setLocation(x, y);
            }
        });
        //=========================================================
    }

    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }

    public ReportPanel getReportPanel() {
        return reportPanel;
    }

    public QuestionPanel getQuestionsPanel() {
        return questionsPanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public SettingPanel getSettingPanel(){
        return settingPanel;
    }


    public void addReportPanel() throws Exception {
        Principal1.this.add(reportPanel = new ReportPanel(Principal1.this));
    }
}
