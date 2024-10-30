package Interface.newInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Objects;

public class Information extends JDialog {


    private JLabel lblNombre;
    private JLabel lblURL;
    private JLabel lblDescrip;
    private JLabel lblDevelo;
    private JLabel lblSuport;
    private JLabel lblVersion;
    private JLabel lblQR;

    public Information(Principal1 window) {
        super(window, true);
        setTitle("Información del programa");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/info-circle-svgrepo-com(1).png"))).getImage());
        setBounds(100, 100, 706, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel contentPanel = new JPanel();

        contentPanel.setLayout(null);
        contentPanel.setSize(706, 600);
        contentPanel.setBackground(Color.WHITE);

        getContentPane().add(contentPanel);

        contentPanel.add(getNombre());
        contentPanel.add(getURL());
        contentPanel.add(getDescrip());
        contentPanel.add(getDevelo());
        contentPanel.add(getSuport());
        contentPanel.add(getVersion());
        contentPanel.add(getQR());

    }

    // Con esta función se le añade al JLabel el nombre del proyecto

    private JLabel getNombre() {
        if (lblNombre == null) {
            lblNombre = new JLabel();
            lblNombre.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblNombre.setText("TETR4DIG.");
            lblNombre.setBounds(20, 70, 500, 50);
        }
        return lblNombre;
    }

    // Con esta función se le añade al JLabel la descripción general del proyecto TETR4DIG

    private JLabel getDescrip() {
        if (lblDescrip == null) {
            lblDescrip = new JLabel();
            lblDescrip.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblDescrip.setText("<html><p align: left>Esta aplicación fue creada para automátizar el proceso de evaluación " +
                    "de madurez de la transformación digital basada en el modelo TETR4DIG. Al finalizar " +
                    "el cuestionario de la aplicacion se generan reportes visuales en forma de gráficas que muestran el análisis de " +
                    "las respuestas. Puedes usar esta herramienta para realizar encuestas y obtener feedback de tus usuarios.</p> </html>");
            lblDescrip.setBounds(20, 100, 660, 100);
        }
        return lblDescrip;
    }

    // Con esta función se le añade al JLabel el nombre de los desarrolladores del proyecto TETR4DIG

    private JLabel getDevelo() {
        if (lblDevelo== null) {
            lblDevelo = new JLabel();
            lblDevelo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblDevelo.setText("<html>Desarrolladores:<br>" +
                    "Estudiantes de 3er año de la Universidad Tecnológica de La Habana (Cujae).<br><br>" +
                    "-Leyder Ignacio Herrera Bello<br>" +
                    "-Orlando Javier Marrero González<br>" +
                    "-Abdiel Rodríguez Lara<br>" +
                    "-Dennis Serafin Rodríguez Almentero</html>");
            lblDevelo.setBounds(20, 210, 501, 150);
        }
        return lblDevelo;
    }

    // Con esta función se le añade al JLabel el soporte(correo electrónico) de los desarrolladores del proyecto TETR4DIG

    private JLabel getSuport() {
        if (lblSuport == null) {
            lblSuport = new JLabel();
            lblSuport.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblSuport.setText("<html>Soporte técnico:<br> Correo electrónico:  mafiasupport@gmail.com.");
            lblSuport.setBounds(20, 340, 500, 100);
        }
        return lblSuport;
    }

    // Con esta función se le añade al JLabel la versión actual del proyecto TETR4DIG

    private JLabel getVersion() {
        if (lblVersion == null) {
            lblVersion = new JLabel();
            lblVersion.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblVersion.setText("<html><strong>Versión: 0.0.1 - [29/10/2024]<strong>");
            lblVersion.setBounds(250, 520, 500, 50);
        }
        return lblVersion;

    }
    private JLabel getQR(){

        if(lblQR == null) {
            lblQR = new JLabel();

            ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/qr-code.png")));
            // Cambiar el tamaño de la imagen
            int newWidth = 150;  // Ancho deseado
            int newHeight = 150; // Alto deseado
            Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen escalada
            Icon icon = new ImageIcon(scaledImage);
            lblQR.setIcon(icon);
            lblQR.setBounds(525, 400, icon.getIconWidth(), icon.getIconHeight());
        }
        return lblQR;
    }

    /* Con esta funcion y este evento de MouseListener el JLabel al ser presionado abre en el navegador el enlace
     *  que le ponemos dentro del evento*/

    private JLabel getURL() {
        if (lblURL == null) {
            lblURL = new JLabel();
            lblURL.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
            lblURL.setText("<html>Si necesitas ayuda escanea el siguiente código QR o presiona"+"<font color = 'blue'> aquí </font>"+"para ir a nuestra pag web.");
            lblURL.setBounds(20, 0, 600, 50);

            lblURL.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    try {
                        if (Desktop.isDesktopSupported()) {
                            Desktop desktop = Desktop.getDesktop();
                            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                desktop.browse(new URI("https://www.etecsa.cu/es/empresas/consultoria"));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return lblURL;
    }
}