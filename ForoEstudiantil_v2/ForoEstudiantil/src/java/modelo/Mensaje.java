package modelo;

import java.util.Date;

/**
 * Clase modelo que representa un mensaje del foro.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
public class Mensaje {
    private int    id;
    private int    usuarioId;
    private String titulo;
    private String contenido;
    private Date   fechaPublicacion;
    private String nombreAutor;

    public Mensaje() {}

    public Mensaje(int usuarioId, String titulo, String contenido) {
        this.usuarioId = usuarioId;
        this.titulo    = titulo;
        this.contenido = contenido;
    }

    public int getId()                            { return id; }
    public void setId(int id)                     { this.id = id; }
    public int getUsuarioId()                     { return usuarioId; }
    public void setUsuarioId(int usuarioId)       { this.usuarioId = usuarioId; }
    public String getTitulo()                     { return titulo; }
    public void setTitulo(String titulo)          { this.titulo = titulo; }
    public String getContenido()                  { return contenido; }
    public void setContenido(String contenido)    { this.contenido = contenido; }
    public Date getFechaPublicacion()             { return fechaPublicacion; }
    public void setFechaPublicacion(Date f)       { this.fechaPublicacion = f; }
    public String getNombreAutor()                { return nombreAutor; }
    public void setNombreAutor(String nombreAutor){ this.nombreAutor = nombreAutor; }
}
