<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object usuario = session.getAttribute("usuario");
    if (usuario != null) {
        response.sendRedirect(request.getContextPath() + "/foro");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>
