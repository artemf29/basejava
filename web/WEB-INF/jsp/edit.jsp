<%@ page import="com.core.webapp.model.ContactType" %>
<%@ page import="com.core.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.core.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <d1>
            <d2>Имя:</d2>
            <d3><input type="text" name="fullName" size="50" value="${resume.fullName}"></d3>
        </d1>
        <h3>Контакты:</h3>
        <p>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <d1>
                    <d2>${type.contact}</d2>
                    <d3><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></d3>
                </d1>
            </c:forEach>
        </p>
        <h3>Секции:</h3>
        <p>
            <c:forEach var="type" items="<%=SectionType.values()%>">
                <d1>
                    <d2>${type.title}</d2>
                    <d3><input type="text" name="${type.name()}" size="30" value="${resume.getSection(type)}"></d3>
                </d1>
            </c:forEach>
        </p>
        <button type="submit">Сохранить изменения</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
