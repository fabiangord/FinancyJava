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
import Models.Expense;

public class ExpensesDB {
 private static Connection con = MySQL.connect();
    public void insert(String name, String category, BigInteger value) {
        if (con != null) {
            String sql = "INSERT INTO expenses (name, value, category) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.executeUpdate();
                System.out.println("gasto insertado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar gasto: " + e.getMessage());
            }
        }
    }

    public List<Expense> getAll() {
        List<Expense> expenses = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `expenses`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    Helpers.Category category = Helpers.Category.valueOf(rs.getString("category"));

                    System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value);
                    Expense expense = new Expense(name, value, category);
                    expense.setId(id);
                    expenses.add(expense);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar gasto: " + e.getMessage());
                return null;
            }
        }

        if(expenses.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }

        return expenses;
    }

    public void update(String name, BigInteger value, String category, int id){
        if(con != null){
            String sql ="UPDATE expenses SET name = ?, value = ?, category = ? WHERE id = ?"; 
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.setInt(4, id);
                ps.executeUpdate();
                System.out.println("gasto actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar gasto: " + e.getMessage());
            }
        }
    }

    public void delete(int id){
        if(con != null){
            String sql = "DELETE FROM expenses WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("gasto Eliminado correctamente");
            } catch (Exception e) {
                System.out.println("Error al actualizar gasto: " + e.getMessage());
            }
        }
    }

    public List<Expense> getOne(int id){
        List<Expense> expenses = new ArrayList<>();
        if(con != null){
            String sql = "SELECT * FROM expenses WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int expensesId  = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    Helpers.Category category = Helpers.Category.valueOf(rs.getString("category"));
                    System.out.println("ID: " + expensesId + " Nombre: " + name + " valor: " + value + " categoria: " + category);

                    Expense expense = new Expense(name, value, category);
                    expense.setId(expensesId);

                    expenses.add(expense);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar gastos: " + e.getMessage());
            }
        }
        if(expenses.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }
        return expenses;
    }

    public BigInteger getTotal() {
        BigInteger total = BigInteger.ZERO;
        if (con != null) {
            String sql = "SELECT SUM(value) FROM expenses";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = BigInteger.valueOf(rs.getLong(1));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el total del gasto: " + e.getMessage());
            }
        }
        return total;
    }


    public List<Expense> getMonthlyExpenses(int year, int month) {
        List<Expense> expenses = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `expenses` WHERE YEAR(date) = ? AND MONTH(date) = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, year);
                ps.setInt(2, month);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                        Helpers.Category category = Helpers.Category.valueOf(rs.getString("category"));
    
                        System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value);
                        Expense expense = new Expense(name, value, category);
                        expense.setId(id);
                        expenses.add(expense);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar gasto: " + e.getMessage());
                return null;
            }
        }
    
        if (expenses.isEmpty()) {
            new NoDataFoundException("Error al devolver la información");
        }
    
        return expenses;
    }
    

}
