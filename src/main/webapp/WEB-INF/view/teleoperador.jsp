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
    List<ConversacionDTO> listaChats = (List<ConversacionDTO>) request.getAttribute("todosChat");

        if(listaChats.isEmpty() || listaChats == null){
    %>
        Actualmente no tienes Chats.
    <%
        }else{
    %>
        <table>
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
                for(ConversacionDTO c : listaChats){
                %>
                <tr>
                    <td><%= c.getTeleoperadorConversacion().getCorreo() %></td>
                    <td><%= c.getUsuarioConversacion().getCorreo() %> </td>
                    <td><a href="conversacion/verConversacion/<%= c.getId()%>">Ver</a> </td>
                    <td><a href="conversacion/borrarConversacion/<%= c.getId()%>"></a>Eliminar</td>
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