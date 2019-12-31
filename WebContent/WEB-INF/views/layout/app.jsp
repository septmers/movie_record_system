<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Movies Records</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
        <link href="https://fonts.googleapis.com/css?family=Courier+Prime&display=swap" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <header class="page-header wrapper">
                <h1>Movies Records</h1>
                <nav>
                    <ul class = "main-nav">
                       <c:if test="${sessionScope.login_user != null}">
                           <a class = "button" href ="<c:url value = '/records/new' />">+Add Record</a>&nbsp;
                           <a class = "button" href="<c:url value='/myrecords/index' />">My Records</a>&nbsp;
                           <a class = "button" href="<c:url value='/users/show?id=${sessionScope.login_user.id}' />">${sessionScope.login_user.user_name}</a>&nbsp;
                           <a class = "button" href="<c:url value='/logout' />">Logout</a>
                       </c:if>
                    </ul>
               </nav>
            </header>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Nanami Kojima  &nbsp;
                <c:if test="${sessionScope.login_user != null}">
                    | &nbsp;&nbsp;
                    <a href ="<c:url value = '/' />">TopPage</a>
                </c:if>
            </div>
        </div>
    </body>
</html>