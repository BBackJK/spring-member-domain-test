<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>테스트</title>
</head>
<body>
<div>
    <ul>
        <li><a href="/index?${LANG_CODE_QUERY}">index</a> </li>
        <li><a href="/about?${LANG_CODE_QUERY}">about</a> </li>
    </ul>

    <h1>ABOUT</h1>

    <div><spring:message code="language" /></div>
    <div><spring:message code="welcome" arguments="${welcomeContext}" /></div>
</div>
</body>
</html>
