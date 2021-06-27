<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="es.taw.eventaw.dto.EventoEtiquetaDTO" %>
<%@ page import="es.taw.eventaw.dto.EtiquetaDTO" %><%--
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
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("userDTO");
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    List<EventoDTO> misEventos = (List<EventoDTO>) request.getAttribute("misEventos");
    List<EventoDTO> todosEventos = (List<EventoDTO>) request.getAttribute("todosEventos");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
        <li style="float:right"><a href="/conversacion/iniciar">CHAT TELEOPERADOR</a></li>
    </ul>
</div>

<!-- Imagen fondo -->
<header class="header-inicio text-center text-white" style="height: 60vh;">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> Bienvenido a Eventaw </h1>
            <h2 style="font-size: 3rem"> un espacio para tus eventos</h2>
            <a class="btn btn-primary btn-xl rounded-pill mt-5" href="/evento/crear">Crear evento</a><br/>
            <a class="btn btn-primary btn-xl rounded-pill mt-3" href="#eventos">Mis eventos</a>
        </div>
    </div>
</header>
<section id="eventos">
    <%
        if (!todosEventos.isEmpty()) { %>

    <div class="container m-t-30">
        <div class="row">
            <div class="col-sm-10 col-md-7">
                <h1 class="bg-text" style=" color:#b997f6;"> Mis eventos: </h1>
            </div>
        </div>

        <form:form action="/evento/filtrarCreador" modelAttribute="eventoDTO">
            <div class="row m-t-10 justify-content-center">
                <div class="col-5 wrap-input2">
                    <form:input path="titulo" class="input2" type="text" placeholder="Introduzca el filtro..."
                                name="buscador"/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10">
                    <form:input path="fecha" class="input2" type="date" id="start" name="fechaInicio"
                                min="<%=formato.format(new Date())%>"/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10">
                    <form:input path="fechacompra" class="input2" type="date" id="start2" name="fechaFinal"
                                min="<%=formato.format(new Date())%>"/>
                </div>
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" value="Buscar" name="buscarBoton">
                            Buscar
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</section>
<%
    if (misEventos.isEmpty()) { %>
<div class="container justify-content-center text-center" style="margin-top: 10vh">
    <h1 class="bg-text" style="color: #9e9e9e; font-size: 3rem">No tienes eventos con ese filtro</h1>
</div>
<% } else {
%>
<div class="container">
    <table class="center table table-striped align-middle m-t-30" id="tabla-custom">
        <thead>
        <tr style="text-align: center; vertical-align: middle; font-size:1.1rem">
            <th>NOMBRE</th>
            <th>DESCRIPCI&Oacute;N</th>
            <th>CIUDAD</th>
            <th>FECHA</th>
            <th>COMPRA HASTA</th>
            <th>PRECIO</th>
            <th>AFORO</th>
            <th>ENTRADAS POR USUARIO</th>
            <th>Nº FILAS</th>
            <th>ASIENTOS POR FILA</th>
            <th>ETIQUETAS</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (EventoDTO e : misEventos) {
        %>
        <tr style="text-align: center; vertical-align: middle; font-size:1.2rem">
            <td><%= e.getTitulo()%>
            </td>
            <td><%= e.getDescripcion()%>
            </td>
            <td><%= e.getCiudad()%>
            </td>
            <td><%= e.getFecha()%>
            </td>
            <td><%= e.getFechacompra()%>
            </td>
            <td><%= new DecimalFormat("#0.00").format(e.getPrecio())%>€</td>
            <td><%= e.getAforo()%>
            </td>
            <td><%= e.getMaxentradasusuario()%>
            </td>
            <td><%= (e.getNumfilas() == null ? "-" : e.getNumfilas())%>
            </td>
            <td><%= (e.getAsientosfila() == null ? "-" : e.getAsientosfila())%>
            </td>
            <td>
                <%
                    for(String s: e.getEtiquetasString()) { %>
                        <%= s %>
                <%    }
                %>

            </td>
            <td><a class="btn  btn-primary" style="color: white" href="/evento/editar/<%=e.getId()%>">EDITAR</a></td>
            <td><a class="btn  btn-primary" style="color: white" href="/evento/borrar/<%=e.getId()%>">BORRAR</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <% } %>
</div>

<% } else { %>
<div class="container justify-content-center text-center" style="margin-top: 10vh">
    <h1 class="bg-text" style="color: #9e9e9e; font-size: 3rem">Actualmente no tienes eventos :(</h1>
    <h1 class="bg-text" style="color: #9e9e9e; font-size: 2.5rem">Prueba a crear uno.</h1>
</div>

<% }
%>
<div class="container p-b-100"></div>
</body>

</html>
