<%@ page import="es.taw.eventaw.entity.Evento" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.entity.Usuario" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Administrador</title>
</head>
    <%
        List<EventoDTO> eventosFuturos = (List<EventoDTO>) request.getAttribute("eventosFuturos");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        List<UsuarioDTO> listaUsuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
        UsuarioDTO us = (UsuarioDTO) session.getAttribute("userDTO");
    %>
<body>

<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a  href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="noestaCreado<%= us.getId()%>">Mi perfil</a></li>
    </ul>
</div>

<!-- Imagen fondo -->
<header class="header-inicio text-center text-white">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> Bienvenido de nuevo, </h1>
            <h2 style="font-size: 3rem"> administrador</h2>
            <a class="btn btn-primary btn-xl rounded-pill mt-5" href="#usuarios">Ver usuarios</a><br/>
            <a class="btn btn-primary btn-xl rounded-pill mt-3" href="#usuarios">Ver eventos</a>
        </div>
    </div>
</header>

    <h1>Usuarios Disponibles</h1>
    <form:form modelAttribute="usuarioFiltroDTO" action="/filtrar">
        <form:input path="correo"></form:input>
        <form:select path="contrasenya2">
            <form:option value="ID"></form:option>
            <form:option value="CORREO"></form:option>
            <form:option value="ROL"></form:option>
        </form:select>
        <button value="Buscar">Buscar</button>
    </form:form>

<table border="1">
    <thead>
    <tr>
        <td>ID</td>
        <td>EMAIL</td>
        <td>CONTRASEÑA</td>
        <td>ROL</td>
        <td>EDITAR</td>
        <td>ELIMINAR</td>
    </tr>
    </thead>
    <tbody>
    <%
    if(listaUsuarios.isEmpty()){
    %>
    <h1>No hay usuarios con este filtrado.</h1>
    <%

        }else{


            for(UsuarioDTO u : listaUsuarios){
    %>
    <tr>
            <td><%= u.getId()%></td>
            <td><%= u.getCorreo()%></td>
            <td><%= u.getContrasenya() %></td>
            <td><%= u.getRolDTOByRol().getTipo()%></td>
        <%
                if(u.getRolDTOByRol().getId() == 2){
        %>
                    <td><a href="/usuarioEvento/editar/<%= u.getId()%>">Editar</a></td>
                    <td><a href="/usuarioEvento/borrar/<%= u.getId()%>">Borrar</a></td>
        <%
                }else{

        %>
                    <td><a href="/editar/<%= u.getId()%>">Editar</a></td>
        <%
                    if(u.getId() != us.getId()){
        %>
                        <td><a href="/borrar/<%= u.getId()%>">Borrar</a></td>
        <%
                    }
        %>
    </tr>
    <%
                }
            }
        }
    %>
    </tbody>
    <h1>Eventos Disponibles</h1>


            <form:form modelAttribute="eventoDTO" action="/evento/filtrar">

                <form:input path="titulo" class="input2" type="text" name="buscadorNombre" placeholder="Buscar eventos por nombre y/o fecha"/>
                <form:input path="fecha" class="input2" type="date" name="fechaInicio" min="<%=formato.format(new Date())%>"/>
                <form:input path="fechacompra" class="input2" type="date" name="fechaFin" min="<%=formato.format(new Date())%>"/>
 º              <button value="filtroEvento">Buscar</button>

            </form:form>
</table>
<a href="/evento/crear"><input type="button" value="Crear Evento" /> </a>
<!--Eventos Disponibles:-->
<div class="container m-t-30">
    <div class="row justify-content-center m-t-10">
        <div class="col-12">
            <% if (eventosFuturos.isEmpty()) { %>
            <div class="bg-text justify-content-center text-center">
                <h1 style="color: #9e9e9e"> Actualmente no hay eventos disponibles</h1>
            </div>
            <% } else { %>
            <table class="center table table-striped align-middle" id="tabla-custom" style="font-size:1.2rem">
                <thead>
                <tr>
                    <th>NOMBRE</th>
                    <th>DESCRIPCION</th>
                    <th>CIUDAD</th>
                    <th>FECHA</th>
                    <th>PRECIO</th>
                    <th>AFORO</th>
                    <th>ETIQUETAS</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <%
                    for (EventoDTO ev : eventosFuturos) {
                %>
                <tr>
                    <td><%= ev.getTitulo()%> </td>
                    <td><%= ev.getDescripcion()%>  </td>
                    <td><%= ev.getCiudad()%> </td>
                    <td><%= formato.format(ev.getFecha())%> </td>
                    <td><%= ev.getPrecio()%></td>
                    <td><%= ev.getAforo()%></td>
                    <td></td>
                    <td><a class="btn  btn-primaryazul" href="evento/editar/<%= ev.getId()%>">EDITAR</a></td>
                    <td><a class="btn  btn-primaryazul" href=evento/borrar/<%= ev.getId()%>">BORRAR</a></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <% }
            %>
        </div>
    </div>
</div>

</body>