<%@ page import="es.taw.eventaw.entity.Evento" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.entity.Usuario" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador</title>
</head>
    <%
        List<EventoDTO> listaEventos = (List<EventoDTO>) request.getAttribute("todosEventos");
        List<UsuarioDTO> listaUsuarios = (List<UsuarioDTO>) request.getAttribute("todosUsuarios");
        UsuarioDTO us = (UsuarioDTO) session.getAttribute("userDTO");
    %>
<body>

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
                <td><a href="/usuarioEvento/editarUsuarioEvento/<%= u.getId()%>">Editar</a></td>
                <td><a href="/usuarioEvento/borrarUsuarioEvento/<%= u.getId()%>">Borrar</a></td>
        <%
            }else{

        %>
                <td><a href="/editarUsuario/<%= u.getId()%>">Editar</a></td>
        <%
                if(u.getId() != us.getId()){
        %>
                 <td><a href="/borrarUsuario/<%= u.getId()%>">Borrar</a></td>
        <%
            }
        %>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<table border="1">
    <thead>
    <tr>
        <td>ID</td>
        <td>TITULO</td>
        <td>DESCRIPCION</td>
        <td>CREADOR</td>
        <td>FECHA</td>
        <td>CIUDAD</td>
        <th>PRECIO</th>
        <th>AFORO</th>
        <th>ETIQUETAS</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <%
        for(EventoDTO e : listaEventos){
    %>
    <tr>
        <td><%= e.getId() %></td>
        <td><%= e.getTitulo() %></td>
        <td><%= e.getDescripcion() %></td>
        <th><%= e.getCreadorDTO().getCorreo() %></th>
        <td><%= e.getFecha() %></td>
        <td><%= e.getCiudad() %></td>
        <td><%= e.getPrecio() %></td>
        <th><%= e.getAforo() %></th>
        <th>Etiquetas</th>
        <th><a href="evento/editar/<%= e.getId() %>">Editar</a></th>
        <th><a href="evento/borrar/<%= e.getId() %>">Borrar</a> </th>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>