<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <div class = "wrapper">
        <c:if test="${flush != null}">
            <div id= "flush_success">
                <c:out value = "${flush}"/>
            </div>
        </c:if>

        <div id = "search">
        <form method = "GET" action = "<c:url value = '/' />">

        <label for = "genre">Genre</label>
        <select name = "genre">
        <option value="0"<c:if test="${genre == 0}"> selected</c:if> >All</option>
        <option value="1"<c:if test="${genre == 1}"> selected</c:if> >Anime</option>
        <option value="2"<c:if test="${genre == 2}"> selected</c:if> >Love Romance</option>
        <option value="3"<c:if test="${genre == 3}"> selected</c:if> >Horror</option>
        <option value="4"<c:if test="${genre == 4}"> selected</c:if> >SF</option>
        <option value="5"<c:if test="${genre == 5}"> selected</c:if> >Comedy</option>
        <option value="6"<c:if test="${genre == 6}"> selected</c:if> >Action</option>
        <option value="7"<c:if test="${genre == 7}"> selected</c:if> >Documentary</option>
        <option value="8"<c:if test="${genre == 8}"> selected</c:if> >HumanDrama</option>
        <option value="9"<c:if test="${genre == 9}"> selected</c:if> >Mystery</option>
        <option value="10"<c:if test="${genre == 10}"> selected</c:if> >Others</option>
        </select>

        <label for = "value">Value</label>
        <select name = "value">
        <option value="0"<c:if test="${value == 0}"> selected</c:if> >All</option>
        <option value="1"<c:if test="${value == 1}"> selected</c:if> >★☆☆☆☆</option>
        <option value="2"<c:if test="${value == 2}"> selected</c:if> >★★☆☆☆</option>
        <option value="3"<c:if test="${value == 3}"> selected</c:if> >★★★☆☆</option>
        <option value="4"<c:if test="${value == 4}"> selected</c:if> >★★★★☆</option>
        <option value="5"<c:if test="${value == 5}"> selected</c:if> >★★★★★</option>
        </select>

        <label for = "ages">Ages</label>
        <select name = "ages">
        <option value="0" <c:if test="${ages == 0}"> selected</c:if> >All</option>
        <option value="1" <c:if test="${ages == 1}"> selected</c:if> >Under 20</option>
        <option value="2" <c:if test="${ages == 2}"> selected</c:if> >20's</option>
        <option value="3" <c:if test="${ages == 3}"> selected</c:if> >30's</option>
        <option value="4" <c:if test="${ages == 4}"> selected</c:if> >40's</option>
        <option value="5" <c:if test="${ages == 5}"> selected</c:if> >50's</option>
        <option value="6" <c:if test="${ages == 6}"> selected</c:if> >60's</option>
        <option value="7" <c:if test="${ages == 7}"> selected</c:if> >Over 70</option>
        </select>

        <label for = "sex">Sex</label>
        <select name = "sex">
        <option value="0" <c:if test="${sex == 0}"> selected</c:if> >All</option>
        <option value="1" <c:if test="${sex == 1}"> selected</c:if> >Male</option>
        <option value="2" <c:if test="${sex == 2}"> selected</c:if> >Female</option>
        <option value="3" <c:if test="${sex == 3}"> selected</c:if> >Others</option>
        </select>

        <label for = "keywords">Keywords</label>
        <input type ="text" name="keywords" value = "${keyword}"/>

        <input type = "submit" class="button" value ="search">
        </form>
        </div>

        <p>${records_count}hit!</p>

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
                            <td><a class= "button" href = "<c:url value = '/records/show?id=${record.id}' />">view</a></td>
                        </tr>
                    </c:forEach>
                </tr>
            </tbody>
        </table>

        <div id="pagination">
            <c:forEach var ="i" begin="1" end="${((records_count - 1) / 5) + 1}">
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