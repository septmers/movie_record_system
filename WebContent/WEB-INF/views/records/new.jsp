<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div class = "wrapper">
        <h2>Register New Record</h2>
            <form method="POST" action="<c:url value='/records/create' />">
            <c:import url="_form.jsp" />
            </form>
    </div>
    </c:param>
</c:import>