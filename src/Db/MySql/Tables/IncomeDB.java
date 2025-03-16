package Db.MySql.Tables;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Db.MySql.MySQL;

public class IncomeDB {
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
}
