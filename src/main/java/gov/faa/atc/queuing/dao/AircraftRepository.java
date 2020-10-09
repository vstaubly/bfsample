package gov.faa.atc.queuing.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

import gov.faa.atc.queuing.model.Aircraft;

public class AircraftRepository
{
    private Connection getConnection()
    {
        return RepositoryConnection.getRepository().getConnection();
    }

    public List<Aircraft> listByPriority()
    {
        List<Aircraft> alist = new ArrayList<Aircraft>();
        try {
            Statement stmt = getConnection().createStatement();
            String sql = "SELECT id, name, type, priority FROM aircraft ORDER BY priority, id";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String size = rs.getString("size");
                Integer pri = rs.getInt("priority");
                Aircraft plane = new Aircraft(name, Aircraft.Type.valueOf(type),  Aircraft.Size.valueOf(size));
                plane.setPriority(pri);
                plane.setId(id);
                alist.add(plane);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alist;
    }

    public Aircraft getFirstByPriority()
    {
        Aircraft plane = null;
        try {
            Statement stmt = getConnection().createStatement();
            String sql = "SELECT id, name, type, priority FROM aircraft ORDER BY priority, id LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String size = rs.getString("size");
                Integer pri = rs.getInt("priority");
                plane = new Aircraft(name, Aircraft.Type.valueOf(type),  Aircraft.Size.valueOf(size));
                plane.setPriority(pri);
                plane.setId(id);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plane;
    }

    public void removeById(long id)
    {
        try {
            String sql = "DELETE FROM aircraft WHERE id = ?";
            PreparedStatement pstatement = getConnection().prepareStatement(sql);
            pstatement.setLong(1, id);
            int rows = pstatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(Aircraft plane)
    {
        try {
            String sql = "INSERT INTO aircraft ( name, type, size, priority ) VALUES ( ?, ?, ?, ? )";
            PreparedStatement pstatement = getConnection().prepareStatement(sql);
            pstatement.setString(1, plane.getName());
            pstatement.setString(2, "" + plane.getType());
            pstatement.setString(3, "" + plane.getType());
            pstatement.setInt(4, plane.getPriority());
            int rows = pstatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
