package Db.MySql.Tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Db.MySql.MySQL;

import Models.Projection;

public class ProjectionDB {
    public static Connection con = MySQL.connect();
    

    public void insert(Projection projections){
        if(con != null){
            String query = "INSERT INTO projections (projected_budgets, projected_expenses, projected_incomes, projected_savings, months) VALUES (?,?,?,?,?)";

            try(PreparedStatement ps = con.prepareStatement(query)) {

                ps.setLong(1, projections.projectedBudgets.longValue());
                ps.setLong(2, projections.projectedExpenses.longValue());
                ps.setLong(3, projections.projectedIncomes.longValue());
                ps.setLong(4, projections.projectedSavings.longValue());

                ps.setInt(5, projections.months);
                ps.executeUpdate();
                System.out.println("Proyección insertada con exito!");
                
            } catch (SQLException e) {
                System.out.println("Error al guardar la proyeccion " + e.getMessage());
            }
        }
    }

    public List<Projection> getAll(){
        List<Projection> projections = new ArrayList<>();
        return projections;
    }

    public void update(){

    }

    public void delete(){

    }

    public Projection getOne(){
        Projection projection = new Projection(null, null, null, null, 0);
        return projection;
    }

}
