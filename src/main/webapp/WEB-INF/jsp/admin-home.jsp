<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
   </head>
   <body>
      <h1>Hello <c:out value = "${username}"/>!</h1>
      <c:out value = "${message}"/>
      <table id="example">
         <tr>
            <th>Products</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Action</th>
            <c:forEach var="inventoryItem" items="${inventoryItemList}">
         <tr>
         <td>
         <c:out value = "${inventoryItem.product.name}"/>
         </td>
         <td>
         &#8369;<c:out value = "${inventoryItem.product.price}"/>
         </td>
         <td>	
         <c:out value = "${inventoryItem.quantity}"/>
         <td>
         <a href="/example/admin/edit-stock?inventoryItemId=${inventoryItem.id}">Edit stock</a>			
         <a href="/example/admin/delete-stock?inventoryItemId=${inventoryItem.id}">Delete</a>		
         </td>
         </tr>
         </c:forEach>
      </table>
      <c:set var="message" value="" scope="session"/>
<a href="/example/logout">Logout</a> 
   </body>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js">
      $(document).ready(function() {
      
         $('#example tr').click(function() {
             var href = $(this).find("a").attr("href");
             if(href) {
                 window.location = href;
             }
         });
      
      });
   </script>
</html>