<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %>
<%@ page import="es.taw.eventaw.dto.EntradaDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: rafa
  Date: 5/6/21
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    List<EntradaDTO> listaEntradas = (List<EntradaDTO>) request.getAttribute("listaEntradas");
%>
<body>

    <form:form action="/analisis/guardar" modelAttribute="analisis">
        <form:hidden path="id"></form:hidden>
        Analisis: <form:input path="nombre"></form:input><br>

        Fecha superior a : <form:input path="fechamayor" type="date"></form:input><br>
        Fecha inferior a : <form:input path="fechamenor" type="date"></form:input><br>

        Precio superior a : <form:input path="preciomayor"></form:input><br>
        Precio inferior a : <form:input path="preciomenor"></form:input><br>
        <input type="submit" value="Guardar">
    </form:form>
    <form:form method="get" action="/analisis/">
        <input type="submit" value="Salir sin guardar">
    </form:form>
    <%
        if(listaEntradas != null){
    %>
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
    <%
        }
    %>
</body>
</html>
