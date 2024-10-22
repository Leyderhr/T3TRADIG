package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Dimension;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODimension {

    Connection cx;

    public DAODimension() {
        cx = new Connection();
    }

    public boolean insertDimension(Dimension d) {

        PreparedStatement ps = null;
        try {

            ps = cx.connect().prepareStatement("insert into dimension(nombre_dimension, cant_preguntas, cant_puntos ,id_perspectiva)values(?, ?, ?, ?)");

            ps.setString(1, d.getNombre_dimension());
            ps.setInt(2, d.getCant_preguntas());
            ps.setInt(3, d.getCant_puntos());
            ps.setInt(4, d.getId_perspectiva());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Dimension> consultDimensiones() {
        ArrayList<Dimension> lista = new ArrayList<Dimension>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM dimension ORDER BY id_dimension ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Dimension d = new Dimension();
                d.setId_dimension(rs.getInt("id_dimension"));
                d.setNombre_dimension(rs.getString("nombre_dimension"));
                d.setCant_preguntas(rs.getInt("cant_preguntas"));
                d.setCant_puntos(rs.getInt("cant_puntos"));
                d.setId_perspectiva(rs.getInt("id_perspectiva"));
                lista.add(d);
            }

            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return lista;
    }

    public ArrayList<Dimension> consultDimensiones(int id) {
        ArrayList<Dimension> list = new ArrayList<Dimension>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM dimension WHERE id_perspectiva = ? ORDER BY id_dimension ASC");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                Dimension d = new Dimension();
                d.setId_dimension(rs.getInt("id_dimension"));
                d.setNombre_dimension(rs.getString("nombre_dimension"));
                d.setCant_preguntas(rs.getInt("cant_preguntas"));
                d.setCant_puntos(rs.getInt("cant_puntos"));
                d.setId_perspectiva(rs.getInt("id_perspectiva"));
                list.add(d);
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return list;
    }

    public Dimension findDimension(int id){
        Dimension d = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM dimension WHERE id_dimension = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                d = new Dimension();
                d.setId_dimension(rs.getInt("id_dimension"));
                d.setNombre_dimension(rs.getString("nombre_dimension"));
                d.setCant_preguntas(rs.getInt("cant_preguntas"));
                d.setCant_puntos(rs.getInt("cant_puntos"));
                d.setId_perspectiva(rs.getInt("id_perspectiva"));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return d;
    }

    public boolean deleteDimension(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE dimension SET eliminado = 1 WHERE id_dimension = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverDimension(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE dimension SET eliminado = 0 WHERE id_dimension = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean updateDimension(Dimension d, int choise) {

        PreparedStatement ps = null;
        try {
            switch (choise){
                case 1:
                    ps = cx.connect().prepareStatement("UPDATE dimension SET cant_puntos = ? WHERE id_dimension = ?");
                    ps.setInt(1, d.getCant_puntos());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE dimension SET cant_preguntas = ? WHERE id_dimension = ?");
                    ps.setInt(1, d.getCant_preguntas());
                    break;
                default:
                    ps = cx.connect().prepareStatement("UPDATE dimension SET nombre_dimension = ? WHERE id_dimension = ?");
                    ps.setString(1, d.getNombre_dimension());
                    break;
            }
            ps.setInt(2, d.getId_dimension());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
