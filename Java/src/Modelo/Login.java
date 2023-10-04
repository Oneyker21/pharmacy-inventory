package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diers
 */
public class Login {

    public static boolean validarCredenciales(Connection connection, String usuario, String contraseña) {
        String query = "SELECT Rol FROM Usuarios WHERE Usuario = ? AND Contraseña = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Si hay resultados, las credenciales son válidas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String obtenerRol(Connection connection, String usuario) {
        String query = "SELECT Rol FROM Usuarios WHERE Usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, usuario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
