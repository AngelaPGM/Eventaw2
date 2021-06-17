<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %><%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 08/06/2021
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Inicio</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
</head>
<%
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("userDTO");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a  href="ServletCierreSesion">Cerrar sesión</a></li>
        <li style="float:right"><a href="ServletCrudUsuario?id=<%= usuario.getId() %>">Mi perfil</a></li>
        <li style="float:right"><a href="ServletNuevaConversacion">CHAT TELEOPERADOR</a></li>
    </ul>
</div>
</body>
<!-- Imagen fondo -->
<header class="header-inicio text-center text-white" style="height: 60vh;">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> Bienvenido a Eventaw </h1>
            <h2 style="font-size: 3rem"> un espacio para tus eventos</h2>
            <a class="btn btn-primary btn-xl rounded-pill mt-5" href="ServletCRUDEvento">Crear evento</a><br/>
            <a class="btn btn-primary btn-xl rounded-pill mt-3" href="#eventos">Mis eventos</a>
        </div>
    </div>
</header>

</html>
