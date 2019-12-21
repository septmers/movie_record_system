<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test= "${errors != null}">
    <div id = "flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var = "error" items = "${errors}">
            <c:out value = "${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for = "user_name">User name</label><br />
<input type ="text" name="user_name" value="${user.user_name}" />
<br /><br />
<label for = "password">password</label><br />
<input type = "password" name="password" />
<br /><br />
<label for ="name">Name</label><br />
<input type="text" name="name" value="${user.name}" />
<br /><br />
<label for = "birthday">Birthday</label><br />
<input type = "date" name = "birthday" value = "<fmt:formatDate value = '${user.birthday}' pattern = 'yyyy-MM-dd' />" />
<br /><br />
<label for = "sex">Sex</label><br />
<select name = "sex">
    <option value="0"<c:if test="${user.sex == 0}"> selected</c:if>>Male</option>
    <option value="1"<c:if test="${user.sex == 1}"> selected</c:if> >Female</option>
    <option value="2"<c:if test="${user.sex == 2}"> selected</c:if> >Others</option>
</select>
<br /><br />

<input type ="hidden"  name="_token"  value="${_token}" />
<button type="submit">create</button>