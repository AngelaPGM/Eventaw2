<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
    <title>Mi perfil</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    String errorLog = (String) request.getAttribute("errorLog");
    Boolean guardado = (Boolean) request.getAttribute("guardado");
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a href="/inicioUEvento">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a class="active">Mi perfil</a></li>
        <li style="float:right"><a href="/usuarioEvento/misEntradas">MIS ENTRADAS</a></li>
        <li style="float:right"><a href="/conversacion/iniciar">CHAT TELEOPERADOR</a></li>
    </ul>
</div>

<div class="fondo-pagina">
    <div class="container-perfil">
        <div class="wrap-registro" style=" margin-top: 3%">
            <form:form class="register-form" modelAttribute="userDTO" action="/usuarioEvento/guardar">
                <form:hidden path="id"/>
                <form:hidden path="rolDTOByRol.id"/>
                <form:hidden path="usuarioeventoDTOById.id"/>
                <form:hidden path="editar"/>
            <span class="login-form-title">
                            Modificar perfil
                        </span>
                <%
                    if (errorLog != null && !errorLog.equals("")) {%>
                <div class=" alert alert-danger vertical-align-middle text-center" style="margin-top: 5px">
                    <strong>Error:</strong> <%= errorLog%>
                </div>
                <% } else if (guardado != null && guardado) { %>

                <div class=" alert alert-success vertical-align-middle text-center" style="margin-top: 5px">
                    <strong> Los cambios se han guardado satisfactoriamente. </strong>
                </div>
                <% }
                %>
                <hr/>
                <div class="row justify-content-around p-t-5">
                    <div class="col-3 text-center">Nombre:</div>
                    <div class="col-4 text-center">Primer apellido:</div>
                    <div class="col-4  text-center">Segundo apellido:</div>
                </div>

                <div class="row p-b-20 justify-content-around">
                    <div class="col-3 wrap-input2">
                        <form:input class="input2" type="text" name="nombre" path="usuarioeventoDTOById.nombre"
                                    required="required"/>
                    </div>
                    <div class="col-4 wrap-input2  ">
                        <form:input class="input2" type="text" name="ape1" path="usuarioeventoDTOById.apellido1"
                                    required="required"/>
                    </div>
                    <div class="col-4 wrap-input2 ">
                        <form:input class="input2" type="text" name="ape2" path="usuarioeventoDTOById.apellido2"
                              />
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-8 ">Domicilio:</div>
                    <div class="col-3 ">Ciudad:</div>
                </div>
                <div class="row justify-content-around p-b-20">
                    <div class="col-8 wrap-input2">
                        <form:input class="input2" type="text" name="domicilio"
                                    path="usuarioeventoDTOById.domicilio"
                                    required="required"/>
                    </div>
                    <div class="col-3 wrap-input2">
                        <form:input class="input2" type="text" name="ciudad" path="usuarioeventoDTOById.ciudad"
                                    required="required"/>
                    </div>
                </div>

                <div class="row justify-content-around">
                    <div class="col-4 text-center">Fecha de nacimiento:</div>
                    <div class="col-3 text-center">Sexo:</div>
                </div>
                <div class="row justify-content-around p-b-20">
                    <div class="col-4 wrap-input2 ">
                        <form:input class="input2" type="date" name="fNac" max="<%= formato.format(new Date())%>"
                                    path="usuarioeventoDTOById.fechanacimiento" required="required"/>
                    </div>

                    <div class="col-3">
                        <form:select path="usuarioeventoDTOById.sexo" class="custom-select" name="sexo">
                            <form:option value="M" label="Mujer"/>
                            <form:option value="H" label="Hombre"/>
                        </form:select>
                    </div>
                </div>

                <hr/>
                <div class="row justify-content-around">
                    <div class="col-11">
                        Email:
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-11 wrap-input2">
                        <form:input class="input2" type="email" name="email" required="required" path="correo"/>
                    </div>
                </div>
                <div class="row justify-content-around m-t-20">
                    <div class="col-5">
                        Nueva contraseña:
                    </div>
                    <div class="col-5">
                        Repita nueva contraseña:
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-6  wrap-input2" style="width: 45%;">
                        <form:input class="input2" type="password" name="pass1" path="contrasenya"/>
                    </div>
                    <div class="col-6  wrap-input2" style="width: 45%;">
                        <form:input class="input2" type="password" name="pass2" path="contrasenya2"/>
                    </div>
                </div>


                <div class="row justify-content-center">
                    <div class="col-6">
                        <div class="container-login100-form-btn p-t-30 justify-content-center">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    GUARDAR
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
