<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Cart</title>
   </head>
   <body>
      <table>
         <tr>
            <th>Products</th>
            <th>Subtotal Price</th>
            <th>Stock</th>
            <th>Quantity</th>
            <th>Action</th>
         <c:forEach var="cartInventoryBean" items="${cartInventoryBeanList}">
         <tr>
         <td>
         <c:out value = "${cartInventoryBean.cartItem.product.name}"/>
         </td>
         <td>
         &#8369; <c:out value = "${cartInventoryBean.cartItem.subTotalPrice}"/>
         </td>
		 <td>
         <c:out value = "${cartInventoryBean.stock}"/>
         <td> 
         <c:out value = "${cartInventoryBean.cartItem.quantity}"/>
         </td>
         <td>	
         <a href="/example/user/delete-cart-item?cartItemId=${cartInventoryBean.cartItem.id}">Delete</a> 
         <a href="/example/user/edit-cart-quantity-view?cartInventoryId=${cartInventoryBean.cartItem.id}">Edit</a> 
         </td>	
         </c:forEach>
         <tfoot>
            <tr>
               <td>Total Price :${totalPrice} </td>
            </tr>
         </tfoot>
      </table>
      <form action="checkout" method = "POST">
         <input type="Submit" value = "Check Out" >
      </form>
      <br>
      <c:out value = "${message}"/>
      <c:set var="message" value="" scope="session"/>
      <br>
      <a href="/example/user/home">Go back shopping</a> 
   </body>
</html>