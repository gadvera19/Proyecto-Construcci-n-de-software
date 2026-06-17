<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Usuario,modelo.Mensaje,java.util.List" %>
<%
    Usuario usuarioActual = (Usuario) session.getAttribute("usuario");
    if(usuarioActual==null){ response.sendRedirect(request.getContextPath()+"/login"); return; }
    List<Mensaje> mensajes = (List<Mensaje>) request.getAttribute("mensajes");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Foro Estudiantil</title>
    <style>
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:Arial,sans-serif;background:#f0f2f5}
        .nav{background:#1a73e8;padding:13px 22px;display:flex;justify-content:space-between;align-items:center;color:#fff}
        .nav .logo{font-size:18px;font-weight:bold}
        .nav a{color:#fff;text-decoration:none;background:rgba(255,255,255,.2);padding:6px 13px;border-radius:4px;margin-left:7px;font-size:13px}
        .nav a:hover{background:rgba(255,255,255,.35)}
        .wrap{max-width:780px;margin:22px auto;padding:0 14px}
        .form-card{background:#fff;padding:22px;border-radius:8px;box-shadow:0 1px 4px rgba(0,0,0,.1);margin-bottom:22px}
        .form-card h2{color:#333;margin-bottom:14px;font-size:16px}
        .form-card input,.form-card textarea{width:100%;padding:9px 11px;border:1px solid #ddd;border-radius:4px;font-size:13px;margin-bottom:10px;font-family:Arial,sans-serif}
        .form-card input:focus,.form-card textarea:focus{outline:none;border-color:#1a73e8}
        .form-card textarea{resize:vertical;min-height:75px}
        .form-card button{background:#1a73e8;color:#fff;border:none;padding:9px 22px;border-radius:4px;font-size:13px;cursor:pointer}
        .form-card button:hover{background:#1557b0}
        .sec-titulo{color:#333;margin-bottom:14px;font-size:16px}
        .msg{background:#fff;padding:18px 22px;border-radius:8px;box-shadow:0 1px 4px rgba(0,0,0,.1);margin-bottom:14px}
        .msg .titulo{font-size:15px;font-weight:bold;color:#1a73e8;margin-bottom:7px}
        .msg .cont{color:#333;font-size:13px;line-height:1.6;margin-bottom:10px}
        .msg .meta{font-size:11px;color:#999}
        .msg .meta span{margin-right:14px}
        .err{background:#fce8e6;color:#c5221f;padding:10px;border-radius:4px;margin-bottom:12px;font-size:13px}
        .ok{background:#e6f4ea;color:#137333;padding:10px;border-radius:4px;margin-bottom:12px;font-size:13px}
        .empty{text-align:center;color:#999;padding:36px;font-size:13px}
    </style>
</head>
<body>
<div class="nav">
    <div class="logo">&#128218; Foro Estudiantil</div>
    <div>
        <span style="font-size:13px">Hola, <%=usuarioActual.getNombre()%></span>
        <a href="${pageContext.request.contextPath}/perfil">Mi Perfil</a>
        <a href="${pageContext.request.contextPath}/logout">Cerrar Sesion</a>
    </div>
</div>
<div class="wrap">
    <div class="form-card">
        <h2>&#9998; Publicar nuevo mensaje</h2>
        <% if(request.getAttribute("error")!=null){ %><div class="err"><%=request.getAttribute("error")%></div><% } %>
        <% if(request.getAttribute("exito")!=null){ %><div class="ok"><%=request.getAttribute("exito")%></div><% } %>
        <form action="${pageContext.request.contextPath}/foro" method="post">
            <input type="text" name="titulo" placeholder="Titulo del mensaje..." maxlength="200" required>
            <textarea name="contenido" placeholder="Escribe tu mensaje aqui..." required></textarea>
            <button type="submit">Publicar</button>
        </form>
    </div>
    <h2 class="sec-titulo">&#128172; Mensajes del Foro</h2>
    <% if(mensajes==null||mensajes.isEmpty()){ %>
        <div class="empty">Aun no hay mensajes. &#161;Se el primero en publicar!</div>
    <% } else { for(Mensaje m : mensajes){ %>
        <div class="msg">
            <div class="titulo"><%=m.getTitulo()%></div>
            <div class="cont"><%=m.getContenido()%></div>
            <div class="meta">
                <span>&#128100; <%=m.getNombreAutor()%></span>
                <span>&#128197; <%=m.getFechaPublicacion()%></span>
            </div>
        </div>
    <% }} %>
</div>
</body>
</html>
