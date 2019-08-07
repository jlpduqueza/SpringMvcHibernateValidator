<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" isErrorPage="true"%>
<%@ page errorPage = "error.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
   </head>
   <body>
      <h3>
         <c:out value = "${inventoryItem.product.name}"/>
      </h3>
      <c:out value = "${message}"/>
	  <form action="add-to-cart" method = "POST">
         Quantity (between 1 and ${inventoryItem.quantity}): 
         <input type="number" min="1" max="${inventoryItem.quantity}" name="quantity" oninput="(!validity.rangeOverflow||(value=${inventoryItem.quantity})) && (!validity.rangeUnderflow||(value=1)) &&(!validity.stepMismatch||(value=parseInt(this.value)));">
         <input type="hidden" name="inventoryItemId" value ="${inventoryItem.id}">
         <input type="submit" value = "add to cart" >
      </form>
      			

      
      <br>
      <c:set var="message" value="" scope="session"/>
      <br>
      <a href="home">Go back shopping</a>
   </body>
</html>