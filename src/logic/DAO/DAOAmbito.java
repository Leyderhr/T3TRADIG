package logic.DAO;

import logic.Connection.Connection;
import logic.Entitys.Ambito;
import logic.Entitys.Perspectiva;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOAmbito {

    Connection cx;

    public DAOAmbito() {
        cx = new Connection();
    }

    public boolean insertAmbito(Ambito a) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("insert into ambito(nombre_ambito, cant_puntos, cant_preguntas, eliminado)values(?, ?, ?, ?)");

            ps.setString(1, a.getNombre_ambito());
            ps.setInt(2, a.getCant_ptos());
            ps.setInt(3, a.getCant_preguntas());
            ps.setInt(4, 0);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Ambito> consultAmbitos() {
        ArrayList<Ambito> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM ambito WHERE eliminado = 0 ORDER BY id_ambito ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Ambito a = new Ambito();
                a.setId_ambito(rs.getInt("id_ambito"));
                a.setNombre_ambito(rs.getString("nombre_ambito"));
                a.setCant_preguntas(rs.getInt("cant_preguntas"));
                a.setCant_ptos(rs.getInt("cant_puntos"));
                lista.add(a);
            }
            cx.desconect();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return lista;
    }

    public Ambito findAmbito(int id){
        Ambito a = null;
        PreparedStatement ps;
        ResultSet rs;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM ambito WHERE id_ambito = ? AND eliminado = 0");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                a = new Ambito();
                a.setId_ambito(rs.getInt("id_ambito"));
                a.setNombre_ambito(rs.getString("nombre_ambito"));
                a.setCant_preguntas(rs.getInt("cant_preguntas"));
                a.setCant_ptos(rs.getInt("cant_puntos"));
            }
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
        return a;
    }

    public boolean deleteAmbito(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE ambito SET eliminado = 1 WHERE id_ambito = ? AND eliminado = 0");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverAmbito(int id) {
        PreparedStatement ps;

        try {
            ps = cx.connect().prepareStatement("UPDATE ambito SET eliminado = 0 WHERE id_ambito = ? AND eliminado = 1");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean updateAmbito(Ambito a, int choise) {
        PreparedStatement ps = null;

        try {
            switch (choise){
                case 1:
                    ps = cx.connect().prepareStatement("UPDATE ambito SET cant_preguntas = ? WHERE id_ambito = ? AND eliminado = 0");
                    ps.setInt(1, a.getCant_preguntas());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE ambito SET nombre_ambito = ? WHERE id_ambito = ? AND eliminado = 0");
                    ps.setString(1, a.getNombre_ambito());
                    break;
                default: break;
            }
            assert ps != null;
            ps.setInt(2, a.getId_ambito());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void updateAmbito(ArrayList<Ambito> list) {
        try {
            PreparedStatement ps = cx.connect().prepareStatement(getString(list));

            int index = 1;
            // Establecer los parámetros para el CASE
            for (Ambito a: list) {
                ps.setInt(index++, a.getId_ambito());
                ps.setInt(index++, a.getCant_ptos());
            }
            // Establecer los parámetros para el WHERE IN
            for (Ambito a: list) {
                ps.setInt(index++, a.getId_ambito());
            }

            ps.execute();
            cx.desconect();
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getString(ArrayList<Ambito> list) {
        StringBuilder setClause = new StringBuilder();
        StringBuilder idsPlaceholder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            setClause.append("WHEN id_ambito = ? THEN ? ");
            if (i > 0) {
                idsPlaceholder.append(", ");
            }
            idsPlaceholder.append("?");
        }
        return String.format(
                "UPDATE ambito SET cant_puntos = CASE %s ELSE cant_puntos END WHERE id_ambito IN (%s)",
                setClause,
                idsPlaceholder);
    }
}
