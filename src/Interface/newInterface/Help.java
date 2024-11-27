package Interface.newInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Objects;

public class Help extends JDialog {

    private JLabel lblInfo;
    private JLabel lblPorcent;
    private JLabel lblLevels;

    public Help(Principal1 window){
        super(window, true);
        setTitle("¿Necesitas Ayuda?");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/help-circle-svgrepo-com(1).png"))).getImage());
        setBounds(100, 100, 706, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setSize(706,600);
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(getInfo());
        contentPanel.add(getLblPorcentaje());
        contentPanel.add(getLblLevels());
    }


    private JLabel getLblPorcentaje() {

        if (lblPorcent == null) {
            lblPorcent = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/porcentajes.PNG")));
            lblPorcent.setIcon(icon);
            lblPorcent.setHorizontalAlignment(SwingConstants.CENTER);
            lblPorcent.setHorizontalTextPosition(SwingConstants.CENTER);
            lblPorcent.setIconTextGap(1);
            lblPorcent.setBounds(10, 150, icon.getIconWidth(), icon.getIconHeight());
        }
        return lblPorcent;
    }

    private JLabel getLblLevels() {

        if (lblLevels == null) {
            lblLevels = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/niveles.PNG")));
            lblLevels.setIcon(icon);
            lblLevels.setHorizontalAlignment(SwingConstants.CENTER);
            lblLevels.setHorizontalTextPosition(SwingConstants.CENTER);
            lblLevels.setIconTextGap(1);
            lblLevels.setBounds(10, lblPorcent.getY() + lblPorcent.getHeight() + 10, icon.getIconWidth(), icon.getIconHeight());
        }
        return lblLevels;
    }


    private JLabel getInfo(){
        if(lblInfo == null){
            lblInfo = new JLabel();
            lblInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblInfo.setText("<html><p align: left>  Esta herramienta permite conocer el estado actual del cliente (autoevaluación) con " +
                    "respecto a la transformación digital, en términos preliminares de resultados del proceso y de " +
                    "las capacidades estratégicas y de creación de valor sustentable; así como a nivel de " +
                    "perspectivas y dimensiones organizacionales; y le facilita trazar líneas de trabajo o una hoja" +
                    "de ruta de mejora. Se entrega un informe con los resultados de madurez digital, que incluye " +
                    "datos y gráficos sobre índices de <br>Madurez Digital Global (IMDG), por Ámbitos (IMDA), " +
                    "Perspectivas (IMDP) y Dimensiones. (IMDD)</p></html>");
            lblInfo.setLocation(5, 0);
            lblInfo.setSize(695, 150);
            lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
            lblInfo.setVerticalTextPosition(SwingConstants.TOP);
        }
        return lblInfo;
    }

}
