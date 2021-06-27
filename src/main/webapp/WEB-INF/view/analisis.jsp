<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %>
<%@ page import="es.taw.eventaw.dto.EntradaDTO" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <title>Analisis</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    List<EntradaDTO> listaEntradas = (List) request.getAttribute("listaEntradas");
    AnalisisDTO a = (AnalisisDTO) request.getAttribute("analisis");
    Integer totales = (Integer) request.getAttribute("totales");
    int[] estadistico = (int[]) request.getAttribute("estadistico");
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a href="/analisis/">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
    </ul>
</div>
<header class="header-inicio text-center text-white" style="height: 30vh">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> An&aacute;lisis </h1>
        </div>
    </div>
</header>
<form:form action="/analisis/guardar" modelAttribute="analisis" class="register-form">
    <form:hidden path="id"/>
    <div class="row justify-content-center text-center d-flex align-items-center" >
        <div class="col-1" style="font-size: 1.3rem; display:flex;">Nombre:</div>
        <div class="col-2 wrap-input2">
            <form:input path="nombre" class="input2"/>
        </div>

        <hr class="mt-3">
    </div>
    <span class="bg-text" style="font-size: 1.3rem; padding-left:12%"> Datos para eventos con:</span>

    <div class="row pt-3 justify-content-center  d-flex align-items-center">
        <div class="col-2" style="font-size: 1.3rem; display:flex;">Fecha superior a:</div>
        <div class="col-2 wrap-input2"><form:input path="fechamayor" type="date" class="input2"/></div>
        <div class="col-2 offset-1" style="font-size: 1.3rem; display:flex;">Fecha inferior a:</div>
        <div class="col-2 wrap-input2"><form:input path="fechamenor" type="date" class="input2"/></div>
    </div>

    <div class="row pt-3  justify-content-center  d-flex align-items-center">
        <div class="col-2" style="font-size: 1.3rem; display:flex;">Precio superior a:</div>
        <div class="col-2 wrap-input2"><form:input path="preciomayor" class="input2"/></div>
        <div class="col-2 offset-1" style="font-size: 1.3rem; display:flex;">Precio inferior a:</div>
        <div class="col-2 wrap-input2"><form:input path="preciomenor" class="input2"/></div>
    </div>

    <div class="row pt-5"><span class="bg-text " style="font-size: 1.3rem; padding-left:12%"> A los que asistiran usuarios con:</span>
    </div>
    <div class="row pt-3  justify-content-center  d-flex align-items-center">
        <div class="col-2" style="font-size: 1.3rem">Fecha nacimiento superior:</div>
        <div class="col-2 wrap-input2"><form:input path="nacimientomayor" type="date" class="input2"/></div>
        <div class="col-2 offset-1" style="font-size: 1.3rem; display:flex;">Fecha nacimiento inferior:</div>
        <div class="col-2 wrap-input2"><form:input path="nacimientomenor" type="date" class="input2"/></div>
    </div>
    <div>
        <span style="font-size: 1.3rem; padding-left:12%"> Datos: </span>
        <%
            if (a.getSexo() == null) {
        %>
        <form:radiobutton path="sexo" value="H" label="Hombre"/>
        <form:radiobutton path="sexo" value="M" label="Mujer"/>
        <form:radiobutton path="sexo" value="N" checked="checked" label="Ambos"/><br>
        <%
        } else {
            switch (a.getSexo()) {
                case "H":
        %>
        <form:radiobutton path="sexo" value="H" label="Hombre" checked="checked"/>
        <form:radiobutton path="sexo" value="M" label="Mujer"/>
        <form:radiobutton path="sexo" value="N" label="Ambos"/><br>
        <%
                break;
            case "M":
        %>
        <form:radiobutton path="sexo" value="H" label="Hombre"/>
        <form:radiobutton path="sexo" value="M" checked="checked" label="Mujer"/>
        <form:radiobutton path="sexo" value="N" label="Ambos"/><br>
        <%
                        break;
                }
            }
        %>

    </div>
    <div class="row justify-content-center">
        <div class="col-2 pt-2">
            <input class="btn btn-primary btn-xl rounded-pill mt-0" type="submit" value="Guardar">
        </div>
    </div>
    <hr>

</form:form>

<div class="container">

    <div class="row">
        <div class="row"><span class="bg-text " style="font-size: 2rem;"> Estad&iacute;sticas</span></div>
        <%
            if (listaEntradas != null) {
        %>
        Entradas mostradas: <%=listaEntradas.size()%>/<%=totales%>
        (<%= (totales == 0) ? 0 : listaEntradas.size() / totales * 100%>%)<br>
        Hombres: <%= estadistico[0]%>/<%=listaEntradas.size()%>
        (<%= (listaEntradas.size() == 0) ? 0 : estadistico[0] / listaEntradas.size() * 100%>%).
        Mayores de edad: <%=estadistico[1]%>/<%= estadistico[0]%>
        (<%= (estadistico[0] == 0) ? 0 : estadistico[1] / estadistico[0] * 100%>%)<br>

        Mujeres: <%= estadistico[2]%>/<%=listaEntradas.size()%>
        (<%= (listaEntradas.size() == 0) ? 0 : estadistico[2] / listaEntradas.size() * 100%>%).
        Mayores de edad: <%=estadistico[3]%>/<%= estadistico[2]%>
        (<%= (estadistico[3] == 0) ? 0 : estadistico[3] / estadistico[2] * 100%>%)<br>
        <br/>
        <table border="1" class="center table table-striped align-middle" id="tabla-custom">
            <tr>
                <th>ENTRADA</th>
                <th>FECHA EVENTO</th>
                <th>PRECIO EVENTO</th>
                <th>NACIMIENTO</th>
                <th>SEXO</th>
            </tr>
            <%
                for (EntradaDTO e : listaEntradas) {
            %>
            <tr>
                <td><%= e.getId()%>
                </td>
                <td><%= e.getEventoDTO().getFecha()%>
                </td>
                <td><%= e.getEventoDTO().getPrecio()%>
                </td>
                <td><%= e.getUsuarioeventoDTO().getFechanacimiento() %>
                </td>
                <td><%= e.getUsuarioeventoDTO().getSexo() %>
                </td>
            </tr>
            <%
                }
            %>
            <%
                }
            %>
        </table>
    </div>
</div>
<div class="container p-b-100"></div>
</body>
</html>
