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

        PreparedStatement ps = null;
        try {

            ps = cx.connect().prepareStatement("insert into ambito(nombre_ambito, cant_puntos, cant_perspectiva)values(?, ?, ?)");

            ps.setString(1, a.getNombre_ambito());
            ps.setInt(2, a.getCant_ptos());
            ps.setInt(3, a.getCant_perspectivas());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public ArrayList<Ambito> consultAmbitos() {
        ArrayList<Ambito> lista = new ArrayList<Ambito>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cx.connect().prepareStatement("SELECT * FROM ambito ORDER BY id_ambito ASC");
            rs = ps.executeQuery();
            while ((rs.next())) {
                Ambito a = new Ambito();
                a.setId_ambito(rs.getInt("id_ambito"));
                a.setNombre_ambito(rs.getString("nombre_ambito"));
                a.setCant_perspectivas(rs.getInt("cant_perspectiva"));
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
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = cx.connect().prepareStatement("SELECT * FROM ambito WHERE id_ambito = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                a = new Ambito();
                a.setId_ambito(rs.getInt("id_ambito"));
                a.setNombre_ambito(rs.getString("nombre_ambito"));
                a.setCant_perspectivas(rs.getInt("cant_perspectiva"));
                a.setCant_ptos(rs.getInt("cant_puntos"));
            }
        }catch (SQLException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        return a;
    }

    public boolean deleteAmbito(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE ambito SET eliminado = 1 WHERE id_ambito = ?");
            ps.setInt(1, id);
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public boolean recoverAmbito(int id) {

        PreparedStatement ps = null;

        try {
            ps = cx.connect().prepareStatement("UPDATE ambito SET eliminado = 0 WHERE id_ambito = ?");
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
                    ps = cx.connect().prepareStatement("UPDATE ambito SET cant_puntos = ? WHERE id_ambito = ?");
                    ps.setInt(1, a.getCant_ptos());
                    break;
                case 2:
                    ps = cx.connect().prepareStatement("UPDATE ambito SET cant_perspectiva = ? WHERE id_ambito = ?");
                    ps.setInt(1, a.getCant_perspectivas());
                    break;
                default:
                    ps = cx.connect().prepareStatement("UPDATE ambito SET nombre_ambito = ? WHERE id_ambito = ?");
                    ps.setString(1, a.getNombre_ambito());
                    break;
            }
            ps.setInt(2, a.getId_ambito());
            ps.execute();
            cx.desconect();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
