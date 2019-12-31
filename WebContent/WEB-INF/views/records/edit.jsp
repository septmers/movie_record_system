<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url = "/WEB-INF/views/layout/app.jsp">
    <c:param name= "content">
    <div class = "wrapper">
        <c:choose>
            <c:when test = "${record != null}">
                <h2>Edit Record Info</h2>
                <form method = "POST" action = "<c:url value = '/records/update' />">
                    <c:import url = "_form.jsp" />
                </form>
            </c:when>
            <c:otherwise>
                <h2>Couldn't find the page...</h2>
            </c:otherwise>
        </c:choose>

        <p><a href = "<c:url value = '/' />">Back to Top</a></p>
        </div>
    </c:param>
</c:import>