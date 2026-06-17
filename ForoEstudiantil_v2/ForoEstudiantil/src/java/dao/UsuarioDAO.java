package dao;

import modelo.Usuario;
import java.sql.*;

/**
 * Acceso a datos para la entidad Usuario.
 * Usa PreparedStatement para prevenir SQL Injection.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class UsuarioDAO {

    /**
     * Registra un nuevo usuario en la base de datos.
     * @param usuario objeto con los datos a insertar
     * @return true si fue exitoso
     */
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido, correo, contrasena) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasena());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error registrar usuario: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    /**
     * Valida credenciales para inicio de sesion.
     * @param correo del usuario
     * @param contrasena hash SHA-256
     * @return Usuario si credenciales correctas, null si no
     */
    public Usuario iniciarSesion(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo=? AND contrasena=? AND activo=1";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCorreo(rs.getString("correo"));
                u.setFechaRegistro(rs.getDate("fecha_registro"));
                return u;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Error login: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
            return null;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    /**
     * Verifica si un correo ya esta registrado.
     * @param correo a verificar
     * @return true si ya existe
     */
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo=?";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error verificar correo: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }
}
