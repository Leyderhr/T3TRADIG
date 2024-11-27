package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;
import logic.Entitys.Ambito;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ChartMDG extends JPanel {

    private JLabel matrixIMDG;
    private JLabel generalIMDG;
    private DonutPie donutChartIMDG;
    private JLabel header;
    private float generalIndex;

    /*Estos valores le pertenecen a los índices de
     * RESULTADOS DE DIGITALIZACIÓN Y CAPACIDADES ESTRATÉGICAS Y DE CREACIÓN DE VALOR SUSTENTABLES
     * Necesarios para colocar el IMDG en la gráfica*/
    //==================================================================
    private float valorX;  // Capacidades Estrategicas
    private float valorY;  // Resultados de digitalizacion
    //==================================================================

    public float getGeneralIndex() {
        return generalIndex;
    }

    public void setGeneralIndex(int generalIndex) {
        this.generalIndex = generalIndex;
    }

    public float getValorX() {
        return valorX;
    }

    public void setValorX(float valorX) {
        this.valorX = valorX;
    }

    public float getValorY() {
        return valorY;
    }

    public void setValorY(float valorY) {
        this.valorY = valorY;
    }

    public ChartMDG(Ambito valueX, Ambito valueY) {
        setLayout(null);
        setValorX(valueX.calculate_MDr_IMD()[1]);
        setValorY(valueY.calculate_MDr_IMD()[1]);
        this.generalIndex = ((((float)valueX.getCant_ptos() + (float) valueY.getCant_ptos()) / ((float) valueX.getCant_preguntas() + (float) valueY.getCant_preguntas())) / 4) * 100;

        add(getHeader());

        add(getDonutChartIMDG());
        add(getGeneralIMDG());
        update();
        add(getMatrixIMDG());

        setBackground(Color.WHITE);
        setSize(matrixIMDG.getWidth() + donutChartIMDG.getWidth() + 100, header.getHeight() + matrixIMDG.getHeight() + 50);
    }

    /**
     * Este método se encarga de crear el Encabezado para las gráficas
     */
    private JLabel getHeader() {
        if (header == null) {
            header = new JLabel("Resultados: Madurez Digital Global (MDG)");
            header.setFont(new Font("Myriad Pro Bold Cond", Font.BOLD, 30));
            header.setBounds(20, 10, 800, 80);
            header.setOpaque(false);
            header.setForeground(new Color(8, 52, 128));
        }
        return header;
    }


    private JLabel getMatrixIMDG() {
        if (matrixIMDG == null) {
            matrixIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/MatrizIMDG.png")));
            matrixIMDG.setIcon(icon);
            matrixIMDG.setHorizontalAlignment(SwingConstants.CENTER);
            matrixIMDG.setHorizontalTextPosition(SwingConstants.CENTER);
            matrixIMDG.setBounds(donutChartIMDG.getHeight() + 60, header.getHeight() + 30, icon.getIconWidth(), icon.getIconHeight());
        }
        return matrixIMDG;
    }


    private JLabel getGeneralIMDG() {
        if ((generalIMDG == null)) {
            generalIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/icons8-emoji-circulo-azul-20.png")));
            generalIMDG.setIcon(icon);
            generalIMDG.setToolTipText("<html>" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Tooltip Personalizado</title>\n" +
                    "    <style>\n" +
                    "        /* Estilo del tooltip */\n" +
                    "        .tooltip {\n" +
                    "            position: relative;\n" +
                    "            display: inline-block;\n" +
                    "            cursor: pointer;\n" +
                    "        }\n" +
                    "\n" +
                    "        .tooltip .tooltiptext {\n" +
                    "            visibility: hidden;\n" +
                    "            width: 80px;\n" +
                    "            height: 10px;\n" +
                    "            /*background-color: #555;*/\n" +
                    "            color: black;\n" +
                    "            text-align: left;\n" +
                    "            border-radius: 5px;\n" +
                    "            padding: 10px;\n" +
                    "            position: absolute;\n" +
                    "            z-index: 1;\n" +
                    "            bottom: 125%; /* Posición del tooltip */\n" +
                    "            left: 50%;\n" +
                    "            margin-left: -250px; /* Centrar el tooltip */\n" +
                    "            opacity: 0;\n" +
                    "            transition: opacity 0.3s;\n" +
                    "        }\n" +
                    "\n" +
                    "        .tooltip:hover .tooltiptext {\n" +
                    "            visibility: visible;\n" +
                    "            opacity: 1;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"tooltip\">\n" +
                    "    <div class=\"tooltiptext\"> <p align: center>" + valorX + "; " + valorY + ";" + "<br> <b>"+
                    generalIndex +
                    "</b></p> </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>" +
                    "</html>");

        }
        return generalIMDG;
    }


    /**
     * <l>@param: x -> Valor del resultado de digitalización</l>
     */
    private Integer[] calcIndexPosition(float x, float y) {
        y = 100 - y;
        //NOTA → Cada cuadrado tiene 137px de ancho por 110px de largo
        // 445x365 → medida de la gráfica central (los 12 cuadrados)
        Integer[] pos = new Integer[2];
        pos[0] = Math.round((x * 4.45f) + 446);
        pos[1] = Math.round((y * 3.65f) + 127);


        return pos;
    }


    public void update() {
        Integer[] i = calcIndexPosition(this.valorX, this.valorY);
        generalIMDG.setLocation(i[0], i[1]);
        generalIMDG.setSize(20, 20);
    }


    private DonutPie getDonutChartIMDG() {
        if (donutChartIMDG == null) {
            donutChartIMDG = new DonutPie("Índice Madurez Digital Global (IMDG)", generalIndex, 30, header.getHeight() + 50, 250, 250);
            donutChartIMDG.setLocation(30, header.getHeight() + 50);
        }
        return donutChartIMDG;
    }

}
