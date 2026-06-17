package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import util.SeguridadUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet que maneja el registro de nuevos usuarios.
 * @author Hidalgo Asencio Xavier Andres / Vera Velasquez Gad Michel
 * @version 1.0
 */
@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre    = request.getParameter("nombre");
        String apellido  = request.getParameter("apellido");
        String correo    = request.getParameter("correo");
        String password  = request.getParameter("contrasena");
        String password2 = request.getParameter("confirmar");

        if (nombre == null || nombre.trim().isEmpty() || apellido == null || apellido.trim().isEmpty()) {
            request.setAttribute("error", "El nombre y apellido son obligatorios.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }
        if (!SeguridadUtil.esCorreoValido(correo)) {
            request.setAttribute("error", "El correo electronico no es valido.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }
        if (!SeguridadUtil.esContrasenaValida(password)) {
            request.setAttribute("error", "La contrasena debe tener al menos 6 caracteres.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }
        if (!password.equals(password2)) {
            request.setAttribute("error", "Las contrasenas no coinciden.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.existeCorreo(correo.trim())) {
            request.setAttribute("error", "Este correo ya esta registrado.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }

        String hash = SeguridadUtil.hashearContrasena(password);
        if (hash == null) {
            request.setAttribute("error", "Error interno. Intenta de nuevo.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response); return;
        }

        Usuario u = new Usuario(nombre.trim(), apellido.trim(), correo.trim(), hash);
        if (usuarioDAO.registrarUsuario(u)) {
            response.sendRedirect(request.getContextPath() + "/login?registro=exitoso");
        } else {
            request.setAttribute("error", "Error al registrar. Intenta de nuevo.");
            request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
        }
    }
}
