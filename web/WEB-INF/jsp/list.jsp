<%@ page import="com.core.webapp.model.ContactType" %>
<%@ page import="com.core.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th>Достижения</th>
            <th>Изменить</th>
            <th>Удалить</th>
        </tr>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="com.core.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a>
                </td>
                <td>
                        ${resume.getContact(ContactType.MAIL)}
                </td>
                <td>
                    <%=SectionType.ACHIEVEMENT.toHtml(resume.getSection(SectionType.ACHIEVEMENT))%>
                </td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a>
                </td>
                <td>
                    <a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>