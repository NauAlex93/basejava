package ru.javawebinar.basejava.web;

import com.mysql.cj.util.StringUtils;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        boolean isResumeToAdd = StringUtils.isNullOrEmpty(uuid);

        if (isResumeToAdd) {
            r = new Resume(fullName);
        }
        else
        {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value == null || value.trim().length() == 0) {
                r.getContacts().remove(type);
            } else {
                r.addContact(type, value);
            }
        }
        for (SectionType type : SectionType.values()) {

            if (type == SectionType.EXPERIENCE || type == SectionType.EDUCATION)
            {
                break;
            }

            String value = request.getParameter(type.name());
            if (value == null || value.trim().length() == 0) {
                r.getSections().remove(type);
            } else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.addSection(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(type, new ListSection(Arrays.asList(value.split("\\n"))));
                        break;
                }
            }
        }

        if (isResumeToAdd)
        {
            storage.save(r);
        }
        else
        {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null)
        {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }

        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                resume = storage.get(uuid);
                break;
            case "edit":
                resume = storage.get(uuid);
                for (SectionType type : SectionType.values()) {

                    AbstractSection section = resume.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY_TEXT_SECTION;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY_LIST_SECTION;
                            }
                            break;
                        case EDUCATION:
                        case EXPERIENCE:
                            if (section == null) {
                                section = new CareerSection(Collections.singletonList(Career.EMPTY_CAREER_SECTION));
                            }
                            break;
                    }
                    resume.addSection(type, section);
                }
                break;
            case "add":
                resume = Resume.EMPTY_RESUME;
                resume.addSection(SectionType.OBJECTIVE, TextSection.EMPTY_TEXT_SECTION);
                resume.addSection(SectionType.PERSONAL, TextSection.EMPTY_TEXT_SECTION);
                resume.addSection(SectionType.ACHIEVEMENT, ListSection.EMPTY_LIST_SECTION);
                resume.addSection(SectionType.QUALIFICATIONS, ListSection.EMPTY_LIST_SECTION);
                resume.addSection(SectionType.EXPERIENCE, new CareerSection(Collections.singletonList(Career.EMPTY_CAREER_SECTION)));
                resume.addSection(SectionType.EDUCATION, new CareerSection(Collections.singletonList(Career.EMPTY_CAREER_SECTION)));
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

}
