package Db.MySql.Tables;

import Db.MySql.MySQL;
import Models.Investment;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InvestmentDB {
    public static Connection con = MySQL.connect();
    List<Investment> investments = new ArrayList<>();
    
    public void insert(String concept, BigDecimal investment, float interest, int months, BigDecimal feeBack) throws SQLException {
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
                JOptionPane.showMessageDialog(null, "INVESTMENT INSERTED");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR TRYING TO INSERT DATA, " +e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Connection to BD failed");
        }
    }

    public void update(String concept, long goal) throws SQLException {
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
        } else {
            JOptionPane.showMessageDialog(null, "Connection to BD failed");
        }
    }
    public void delete(String concept) {
        if (con != null) {
            String sql = "DELETE FROM investments WHERE concept = ?";
    
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, concept);
                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "INVESTMENT DELETED");
                } else {
                    JOptionPane.showMessageDialog(null, "INVESTMENT DOESN'T EXIST");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "BD ERROR: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Connection to BD failed");
        }
    }
    

    public List<Investment> getOne(String concept){
    
        if (con != null) {
            String sql = "SELECT * FROM investments WHERE concept = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, concept);
                ResultSet rs = ps.executeQuery();
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    String conceptD = rs.getString("concept");
                    long investmentD = rs.getLong("investment");
                    BigDecimal investmentBD = BigDecimal.valueOf(investmentD);
                    float interestD = rs.getFloat("interest");
                    int monthsD = rs.getInt("months");
                    long feeBackD = rs.getLong("feeBack");
                    BigDecimal feeBackBD = BigDecimal.valueOf(feeBackD);
                    Investment investment = new Investment(conceptD, investmentBD, interestD, monthsD, feeBackBD);
                    investments.add(investment);
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "INVESTMENT DOESN'T EXIST");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "BD ERROR: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Connection to BD failed");
        }
        return investments;
    }
    
}
