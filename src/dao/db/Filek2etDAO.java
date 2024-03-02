package dao.db;

import dto.Filek2et;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.DbConnector;
import utils.KuroEncrypterTool;

/**
 *
 * @author maindesktop
 */
public class Filek2etDAO extends DbConnector{
    KuroEncrypterTool KuroEncrypterTool = new KuroEncrypterTool();

    public void update(Filek2et Filek2et) {
        try {
            this.connect();
            String sql = "UPDATE filek2et SET name=?, text=?, created_at=?, updated_at=? WHERE id=?";
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setString(1, Filek2et.getName());
            st.setString(2, KuroEncrypterTool.saveTextToHex(Filek2et.getText()));
            st.setString(3, Filek2et.getCreated_at());
            st.setString(4, Filek2et.getUpdated_at());
            st.setInt(5, Filek2et.getId());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            this.disconnect();
        }
    }

    public void delete(Filek2et Filek2et) {
        try {
            this.connect();
            String sql = "DELETE FROM filek2et"
                    + " WHERE id=?";
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setInt(1, Filek2et.getId());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            this.disconnect();
        }
    }

    public Filek2et get(int id) {
        Filek2et Filek2et = new Filek2et();
        try {
            this.connect();
            String sql = "SELECT *"
                    + " FROM filek2et WHERE id=?";
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Filek2et.setId(rs.getInt(1));
                Filek2et.setName(rs.getString(2));
                Filek2et.setText(KuroEncrypterTool.loadHexToString(rs.getString(3)));
                Filek2et.setCreated_at(rs.getString(4));
                Filek2et.setUpdated_at(rs.getString(5));
            }
            rs.close();
        } catch (Exception e) {
            Filek2et = null;
        } finally {
            this.disconnect();
        }
        return Filek2et;
    }

    public void add(Filek2et Filek2et) {
        try {
            this.connect();
            String sql = "INSERT INTO filek2et(name,text,created_at,updated_at) VALUES(?,?,?,?)";
            PreparedStatement st = this.connection.prepareStatement(sql);
            st.setString(1, Filek2et.getName());
            st.setString(2, KuroEncrypterTool.saveTextToHex(Filek2et.getText()));
            st.setString(3, Filek2et.getCreated_at());
            st.setString(4, Filek2et.getUpdated_at());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {
            this.disconnect();
        }
    }

    public ArrayList<Filek2et> getAll() {
        ArrayList<Filek2et> Filek2etArray = new ArrayList<>();
        try {
            this.connect();
            String sql = "SELECT *"
                    + " FROM filek2et";
            PreparedStatement st = this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Filek2et Filek2et = new Filek2et();
                Filek2et.setId(rs.getInt(1));
                Filek2et.setName(rs.getString(2));
                Filek2et.setText(KuroEncrypterTool.loadHexToString(rs.getString(3)));
                Filek2et.setCreated_at(rs.getString(4));
                Filek2et.setUpdated_at(rs.getString(5));
                Filek2etArray.add(Filek2et);
            }
            rs.close();
        } catch (Exception e) {
            Filek2etArray = null;
        } finally {
            this.disconnect();
        }
        return Filek2etArray;
    }

    public static void main(String[] args) {
        Filek2etDAO Filek2etDAO = new Filek2etDAO();

        System.out.println(Filek2etDAO.get(5).getText());

        KuroEncrypterTool KuroEncrypterTool = new KuroEncrypterTool();
        Filek2etDAO.getAll();
        /*for (Filek2et Filek2et:Filek2etDAO.getAll()) {
        System.out.print(Filek2et.getId());
        System.out.print(Filek2et.getName());
        System.out.print(Filek2et.getText());
          System.out.println("");
        }
        */
        //Filek2etDAO.add(new Filek2et("name","OLITA",String.valueOf(new Timestamp(System.currentTimeMillis())),String.valueOf(new Timestamp(System.currentTimeMillis()))));
    }
}