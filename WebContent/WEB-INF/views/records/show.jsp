<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url = "../layout/app.jsp">
    <c:param name = "content">
    <div class = "wrapper">
        <c:if test = "${flush != null}">
            <div id = "flush_success">
                <c:out value = "${flush}"/>
            </div>
        </c:if>
        <c:choose>
            <c:when test = "${record != null}">
                <h2>Record Detail Page</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>Title</th>
                            <td><c:out value = "${record.title}" /></td>
                        </tr>
                        <tr>
                        <tr>
                            <th>Value</th>
                            <td>
                                <c:choose>
                                    <c:when test = "${record.value == 1}">
                                        <p>★☆☆☆☆</p>
                                    </c:when>
                                    <c:when test = "${record.value == 2}">
                                        <p>★★☆☆☆</p>
                                    </c:when>
                                    <c:when test = "${record.value == 3}">
                                        <p>★★★☆☆</p>
                                    </c:when>
                                    <c:when test = "${record.value == 4}">
                                        <p>★★★★☆</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>★★★★★</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Register Date</th>
                            <td><fmt:formatDate value = "${record.created_at}" pattern = "yyyy-MM-dd hh:MM:ss" /></td>
                        </tr>
                        <tr>
                            <th>User Name</th>
                            <td><c:out value = "${record.user.user_name}" /></td>
                        </tr>
                        <tr>
                            <th>User Sex</th>
                            <td>
                                <c:choose>
                                    <c:when test = "${record.user.sex == 1}">
                                        <p>Male</p>
                                    </c:when>
                                    <c:when test = "${record.user.sex == 2}">
                                        <p>Female</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Others</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Impression</th>
                            <td><c:out value = "${record.impression}" /></td>
                        </tr>
                        <tr>
                            <th>Keyword</th>
                            <td>
                                <c:forEach var = "tag" items = "${tags}">
                                <a href = "<c:url value = '/tagged/records/index?id=${tag.id}' />">${tag.tag}</a>
                                </c:forEach>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <h2>Couldn't find the page...</h2>
            </c:otherwise>
        </c:choose>

        <div class = "control">
                    <div id = "record_edit">
                    <c:if test = "${sessionScope.login_user.id == record.user_id}">
                        <p><a class="button" href = "<c:url value = '/records/edit?id=${record.id}' />">Edit</a>&nbsp;</p>
                    </c:if>
                    </div>
                    <div id = "record_delete">
                    <c:if test = "${sessionScope.login_user.id == record.user_id}">
                        <form name = "form" method="POST" action="<c:url value='/records/destroy'/>">
                        <input type="hidden" name="_token" value="${_token}"/>
                        <input type="hidden" name="record_id" value ="${record.id}" />
                        <input type = "submit" class="button" value ="delete" onclick="confirmDestroy()">
                        </form>
                        <script>
                        function confirmDestroy(){
                            if(confirm("Are you sure to delete this record？")){
                                document.form.submit();
                                }
                            }
                        </script>
                    </c:if>
                    </div>
                </div>
    </div>
    </c:param>
</c:import>