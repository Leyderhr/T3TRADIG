package logic.Entitys;

public class Perspectiva {

    private int id_perspectiva, cant_ptos, cant_dimensiones, id_ambito;
    private String nombre_perspectiva;

    public Perspectiva() {
    }

    public Perspectiva(int id_perspectiva, int cant_ptos, int cant_dimensiones, int id_ambito, String nombre_perspectiva) {
        setId_perspectiva(id_perspectiva);
        setCant_ptos(cant_ptos);
        setCant_dimensiones(cant_dimensiones);
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

    public int getCant_dimensiones() {
        return cant_dimensiones;
    }

    public void setCant_dimensiones(int cant_dimensiones) {
        this.cant_dimensiones = cant_dimensiones;
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
}
