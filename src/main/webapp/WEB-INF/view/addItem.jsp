<%@ page import="javax.lang.model.element.Element" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=\"en\">
<head>
    <title>Select goods</title>
    <style type="text/css">
        #greetingStyle {
            margin-left: 10%;
            margin-right: 10%;
            background: #fc0;
            padding: 10px;
            text-align: center;
        }
    </style>
    <style type="text/css">
        #formStyle {
            margin-left: 10%;
            margin-right: 10%;
            background: #01DF01;
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
<div id="greetingStyle">
    <c:set var="userName" scope="session" value="${name}"/>
    <h3>Hello ${userName}!</h3>
</div>

<c:set var="orderList" scope="session" value="${order.getGoods()}"/>
<div id="formStyle">

    <!-We print it if order is empty-!>
    <c:if test="${empty orderList}">
        <p>Make your order</p>
    </c:if>
    <!-We print it if order contains goods-!>
    <c:if test="${not empty orderList}">
        <p>You have already chosen:</p>
    </c:if>
    <c:forEach items="${orderList}" var="pair">
        <p>${pair['key']} x ${pair['value']}</p>
    </c:forEach>

    <form action="goodsAddServlet" method="post">
        <select name="good">
            <c:set var="allGoodsMap" scope="session" value="${allGoodsList}"/>
            <option>--Choose item--</option>
            <c:forEach items="${allGoodsMap}" var="entry">
                <option>${entry.getName()} (${entry.getPrice()} $)</option>
                <br/>
            </c:forEach>
        </select>
        <p>
            <button type="submit" name="button" value="add">Add Item</button>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp;
            <button type="submit" name="button" value="submit">Submit</button>
        </p>
    </form>
</div>
</body>
</html>


