<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %>
<%@ page import="es.taw.eventaw.dto.EntradaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
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
    AnalisisDTO a = (AnalisisDTO) request.getAttribute("analisis");
%>
<body>

    <form:form action="/analisis/guardar" modelAttribute="analisis">
        <form:hidden path="id"></form:hidden>
        Analisis: <form:input path="nombre"></form:input><br>

        Fecha superior a: <form:input path="fechamayor" type="date"></form:input><br>
        Fecha inferior a: <form:input path="fechamenor" type="date"></form:input><br>

        Precio superior a: <form:input path="preciomayor"></form:input><br>
        Precio inferior a: <form:input path="preciomenor"></form:input><br>

        Fecha nacimiento superior a: <form:input path="nacimientomayor" type="date"></form:input><br>
        Fecha nacimiento inferior a: <form:input path="nacimientomenor" type="date"></form:input><br>

        Sexo comprador:
        <%
            if(a.getSexo() == null || a.getSexo().isEmpty()){
        %>
        H <form:radiobutton path="sexo" value="H"/>
        M <form:radiobutton path="sexo" value="M"/><br>
        <%
            }else{
                if(a.getSexo().contains("H")){
        %>
        H <form:radiobutton path="sexo" value="H" checked="checked"/>
        M <form:radiobutton path="sexo" value="M"/><br>
        <%
            }else if(a.getSexo().contains("M")){
        %>
        H <form:radiobutton path="sexo" value="H"/>
        M <form:radiobutton path="sexo" value="M" checked="checked"/><br>
        <%
                }
            }
        %>

        <input type="submit" value="Guardar">
    </form:form>
    <form:form method="get" action="/analisis/">
        <input type="submit" value="Salir">
    </form:form>

    <%
        if(listaEntradas != null){
    %>
    <h2>Estadísticas</h2>
    Resultados mostrados: <%=listaEntradas.size()%>/

    <table border="1">
        <tr>
            <th>FECHA EVENTO</th>
            <th>PRECIO EVENTO</th>
            <th>NACIMIENTO</th>
            <th>SEXO</th>
        </tr>
        <%
            for(EntradaDTO e: listaEntradas){
        %>
        <tr>
            <td><%= e.getEventoDTOByEvento().getFecha() %></td>
            <td><%= e.getEventoDTOByEvento().getPrecio() %></td>
            <td><%= e.getUsuarioeventoDTOByUsuario().getFechanacimiento() %></td>
            <td><%= e.getUsuarioeventoDTOByUsuario().getSexo() %></td>
        </tr>
        <%
            }
        %>
        <%
            }
        %>
    </table>
</body>
</html>
