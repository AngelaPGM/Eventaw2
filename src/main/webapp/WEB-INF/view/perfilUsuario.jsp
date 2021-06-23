<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: angep
  Date: 23/06/2021
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Datos Usuario</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/util.css">
</head>
<%
    String errorLog = (String) request.getAttribute("errorLog");
    UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("userDTO");
    Boolean guardado = (Boolean) request.getAttribute("guardado");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a href="/inicioCreador">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a class="active">Mi perfil</a></li>
        <%
            if (usuarioDTO.getRolDTOByRol().getId() == 3) {
        %>
        <li style="float:right"><a href="Sin hacer">CHAT TELEOPERADOR</a></li>
        <% }
        %>
    </ul>
</div>

<div class="fondo-pagina">
    <div class="container-perfil">
        <div class="wrap-registro justify-content-center text-center">
            <form:form modelAttribute="userDTO" class="register-form" action="/guardar">
                <form:hidden path="id"/>
                <form:hidden path="rolDTOByRol.id"/>
                <h1 class="bg-text">Datos de usuario</h1>
                <%
                    if (errorLog != null && errorLog != "") {%>
                <div class=" alert alert-danger vertical-align-middle">
                    <strong>¡Error!</strong> <%= errorLog %> </a>
                </div>
                <% } else if (guardado != null && guardado) { %>

                <div class=" alert alert-success vertical-align-middle text-center" style="margin-top: 5px">
                    <strong> Los cambios se han guardado satisfactoriamente. </strong>
                </div>
                <% }
                %>
                <hr/>

                <div class="row justify-content-around m-t-20">
                    <div class="col-11">
                        Email: <span style="color:#a64bf4">(*)</span>
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-11 wrap-input2">
                        <form:input class="input2" path="correo" type="email" name="correo" required="required"/>
                    </div>
                </div>
                <div class="row justify-content-around m-t-20">
                    <div class="col-5">
                        Contraseña: <span style="color:#a64bf4">(*)</span>
                    </div>
                    <div class="col-5">
                        Repita contraseña: <span style="color:#a64bf4">(*)</span>
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-6  wrap-input2" style="width: 45%;">
                        <form:input class="input2" type="password" name="contrasenia" path="contrasenya"/>
                    </div>
                    <div class="col-6  wrap-input2" style="width: 45%;">
                        <form:input class="input2" type="password" name="contrasenia1" path="contrasenya2"/>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-6">
                        <div class="container-login100-form-btn p-t-30 justify-content-center">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Guardar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
