<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PerfilUsuario</title>
</head>

<%
    String errorLog = (String) request.getAttribute("errorLog");
    Boolean guardado = (Boolean) request.getAttribute("guardado");
%>
<body>

<form:form modelAttribute="userDTO" action="/guardar">
    <form:hidden path="id"/>
    Correo: <form:input path="correo" type="email" name="email" required="required"/> <br>
    Contraseña: <form:input class="input2" type="password" name="pass1" path="contrasenya"/> <br>
    Repita Contraseña: <form:input class="input2" type="password" name="pass2" path="contrasenya2"/> <br>
    Rol: <form:radiobuttons path="rolDTOByRol.tipo" itemValue="tipo" items="${listaRolDTO}" itemLabel="tipo"></form:radiobuttons> <br>
    <%
        if (errorLog != null && !errorLog.equals("")) {%>

        Error: <%= errorLog%>

    <% } else if (guardado != null && guardado) { %>

       Los cambios se han guardado satisfactoriamente.
    <% }
    %>
    <button type="button"> Guardar</button>
</form:form>

</body>
</html>