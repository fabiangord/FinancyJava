package Db.MySql.Tables;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Db.MySql.MySQL;
import Db.MySql.Exceptions.NoDataFoundException;
import Models.Budget;

public class BudgetDB {
    private static Connection con = MySQL.connect();
    public void insert(String name, String category, BigInteger value) {
        if (con != null) {
            String sql = "INSERT INTO budgets (name, value, category) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.executeUpdate();
                System.out.println("Presupuesto insertado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar presupuesto: " + e.getMessage());
            }
        }
    }

    public List<Budget> getAll() {
        List<Budget> budgets = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `budgets`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));

                    System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value);
                    Budget budget = new Budget(name, value);
                    budget.setId(id);
                    budgets.add(budget);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar Presupuesto: " + e.getMessage());
                return null;
            }
        }

        if(budgets.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }

        return budgets;
    }

    public void update(String name, BigInteger value, String category, int id){
        if(con != null){
            String sql ="UPDATE budgets SET name = ?, value = ?, category = ? WHERE id = ?"; 
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.setInt(4, id);
                ps.executeUpdate();
                System.out.println("Presupuesto actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar presupuesto: " + e.getMessage());
            }
        }
    }

    public void delete(int id){
        if(con != null){
            String sql = "DELETE FROM budgets WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Presupuesto Eliminado correctamente");
            } catch (Exception e) {
                System.out.println("Error al actualizar presupuesto: " + e.getMessage());
            }
        }
    }

    public List<Budget> getOne(int id){
        List<Budget> budgets = new ArrayList<>();
        if(con != null){
            String sql = "SELECT * FROM budgets WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int savingId  = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    String category = rs.getString("category");
                    System.out.println("ID: " + savingId + " Nombre: " + name + " valor: " + value + " categoria: " + category);

                    Budget budget = new Budget(name, value);
                    budget.setId(savingId);

                    budgets.add(budget);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar presupuestos: " + e.getMessage());
            }
        }
        if(budgets.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }
        return budgets;
    }

    public BigInteger getTotal() {
        BigInteger total = BigInteger.ZERO;
        if (con != null) {
            String sql = "SELECT SUM(value) FROM budgets";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = BigInteger.valueOf(rs.getLong(1));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el total del presupuesto: " + e.getMessage());
            }
        }
        return total;
    }

}
