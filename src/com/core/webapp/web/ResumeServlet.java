package com.core.webapp.web;

import com.core.webapp.Config;
import com.core.webapp.model.*;
import com.core.webapp.storage.Storage;
import com.core.webapp.util.DateUtil;
import com.core.webapp.util.HtmlUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        final boolean isCreate = (uuid == null || uuid.length() == 0);
        Resume resume;
        if (isCreate) {
            resume = new Resume(fullName);
        } else {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        }
        for (ContactType contact : ContactType.values()) {
            String value = request.getParameter(contact.name());
            if (HtmlUtil.isEmpty(value)) {
                resume.getContacts().remove(contact);
            } else {
                resume.setContact(contact, value);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                resume.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE, PERSONAL -> resume.setSection(type, new TextSection(value));
                    case ACHIEVEMENT, QUALIFICATIONS -> resume.setSection(type, new ListSection(value.split("\\n")));
                    case EDUCATION, EXPERIENCE -> {
                        List<Organization> organizations = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                List<Organization.Information> informational = new ArrayList<>();
                                String pfx = type.name() + i;
                                String[] start = request.getParameterValues(pfx + "start");
                                String[] end = request.getParameterValues(pfx + "end");
                                String[] positions = request.getParameterValues(pfx + "position");
                                String[] inform = request.getParameterValues(pfx + "info");
                                for (int k = 0; k < start.length; k++) {
                                    if (!HtmlUtil.isEmpty(start[k])) {
                                        informational.add(new Organization.Information(
                                                DateUtil.parse(start[k]), DateUtil.parse(end[k]), positions[k], inform[k]));
                                    }
                                }
                                organizations.add(new Organization(new Link(name, urls[i]), informational));
                            }
                        }
                        resume.setSection(type, new OrganizationSection(organizations));
                    }
                }
            }
        }
        if (isCreate) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete" -> {
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> resume = storage.get(uuid);
            case "add" -> resume = Resume.EMPTY;
            case "edit" -> {
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    Section section = resume.getSection(type);
                    switch (type) {
                        case OBJECTIVE, PERSONAL -> {
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                        }
                        case ACHIEVEMENT, QUALIFICATIONS -> {
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                        }
                        case EXPERIENCE, EDUCATION -> {
                            OrganizationSection orgSection = (OrganizationSection) section;
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY);
                            if (orgSection != null) {
                                for (Organization organization : orgSection.getOrganizations()) {
                                    List<Organization.Information> emptyFirstPositions = new ArrayList<>();
                                    emptyFirstPositions.add(Organization.Information.EMPTY);
                                    emptyFirstPositions.addAll(organization.getInformation());
                                    emptyFirstOrganizations.add(new Organization(organization.getName(), emptyFirstPositions));
                                }
                            }
                            section = new OrganizationSection(emptyFirstOrganizations);
                        }
                    }
                    resume.setSection(type, section);
                }
            }
            default -> throw new IllegalArgumentException("Action" + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
                .forward(request, response);
    }
}
