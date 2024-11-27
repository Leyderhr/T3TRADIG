package logic.Entitys;

public class Perspectiva {

    private int id_perspectiva, cant_ptos, cant_preguntas, id_ambito;
    private String nombre_perspectiva;

    public Perspectiva() {
    }

    public Perspectiva(int id_perspectiva, int cant_ptos, int cant_preguntas, int id_ambito, String nombre_perspectiva) {
        setId_perspectiva(id_perspectiva);
        setCant_ptos(cant_ptos);
        setCant_preguntas(cant_preguntas);
        setId_ambito(id_ambito);
        setNombre_perspectiva(nombre_perspectiva);
    }

    public int getId_perspectiva() {
        return id_perspectiva;
    }

    public void setId_perspectiva(int id_perspectiva) {
        this.id_perspectiva = id_perspectiva;
    }

    public int getCant_ptos() {
        return cant_ptos;
    }

    public void setCant_ptos(int cant_ptos) {
        this.cant_ptos = cant_ptos;
    }

    public int getCant_preguntas() {
        return cant_preguntas;
    }

    public void setCant_preguntas(int cant_preguntas) {
        this.cant_preguntas = cant_preguntas;
    }

    public int getId_ambito() {
        return id_ambito;
    }

    public void setId_ambito(int id_ambito) {
        this.id_ambito = id_ambito;
    }

    public String getNombre_perspectiva() {
        return nombre_perspectiva;
    }

    public void setNombre_perspectiva(String nombre_perspectiva) {
        this.nombre_perspectiva = nombre_perspectiva;
    }

    /*Este metodo retorna un array de tamaño 2:
     * En la posicion 0 se encuentra el MDr(Madurez Digital media real de autoevaluacion)
     * En la posicion 1 se encuentra el IMD(Índice de Madurez Digital %)*/
    public float[] calculate_MDr_IMD(){
        float[] md = new float[2];

        md[0] = (float) cant_ptos / (float)cant_preguntas;
        md[1] = (md[0] / 4) * 100;

        return md;
    }
}
