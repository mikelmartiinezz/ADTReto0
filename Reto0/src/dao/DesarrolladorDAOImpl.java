/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Inés Carrasco
 */


import modelo.Desarrollador;
import java.sql.*;


public class DesarrolladorDAOImpl implements DesarrolladorDAO {

    @Override
    public boolean registrarDesarrollador(Desarrollador desarrollador) {
        String sql = "INSERT INTO desarrollador (nombre, pais, añoFundacion) VALUES (?, ?, ?)";
        Connection conn = DBConnection.getInstance().getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, desarrollador.getNombre());
            stmt.setString(2, desarrollador.getPais());
            stmt.setInt(3, desarrollador.getAñoFundacion());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar desarrollador: " + e.getMessage());
            return false;
        }
    }

   
}
