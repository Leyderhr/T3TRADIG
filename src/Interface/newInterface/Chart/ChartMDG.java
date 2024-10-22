package Interface.newInterface.Chart;

import Interface.newInterface.python.PythonExecutor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ChartMDG extends JPanel {

    private JLabel matrixIMDG;
    private JLabel generalIMDG;
    private JLabel pieLabel;
    private JLabel header;
    private JLabel indicator;
    private float generalIndex;

    /*Estos valores le pertenecen a los índices de
     * RESULTADOS DE DIGITALIZACIÓN Y CAPACIDADES ESTRATÉGICAS Y DE CREACIÓN DE VALOR SUSTENTABLES
     * Necesarios para colocar el IMDG en la gráfica*/
    //==================================================================
    private float valorX;
    private float valorY;
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

    public ChartMDG(float valorX, float valorY) {
        setLayout(null);
        setValorX(valorX);
        setValorY(valorY);
        this.generalIndex = ((this.valorX + this.valorY) / 2);

        add(getHeader());
        add(getIndicator());

        add(getPieChart());
        add(getGeneralIMDG());
        update();
        add(getMatrixIMDG());

        setBackground(Color.blue);
        setSize(matrixIMDG.getWidth() + pieLabel.getWidth() + 100, header.getHeight() + matrixIMDG.getHeight() + 50);
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

    private JLabel getIndicator() {
        if (indicator == null) {
            indicator = new JLabel();
            indicator.setFont(new Font("Arial", Font.PLAIN, 18));
            indicator.setBounds(145, 440, 300, 20);
            indicator.setOpaque(false);
            indicator.setHorizontalAlignment(SwingConstants.LEFT);
            indicator.setHorizontalTextPosition(SwingConstants.RIGHT);



            if (generalIndex >= 0 && generalIndex <= 25) {
                indicator.setText("BÁSICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Basico.png")));
                indicator.setIcon(icon);
            } else if (generalIndex > 25 && generalIndex <= 50) {
                indicator.setText("INICIAL");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador- Inicial.png")));
                indicator.setIcon(icon);
            } else if (generalIndex > 50 && generalIndex <= 75) {
                indicator.setText("ESTRATÉGICO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Estrategico.png")));
                indicator.setIcon(icon);
            } else {
                indicator.setText("INNOVADOR - DIRSUPTIVO");
                Icon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/indicador - Innovador.png")));
                indicator.setIcon(icon);
            }
        }
        return indicator;
    }

    private JLabel getMatrixIMDG() {
        if (matrixIMDG == null) {
            matrixIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/MatrizIMDG.png")));
            matrixIMDG.setIcon(icon);
            matrixIMDG.setHorizontalAlignment(SwingConstants.CENTER);
            matrixIMDG.setHorizontalTextPosition(SwingConstants.CENTER);
            matrixIMDG.setBounds(pieLabel.getHeight() + 31, header.getHeight() + 30, icon.getIconWidth(), icon.getIconHeight());
        }
        return matrixIMDG;
    }


    private JLabel getGeneralIMDG() {
        if ((generalIMDG == null)) {
            generalIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/icons8-emoji-circulo-azul-20.png")));
            generalIMDG.setIcon(icon);
            generalIMDG.setToolTipText(this.valorX + ";" + " " + this.valorY + ";" + " " + this.generalIndex);
        }
        return generalIMDG;
    }


    /**
     * <l>@param: x -> Valor del resultado de digitalización</l>
     */
    private Integer[] calcIndexPosition(float x, float y) {
        y = 100-y;
        //NOTA → Cada cuadrado tiene 137px de ancho por 110px de largo
        // 445x365 → medida de la gráfica central (los 12 cuadrados)
        Integer[] pos = new Integer[2];
        pos[0] = Math.round((x * 4.45f) + 446);
        pos[1] = Math.round((y * 3.65f) +127);


        return pos;
    }


    public void update() {
        Integer[] i = calcIndexPosition(this.valorX, this.valorY);
        generalIMDG.setLocation(i[0], i[1]);
        generalIMDG.setSize(20, 20);
    }


    private JLabel getPieChart() {
        if (pieLabel == null) {
            pieLabel = new JLabel("");

            // Se crea la gráfica de MDA (Madurez Digital por Ámbitos)
            String generalValue = String.valueOf(this.generalIndex);

            File file = new File("/util/chartsPython/graficaCircular1.png");
            if(!file.exists() && file.isFile() && file.getName().endsWith(".png"))
                PythonExecutor.pieChart("'Índice de Madurez Digital Global\\n (IMDG)'", generalValue, "3", "3.8", "'1'");

            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/util/chartsPython/graficaCircular1.png"), "Imagen no encontrada"));
            pieLabel.setIcon(icon);
            pieLabel.setBounds(1, header.getHeight() + 50, icon.getIconWidth(), icon.getIconHeight());
        }
        return pieLabel;
    }

}
