import Interface.newInterface.Principal1;

import java.awt.*;


public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal1().setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}