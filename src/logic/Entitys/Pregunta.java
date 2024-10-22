package logic.Entitys;

public class Pregunta {

    private int id_pregunta, ptos, id_dimension;
    private String pregunta;

    public Pregunta() {
    }

    public Pregunta(int id_pregunta, int ptos, int id_dimension, String pregunta) {
        setId_pregunta(id_pregunta);
        setPtos(ptos);
        setId_dimension(id_dimension);
        setPregunta(pregunta);
    }

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public int getPtos() {
        return ptos;
    }

    public void setPtos(int ptos) {
        this.ptos = ptos;
    }

    public int getId_dimension() {
        return id_dimension;
    }

    public void setId_dimension(int id_dimension) {
        this.id_dimension = id_dimension;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
