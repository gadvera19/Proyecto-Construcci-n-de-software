package modelo;

import java.util.Date;

/**
 * Clase modelo que representa a un usuario del sistema.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class Usuario {
    private int    id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private Date   fechaRegistro;
    private int    activo;

    public Usuario() {}

    public Usuario(String nombre, String apellido, String correo, String contrasena) {
        this.nombre     = nombre;
        this.apellido   = apellido;
        this.correo     = correo;
        this.contrasena = contrasena;
        this.activo     = 1;
    }

    public int getId()                       { return id; }
    public void setId(int id)                { this.id = id; }
    public String getNombre()                { return nombre; }
    public void setNombre(String nombre)     { this.nombre = nombre; }
    public String getApellido()              { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getCorreo()                { return correo; }
    public void setCorreo(String correo)     { this.correo = correo; }
    public String getContrasena()            { return contrasena; }
    public void setContrasena(String c)      { this.contrasena = c; }
    public Date getFechaRegistro()           { return fechaRegistro; }
    public void setFechaRegistro(Date f)     { this.fechaRegistro = f; }
    public int getActivo()                   { return activo; }
    public void setActivo(int activo)        { this.activo = activo; }

    /** @return nombre completo del usuario */
    public String getNombreCompleto() { return nombre + " " + apellido; }
}
