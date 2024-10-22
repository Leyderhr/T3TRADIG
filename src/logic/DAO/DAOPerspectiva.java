package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPerspectiva {

    Connection cx;

    public DAOPerspectiva() {
        cx = new Connection();
    }

    public boolean insertPerspectiva(Perspectiva p) {

        PreparedStatement ps = null;
        try {

            ps = cx.connect().prepareStatement("insert into perspectiva(nombre_perspectiva, cant_dimensiones, cant_puntos ,id_ambito)values(?, ?, ?, ?)");

            ps.setString(1, p.getNombre_perspectiva());
            ps.setInt(2, p.getCant_dimensiones());
            ps.setInt(3, p.getCant_ptos());
            ps.setInt(4, p.getId_ambito());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Perspectiva> consultPerspectivas() {
        ArrayList<Perspectiva> lista = new ArrayList<Perspectiva>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva ORDER BY id_perspectiva ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Perspectiva p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_dimensiones(rs.getInt("cant_dimensiones"));
                p.setNombre_perspectiva(rs.getString("nombre_perspectiva"));
                lista.add(p);
            }

            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return lista;
    }

    public ArrayList<Perspectiva> consultPerspectivas(int id) {
        ArrayList<Perspectiva> lista = new ArrayList<Perspectiva>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva WHERE id_ambito = ? ORDER BY id_perspectiva ASC");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while ((rs.next())) {
                Perspectiva p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_dimensiones(rs.getInt("cant_dimensiones"));
                p.setNombre_perspectiva(rs.getString("nombre_perspectiva"));
                lista.add(p);
            }

            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return lista;
    }

    public Perspectiva findPerspectiva(int id){
        Perspectiva p = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva WHERE id_perspectiva = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_dimensiones(rs.getInt("cant_dimensiones"));
                p.setNombre_perspectiva(rs.getString("nombre_perspectiva"));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return p;
    }

    public boolean deletePerspectiva(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE perspectiva SET eliminado = 1 WHERE id_perspectiva = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverPerspectiva(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE perspectiva SET eliminado = 0 WHERE id_perspectiva = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean updatePerspectiva(Perspectiva p, int choise) {

        PreparedStatement ps = null;
        try {
            switch (choise){
                case 1:
                    ps = cx.connect().prepareStatement("UPDATE perspectiva SET cant_puntos = ? WHERE id_perspectiva = ?");
                    ps.setInt(1, p.getCant_ptos());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE perspectiva SET cant_dimensiones = ? WHERE id_perspectiva = ?");
                    ps.setInt(1, p.getCant_dimensiones());
                    break;
                default:
                    ps = cx.connect().prepareStatement("UPDATE perspectiva SET nombre_perspectiva = ? WHERE id_perspectiva = ?");
                    ps.setString(1, p.getNombre_perspectiva());
                    break;
            }
            ps.setInt(2, p.getId_perspectiva());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
