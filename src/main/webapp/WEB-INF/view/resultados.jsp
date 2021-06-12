<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.entity.Entrada" %><%--
  Created by IntelliJ IDEA.
  User: rafa
  Date: 5/6/21
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    List<Entrada> listaEntradas = (List) request.getAttribute("listaEntradas");
%>
<body>
    <%
        for(Entrada e: listaEntradas){
    %>
    <tr>
        <td><%= e.getEvento()%></td>
        <td><%= e.getUsuario()%></td>
    </tr>
    <%
        }
    %>
</body>
</html>
