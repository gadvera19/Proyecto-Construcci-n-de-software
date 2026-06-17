package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.SeguridadUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet que maneja el inicio de sesion.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/foro"); return;
        }
        if ("exitoso".equals(request.getParameter("registro"))) {
            request.setAttribute("exito", "Registro exitoso. Ya puedes iniciar sesion.");
        }
        request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String correo   = request.getParameter("correo");
        String password = request.getParameter("contrasena");

        if (correo == null || correo.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Correo y contrasena son obligatorios.");
            request.getRequestDispatcher("/vistas/login.jsp").forward(request, response); return;
        }

        String hash = SeguridadUtil.hashearContrasena(password);
        if (hash == null) {
            request.setAttribute("error", "Error interno. Intenta de nuevo.");
            request.getRequestDispatcher("/vistas/login.jsp").forward(request, response); return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.iniciarSesion(correo.trim(), hash);
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setMaxInactiveInterval(30 * 60);
            response.sendRedirect(request.getContextPath() + "/foro");
        } else {
            request.setAttribute("error", "Correo o contrasena incorrectos.");
            request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
        }
    }
}
