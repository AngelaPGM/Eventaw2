<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: angep
  Date: 11/06/2021
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
</head>
<body>
<div class="fondo-login">
    <div class="container-login">
        <div class="wrap-login">
            <%
                String errorLog = (String) request.getAttribute("errorLog");
            %>
            <form:form class="login-form" method="POST" action="/login" modelAttribute="user">
                <!-- Logo y texto -->
                <span class="login-form-title p-b-35">
                      <img class="img-zoom" src="images/Eventaw.png" alt="Logo" style="width: 60%;"/><br/>
                      Inicia sesión
                </span>

                <!-- Alerta error al iniciar sesion -->
                <%
                    if (null != errorLog) {%>
                <div class=" alert alert-danger vertical-align-middle">
                    <strong>¡Error!</strong> <%= errorLog%>
                </div>
                <% }
                %>

                <div class="wrap-input100 ">
                    Email:<br>
                    <form:input path="correo" class="input100" type="email" name="email"/>
                </div>

                <div class="wrap-input100 m-t-30 m-b-30">
                    Contraseña:<br>
                    <form:input path="contrasenya" class="input100" type="password" name="pass"/>
                </div>

                <div class="container-login100-form-btn p-b-10">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn">
                            Acceder
                        </button>
                    </div>
                </div>
                <div class="text-center p-t-8">
                    ¿No tienes cuenta?
                    <a href="/usuarioEvento/registrarFormulario" class="txt2">
                        REGÍSTRATE AQUÍ
                    </a>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
