package logic.Entitys;

public class Ambito {

    private int id_ambito, cant_ptos, cant_preguntas;
    private String nombre_ambito;

    public Ambito() {
    }

    public Ambito(int id_ambito, int cant_ptos, int cant_preguntas, String nombre_ambito) {
        setId_ambito(id_ambito);
        setCant_ptos(cant_ptos);
        setCant_preguntas(cant_preguntas);
        setNombre_ambito(nombre_ambito);
    }

    public int getId_ambito() {
        return id_ambito;
    }

    public void setId_ambito(int id_ambito) {
        this.id_ambito = id_ambito;
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

    public String getNombre_ambito() {
        return nombre_ambito;
    }

    public void setNombre_ambito(String nombre_ambito) {
        this.nombre_ambito = nombre_ambito;
    }

    /*Este metodo retorna un array de tamaño 2:
    * En la posicion 0 se encuentra el MDr(Madurez Digital media real de autoevaluacion)
    * En la posicion 1 se encuentra el IMD(Índice de Madurez Digital %)*/
    public float[] calculate_MDr_IMD(){
        float[] md = new float[2];

        nombre_ambito.translateEscapes();

        md[0] = (float) cant_ptos / (float) cant_preguntas;
        md[1] = (md[0] / 4) * 100;

        return md;
    }
}
