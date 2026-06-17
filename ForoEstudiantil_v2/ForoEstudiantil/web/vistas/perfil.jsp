<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario,modelo.Mensaje,java.util.List" %>
<%
    Usuario usuarioActual = (Usuario) session.getAttribute("usuario");
    if(usuarioActual==null){ response.sendRedirect(request.getContextPath()+"/login"); return; }
    List<Mensaje> misMensajes = (List<Mensaje>) request.getAttribute("misMensajes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil - Foro Estudiantil</title>
    <style>
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:Arial,sans-serif;background:#f0f2f5}
        .nav{background:#1a73e8;padding:13px 22px;display:flex;justify-content:space-between;align-items:center;color:#fff}
        .nav .logo{font-size:18px;font-weight:bold}
        .nav a{color:#fff;text-decoration:none;background:rgba(255,255,255,.2);padding:6px 13px;border-radius:4px;margin-left:7px;font-size:13px}
        .nav a:hover{background:rgba(255,255,255,.35)}
        .wrap{max-width:780px;margin:22px auto;padding:0 14px}
        .perfil-card{background:#fff;padding:30px;border-radius:8px;box-shadow:0 1px 4px rgba(0,0,0,.1);margin-bottom:22px;text-align:center}
        .avatar{width:72px;height:72px;border-radius:50%;background:#1a73e8;color:#fff;display:flex;align-items:center;justify-content:center;font-size:28px;margin:0 auto 14px}
        .perfil-card h2{color:#333;margin-bottom:7px}
        .perfil-card p{color:#666;font-size:13px;margin-bottom:3px}
        .sec-titulo{color:#333;margin-bottom:14px;font-size:16px}
        .msg{background:#fff;padding:18px 22px;border-radius:8px;box-shadow:0 1px 4px rgba(0,0,0,.1);margin-bottom:12px}
        .msg .titulo{font-size:14px;font-weight:bold;color:#1a73e8;margin-bottom:5px}
        .msg .cont{color:#333;font-size:13px;margin-bottom:7px}
        .msg .fecha{font-size:11px;color:#999}
        .empty{text-align:center;color:#999;padding:30px;font-size:13px}
    </style>
</head>
<body>
<div class="nav">
    <div class="logo">&#128218; Foro Estudiantil</div>
    <div>
        <a href="${pageContext.request.contextPath}/foro">&#8592; Volver al Foro</a>
        <a href="${pageContext.request.contextPath}/logout">Cerrar Sesion</a>
    </div>
</div>
<div class="wrap">
    <div class="perfil-card">
        <div class="avatar">&#128100;</div>
        <h2><%=usuarioActual.getNombreCompleto()%></h2>
        <p>&#128231; <%=usuarioActual.getCorreo()%></p>
        <p>&#128197; Miembro desde: <%=usuarioActual.getFechaRegistro()%></p>
    </div>
    <h2 class="sec-titulo">&#128203; Mis mensajes publicados</h2>
    <% if(misMensajes==null||misMensajes.isEmpty()){ %>
        <div class="empty">Aun no has publicado ningun mensaje.</div>
    <% } else { for(Mensaje m : misMensajes){ %>
        <div class="msg">
            <div class="titulo"><%=m.getTitulo()%></div>
            <div class="cont"><%=m.getContenido()%></div>
            <div class="fecha">&#128197; <%=m.getFechaPublicacion()%></div>
        </div>
    <% }} %>
</div>
</body>
</html>
