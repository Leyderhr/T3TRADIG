package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Ambito;
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
        PreparedStatement ps;

        try {

            ps = cx.connect().prepareStatement("insert into perspectiva(nombre_perspectiva, cant_preguntas, cant_puntos ,id_ambito, eliminado)values(?, ?, ?, ?, ?)");

            ps.setString(1, p.getNombre_perspectiva());
            ps.setInt(2, p.getCant_preguntas());
            ps.setInt(3, p.getCant_ptos());
            ps.setInt(4, p.getId_ambito());
            ps.setInt(5, 0);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Perspectiva> consultPerspectivas() {
        ArrayList<Perspectiva> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva WHERE eliminado = 0 ORDER BY id_perspectiva ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Perspectiva p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_preguntas(rs.getInt("cant_preguntas"));
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
        ArrayList<Perspectiva> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva WHERE id_ambito = ?  AND eliminado = 0 ORDER BY id_perspectiva ASC");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while ((rs.next())) {
                Perspectiva p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_preguntas(rs.getInt("cant_preguntas"));
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
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM perspectiva WHERE id_perspectiva = ? AND eliminado = 0");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                p = new Perspectiva();
                p.setId_perspectiva(rs.getInt("id_perspectiva"));
                p.setCant_ptos(rs.getInt("cant_puntos"));
                p.setId_ambito(rs.getInt("id_ambito"));
                p.setCant_preguntas(rs.getInt("cant_preguntas"));
                p.setNombre_perspectiva(rs.getString("nombre_perspectiva"));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return p;
    }

    public boolean deletePerspectiva(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE perspectiva SET eliminado = 1 WHERE id_perspectiva = ? AND eliminado = 0");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverPerspectiva(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE perspectiva SET eliminado = 0 WHERE id_perspectiva = ? AND eliminado = 1");
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
                    ps = cx.connect().prepareStatement("UPDATE perspectiva SET cant_preguntas = ? WHERE id_perspectiva = ? AND eliminado = 0");
                    ps.setInt(1, p.getCant_preguntas());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE perspectiva SET nombre_perspectiva = ? WHERE id_perspectiva = ? AND eliminado = 0");
                    ps.setString(1, p.getNombre_perspectiva());
                    break;
                default: break;
            }
            assert ps != null;
            ps.setInt(2, p.getId_perspectiva());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updatePerspectiva(ArrayList<Perspectiva> list) {
        try {
            PreparedStatement ps = cx.connect().prepareStatement(getString(list));

            int index = 1;
            // Establecer los parámetros para el CASE
            for (Perspectiva p : list) {
                ps.setInt(index++, p.getId_perspectiva());
                ps.setInt(index++, p.getCant_ptos());
            }
            // Establecer los parámetros para el WHERE IN
            for (Perspectiva p : list) {
                ps.setInt(index++, p.getId_perspectiva());
            }

            ps.execute();
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void calculatePoints(ArrayList<Ambito> list){
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT SUM(cant_puntos) AS total_puntos FROM perspectiva WHERE id_ambito = ? AND eliminado = 0");

            for(Ambito a: list){
                ps.setInt(1, a.getId_ambito());
                rs = ps.executeQuery();

                while(rs.next()){
                    a.setCant_ptos(rs.getInt("total_puntos"));
                }
            }
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getString(ArrayList<Perspectiva> list) {
        StringBuilder setClause = new StringBuilder();
        StringBuilder idsPlaceholder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            setClause.append("WHEN id_perspectiva = ? THEN ? ");
            if (i > 0) {
                idsPlaceholder.append(", ");
            }
            idsPlaceholder.append("?");
        }
        return String.format(
                "UPDATE perspectiva SET cant_puntos = CASE %s ELSE cant_puntos END WHERE id_perspectiva IN (%s)",
                setClause,
                idsPlaceholder);
    }
}
