<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<body>
    <form:form action="/guardar" modelAttribute="a">
        Analisis: <form:input path="nombre"></form:input><br>
        Fecha superior a : <form:input path="fechamayor" type="date"></form:input><br>
        Fecha inferior a : <form:input path="fechamenor" type="date"></form:input><br>
        Precio superior a : <form:input path="preciomayor"></form:input>
        Precio inferior a : <form:input path="preciomenor"></form:input>
        Edad superior a : <form:input path="edadmayor"></form:input>
        Edad inferior a : <form:input path="edadmenor"></form:input>
        <input type="submit" value="Crear">
    </form:form>
</body>
</html>
