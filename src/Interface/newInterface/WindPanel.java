package Interface.newInterface;

import Interface.export.swing.ButtonMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class WindPanel extends JPanel {
    JPanel windPanel;
    private JButton btnClose;
    private JButton btnMaximize;
    private JButton btnMinimize;
    private JLabel lblLogo;

    public WindPanel(Principal1 pincipal1) {
        windPanel = new JPanel();
        pincipal1.getContentPane().add(windPanel);
        windPanel.setLayout(null);
        windPanel.setBackground(new Color(8,52,128));
        windPanel.setBounds(0, 0, 1280, 100);

        windPanel.add(getBtnClose());
        windPanel.add(getBtnMaximize(pincipal1));
        windPanel.add(getBtnMinimize(pincipal1));
        windPanel.add(new ButtonMenu());
        windPanel.add(getLogo());
    }

    //Botones de Inicio o Principales
    //========================================================
    private JButton getBtnClose() {

        if (btnClose == null) {
            btnClose = new JButton();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/close(1).png")));
            btnClose.setIcon(icon);
            btnClose.setBackground(null);
            btnClose.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            btnClose.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnClose.setBackground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnClose.setBackground(null);
                }
            });

            btnClose.setHorizontalAlignment(SwingConstants.CENTER);
            btnClose.setHorizontalTextPosition(SwingConstants.CENTER);
            btnClose.setIconTextGap(1);
            btnClose.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnClose.setBounds(1245, 5, 30, 30);
            btnClose.setToolTipText("Cerrar");
        }
        return btnClose;
    }


    private JButton getBtnMaximize(Principal1 p) {

        if (btnMaximize == null) {
            btnMaximize = new JButton();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/maximize(1).png")));
            btnMaximize.setIcon(icon);
            btnMaximize.setToolTipText("Maximizar");
            btnMaximize.setBackground(null);
            btnMaximize.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnMaximize.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            btnMaximize.setEnabled(false);
//            btnMaximize.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    if (p.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
//                        p.setExtendedState(JFrame.NORMAL);
//                    } else {
//                        p.setExtendedState(JFrame.MAXIMIZED_BOTH);
//                    }
//                }
//
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    // Cambia el color de fondo cuando se pasa el cursor por encima
//                    btnMaximize.setBackground(Color.gray);
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    // Restaura el color de fondo cuando el cursor sale
//                    btnMaximize.setBackground(null);
//                }
//            });
            btnMaximize.setHorizontalAlignment(SwingConstants.CENTER);
            btnMaximize.setHorizontalTextPosition(SwingConstants.CENTER);
            btnMaximize.setIconTextGap(1);
            btnMaximize.setBounds(1214, 5, 30, 30);
        }
        return btnMaximize;
    }

    private JButton getBtnMinimize(Principal1 p) {

        if (btnMinimize == null) {
            btnMinimize = new JButton();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/minimize(1).png")));
            btnMinimize.setToolTipText("Minimizar");
            btnMinimize.setBackground(null);
            btnMinimize.setIcon(icon);
            btnMinimize.setBorder(new EmptyBorder(0, 0, 0, 0));
            btnMinimize.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
            btnMinimize.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    p.setExtendedState(Frame.ICONIFIED);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color de fondo cuando se pasa el cursor por encima
                    btnMinimize.setBackground(Color.gray);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaura el color de fondo cuando el cursor sale
                    btnMinimize.setBackground(null);
                }
            });
            btnMinimize.setHorizontalAlignment(SwingConstants.CENTER);
            btnMinimize.setHorizontalTextPosition(SwingConstants.CENTER);
            btnMinimize.setIconTextGap(1);
            btnMinimize.setBounds(1183, 5, 30, 30);
        }
        return btnMinimize;
    }


    private JLabel getLogo() {

        if (lblLogo == null) {
            lblLogo = new JLabel("Empresa de Telecomunicaciones de Cuba S.A");
            lblLogo.setFont(new Font("MyriadPro-SemiCond",Font.ITALIC, 13));
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/logoheader.png")));
            lblLogo.setForeground(new Color(102,102,102));
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setIcon(icon);
            lblLogo.setHorizontalAlignment(SwingConstants.LEFT);
            lblLogo.setVerticalTextPosition(SwingConstants.BOTTOM);
            lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
            lblLogo.setIconTextGap(1);
            lblLogo.setBounds(3, 15, 280, 80);
        }
        return lblLogo;
    }
}
