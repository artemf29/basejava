<%@ page import="com.core.webapp.model.TextSection" %>
<%@ page import="com.core.webapp.model.ListSection" %>
<%@ page import="com.core.webapp.model.OrganizationSection" %>
<%@ page import="com.core.webapp.util.HtmlUtil" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><br/><img src="img/edit.png"></a>
        <a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></h2>
    <br/>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.core.webapp.model.ContactType,java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.core.webapp.model.SectionType,com.core.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.core.webapp.model.Section"/>
            <tr>
                <td><h3><a name="type.name">${type.title}</a></h3></td>
                <c:if test="${type=='OBJECTIVE'}">
                    <td>
                        <h3><%=((TextSection) section).getText()%>
                        </h3>
                    </td>
                </c:if>
            </tr>
            <c:if test="${type!='OBJECTIVE'}">
                <c:choose>
                    <c:when test="${type=='PERSONAL'}">
                        <tr>
                            <td>
                                <%=((TextSection) section).getText()%>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <tr>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="<%=((ListSection)section).getList()%>">
                                        <li>${item}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection)section).getOrganizations()%>">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty org.name.url}">
                                            <h3>${org.name.name}</h3>
                                        </c:when>
                                        <c:otherwise>
                                            <h3><a href="${org.name.url}">${org.name.name}</a></h3>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:forEach var="information" items="${org.information}">
                                <jsp:useBean id="information" type="com.core.webapp.model.Organization.Information"/>
                                <tr>
                                    <td>
                                        <%=HtmlUtil.formatDates(information)%>
                                    </td>
                                    <td>
                                        <b>${information.position}</b><br>${information.info}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:if>
        </c:forEach>
    </table>
    <p>
        <button onclick="window.history.back()">Назад</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
