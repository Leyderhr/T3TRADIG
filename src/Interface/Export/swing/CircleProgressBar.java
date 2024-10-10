package Interface.Export.swing;

import javax.swing.*;
import java.awt.*;

public class CircleProgressBar extends JProgressBar {

    public CircleProgressBar(float index) {
        setOpaque(false);
        setBackground(new Color(220, 220, 220));
        setForeground(new Color(97, 97, 97));
        setStringPainted(true);
        setUI(new ProgressCircleUI(this));

        //Aquí es donde se establece el porciento del gráfico de pastel
        setValue(Math.round(index));

    }

}
