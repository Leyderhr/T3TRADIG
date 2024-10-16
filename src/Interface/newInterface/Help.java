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
            lblPorcent.setBounds(10, 40, icon.getIconWidth(), icon.getIconHeight());
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
            lblInfo.setText("Si necesitas ayuda escanea el siguiente código QR o presiona aquí para ir a nuestra pag web.");
            lblInfo.setBounds(0, 0, 500, 50);


            /* Con este evento de MouseListener el JLabel al ser presionado abre en el navegador el enlace
             *  que le ponemos dentro del evento*/
            lblInfo.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    try{
                        if(Desktop.isDesktopSupported()){
                            Desktop desktop = Desktop.getDesktop();
                            if(desktop.isSupported(Desktop.Action.BROWSE)){
                                desktop.browse(new URI("https://www.etecsa.cu/"));
                            }
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        return lblInfo;
    }

}
