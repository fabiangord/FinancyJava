package Db.MySql.Tables;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Db.MySql.MySQL;
import Db.MySql.Exceptions.NoDataFoundException;
import Models.Saving;

public class SavingDB {
    public static Connection con = MySQL.connect();
    public void insert(String name, String category, BigInteger value) {
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

    public List<Saving> getAll() {
        List<Saving> savings = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `savings`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    Helpers.Category category = Helpers.Category.valueOf(rs.getString("category"));

                    System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value + " categoria: " + category);

                    Saving saving = new Saving(name, value, category);

                    saving.setId(id);

                    savings.add(saving);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar Ahorro: " + e.getMessage());
            }
        }

        if(savings.isEmpty()) {
            new NoDataFoundException("Error al devolver la información");
        }

        return savings;
    }

    public void update(String name, BigInteger value, String category, int id){
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

    public void delete(int id){
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

    public void deleteAll(){
        if(con != null){
            String sql = "DELETE FROM savings";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.executeUpdate();
                System.out.println("All deleted!");
            } catch (Exception e) {
                System.out.println("Error deleting saving: " + e.getMessage());
            }
        }
    }

    public List<Saving> getOne(int id){
        List<Saving> savings = new ArrayList<>();
        if(con != null){
            String sql = "SELECT * FROM savings WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int savingId = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    Helpers.Category category = Helpers.Category.valueOf(rs.getString("category"));
                    System.out.println("ID: " + savingId + " Nombre: " + name + " valor: " + value + " categoria: " + category);
                    Saving saving = new Saving(name, value, category);

                    saving.setId(id);

                    savings.add(saving);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar saving: " + e.getMessage());
            }
        }

        if(savings.isEmpty()) {
            new NoDataFoundException("Error al devolver la información");
        }

        return savings;
    }

    public BigInteger getTotal() {
        BigInteger total = BigInteger.ZERO;
        if (con != null) {
            String sql = "SELECT SUM(value) FROM savings";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = BigInteger.valueOf(rs.getLong(1));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el total de los ahorros: " + e.getMessage());
            }
        }
        return total;
    }

}