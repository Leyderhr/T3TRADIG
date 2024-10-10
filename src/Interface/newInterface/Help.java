package Interface.newInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Objects;

public class Help extends JDialog {

    private JLabel lblInfo;

    public Help(Principal1 window){
        super(window, true);
        setTitle("Necesitas Ayuda?");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/Util/help-circle-svgrepo-com(1).png"))).getImage());
        setBounds(100, 100, 750, 450);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setSize(760,450);
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        contentPanel.add(getInfo());
    }


    private JLabel getInfo(){
        if(lblInfo == null){
            lblInfo = new JLabel();
            lblInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblInfo.setText("Si necesitas ayuda escanea el siguiente código QR o presiona aqui para ir a nuestra pag web.");
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
