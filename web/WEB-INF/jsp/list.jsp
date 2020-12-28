<%@ page import="com.core.webapp.model.Resume" %>
<%@ page import="java.util.List" %>
<%@ page import="com.core.webapp.model.ContactType" %>
<%@ page import="com.core.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <th>Achievement</th>
            <th></th>
        </tr>
        <%
            for (Resume resume : (List<Resume>) request.getAttribute("resumes")) {
        %>
        <tr>
            <td><a href="resume?uuid=<%=resume.getUuid()%>"><%=resume.getFullName()%></a>
            </td>
            <td><%=resume.getContact(ContactType.MAIL)%>
            </td>
            <td><%=resume.getSection(SectionType.ACHIEVEMENT)%>
            </td>
        </tr>
        <%}%>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>