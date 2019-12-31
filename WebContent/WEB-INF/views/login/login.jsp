<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url = "/WEB-INF/views/layout/app.jsp">
    <c:param name = "content">
    <div class = "wrapper">
        <c:if test = "${hasError}">
            <div id="flush_error">
                username or password has error!
            </div>
        </c:if>
        <c:if test="${flush != null}">
            <div id = "flush_success">
                <c:out value = "${flush}" />
            </div>
        </c:if>
            <form method="POST" action="<c:url value = '/login'/>">
                <label for = "user_name">User Name</label><br />
                <input type = "text" name = "user_name" value="${user_name}">
                <br /><br />
                <label for = "password">Password</label><br />
                <input type = "password" name = "password">
                <br /><br />
                <input type="hidden" name="_token" value="${_token}">
                <input type = "submit" class="button" value ="login">
            </form>

            <form method="GET" action="<c:url value = '/users/new' />">
                <input type = "submit" class="button" value ="new account">
            </form>
    </div>
    </c:param>
</c:import>