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
</head>
<%
    UsuarioDTO u = (UsuarioDTO) request.getAttribute("usuarioDTO");
    ConversacionDTO c = (ConversacionDTO) request.getAttribute("conversacionDTO");
%>

<body>
    <%
        for(MensajeDTO m : c.getMensajesById()){
    %>
    <b><%= m.getEmisor().getCorreo() %></b><br/>
    <%= m.getContenido() %><br/><br/>
    <%
        }if(u.getId().equals(c.getTeleoperadorConversacion().getId()) || u.getId().equals(c.getUsuarioConversacion().getId())){
    %>
    <form:form action="/conversacion/enviarMensaje" method="POST" modelAttribute="mensaje" >
    <form:hidden path="conversacion.id"/>
    Mensaje:<form:input path="contenido"/>
    <form:hidden path="emisor.correo"/>
    <input type="submit" value="Enviar"/>
    </form:form>
    <%
        }
    %>
</body>
</html>