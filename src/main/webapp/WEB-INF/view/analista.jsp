<%@ page import="es.taw.eventaw.entity.Analisis" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rafa
  Date: 5/6/21
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    List<Analisis> listaAnalisis = (List) request.getAttribute("listaAnalisis");
%>
<body>
    <table border="1">
        <tr>
            <th>id</th>
        </tr>
        <% for (Analisis a: listaAnalisis){%>
            <tr>
                <td><%=a.getId()%></td>
                <td><a href="/ver/<%=a.getId()%>">Resultados</a></td>
            </tr>
        <%
            }
        %>
    </table>
    <a href="/crear">Nuevo análisis</a>
</body>
</html>
