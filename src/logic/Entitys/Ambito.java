package logic.Entitys;

public class Ambito {

    private int id_ambito, cant_ptos, cant_perspectivas;
    private String nombre_ambito;

    public Ambito() {
    }

    public Ambito(int id_ambito, int cant_ptos, int cant_perspectivas, String nombre_ambito) {
        setId_ambito(id_ambito);
        setCant_ptos(cant_ptos);
        setCant_perspectivas(cant_perspectivas);
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

    public int getCant_perspectivas() {
        return cant_perspectivas;
    }

    public void setCant_perspectivas(int cant_perspectivas) {
        this.cant_perspectivas = cant_perspectivas;
    }

    public String getNombre_ambito() {
        return nombre_ambito;
    }

    public void setNombre_ambito(String nombre_ambito) {
        this.nombre_ambito = nombre_ambito;
    }
}
