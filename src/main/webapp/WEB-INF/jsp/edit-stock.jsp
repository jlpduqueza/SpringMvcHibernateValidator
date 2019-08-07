<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" isErrorPage="true"%>
<%@ page errorPage = "error.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
   </head>
   <body>
      <h3><c:out value = "${inventoryItem.product.name}"/></h3>
      <c:out value = "${message}"/>
      <form action="edit-stock" method = "POST">
         <input type="number" min="0" name="quantity" value ="${inventoryItem.quantity}" >
         <input type="hidden" name="inventoryItemId" value = "${inventoryItem.id}">
         <input type="submit" value = "Add" onclick="javascript: form.action='add-stock';" >	
         <input type="submit" value = "Update" onclick="javascript: form.action='update-stock';" >
      </form>
      <br>
      <c:set var="message" value="" scope="session"/>
      <br>
      <a href="/example/user/admin-home">Go back</a>
   </body>
</html>