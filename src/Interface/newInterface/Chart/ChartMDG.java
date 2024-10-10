package Interface.newInterface.Chart;

import javax.swing.*;

public class ChartMDG extends JPanel {

    private JLabel matrixIMDG;
    private JLabel generalIMDG;
    private float generalIndex;

    /*Estos valores le pertenecen a los índices de
     * RESULTADS DE DIGITALIZACIÓN Y CAPACIDADES ESTRATÉGICAS Y DE CREACIÓN
     * DE VALOR SUSTENTABLES
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
        setSize(getMatrixIMDG().getWidth(), getMatrixIMDG().getHeight());
        add(getGeneralIMDG());
        add(getMatrixIMDG());
        update();
    }


    private JLabel getMatrixIMDG() {
        if (matrixIMDG == null) {
            matrixIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/util/MatrizIMDG.png"));
            matrixIMDG.setIcon(icon);
            matrixIMDG.setHorizontalAlignment(SwingConstants.CENTER);
            matrixIMDG.setHorizontalTextPosition(SwingConstants.CENTER);
            matrixIMDG.setBounds(1, 1, icon.getIconWidth(), icon.getIconHeight());
        }
        return matrixIMDG;
    }


    private JLabel getGeneralIMDG() {
        if ((generalIMDG == null)){
            generalIMDG = new JLabel();
            ImageIcon icon = new ImageIcon(getClass().getResource("/util/icons8-emoji-circulo-azul-20.png"));
            generalIMDG.setIcon(icon);
            generalIMDG.setToolTipText(this.valorX+";"+" "+this.valorY+";"+" "+this.generalIndex);
        }
        return generalIMDG;
    }


    /**
     * <l>@param: x -> Valor del resultado de digitalización</l>
     */
    private Integer[] calcIndexPosition(float x, float y) {

        //NOTA → Cada cuadrado tiene 137px de ancho por 110px de largo
        Integer[] pos = new Integer[2];
        pos[0] = Math.round((x * 4.45f) + 59);
        if(y < 50.00f)
            pos[1] = Math.round(((y * 3.65f) + 190) - 25);
        else{
            pos[1] = Math.round((y * 3.65f) - 165);
        }

        return pos;
    }

    public void update(){
        Integer[] i = calcIndexPosition(this.valorX, this.valorY);
        generalIMDG.setLocation(i[0], i[1]);
        generalIMDG.setSize(20,20);
    }

}
