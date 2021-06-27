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
    <title>Inicio</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    List<AnalisisDTO> listaAnalisis = (List) request.getAttribute("listaAnalisis");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a href="/analisis/logout">Cerrar sesión</a></li>
    </ul>
</div>

<!-- Imagen fondo -->
<header class="header-inicio text-center text-white" style="height: 20rem">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> Bienvenido de nuevo, </h1>
            <h2 style="font-size: 3rem"> analista</h2>
        </div>
    </div>
</header>

<div class="container p-t-30">
    <div class="row">
        <div class="col-sm-10 col-md-7" style="padding: 1rem ">
            <h1 class="bg-text" style="color:#b997f6;"> Analisis:</h1>
        </div>
    </div>
    <form:form method="post" action="/analisis/filtrar" modelAttribute="filtro">
    <div class="row m-t-10">
        <div class="col-2">
            <div class="wrap-login100-form-btn">
                <div class="botones-pag"></div>
                <a class="login100-form-btn" style="text-decoration: none" href="/analisis/ver/-1">
                    Nuevo analisis
                </a>
            </div>
        </div>
        <div class="col-5 wrap-input2 offset-3">
            <form:input path="nombre" class="input2" placeholder="Introduzca el filtro por nombre..."/>
        </div>
        <div class="col-2">
            <div class="wrap-login100-form-btn">
                <div class="botones-pag"></div>
                <button class="login100-form-btn" value="Filtrar">
                    FILTRAR
                </button>
            </div>
        </div>
        </form:form>
    </div>
</div>

<!-- TABLA ANALISIS -->
<div class="container m-t-20">
    <table class="center table table-striped align-middle" id="tabla-custom">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <% for (AnalisisDTO a : listaAnalisis) {%>
        <tr>
            <td><%=a.getId()%>
            </td>
            <td><%=a.getNombre()%>
            </td>
            <td><a class="btn btn-primary" href="/analisis/ver/<%=a.getId()%>">Ver
                resultados</a></td>
            <td><a class="btn btn-primary" href="/analisis/borrar/<%=a.getId()%>">Eliminar</a></td>
            </td>
            <td><a class="btn btn-primary"
                   href="/analisis/copiar/<%=a.getId()%>">Duplicar</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
