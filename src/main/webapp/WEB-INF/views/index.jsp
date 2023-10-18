<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>테스트</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.5.0/axios.min.js" integrity="sha512-aoTNnqZcT8B4AmeCFmiSnDlc4Nj/KPaZyB5G7JnOnUEkdNpCZs1LCankiYi01sLTyWy+m2P+W4XM+BuQ3Q4/Dg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<div>
    <ul>
        <li><a href="/index?${LANG_CODE_QUERY}">index</a> </li>
        <li><a href="/about?${LANG_CODE_QUERY}">about</a> </li>
    </ul>
    <h1>INDEX</h1>
    <div><spring:message code="language" /></div>
    <div><spring:message code="welcome" arguments="${welcomeContext}" /></div>

    <script>
        if (confirm('[테스트] 회원을 등록하시겠습니까?')) {
            var member = {
                memberId: 'test1',
                password: '1234',
                name: '테스터1234',
                age: 3,
                gender: 'M',
                email: 'test1234@naver.com'
            };

            console.log(member);
            axios.post('/api/v1/common/member', member)
                .then(function (response) {
                    console.log(response);
                })
                .catch (function (err) {
                    console.error(err);
                });
        }

    </script>
</div>
</body>
</html>
