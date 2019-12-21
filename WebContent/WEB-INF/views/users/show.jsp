<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name ="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value = "${flush}"></c:out>
            </div>
        </c:if>
                <h2>${user.user_name}'s account info</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>User Name</th>
                            <td><c:out value = "${user.user_name}" /></td>
                        </tr>
                        <tr>
                            <th>Name</th>
                            <td><c:out value = "${user.name}"/></td>
                        </tr>
                        <tr>
                            <th>Birthday</th>
                            <td><fmt:formatDate value = "${user.birthday}" pattern="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <th>Sex</th>
                            <td>
                                <c:choose>
                                    <c:when test="${user.sex == 0}">Male</c:when>
                                    <c:when test="${user.sex == 1}">Female</c:when>
                                    <c:otherwise>Others</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value = '/users/edit'/>">Edit</a></p>
                <p><a href="#" onclick="confirmDestroy();">Delete</a></p>
                <form method="POST" action="<c:url value='/users/destroy'/>">
                    <input type="hidden" name="_token" value="${_token}"/>
                </form>
                <script>
                    function confirmDestroy(){
                        if(confirm("Are you sure to delete this account?")){
                            document.forms[1].submit();
                        }
                    }
                </script>

        <P><a href="<c:url value='/'/>">back</a></P>
    </c:param>
</c:import>