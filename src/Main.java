import Interface.newInterface.Principal1;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {

                new Principal1().setVisible(true);
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(null,e.getMessage());
                throw new IllegalArgumentException(e.getMessage());
            }
        });

    }
}