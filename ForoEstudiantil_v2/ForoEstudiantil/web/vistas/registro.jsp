<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Foro Estudiantil</title>
    <style>
        *{box-sizing:border-box;margin:0;padding:0}
        body{font-family:Arial,sans-serif;background:#f0f2f5;display:flex;justify-content:center;align-items:center;min-height:100vh;padding:20px}
        .card{background:#fff;padding:38px;border-radius:8px;box-shadow:0 2px 10px rgba(0,0,0,.1);width:100%;max-width:440px}
        h1{color:#1a73e8;text-align:center;margin-bottom:6px;font-size:22px}
        .sub{text-align:center;color:#666;margin-bottom:22px;font-size:13px}
        .fila{display:flex;gap:12px}
        .campo{flex:1;margin-bottom:14px}
        label{display:block;margin-bottom:4px;font-weight:bold;color:#333;font-size:13px}
        input{width:100%;padding:10px 12px;border:1px solid #ddd;border-radius:4px;font-size:14px}
        input:focus{outline:none;border-color:#1a73e8}
        button{width:100%;padding:11px;background:#1a73e8;color:#fff;border:none;border-radius:4px;font-size:15px;cursor:pointer;margin-top:6px}
        button:hover{background:#1557b0}
        .err{background:#fce8e6;color:#c5221f;padding:11px;border-radius:4px;margin-bottom:14px;font-size:13px}
        .link{text-align:center;margin-top:14px;font-size:13px;color:#666}
        .link a{color:#1a73e8;text-decoration:none}
        .hint{font-size:11px;color:#999;margin-top:2px}
    </style>
</head>
<body>
<div class="card">
    <h1>&#128218; Foro Estudiantil</h1>
    <p class="sub">Crea tu cuenta</p>
    <% if(request.getAttribute("error")!=null){ %><div class="err"><%=request.getAttribute("error")%></div><% } %>
    <form action="${pageContext.request.contextPath}/registro" method="post">
        <div class="fila">
            <div class="campo"><label>Nombre</label><input type="text" name="nombre" placeholder="Juan" required></div>
            <div class="campo"><label>Apellido</label><input type="text" name="apellido" placeholder="Perez" required></div>
        </div>
        <div class="campo"><label>Correo electronico</label><input type="email" name="correo" placeholder="tu@correo.com" required></div>
        <div class="campo">
            <label>Contrasena</label>
            <input type="password" name="contrasena" placeholder="Minimo 6 caracteres" required>
            <p class="hint">Minimo 6 caracteres</p>
        </div>
        <div class="campo"><label>Confirmar contrasena</label><input type="password" name="confirmar" placeholder="Repite tu contrasena" required></div>
        <button type="submit">Crear Cuenta</button>
    </form>
    <p class="link">&#191;Ya tienes cuenta? <a href="${pageContext.request.contextPath}/login">Inicia sesion</a></p>
</div>
</body>
</html>
