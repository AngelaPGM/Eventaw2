<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.taw.eventaw.dto.ConversacionDTO" %>
<%@ page import="es.taw.eventaw.dto.MensajeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.taw.eventaw.dto.UsuarioDTO" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User:
  Date:
  Time:
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Conversacion</title>
</head>
<%
    UsuarioDTO u = (UsuarioDTO) request.getAttribute("userDTO");
    ConversacionDTO c = (ConversacionDTO) request.getAttribute("conversacion");
    List<MensajeDTO> mensajes = (List<MensajeDTO>) request.getAttribute("mensajesConversacion");
%>

<body>
    <form:form modelAttribute="conversacion" >
    <%
        if(u.getId() == c.getTeleoperadorConversacion().getId() || u.getId() == c.getUsuarioConversacion().getId()){

    %>

    <form:hidden path="id" name = "id"></form:hidden>
    Mensaje:<form:input path="mensajesById.contenido" name = "message"></form:input>
    <form:hidden path="usuarioConversacion.correo" name = "name"></form:hidden>
    <a type="button" onclick="postMessage();" value="Enviar" />
    <%
        }
        <% if (application.getAttribute("messages") != null) {%>
         <%= application.getAttribute("messages")%>
        <% }%>
    %>
    <script>
        function postMessage() {
            var xmlhttp = new XMLHttpRequest();
            //xmlhttp.open("POST", "shoutServlet?t="+new Date(), false);
            xmlhttp.open("POST","shoutServlet", false);
            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            var idText = escape(document.getElementById("id").value);
            var nameText = escape(document.getElementById("name").value);
            var messageText = escape(document.getElementById("message").value);
            document.getElementById("message").value = "";
            xmlhttp.send("id=" + idText + "&name=" + nameText + "&message=" + messageText);
        }
        var messagesWaiting = false;
        function getMessages() {
            if (!messagesWaiting) {
                messagesWaiting = true;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                        messagesWaiting = false;
                        var contentElement = document.getElementById("content");
                        contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                    }
                }
                //xmlhttp.open("GET", "shoutServlet?t="+new Date(), true);
                xmlhttp.open("GET", "/conversacion/shoutServlet", true);
                xmlhttp.send();
            }
        }
        setInterval(getMessages, 1000);
    </script>
      </form:form>
</body>

</html>