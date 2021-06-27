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
    <title>Teleoperador</title>
</head>
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
        Correo:<form:input path="usuarioConversacion.correo"/>
        <input type="submit" value="Filtrar"/>
    </form:form>
        <table border="1">
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
                    <td><a href="ver/<%= c.getId()%>">Ver</a> </td>
                    <td><a href="borrar/<%= c.getId()%>">Eliminar</a></td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    <%

        }
    %>
<body>

</body>
</html>