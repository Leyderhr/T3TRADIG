package logic.Entitys;

public class Dimension {

    private int id_dimension, cant_preguntas, cant_puntos, id_perspectiva;
    private String nombre_dimension;


    public Dimension() {
    }

    public Dimension(int id_dimension, int cant_preguntas, int cant_puntos, int id_perspectiva, String nombre_dimension) {
        setId_dimension(id_dimension);
        setCant_preguntas(cant_preguntas);
        setCant_puntos(cant_puntos);
        setId_perspectiva(id_perspectiva);
        setNombre_dimension(nombre_dimension);
    }

    public int getId_dimension() {
        return id_dimension;
    }

    public void setId_dimension(int id_dimension) {
        this.id_dimension = id_dimension;
    }

    public int getCant_preguntas() {
        return cant_preguntas;
    }

    public void setCant_preguntas(int cant_preguntas) {
        this.cant_preguntas = cant_preguntas;
    }

    public int getCant_puntos() {
        return cant_puntos;
    }

    public void setCant_puntos(int cant_puntos) {
        this.cant_puntos = cant_puntos;
    }

    public int getId_perspectiva() {
        return id_perspectiva;
    }

    public void setId_perspectiva(int id_perspectiva) {
        this.id_perspectiva = id_perspectiva;
    }

    public String getNombre_dimension() {
        return nombre_dimension;
    }

    public void setNombre_dimension(String nombre_dimension) {
        this.nombre_dimension = nombre_dimension;
    }

    /*Este metodo retorna un array de tamaño 2:
     * En la posicion 0 se encuentra el MDr(Madurez Digital media real de autoevaluacion)
     * En la posicion 1 se encuentra el IMD(Índice de Madurez Digital %)*/
    public float[] calculate_MDr_IMD(){
        float[] md = new float[2];

        md[0] = (float) cant_puntos / (float)cant_preguntas;
        md[1] = (md[0] / 4) * 100;

        return md;
    }
}
