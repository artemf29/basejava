<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="com.core.webapp.model.ContactType" %>
<%@ page import="com.core.webapp.model.SectionType" %>
<%@ page import="com.core.webapp.model.ListSection" %>
<%@ page import="com.core.webapp.model.OrganizationSection" %>
<%@ page import="com.core.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.core.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="/fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <d1>
            <d2>Имя:</d2>
            <d3><input type="text" name="fullName" size="50" value="${resume.fullName}"></d3>
        </d1>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <d1>
                <d2>${type.contact}</d2>
                <d3><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></d3>
            </d1>
        </c:forEach>
        <hr>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="com.core.webapp.model.Section"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type="text" name="${type}" size=75 value="<%=section%>">
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name="${type}" cols="75" rows="5"><%=section%></textarea>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <textarea name="${type}" cols="75"
                              rows="5"><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization" items="<%=((OrganizationSection)section).getOrganizations()%>"
                               varStatus="counter">
                        <d1>
                            <d2>Название</d2>
                            <d3><input type="text" name="${type}" size="100" value="${organization.name.name}"></d3>
                        </d1>
                        <d1>
                            <d2>Сайт</d2>
                            <d3><input type="text" name="${type}url" size="100" value="${organization.name.url}"></d3>
                        </d1>
                        <br>
                        <div style="margin-left: 30px">
                            <c:forEach var="information" items="${organization.information}">
                                <jsp:useBean id="information" type="com.core.webapp.model.Organization.Information"/>
                                <d1>
                                    <d2>Начальная дата:</d2>
                                    <d3><input type="text" name="${type}${counter.index}start" size="10"
                                               value="<%=DateUtil.format(information.getStart())%>"
                                               placeholder="MM/yyyy"></d3>
                                </d1>
                                <d1>
                                    <d2>Дата окончания:</d2>
                                    <d3><input type="text" name="${type}${counter.index}end" size="10"
                                               value="<%=DateUtil.format(information.getEnd())%>" placeholder="MM/yyyy">
                                    </d3>
                                </d1>
                                <br>
                                <d1>
                                    <d2>Позиция:</d2>
                                    <d3><input type="text" name="${type}${counter.index}position" size="73"
                                               value="${information.position}"></d3>
                                </d1>
                                <d1>
                                    <d2>Информация:</d2>
                                    <d3><textarea name="${type}${counter.index}info" rows="5"
                                                  cols="75">${information.info}</textarea></d3>
                                </d1>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <button type="submit">Сохранить изменения</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="/fragments/footer.jsp"/>
</body>
</html>
