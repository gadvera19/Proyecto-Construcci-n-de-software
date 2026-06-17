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
 * Servlet que muestra el perfil del usuario activo.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login"); return;
        }
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        MensajeDAO dao = new MensajeDAO();
        List<Mensaje> misMensajes = dao.obtenerMensajesPorUsuario(usuario.getId());
        request.setAttribute("misMensajes", misMensajes);
        request.getRequestDispatcher("/vistas/perfil.jsp").forward(request, response);
    }
}
