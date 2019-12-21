<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url = "/WEB-INF/views/layout/app.jsp">
    <c:param name = "content">
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
        <h2>ログイン</h2>
        <form method="POST" action="<c:url value = '/login'/>">
            <label for = "user_name">User Name</label><br />
            <input type = "text" name = "user_name" value="${user_name}">
            <br /><br />
            <label for = "password">パスワード</label><br />
            <input type = "password" name = "password">
            <br /><br />

            <input type="hidden" name="_token" value="${_token}">
            <button type="submit">Login</button>
        </form>
        <form method="GET" action="<c:url value = '/users/new' />">
            <button type="submit">New Account</button>
        </form>
    </c:param>
</c:import>