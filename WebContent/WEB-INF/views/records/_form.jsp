<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test= "${errors != null}">
    <div id = "flush_error">
        **There are ERRORS ! **<br />
        <c:forEach var = "error" items = "${errors}">
            <c:out value = "${error}" /><br />
        </c:forEach>
    </div>
</c:if>

<label for = "title">Title</label><br />
<input type ="text" name="title" value="${record.title}" /><br /><br />

<label for = "genre">Genre</label>
        <select name = "genre">
        <option value="1"<c:if test="${record.genre == 1}"> selected</c:if> >Anime</option>
        <option value="2"<c:if test="${record.genre == 2}"> selected</c:if> >Love Romance</option>
        <option value="3"<c:if test="${record.genre == 3}"> selected</c:if> >Horror</option>
        <option value="4"<c:if test="${record.genre == 4}"> selected</c:if> >SF</option>
        <option value="5"<c:if test="${record.genre == 5}"> selected</c:if> >Comedy</option>
        <option value="6"<c:if test="${record.genre == 6}"> selected</c:if> >Action</option>
        <option value="7"<c:if test="${record.genre == 7}"> selected</c:if> >Documentary</option>
        <option value="8"<c:if test="${record.genre == 8}"> selected</c:if> >HumanDrama</option>
        <option value="9"<c:if test="${record.genre == 9}"> selected</c:if> >Mystery</option>
        <option value="10"<c:if test="${record.genre == 10}"> selected</c:if> >Others</option>
        </select><br /><br />

<label for = "value">Value</label>
        <select name = "value">
        <option value="1"<c:if test="${record.value == 1}"> selected</c:if> >★☆☆☆☆</option>
        <option value="2"<c:if test="${record.value == 2}"> selected</c:if> >★★☆☆☆</option>
        <option value="3"<c:if test="${record.value == 3}"> selected</c:if> >★★★☆☆</option>
        <option value="4"<c:if test="${record.value == 4}"> selected</c:if> >★★★★☆</option>
        <option value="5"<c:if test="${record.value == 5}"> selected</c:if> >★★★★★</option>
        </select><br /><br />

<label for = "impression">Impression</label><br />
<textarea name = "impression" rows = "10" cols = "50">${record.impression}</textarea><br />

<label for = "mylife">MyLife</label><br />
<textarea name = "mylife" rows = "10" cols = "50">${record.mylife}</textarea><br />

<label for = "keyword">Keywords</label><br />
<textarea name="keyword" rows = "10" cols = "50">
    <c:choose>
        <c:when test="${tags != null}">
            <c:forEach var = "tag" items = "${tags}">#${tag.tag}</c:forEach>
        </c:when>
        <c:otherwise>
            #keyword
        </c:otherwise>
    </c:choose>
</textarea><br />


<input type ="hidden"  name="_token"  value="${_token}" />
<input type = "submit" class="button" value ="create">