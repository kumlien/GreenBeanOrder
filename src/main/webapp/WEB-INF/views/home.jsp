<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Green Bean Order</title>
</head>
<body>
<h1>
	Welcome to Green Bean Order form
</h1>


<form:form action="placeOrder" method="post">
	<select name="productId">
    	<c:forEach items="${products}" var="product">
        	<option value="${product.value}">${product.key}</option>
    	</c:forEach>
	</select>
	<form:input path="qty"/>
	<input type="submit" value="Hit me!"/>
</form:form>

</body>
</html>
