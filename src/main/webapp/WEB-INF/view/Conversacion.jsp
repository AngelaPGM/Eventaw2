<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.dto.ConversacionDTO" %>
<%@ page import="es.taw.eventaw.dto.MensajeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User:
  Date:
  Time:
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Conversacion</title>
    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    UsuarioDTO u = (UsuarioDTO) request.getAttribute("usuarioDTO");
    ConversacionDTO c = (ConversacionDTO) request.getAttribute("conversacionDTO");
%>

<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>

        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <%
            if (u.getRolDTOByRol().getId() == 3) {%>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
        <li><a href="/inicioCreador">Inicio</a></li>
        <% } else if (u.getRolDTOByRol().getId() == 2) { %>
        <li style="float:right"><a href="/usuarioEvento/perfil">Mi perfil</a></li>
        <li style="float:right"><a href="/usuarioEvento/misEntradas">MIS ENTRADAS</a></li>
        <li><a href="/inicioUEvento">Inicio</a></li>
        <% } else if (u.getRolDTOByRol().getId() == 4) { %>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
        <li><a href="/conversacion/verChats">Inicio</a></li>
        <% }
        %>
        <li style="float:right"><a class="active">CHAT TELEOPERADOR</a></li>
    </ul>
</div>
<div class="fondo-pagina">
    <div class="container-perfil">
        <div class="wrap-registro justify-content-center" style=" margin-top: 3%">
                    <span class="login-form-title m-t-20">
                        Conversaci&oacute;n
                        <hr>
                    </span>
            <div class="content pt-5" style="padding-left: 10%">
                <%
                    for (MensajeDTO m : c.getMensajesById()) {
                %>
                <b><%= m.getEmisor().getCorreo() %>
                </b><br/>
                <%= m.getContenido() %><br/><br/>
                <%
                    }
                    if (u.getId().equals(c.getTeleoperadorConversacion().getId()) || u.getId().equals(c.getUsuarioConversacion().getId())) {
                %>
            </div>
            <form:form class="login-form" action="/conversacion/enviarMensaje" method="POST" modelAttribute="mensaje">
            <form:hidden path="conversacion.id"/>
            <div class="row">
                <div class="col-9">
                    <div class="wrap-input100">
                        <form:input class="input100" path="contenido" placeholder="Escribe un mensaje aqui"/>
                    </div>
                </div>
                <form:hidden path="emisor.correo"/>
                <div class="col-3">
                    <div class="wrap-login100-form-btn">
                        <div class="botones-pag"></div>
                        <input class="login100-form-btn" style="background-color: #a64bf4" type="submit"
                               value="Enviar"/>
                    </div>
                </div>
                </form:form>


                <%
                    }
                %>

            </div>
        </div>
    </div>

</body>
</html>