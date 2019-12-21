<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>Movies Records</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                    <h1>Movies Records</h1>&nbsp;&nbsp;&nbsp;
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id ="user_records">
                        <a href="<c:url value='/movies/index' />">my movie records</a>&nbsp;
                    </div>
                    <div id="user_name">
                        <a href="<c:url value='/users/show?id=${sessionScope.login_user.id}' />">${sessionScope.login_user.user_name}</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">Logout</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Nanami Kojima.
            </div>
        </div>
    </body>
</html>