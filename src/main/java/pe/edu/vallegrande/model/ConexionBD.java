package pe.edu.vallegrande.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://reto.c0u8dgzrembt.us-east-1.rds.amazonaws.com:3306/bd_incidencias?serverTimezone=UTC";
    private static final String USER = "admin"; // Tu usuario de MySQL
    private static final String PASSWORD = "CamiloTech"; // ¡Cambia esto!

    /**
     * Establece y retorna la conexión a la base de datos.
     * @return Objeto Connection o null si falla.
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Cierra la conexión.
     */
    public static void close(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}