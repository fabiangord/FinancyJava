package Db.MySql.Tables;

import Db.MySql.Exceptions.NoDataFoundException;
import Db.MySql.MySQL;
import Models.Investment;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDB {
    public static Connection con = MySQL.connect();
    
    public void insert(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) {
        if (con != null) {
            String sql = "INSERT INTO investments (concept, investment, interest, months, feeback)" 
                         + "VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, concept);
                ps.setBigDecimal(2, investment);
                ps.setFloat(3, interest);
                ps.setInt(4, months);
                ps.setBigDecimal(5, feeBack);
                ps.executeUpdate();
                System.out.println("INVESTMENT INSERTED");
            } catch (SQLException e) {
                System.out.println("ERROR WHILE TRY INSERT THE INVESTMENT: " + e.getMessage());
            }
        }
    }

    public List<Investment> getAll() {
        List<Investment> investments = new ArrayList<>();
        if (con != null) {
            String sql = "SELECT * FROM `investments`";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String concept = rs.getString("concept");
                    int goal = rs.getInt("goal");

                    System.out.println(" Concepto: " + concept + " Meta: " + goal);
                }
            } catch (SQLException e) {
                System.out.println("Error al visualizar Ingreso: " + e.getMessage());
            }
        }

        if(investments.isEmpty()) {
            new NoDataFoundException("Error al devolver la información");
        }

        return investments;
        
    }

    public void update(String concept, long goal){
        if(con != null){
            String sql ="UPDATE investments SET concept = ?, goal = ? WHERE concept = ?"; 
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, concept);
                ps.setLong(2, goal);
                ps.setString(4, concept);
                ps.executeUpdate();
                System.out.println("Ingreso actualizado correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el ingreso: " + e.getMessage());
            }
        }
    }

    public void delete(String concept){
        if(con != null){
            String sql = "DELETE FROM investments WHERE concept = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, concept);
                ps.executeUpdate();
                System.out.println("Ingreso Eliminado correctamente");
            } catch (Exception e) {
                System.out.println("Error al actualizar ingreso: " + e.getMessage());
            }
        }
    }

    public List<Investment> getOne(String concept){
        List<Investment> investments = new ArrayList<>();
        if(con != null){
            String sql = "SELECT * FROM investments WHERE concept = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)){
                ps.setString(1, concept);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String concept2 = rs.getString("concept");
                    long goal = rs.getLong("goal");
                    System.out.println(" Concepto: " + concept2 + " Meta " + goal );

                    Investment investment = new Investment(concept, goal);

                    investments.add(investment);
                }
            } catch (Exception e) {
                System.out.println("Error al buscar ingresos: " + e.getMessage());
            }
        }
        if(investments.isEmpty()){
            new NoDataFoundException("Error al devolver la información");
        }
        return investments;
    }

    public long getTotal() {
        long total = 0;
        if (con != null) {
            String sql = "SELECT SUM(goal) FROM investments";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getLong(1);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener el total de los ingresos: " + e.getMessage());
            }
        }
        return total;
    }
}
