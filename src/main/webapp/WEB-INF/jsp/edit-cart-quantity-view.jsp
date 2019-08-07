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
      <h3>
         <c:out value = "${cartItem.product.name}"/>
      </h3>
      <c:out value = "${message}"/>
      <form action="edit-cart-quantity" method = "GET">
         Quantity (between 1 and ${cartInventoryBean.stock}): 
         <input type="number" min="1" max="${cartInventoryBean.stock}" name="cartItemQuantity" oninput="(!validity.rangeOverflow||(value=${cartInventoryBean.stock})) && (!validity.rangeUnderflow||(value=1)) &&(!validity.stepMismatch||(value=parseInt(this.value)));">
         <input type="hidden" name="cartItemId" value ="${cartInventoryBean.cartItem.id}">
         <input type="submit" value = "save" >
      </form>
      <br>
      <c:set var="message" value="" scope="session"/>
      <br>
      <a href="home">Go back shopping</a>
   </body>
</html>