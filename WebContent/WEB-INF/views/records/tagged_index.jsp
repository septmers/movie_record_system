<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
    <div class = "wrapper">
        <h2>#${tag_name}：${record_count}件</h2>
        <table id = "record_list">
            <tbody>
                <tr>
                    <th class = "record_user">UserName</th>
                    <th class = "record_age">Age</th>
                    <th class = "record_sex">Sex</th>
                    <th class = "record_title">Title</th>
                    <th class = "record_genre">Genre</th>
                    <th class = "record_value">Value</th>
                    <th class = "record_view">View</th>
                </tr>
                <tr>
                    <c:forEach var = "record" items = "${records}" varStatus = "status">
                        <tr class ="row${status.count % 2}">
                            <td class = "record_user"><c:out value = "${record.user.user_name}" /></td>
                            <td class = "record_age"><c:out value = "${record.user_age}" /></td>
                            <td class = "record_sex">
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
                            <td class = "record_title"><c:out value = "${record.title}" /></td>
                            <td class = "record_genre">
                                <c:choose>
                                    <c:when test = "${record.genre == 1}">
                                        <p>Anime</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 2}">
                                        <p>Love Romance</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 3}">
                                        <p>Horror</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 4}">
                                        <p>SF</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 5}">
                                        <p>Comedy</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 6}">
                                        <p>Action</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 7}">
                                        <p>Documentary</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 8}">
                                        <p>HumanDram</p>
                                    </c:when>
                                    <c:when test = "${record.genre == 9}">
                                        <p>Mystery</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Others</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class = "record_value">
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
                            <td><a class="button" href = "<c:url value = '/records/show?id=${record.id}' />">view</a></td>
                        </tr>
                    </c:forEach>
                </tr>
            </tbody>
        </table>

        <div id="pagination">
            <c:forEach var ="i" begin="1" end="${((record_count - 1) / 5) + 1}">
                <c:choose>
                    <c:when test = "${i == page}">
                        <c:out value = "${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href = "<c:url value = '/?page=${i}' />"><c:out value = "${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </div>
    </c:param>
</c:import>