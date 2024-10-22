package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Dimension;
import logic.Entitys.Pregunta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPregunta {

    Connection cx;

    public DAOPregunta() {
        cx = new Connection();
    }

    public boolean insertPregunta(Pregunta p) {

        PreparedStatement ps = null;
        try {

            ps = cx.connect().prepareStatement("insert into pregunta(pregunta, puntos, id_dimension)values(?, ?, ?)");

            ps.setString(1, p.getPregunta());
            ps.setInt(2, p.getPtos());
            ps.setInt(3, p.getId_dimension());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Pregunta> consultPregunta() {
        ArrayList<Pregunta> lista = new ArrayList<Pregunta>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM pregunta ORDER BY id_pregunta ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Pregunta p = new Pregunta();
                p.setId_pregunta(rs.getInt("id_pregunta"));
                p.setPregunta(rs.getString("pregunta"));
                p.setPtos(rs.getInt("puntos"));
                p.setId_dimension(rs.getInt("id_dimension"));
                lista.add(p);
            }

            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return lista;
    }

    public ArrayList<Pregunta> consultPregunta(int id) {
        ArrayList<Pregunta> lista = new ArrayList<Pregunta>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM pregunta WHERE id_dimension = ? ORDER BY id_pregunta ASC");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while ((rs.next())) {
                Pregunta p = new Pregunta();
                p.setId_pregunta(rs.getInt("id_pregunta"));
                p.setPregunta(rs.getString("pregunta"));
                p.setPtos(rs.getInt("puntos"));
                p.setId_dimension(rs.getInt("id_dimension"));
                lista.add(p);
            }

            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return lista;
    }

    public Pregunta findPregunta(int id){
        Pregunta p = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM pregunta WHERE id_pregunta = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                p = new Pregunta();
                p.setId_pregunta(rs.getInt("id_pregunta"));
                p.setPregunta(rs.getString("pregunta"));
                p.setPtos(rs.getInt("puntos"));
                p.setId_dimension(rs.getInt("id_dimension"));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return p;
    }

    public boolean deletePregunta(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE pregunta SET eliminado = 1 WHERE id_pregunta = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean deletePreguntaForDimension(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE pregunta SET eliminado = 1 WHERE id_dimension = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverPregunta(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE pregunta SET eliminado = 0 WHERE id_pregunta = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean updatePregunta(Pregunta p) {

        PreparedStatement ps = null;
        try {
            ps = cx.connect().prepareStatement("UPDATE pregunta SET pregunta = ? WHERE id_pregunta = ?");
            ps.setString(1, p.getPregunta());
            ps.setInt(2, p.getId_pregunta());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void update(ArrayList<Integer> values, ArrayList<Integer> ids){
        StringBuilder setClause = new StringBuilder();
        StringBuilder idsPlaceholder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            setClause.append("WHEN id_pregunta = ? THEN ? ");
            if (i > 0) {
                idsPlaceholder.append(", ");
            }
            idsPlaceholder.append("?");
        }

        String sql = String.format(
                "UPDATE pregunta SET puntos = CASE %s ELSE puntos END WHERE id_pregunta IN (%s)",
                setClause.toString(),
                idsPlaceholder.toString());

        try {
            PreparedStatement pstmt = cx.connect().prepareStatement(sql);

            int index = 1;
            // Establecer los parámetros para el CASE
            for (int i = 0; i < values.size(); i++) {
                pstmt.setInt(index++, i + 1);
                pstmt.setInt(index++, values.get(i));
            }
            // Establecer los parámetros para el WHERE IN
            for (int id : ids) {
                pstmt.setInt(index++, id);
            }

            pstmt.execute();
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
