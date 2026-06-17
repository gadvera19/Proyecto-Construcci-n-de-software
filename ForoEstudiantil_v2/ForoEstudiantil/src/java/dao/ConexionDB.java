package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexion con Oracle Database.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class ConexionDB {

    // =========================================================
    // CAMBIAR ESTOS 3 VALORES SEGUN TU INSTALACION DE ORACLE
    // =========================================================
    private static final String URL      = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USUARIO  = "system";
    private static final String PASSWORD = "gadvera1";
    private static final String DRIVER   = "oracle.jdbc.driver.OracleDriver";

    /**
     * Obtiene una conexion activa a Oracle Database.
     * @return Connection activa
     * @throws SQLException si falla la conexion
     * @throws ClassNotFoundException si no encuentra el driver
     */
    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    /**
     * Cierra la conexion de forma segura.
     * @param conn la conexion a cerrar
     */
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try { conn.close(); }
            catch (SQLException e) {
                System.err.println("Error al cerrar conexion: " + e.getMessage());
            }
        }
    }
}
