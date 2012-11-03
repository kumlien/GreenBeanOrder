<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" %>
<html>
<head>
	<title>Green Bean Order</title>
</head>
<body>
<h1>
	Välkommen till Green Bean Order
</h1>


<form:form action="placeOrder" method="post">
	<table>
	<tr>
		<td><form:label path="productId">Produkt</form:label> </td>
		<td>
			<select name="productId">
    			<c:forEach items="${products}" var="product">
        			<option value="${product.value}">${product.key}</option>
    			</c:forEach>
			</select>
		</td>
	</tr>
	<tr><td><form:label path="qty">Antal</form:label></td><td><form:input path="qty"/></td></tr>
	<tr><td><form:label path="customerName">Ditt namn</form:label></td><td><form:input path="customerName"/></td></tr>
	<tr><td><form:label path="customerEmail">Din epost</form:label></td><td><form:input path="customerEmail"/></td></tr>
	<tr><td><input type="submit" value="Beställ!"/></td>
		<td>
			<c:if test="${success == true}">
    			<div class="msg"><spring:message code="order.confirmed" /></div>
			</c:if>
		</td>
	</tr>
	</table>
</form:form>

</body>
</html>
