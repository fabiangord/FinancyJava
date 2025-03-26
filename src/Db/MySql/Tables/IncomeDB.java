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
import Models.Income;

public class IncomeDB {
    public static Connection con = MySQL.connect();
    public void insert(String name, String category, BigInteger value) {
        if (con != null) {
            String sql = "INSERT INTO incomes (name, value, category) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.executeUpdate();
                System.out.println("Ingreso insertado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al insertar ingreso: " + e.getMessage());
            }
        }
    }

    public List<Income> getAll() {
        List<Income> incomes = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `incomes`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getInt("value"));
                    String category = rs.getString("category");

                    System.out.println("ID: " + id + " Nombre: " + name + " valor: " + value + " categoria: " + category);

                    Income income = new Income(name, value);

                    income.setId(id);
                    incomes.add(income);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar Ingreso: " + e.getMessage());
            }
        }

        if(incomes.isEmpty()) {
            new NoDataFoundException("Error al devolver la información");
        }

        return incomes;
        
    }

    public void update(String name, BigInteger value, String category, int id){
        if(con != null){
            String sql ="UPDATE incomes SET name = ?, value = ?, category = ? WHERE id = ?"; 
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setLong(2, value.longValue());
                ps.setString(3, category);
                ps.setInt(4, id);
                ps.executeUpdate();
                System.out.println("Ingreso actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el ingreso: " + e.getMessage());
            }
        }
    }

    public void delete(int id){
        if(con != null){
            String sql = "DELETE FROM incomes WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Ingreso Eliminado correctamente");
            } catch (Exception e) {
                System.out.println("Error al actualizar ingreso: " + e.getMessage());
            }
        }
    }

    public List<Income> getOne(int id){
        List<Income> incomes = new ArrayList<>();
        if(con != null){
            String sql = "SELECT * FROM incomes WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int incomeId  = rs.getInt("id");
                    String name = rs.getString("name");
                    BigInteger value = BigInteger.valueOf(rs.getLong("value"));
                    System.out.println("ID: " + incomeId + " Nombre: " + name + " valor: " + value );

                    Income income = new Income(name, value);
                    income.setId(incomeId);

                    incomes.add(income);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar ingresos: " + e.getMessage());
            }
        }
        if(incomes.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }
        return incomes;
    }

    public BigInteger getTotal() {
        BigInteger total = BigInteger.ZERO;
        if (con != null) {
            String sql = "SELECT SUM(value) FROM incomes";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = BigInteger.valueOf(rs.getLong(1));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el total de los ingresos: " + e.getMessage());
            }
        }
        return total;
    }
}
