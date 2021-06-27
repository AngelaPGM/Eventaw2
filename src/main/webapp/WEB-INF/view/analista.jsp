<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %>
<%@ page import="es.taw.eventaw.vo.FiltroAnalisis" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    List<AnalisisDTO> listaAnalisis = (List) request.getAttribute("listaAnalisis");
%>
<body>
    <div class="fondo-pagina">
        <div class="row justify-content-around">
            <div class="topnav fixed-top">
                <ul>
                    <li style="float:right"><a href="/analisis/logout">Cerrar sesión</a></li>
                </ul>
            </div>

            <div class="bg-text">
                <h1 style="font-size: 4rem">Bienvenido</h1>
            </div>

            <form:form method="post" action="/analisis/filtrar" modelAttribute="filtro">
                Busca un analisis por su nombre:<br>
                <form:input path="nombre"></form:input>
                <input class="btn btn-primary btn-primary rounded-pill" type="submit" value="Filtrar">
            </form:form>

            <table border="0" class="center table table-striped align-middle" id="tabla-custom">
                <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                </tr>
                <% for (AnalisisDTO a: listaAnalisis){%>
                <tr>
                    <td><%=a.getId()%></td>
                    <td><%=a.getNombre()%></td>
                    <td><a class="btn btn-primary btn-primary rounded-pill mt-3" href="/analisis/ver/<%=a.getId()%>">Ver resultados</a></td>
                    <td><a class="btn btn-primary btn-primary rounded-pill mt-3" href="/analisis/borrar/<%=a.getId()%>">Eliminar</a></td>
                    <td><a class="btn btn-primary btn-primary rounded-pill mt-3" href="/analisis/copiar/<%=a.getId()%>">Duplicar</a></td>
                </tr>
                <%
                    }
                %>
            </table>

            <a class="btn btn-primary btn-primary rounded-pill" href="/analisis/ver/-1">Nuevo análisis</a>
        </div>
    </div>
</body>
</html>
