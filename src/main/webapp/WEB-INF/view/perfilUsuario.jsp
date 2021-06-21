<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="es.taw.eventaw.dto.UsuarioeventoDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: angep
  Date: 21/06/2021
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Mi perfil</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
</head>
<%
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a href="/inicioUEvento">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a class="active">Mi perfil</a></li>
        <li style="float:right"><a href="sin hacer">MIS ENTRADAS</a></li>
        <li style="float:right"><a href="sin hacer">CHAT TELEOPERADOR</a></li>
    </ul>
</div>



</body>
</html>
