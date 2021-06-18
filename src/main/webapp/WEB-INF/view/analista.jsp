<%@ page import="es.taw.eventaw.entity.Analisis" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %><%--
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
    List<AnalisisDTO> listaAnalisis = (List) request.getAttribute("listaAnalisis");
%>
<body>
    <table border="1">
        <tr>
            <th>id</th>
        </tr>
        <% for (AnalisisDTO a: listaAnalisis){%>
            <tr>
                <td><%=a.getId()%></td>
                <td><a href="/analisis/ver/<%=a.getId()%>">Resultados</a></td>
            </tr>
        <%
            }
        %>
    </table>
    <a href="/analisis/crear">Nuevo análisis</a>
</body>
</html>
