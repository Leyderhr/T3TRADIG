package Interface.newInterface;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DashboardPanel extends JPanel {
    public static final int Frame_Value = 0;
    private final JPanel dashboardPanel;
    private JLabel lblTetradig;


    public DashboardPanel(Principal1 p) {
        dashboardPanel = new JPanel();
        p.getContentPane().add(dashboardPanel);
        dashboardPanel.setLayout(null);
        dashboardPanel.setBackground(Color.WHITE);
        dashboardPanel.setBounds(241, 100, 1039, 620);
        dashboardPanel.add(getLblTetradig());


    }


    private JLabel getLblTetradig() {

        if (lblTetradig == null) {
            lblTetradig = new JLabel("");
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Util/consultoría.jpg")));
            lblTetradig.setIcon(icon);
            lblTetradig.setHorizontalAlignment(SwingConstants.CENTER);
            lblTetradig.setHorizontalTextPosition(SwingConstants.CENTER);
            lblTetradig.setIconTextGap(1);
            lblTetradig.setBounds(0, 0, dashboardPanel.getWidth(), dashboardPanel.getHeight());
            //lblTetradig.setBounds(260, 240, 424, 145);
        }
        return lblTetradig;
    }

    @Override
    public void setVisible(boolean a) {
        this.dashboardPanel.setVisible(a);
    }
}



