<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.AnalisisDTO" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="es.taw.eventaw.entity.Usuario" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: rafa
  Date: 5/6/21
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Datos evento</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    EventoDTO evento = (EventoDTO) request.getAttribute("evento");
    String error = (String) request.getAttribute("error");
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("userDTO");

    String id = "", titulo = "", desc = "", ciudad = "", fecha = "", fechaCompra = "", precio = "",
            aforo = "", max = "", numFilas = "", asientos = "";

    if (evento != null) {
        id = "" + evento.getId();
        titulo = evento.getTitulo();
        desc = evento.getDescripcion();
        ciudad = evento.getCiudad();
        fecha = formato.format(evento.getFecha());
        fechaCompra = formato.format(evento.getFechacompra());
        precio = "" + evento.getPrecio();
        aforo = "" + evento.getAforo();
        max = "" + evento.getMaxentradasusuario();
        numFilas = "" + evento.getNumfilas();
        if (numFilas.equals("null")) {
            numFilas = "";
        }
        asientos = "" + evento.getAsientosfila();
        if (asientos.equals("null")) {
            asientos = "";
        }
    }
%>
<body>
<%
    if(usuario.getId() == 1){
%>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a  href="Sin hacer">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="ServletCrudUsuario?id=<%= usuario.getId()%>">Mi perfil</a></li>
    </ul>
</div>
<%
} else {
%>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a  href="/inicioCreador">Inicio</a></li>
        <li style="float:right"><a href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="ServletCrudUsuario?id=<%= usuario.getId()%>">Mi perfil</a></li>
    </ul>
</div>
<%
    }
%>
<div class="fondo-pagina">
    <div class="container-perfil">
        <div class="wrap-registro justify-content-center text-center" style="width: 1200px">
            <form class="register-form" method="POST" action="ServletGuardarEvento?id=<%= id%>&creador=<%= usuario.getId() + ""%>">

                <h1 class="bg-text" style="color: #a64bf4">
                    Datos del evento
                </h1>
                <%
                    if (error != null && !("".equals(error))) {%>
                <div class=" alert alert-danger vertical-align-middle">
                    <strong>Error:</strong> <%= error%> </a>
                </div>
                <% }
                %>
                <hr/>
                <div class="row p-t-5">
                    <div class="col-3">Nombre: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-4">Descripci&oacute;n: <span style="color:#a64bf4">(*)</span></div>
                </div>
                <div class="row p-b-20 justify-content-around">
                    <div class="col-3 wrap-input2">
                        <input class="input2" type="text" name="titulo" value="<%= titulo%>" required>
                    </div>
                    <div class="col-8 wrap-input2">
                        <input class="input2" type="text" name="desc" value="<%= desc%>" required>
                    </div>
                </div>
                <div class="row justify-content-around">
                    <div class="col-2">Ciudad: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-3">Entradas por usuario: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-3 text-center">Fecha del evento: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-3 text-center">Fecha l&iacute;mite compra: <span style="color:#a64bf4">(*)</span></div>
                </div>
                <div class="row p-b-20 justify-content-around">
                    <div class="col-2 wrap-input2">
                        <input class="input2" type="text" name="ciudad"  value="<%= ciudad%>" required>
                    </div>
                    <div class="col-3 wrap-input2 ">
                        <input class="input2 text-center" type="number" name="max" min="1" value="<%= max%>" required>
                    </div>
                    <%
                        if (fecha.equals("") || fechaCompra.equals("")) {%>

                    <div class="col-3 wrap-input2">
                        <input class="input2" type="date" name="fecha" style="text-align: center" min="<%=formato.format(new Date())%>"
                               value="<%=formato.format(new Date())%>" required>
                    </div>
                    <div class="col-3 wrap-input2">
                        <input class="input2"   type="date"  name="fechaCompra" style="text-align: center" min="<%=formato.format(new Date())%>"
                               value="<%=formato.format(new Date())%>" required>
                    </div>
                    <%
                    } else {%>
                    <div class="col-3 wrap-input2">
                        <input class="input2" type="date"  name="fecha" style="text-align: center" min="<%=formato.format(new Date())%>"
                               value="<%= fecha%>">
                    </div>
                    <div class="col-3 wrap-input2">
                        <input class="input2" type="date"  name="fechaCompra" style="text-align: center" min="<%=formato.format(new Date())%>"
                               value="<%= fechaCompra%>">
                    </div>
                    <% }
                    %>
                    <!-- -->

                </div>
                <div class="row justify-content-around">
                    <div class="col-2 ">Precio: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-2 ">Aforo: <span style="color:#a64bf4">(*)</span></div>
                    <div class="col-2">Nº filas:</div>
                    <div class="col-4">Asientos por fila</div>
                </div>
                <div class="row p-b-20 justify-content-around">
                    <div class="col-2 wrap-input2">
                        <input class="input2 text-center" type="number" min="0" name="precio" step="0.01" value="<%=precio%>" required>
                    </div>
                    <div class="col-2 wrap-input2 ">
                        <input class="input2 text-center" type="number" name="aforo" min="1" value="<%=aforo%>" required>
                    </div>
                    <div class="col-2 wrap-input2">
                        <input class="input2 text-center" type="number" name="numFilas"  min="1" value="<%=numFilas%>">
                    </div>
                    <div class="col-4 wrap-input2">
                        <input class="input2 text-center" type="number" name="asientos" min="1" value="<%=asientos%>">
                    </div>
                </div>
                <hr/>
                <div class="row justify-content-center">
                    <div class="col-3">
                        <div class="container-login100-form-btn p-t-30 justify-content-center">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn">
                                    Guardar
                                </button>
                            </div>
                        </div>

                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>