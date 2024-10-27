package Interface.export.swing;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CircleProgressBar extends JProgressBar {

    public CircleProgressBar(float index) {
        setOpaque(false);
        setBackground(new Color(220, 220, 220));
        setForeground(new Color(97, 97, 97));
        setStringPainted(false);

        setUI(new ProgressCircleUI(this));



        //Aquí es donde se establece el porciento del gráfico de pastel
        setValue(Math.round(index));


    }


}
