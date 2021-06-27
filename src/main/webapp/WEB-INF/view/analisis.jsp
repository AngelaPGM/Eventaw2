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
    <div class="topnav fixed-top">
        <ul>
            <li style="float:right"><a href="/analisis/logout">Cerrar sesión</a></li>
        </ul>
    </div>
    <div class="fondo-pagina">
        <div class="row justify-content-around">
            <div class="container-perfil">
                <form:form action="/analisis/guardar" modelAttribute="analisis">
                    <form:hidden path="id"></form:hidden>
                    <h1>Analisis <form:input path="nombre" class="input2"></form:input></h1>
                    <h2>Mostraremos datos para eventos con</h2>
                    Fecha superior a: <form:input path="fechamayor" type="date" class="input2"></form:input><br>
                    Fecha inferior a: <form:input path="fechamenor" type="date" class="input2"></form:input><br>

                    Precio superior a: <form:input path="preciomayor" class="input2"></form:input><br>
                    Precio inferior a: <form:input path="preciomenor" class="input2"></form:input><br>

                    <h2>a los que asistirán usuarios con</h2>

                    Fecha nacimiento superior a: <form:input path="nacimientomayor" type="date" class="input2"></form:input><br>
                    Fecha nacimiento inferior a: <form:input path="nacimientomenor" type="date" class="input2"></form:input><br>

                    <div>
                        Sexo:
                        <%
                            if(a.getSexo() == null){
                        %>
                        H <form:radiobutton path="sexo" value="H"/>
                        M <form:radiobutton path="sexo" value="M"/>
                        Ambos <form:radiobutton path="sexo" value="N" checked="checked"/><br>
                        <%
                        }else{
                            switch (a.getSexo()){
                                case "H":
                        %>
                        H <form:radiobutton path="sexo" value="H" checked="checked"/>
                        M <form:radiobutton path="sexo" value="M"/>
                        Ambos <form:radiobutton path="sexo" value="N"/><br>
                        <%
                                break;
                            case "M":
                        %>
                        H <form:radiobutton path="sexo" value="H"/>
                        M <form:radiobutton path="sexo" value="M" checked="checked"/>
                        Ambos <form:radiobutton path="sexo" value="N"/><br>
                        <%
                                        break;
                                }
                            }
                        %>

                    </div>

                    <input class="btn btn-primary btn-xl rounded-pill mt-0" type="submit" value="Guardar">
                </form:form>
            </div>
            <div class="row justify-content-around">
                <form:form method="get" action="/analisis/">
                    <input class="btn btn-primary btn-xl rounded-pill mt-0" type="submit" value="Salir">
                </form:form>
            </div>
            <div class="row justify-content-around">
                <h3>Estadísticas</h3>
                <br>
                <%
                    if(listaEntradas != null){
                %>
                Entradas mostradas: <%=listaEntradas.size()%>/<%=totales%> (<%= (totales==0)?0:listaEntradas.size()/totales *100%>%)<br>
                Hombres: <%= estadistico[0]%>/<%=listaEntradas.size()%> (<%= (listaEntradas.size()==0)?0:estadistico[0]/listaEntradas.size() *100%>%).
                Mayores de edad: <%=estadistico[1]%>/<%= estadistico[0]%> (<%= (estadistico[0]==0)?0:estadistico[1]/estadistico[0] *100%>%)<br>

                Mujeres: <%= estadistico[2]%>/<%=listaEntradas.size()%> (<%= (listaEntradas.size()==0)?0:estadistico[2]/listaEntradas.size() *100%>%).
                Mayores de edad: <%=estadistico[3]%>/<%= estadistico[2]%> (<%= (estadistico[3]==0)?0:estadistico[3]/estadistico[2] *100%>%)<br>

                <table border="1" class="center table table-striped align-middle" id="tabla-custom">
                    <tr>
                        <th>ENTRADA</th>
                        <th>FECHA EVENTO</th>
                        <th>PRECIO EVENTO</th>
                        <th>NACIMIENTO</th>
                        <th>SEXO</th>
                    </tr>
                    <%
                        for(EntradaDTO e: listaEntradas){
                    %>
                    <tr>
                        <td><%= e.getId()%></td>
                        <td><%= e.getEventoDTO().getFecha()%></td>
                        <td><%= e.getEventoDTO().getPrecio()%></td>
                        <td><%= e.getUsuarioeventoDTO().getFechanacimiento() %></td>
                        <td><%= e.getUsuarioeventoDTO().getSexo() %></td>
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
    </div>
</body>
</html>
