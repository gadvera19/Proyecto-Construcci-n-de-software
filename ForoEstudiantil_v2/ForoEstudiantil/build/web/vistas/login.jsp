<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesion - Foro Estudiantil</title>
    <style>
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:Arial,sans-serif;background:#f0f2f5;display:flex;justify-content:center;align-items:center;min-height:100vh}
        .card{background:#fff;padding:40px;border-radius:8px;box-shadow:0 2px 10px rgba(0,0,0,.1);width:100%;max-width:400px}
        h1{color:#1a73e8;text-align:center;margin-bottom:6px;font-size:22px}
        .sub{text-align:center;color:#666;margin-bottom:22px;font-size:13px}
        label{display:block;margin-bottom:4px;font-weight:bold;color:#333;font-size:13px}
        input{width:100%;padding:10px 12px;border:1px solid #ddd;border-radius:4px;font-size:14px;margin-bottom:14px}
        input:focus{outline:none;border-color:#1a73e8}
        button{width:100%;padding:11px;background:#1a73e8;color:#fff;border:none;border-radius:4px;font-size:15px;cursor:pointer}
        button:hover{background:#1557b0}
        .err{background:#fce8e6;color:#c5221f;padding:11px;border-radius:4px;margin-bottom:14px;font-size:13px}
        .ok{background:#e6f4ea;color:#137333;padding:11px;border-radius:4px;margin-bottom:14px;font-size:13px}
        .link{text-align:center;margin-top:14px;font-size:13px;color:#666}
        .link a{color:#1a73e8;text-decoration:none}
    </style>
</head>
<body>
<div class="card">
    <h1>&#128218; Foro Estudiantil</h1>
    <p class="sub">Inicia sesion para participar</p>
    <% if(request.getAttribute("error")!=null){ %><div class="err"><%=request.getAttribute("error")%></div><% } %>
    <% if(request.getAttribute("exito")!=null){ %><div class="ok"><%=request.getAttribute("exito")%></div><% } %>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label>Correo electronico</label>
        <input type="email" name="correo" placeholder="tu@correo.com" required>
        <label>Contrasena</label>
        <input type="password" name="contrasena" placeholder="••••••••" required>
        <button type="submit">Iniciar Sesion</button>
    </form>
    <p class="link">&#191;No tienes cuenta? <a href="${pageContext.request.contextPath}/registro">Registrate aqui</a></p>
</div>
</body>
</html>
