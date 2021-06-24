<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.dto.EventoDTO" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="es.taw.eventaw.dto.EntradaDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: Pepe
  Date: 08/06/2021
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Comprar entrada</title>

    <!-- COPIAR ESTO PARA METER CSS Y BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/util.css">
    <link rel="stylesheet" href="../../css/style.css">
</head>
<%
    EventoDTO evento = (EventoDTO) request.getAttribute("eventoDTO");
    /*UsuarioDTO user = (UsuarioDTO) session.getAttribute("userDTO");
    List<EntradaDTO> entradasUsuario = user.getUsuarioeventoDTOById().getEntradasDTO();
    int entradasLibres = evento.getAforo() - evento.getEntradasDTO().size();
    int contador = 0;

    for (EntradaDTO e : entradasUsuario) {
        if (e.getEventoDTO().equals(evento)) {
            contador++;
        }
    }

    int puedeComprar = evento.getMaxentradasusuario() - contador;*/
%>
<body>
<!-- Barra navegacion -->
<div class="topnav fixed-top">
    <ul>
        <li><a href="/inicioUEvento">Inicio</a></li>
        <li style="float:right"><a  href="/logout">Cerrar sesión</a></li>
        <li style="float:right"><a href="/usuarioEvento/perfil">Mi perfil</a></li>
        <li style="float:right"><a href="/usuarioEvento/misEntradas">MIS ENTRADAS</a></li>
    </ul>
</div>

<div class="fondo-pagina">
    <div class="container-perfil">
        <div class="wrap-registro justify-content-center text-center" >
            <form:form class="register-form" method="POST" action="/evento/aceptarPago/" modelAttribute="eventoDTO">
                <form:hidden path="id"/>
                <form:hidden path="numfilas"/>
                        <span class="bg-text" style="color: #7cc5e5">
                            <h1><%= evento.getTitulo()%></h1>
                            <h4><%= evento.getDescripcion()%></h4>
                            <h4 style="padding-top: 1%;">en <%= evento.getCiudad()%> el <%= new SimpleDateFormat("dd/MM/yyyy").format(evento.getFecha())%></h4>
                        </span>
                <hr/>
                <div class="row">
                    <p style="font-size: 1.2rem; color:black">
                        ¿Cuantas entradas desea comprar? (<%= new DecimalFormat("#0.00").format(evento.getPrecio())%>€ cada una):
                    </p>
                </div>
                <div class="row justify-content-center" >
                    <p class="m-b-10">
                        Nota: para los eventos con distribuci&oacute;n de filas <br/>tendr&aacute;s que confirmar la compra en la siguiente ventana.
                    </p>

                    <div class="col-2">
                        <form:select path="maxentradasusuario" class="custom-select text-center justify-content-center" style="padding: 5px" ame="numEntradas">
                            <form:options items="${entradas}"/>
                        </form:select>
                            <%/*
                                if (entradasLibres < puedeComprar) {
                                    puedeComprar = entradasLibres;
                                }
                                for (int i = 1; i <= puedeComprar; i++) {
                            */%>
                            <option><%// i%></option>
                            <%
                                //}
                            %>
                        </select>-->
                    </div>
                    <div class="col-2">
                        <input class="btn btn-primary" style="border-radius: 40px; background-color: #7cc5e5; border-color: #7cc5e5"
                               type="submit" value="RESERVAR" name="inscribir" />
                    </div>
                </div>

            </form:form>
        </div>
    </div>
</div>
</body>
</html>