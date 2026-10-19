<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Persona</title>
</head>
<body>
 <h1>Editar Persona</h1>
 <form action="personas" method="post">
  <input type="hidden" name="opcion" value="editar">
  <input type="hidden" name="id" value="<c:out value="${persona.id}"/>">

  <table border="1">
   <tr>
    <td>Nombre:</td>
    <td><input type="text" name="nombre" size="50" value="<c:out value="${persona.nombre}"/>"></td>
   </tr>
   <tr>
    <td>Apellidos:</td>
    <td><input type="text" name="apellidos" size="50" value="<c:out value="${persona.apellidos}"/>"></td>
   </tr>
   <tr>
    <td>Edad:</td>
    <td><input type="number" name="edad" size="50" value="<c:out value="${persona.edad}"/>"></td>
   </tr>
   <tr>
    <td>Email:</td>
    <td><input type="email" name="email" size="50" value="<c:out value="${persona.email}"/>"></td>
   </tr>
  </table>
  <br>
  <input type="submit" value="Actualizar">
 </form>
 <br>
 <a href="index.jsp">Volver al Menú Principal</a>
</body>
</html>