<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.EntradaDTO" %><%--
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
    List<EntradaDTO> listaEntradas = (List) request.getAttribute("listaEntradas");
%>
<body>
    <%
        for(EntradaDTO e: listaEntradas){
    %>
    <tr>
        <td><%= e.getEventoByEvento().getId() %></td>
        <td><%= e.getUsuarioeventoByUsuario().getId() %></td>
    </tr>
    <%
        }
    %>
</body>
</html>
