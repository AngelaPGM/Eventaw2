<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.entity.Usuario" %>
<%@ page import="es.taw.eventaw.dto.ConversacionDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User:
  Date:
  Time:
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Teleoperador</title>
</head>

<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
    </ul>
</div>

<header class="header-inicio text-center text-white" style="height: 40%">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem;"> Bienvenido de nuevo, </h1>
            <h2 style="font-size: 3rem"> teleoperador</h2>
        </div>
    </div>
</header>
<%
    Usuario u = (Usuario) session.getAttribute("userDto");
    List<ConversacionDTO> chats = (List<ConversacionDTO>) request.getAttribute("chats");

        if(chats == null){
    %>
        Actualmente no tienes Chats.
    <%
        }else{
    %>
    <form:form action="/conversacion/filtrar" modelAttribute="filtro" >
    <section id="usuarios" class="m-t-30">
    <div class="container">
        <div class="row">
            <div class="col-4">
                <h1 class="bg-text" style=" color:#b997f6;"> Conversaciones: </h1>
            </div>
            <div class="col-4 wrap-input2 offset-2">
                <form:input class="input2" type="text" placeholder="Introduzca el correo" path="usuarioConversacion.correo"/>
            </div>
            <div class="col-2">
                <div class="wrap-login100-form-btn">
                    <div class="botones-pag"></div>
                    <button class="login100-form-btn" value="Filtrar" >
                        FILTRAR
                    </button>
                </div>
            </div>
        </div>
    </div>
    </section>

<div class="container m-t-20">
    </form:form>
        <table class="center table table-striped align-middle" id="tabla-custom">
            <thead>
                <tr>
                    <td>Teleoperador</td>
                    <td>Usuario</td>
                    <td></td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <%
                for(ConversacionDTO c : chats){
                %>
                <tr>
                    <td><%= c.getTeleoperadorConversacion().getCorreo() %></td>
                    <td><%= c.getUsuarioConversacion().getCorreo() %> </td>
                    <td><a class="btn  btn-primary" href="ver/<%= c.getId()%>">Ver</a> </td>
                    <td><a class="btn  btn-primary" href="borrar/<%= c.getId()%>">Eliminar</a></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%

        }
    %>
</div>
<body>

</body>
</html>