package dao;

import modelo.Mensaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos para la entidad Mensaje.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class MensajeDAO {

    /**
     * Publica un nuevo mensaje en el foro.
     * @param mensaje objeto con los datos a insertar
     * @return true si fue exitoso
     */
    public boolean publicarMensaje(Mensaje mensaje) {
        String sql = "INSERT INTO mensajes (usuario_id, titulo, contenido) VALUES (?, ?, ?)";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mensaje.getUsuarioId());
            ps.setString(2, mensaje.getTitulo());
            ps.setString(3, mensaje.getContenido());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error publicar: " + e.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
            return false;
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
    }

    /**
     * Obtiene todos los mensajes del foro con nombre del autor.
     * @return lista de mensajes ordenados por fecha descendente
     */
    public List<Mensaje> obtenerTodosMensajes() {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT m.id, m.titulo, m.contenido, m.fecha_publicacion, " +
                     "u.nombre || ' ' || u.apellido AS nombre_autor " +
                     "FROM mensajes m JOIN usuarios u ON m.usuario_id = u.id " +
                     "ORDER BY m.fecha_publicacion DESC";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("id"));
                m.setTitulo(rs.getString("titulo"));
                m.setContenido(rs.getString("contenido"));
                m.setFechaPublicacion(rs.getDate("fecha_publicacion"));
                m.setNombreAutor(rs.getString("nombre_autor"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error obtener mensajes: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }

    /**
     * Obtiene mensajes de un usuario especifico.
     * @param usuarioId ID del usuario
     * @return lista de mensajes del usuario
     */
    public List<Mensaje> obtenerMensajesPorUsuario(int usuarioId) {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensajes WHERE usuario_id=? ORDER BY fecha_publicacion DESC";
        Connection conn = null;
        try {
            conn = ConexionDB.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("id"));
                m.setTitulo(rs.getString("titulo"));
                m.setContenido(rs.getString("contenido"));
                m.setFechaPublicacion(rs.getDate("fecha_publicacion"));
                lista.add(m);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error obtener mis mensajes: " + e.getMessage());
        } finally {
            ConexionDB.cerrarConexion(conn);
        }
        return lista;
    }
}
