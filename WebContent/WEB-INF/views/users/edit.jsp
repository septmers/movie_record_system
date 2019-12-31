<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div class = "wrapper">
                <h2>Edit ${sessionScope.login_user.user_name}'s account</h2>
                <p>** Type password only when you will change it! **</p>
                <form method="POST" action="<c:url value='/users/update'/>">
                    <c:import url = "_form.jsp"/>
                </form>

        <p><a href="<c:url value = '/users/show?id=${sessionScope.login_user.id}'/>">back</a></p>
    </div>
    </c:param>
</c:import>