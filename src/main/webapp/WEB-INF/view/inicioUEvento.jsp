<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 08/06/2021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/util.css">
</head>
<%
    List<EventoDTO> eventosFuturos = (List<EventoDTO>) request.getAttribute("eventosFuturos");
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyy");
    int plazasDisp;

%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a class="active">Inicio</a></li>
        <li style="float:right"><a  href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="/perfil">Mi perfil</a></li>
        <li style="float:right"><a href="/usuarioEvento/misEntradas">MIS ENTRADAS</a></li>
        <li style="float:right"><a href="sin hacer">CHAT TELEOPERADOR</a></li>
    </ul>
</div>

<!-- Imagen fondo -->
<header class="header-inicio text-center text-white">
    <div class="bg-text">
        <div class="container">
            <h1 style="font-size: 4rem"> Reserva ya tus entradas </h1>
            <h2 style="font-size: 3rem"> y no te pierdas nada</h2>
            <a class="btn btn-primary btn-xl rounded-pill mt-5" href="#ancla">Ver eventos</a>

        </div>
    </div>
</header>

<!-- filtrado -->
<section id="ancla">
    <div class="container p-t-30">
        <div class="row">
            <div class="col-sm-10 col-md-7" style="padding: 1rem ">
                <h1 class="bg-text" style="color:#b997f6;"> Eventos disponibles: </h1>
            </div>
        </div>
        <form:form method="POST" action="/evento/filtrar" modelAtributte="eventoDTO">
            <div class="row justify-content-center">
                <div class="col-5 wrap-input2 ">
                    <form:input path="nombre" class="input2" type="text" name="buscadorNombre" placeholder="Buscar eventos por nombre y/o fecha"/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10" >
                    <form:input path="fecha" class="input2"   type="date" id="start" name="fechaInicio"/>
                </div>
                <div class="col-2 wrap-input2 wrap-separacion10" >
                    <form:input path="fechacompra" class="input2"   type="date" id="fin" name="fechaFinal"/>
                </div>
                <div class="col-2">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" value="Buscar">
                            Buscar
                        </button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</section>

<!--Eventos Disponibles:-->
<div class="container m-t-30">
    <div class="row justify-content-center m-t-10">
        <div class="col-12">
            <% if (eventosFuturos.isEmpty()) { %>
            <div class="bg-text justify-content-center text-center">
                <h1 style="color: #9e9e9e"> Actualmente no hay eventos disponibles</h1>
            </div>
            <%  } else { %>
            <table class="center table table-striped align-middle" id="tabla-custom" style="font-size:1.2rem">
                <thead>
                <tr>
                    <th>NOMBRE</th>
                    <th>DESCRIPCI&Oacute;N</th>
                    <th>CIUDAD</th>
                    <th>FECHA</th>
                    <th>PLAZAS DISPONIBLES</th>
                    <th>PRECIO</th>
                    <th>COMPRA HASTA</th>
                    <th>COMPRAR ENTRADA</th>
                </tr>
                </thead>
                <tbody>

                <%
                    for (EventoDTO ev : eventosFuturos) {
                %>
                <tr>
                    <td> <%= ev.getTitulo()%></td>
                    <td>  <%= ev.getDescripcion()%> </td>
                    <td>  <%= ev.getCiudad()%> </td>
                    <td>  <%= formato.format(ev.getFecha())%> </td>


                    <%
                        plazasDisp = ev.getAforo() - ev.getEntradasDTO().size();
                    %>
                    <td> <%=  plazasDisp == 0 ? "Aforo completo" : plazasDisp%> </td>
                    <td>  <%= new DecimalFormat("#0.00").format(ev.getPrecio())%> € </td>
                    <td>  <%
                        if (ev.getFechacompra().after(new Date())) {%>
                        <%= formato.format(ev.getFechacompra())%>
                        <% } else { %>
                        PLAZO ACABADO
                        <% }
                        %>  </td>
                    <td>  <%
                        if (ev.getFechacompra().after(new Date()) && plazasDisp > 0) {

                    %>

                        <a class="btn  btn-primary"
                           href="ServletEvento?id=<%= ev.getId()%>"> COMPRAR</a>
                        <% } else { %>
                        <a class="btn  btn-primary disabled" style="background-color:gray; border-color: gray"
                           href=""> COMPRAR</a>
                        <% }
                        %>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
            <% }
            %>
        </div>
    </div>
</div>
<div class="container p-b-100"></div>
</body>
</html>
