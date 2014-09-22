<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="sun.org.mozilla.javascript.internal.ast.ForInLoop"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<link rel="stylesheet" type="text/css" href="css/tyyli.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzahallinta</title>
</head>
<body>

<div id="container" style="width:820px">

<div id="header" style="width:820px;height:80px;">
<p>Pizzahallinta</p>
<p> <c:out value="${aloitusaika}" /></p>
</div>

<div id="content" style="width:820px;height:270px;">
<table class="fixed";style="width:810px">
 	<col width="250px" />
    <col width="50px" />
    <col width="200px" />
    <col width="50px" />
    <col width="200px" />

<c:forEach items="${Pizzat}" var="item">
	<tr>
	<td></td>
    <td>${item.getId()}</td>
	<td>${item.getNimi()}</td>
	<td><c:out value="${item.hinta}"/></td>
	<td></td>  
    <td><form action="poista" method="post">
    <!--  <input type="image" src="img/cross.PNG" alt="Submit Form" name='id' value = "${item.getId()}" /> -->
    <input type="hidden" name="id" value="${item.getId()}" />
    <button type="submit">poista</button>
    </form></td>
  	</tr>
  	</c:forEach>
</table>
</div>

<div id="addtext" style="width:80px;height:80px;">
<p>Lisää uusi</p>
</div>

<div id="add" style="width:820px;height:80px;">
<form action="controller" name="message" method="post">
    <ul class="form">
        <li class="twoColumnPart">
            <label for="nimi">Nimi</label>
            <input id="nimi" type="text" value="" name="nimi">
        </li>
        <li class="twoColumnPart">
            <label for="hinta">Hinta</label>
            <input id="hinta" type="text" value="" name="hinta">
        </li>
    </ul>
   <input type="submit" value="Submit">
</form>
</div>

<div id="footer" style="width:820px;height:170px;">
<p><a href="img/Admin.png">Linkki rautalankamalliin</a> </p>
</div>

</body>
</html>