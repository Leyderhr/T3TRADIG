package Interface.newInterface;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Information extends JDialog {


    private JLabel lblInfo;


    public Information(Principal1 window){
        super(window, true);
        setTitle("Información del programa");
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/info-circle-svgrepo-com(1).png"))).getImage());
        setBounds(100, 100, 706, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setSize(706,600);
        contentPanel.setBackground(Color.WHITE);

        getContentPane().add(contentPanel);

        contentPanel.add(getInfo());

    }


    private JLabel getInfo(){
        if(lblInfo == null){
            lblInfo = new JLabel();
            lblInfo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
            lblInfo.setText("Este programa esta hecho por La Mafia");
            lblInfo.setBounds(0, 0, 500, 50);
        }
        return lblInfo;
    }



}
