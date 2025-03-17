package Db.MySql.Tables;
import java.math.BigInteger;
import java.sql.*;
import Db.MySql.MySQL;

public class SavingDB {
    public static Connection con = MySQL.connect();
    public void insertSaving(String name, String category, BigInteger value) {
        if (con != null) {
            String sql = "INSERT INTO savings (name, value, category) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.executeUpdate();
                System.out.println("Ahorro insertado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar saving: " + e.getMessage());
            }
        }
    }

    public void getAllSaving() {
        if (con != null) {
            String sql = "SELECT * FROM `savings`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int value = rs.getInt("value");
                    String category = rs.getString("category");

                    System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value + " categoria: " + category);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar Ahorro: " + e.getMessage());
            }
        }
    }

    public void updateSaving(String name, BigInteger value, String category, int id){
        if(con != null){
            String sql ="UPDATE savings SET name = ?, value = ?, category = ? WHERE id = ?"; 
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.setInt(4, id);
                ps.executeUpdate();
                System.out.println("Ahorro actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar saving: " + e.getMessage());
            }
        }
    }

    public void deleteSaving(int id){
        if(con != null){
            String sql = "DELETE FROM savings WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Ahorro Eliminado correctamente");
            } catch (Exception e) {
                System.out.println("Error al actualizar saving: " + e.getMessage());
            }
        }
    }

    public void getOneSaving(int id){
        if(con != null){
            String sql = "SELECT * FROM savings WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int savingId  = rs.getInt("id");
                    String name = rs.getString("name");
                    int value = rs.getInt("value");
                    String category = rs.getString("category");
                    System.out.println("ID: " + savingId + " Nombre: " + name + " valor: " + value + " categoria: " + category);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar saving: " + e.getMessage());
            }
        }
    }

}