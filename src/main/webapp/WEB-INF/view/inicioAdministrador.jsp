<%@ page import="es.taw.eventaw.entity.Evento" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.entity.Usuario" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="es.taw.eventaw.entity.EventoEtiqueta" %>
<%@ page import="es.taw.eventaw.dto.EventoEtiquetaDTO" %>
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
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyy");
    List<UsuarioDTO> listaUsuarios = (List<UsuarioDTO>) request.getAttribute("usuarios");
    UsuarioDTO us = (UsuarioDTO) session.getAttribute("userDTO");
%>
<body>

<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
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
            <a class="btn btn-primary btn-xl rounded-pill mt-3" href="#eventos">Ver eventos</a>
        </div>
    </div>
</header>
<!-- USUARIOS  -->
<section id="usuarios">
    <div class="container m-t-30">
        <div class="row">
            <div class="col-sm-10 col-md-7">
                <h1 class="bg-text" style=" color:#b997f6;"> Usuarios: </h1>
            </div>
        </div>
        <form:form modelAttribute="usuarioFiltroDTO" action="/filtrar">
            <div class="row m-t-10">
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="botones-pag"></div>
                        <a class="login100-form-btn" style="text-decoration: none" href="/crear">
                            Nuevo usuario
                        </a>
                    </div>
                </div>
                <div class="col-5 wrap-input2 offset-1">
                    <form:input path="correo" class="input2" type="text"
                                placeholder="Introduzca el filtro..."></form:input>
                </div>
                <div class="col-2">
                    <form:select path="contrasenya2" class="custom-select">
                        <form:option value="ID"></form:option>
                        <form:option value="CORREO"></form:option>
                        <form:option value="ROL"></form:option>
                    </form:select>
                </div>
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="botones-pag"></div>
                        <button class="login100-form-btn" value="Buscar">
                            FILTRAR
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</section>

<!-- TABLA USUARIOS -->
<div class="container m-t-20">
    <table class="center table table-striped align-middle" id="tabla-custom">
        <thead>
        <tr>
            <td>ID</td>
            <td>EMAIL</td>
            <td>CONTRASEÑA</td>
            <td>ROL</td>
            <td></td>
            <td></td>
        </tr>
        </thead>
        <tbody>
        <%
            if (listaUsuarios.isEmpty()) {
        %>
        <h1>No hay usuarios con este filtrado.</h1>
        <%

        } else {


            for (UsuarioDTO u : listaUsuarios) {
        %>
        <tr>
            <td><%= u.getId()%>
            </td>
            <td><%= u.getCorreo()%>
            </td>
            <td><%= u.getContrasenya() %>
            </td>
            <td><%= u.getRolDTOByRol().getTipo()%>
            </td>
            <%
                if (u.getRolDTOByRol().getId() == 2) {
            %>
            <td><a class="btn  btn-primary" href="/usuarioEvento/editar/<%= u.getId()%>">Editar</a></td>
            <td><a class="btn  btn-primary" href="/borrar/<%= u.getId()%>">Borrar</a></td>
            <%
            } else {

            %>
            <td><a class="btn  btn-primary" href="/editar/<%= u.getId()%>">Editar</a></td>
            <%
                if (u.getId() != us.getId()) {
            %>
            <td><a class="btn  btn-primary" href="/borrar/<%= u.getId()%>">Borrar</a></td>
            <%
            } else { %>
            <td></td>
            <% }
            %>
        </tr>
        <%
                    }
                }
            }
        %>
        </tbody>
    </table>
</div>

<!-- EVENTOS -->
<section id="eventos">
    <div class="container p-t-30">
        <div class="row">
            <div class="col-sm-10 col-md-7" style="padding: 1rem ">
                <h1 class="bg-text" style="color:#7cc5e5;"> Eventos disponibles: </h1>
            </div>
        </div>
        <form:form modelAttribute="eventoDTO" action="/evento/filtrar">
            <div class="row">
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="botones-pag-azul"></div>
                        <a class="login100-form-btn" style="text-decoration: none" href="/evento/crear">
                            Nuevo evento
                        </a>
                    </div>
                </div>

                <div class="col-1" style="width: 9vh"></div>
                <div class="col-3  wrap-input2 align-self-end">
                    <form:input path="titulo" class="input2" type="text" name="buscadorNombre"
                                placeholder="Introduzca el filtro..."/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10">
                    <form:input path="fecha" class="input2" type="date" name="fechaInicio"
                                min="<%=formato.format(new Date())%>"/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10 align-self-end">
                    <form:input path="fechacompra" class="input2" type="date" name="fechaFin"
                                min="<%=formato.format(new Date())%>"/>
                </div>
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="botones-pag-azul"></div>
                        <button class="login100-form-btn" value="Buscar">
                            Filtrar
                        </button>
                    </div>
                </div>
            </div>

        </form:form>
    </div>
</section>


<!--Eventos Disponibles:-->
<div class="container m-t-30">
    <div class="row justify-content-center m-t-10">
        <div class="col-12">
            <% if (eventosFuturos.isEmpty()) { %>
            <div class="bg-text justify-content-center text-center">
                <h1 style="color: #9e9e9e"> Actualmente no hay eventos disponibles</h1>
            </div>
            <% } else { %>
            <table class="center table table-striped align-middle" id="tabla-custom2" style="font-size:1.2rem">
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
                    <td><%= ev.getTitulo()%>
                    </td>
                    <td><%= ev.getDescripcion()%>
                    </td>
                    <td><%= ev.getCiudad()%>
                    </td>
                    <td><%= formato.format(ev.getFecha())%>
                    </td>
                    <td><%= ev.getPrecio()%>
                    </td>
                    <td><%= ev.getAforo()%>
                    </td>
                    <td><%
                        for (EventoEtiquetaDTO eve : ev.getEventoEtiquetasDTOById()) { %>
                        <%= eve.getEtiquetaDTOByEtiqueta().getNombre() %>
                        <% }
                        %></td>
                    <td><a class="btn  btn-primaryazul" href="evento/editar/<%= ev.getId()%>">EDITAR</a></td>
                    <td><a class="btn  btn-primaryazul" href="evento/borrar/<%= ev.getId()%>">BORRAR</a></td>
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
<div class="container p-b-100"></div>
</body>
</html>
