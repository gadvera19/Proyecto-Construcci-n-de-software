package controlador;

import dao.MensajeDAO;
import modelo.Mensaje;
import modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet que maneja el foro: ver y publicar mensajes.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
@WebServlet("/foro")
public class ForoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!tieneSesion(request)) { response.sendRedirect(request.getContextPath() + "/login"); return; }
        MensajeDAO dao = new MensajeDAO();
        List<Mensaje> mensajes = dao.obtenerTodosMensajes();
        request.setAttribute("mensajes", mensajes);
        request.getRequestDispatcher("/vistas/foro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (!tieneSesion(request)) { response.sendRedirect(request.getContextPath() + "/login"); return; }

        String titulo    = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");

        if (titulo == null || titulo.trim().isEmpty() || contenido == null || contenido.trim().isEmpty()) {
            request.setAttribute("error", "Titulo y contenido son obligatorios.");
            doGet(request, response); return;
        }

        HttpSession session = request.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Mensaje m = new Mensaje(usuario.getId(), titulo.trim(), contenido.trim());
        MensajeDAO dao = new MensajeDAO();

        if (dao.publicarMensaje(m)) {
            request.setAttribute("exito", "Mensaje publicado exitosamente.");
        } else {
            request.setAttribute("error", "Error al publicar. Intenta de nuevo.");
        }
        doGet(request, response);
    }

    private boolean tieneSesion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("usuario") != null;
    }
}
