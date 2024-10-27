package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Dimension;
import logic.Entitys.Perspectiva;

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
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("insert into dimension(nombre_dimension, cant_preguntas, cant_puntos ,id_perspectiva, eliminado)values(?, ?, ?, ?, ?)");

            ps.setString(1, d.getNombre_dimension());
            ps.setInt(2, d.getCant_preguntas());
            ps.setInt(3, d.getCant_puntos());
            ps.setInt(4, d.getId_perspectiva());
            ps.setInt(5, 0);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Dimension> consultDimensiones() {
        ArrayList<Dimension> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM dimension WHERE eliminado = 0 ORDER BY id_dimension ASC");
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
        ArrayList<Dimension> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM dimension WHERE id_perspectiva = ? AND eliminado = 0 ORDER BY id_dimension ASC");
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
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return list;
    }

    public Dimension findDimension(int id){
        Dimension d = null;
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM dimension WHERE id_dimension = ? AND eliminado = 0");
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
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return d;
    }

    public boolean deleteDimension(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE dimension SET eliminado = 1 WHERE id_dimension = ? AND eliminado = 0");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverDimension(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE dimension SET eliminado = 0 WHERE id_dimension = ? AND eliminado = 1");
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
                    ps = cx.connect().prepareStatement("UPDATE dimension SET cant_preguntas = ? WHERE id_dimension = ? AND eliminado = 0");
                    ps.setInt(1, d.getCant_preguntas());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE dimension SET nombre_dimension = ? WHERE id_dimension = ? AND eliminado = 0");
                    ps.setString(1, d.getNombre_dimension());
                    break;
                default: break;
            }
            assert ps != null;
            ps.setInt(2, d.getId_dimension());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateDimension(ArrayList<Dimension> list) {
        try {
            PreparedStatement ps = cx.connect().prepareStatement(getString(list));

            int index = 1;
            // Establecer los parámetros para el CASE
            for (Dimension d : list) {
                ps.setInt(index++, d.getId_dimension());
                ps.setInt(index++, d.getCant_puntos());
            }
            // Establecer los parámetros para el WHERE IN
            for (Dimension d : list) {
                ps.setInt(index++, d.getId_dimension());
            }

            ps.execute();
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void calculatePoints(ArrayList<Perspectiva> list){
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT SUM(cant_puntos) AS total_puntos FROM dimension WHERE id_perspectiva = ? AND eliminado = 0");

            for(Perspectiva p: list){
                ps.setInt(1, p.getId_perspectiva());
                rs = ps.executeQuery();

                while(rs.next()){
                    p.setCant_ptos(rs.getInt("total_puntos"));
                }
            }
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getString(ArrayList<Dimension> list) {
        StringBuilder setClause = new StringBuilder();
        StringBuilder idsPlaceholder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            setClause.append("WHEN id_dimension = ? THEN ? ");
            if (i > 0) {
                idsPlaceholder.append(", ");
            }
            idsPlaceholder.append("?");
        }
        return String.format(
                "UPDATE dimension SET cant_puntos = CASE %s ELSE cant_puntos END WHERE id_dimension IN (%s)",
                setClause,
                idsPlaceholder);
    }
}
