package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                   "<head>\n" +
                   "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                   "<title>Resumes</title>\n" +
                   "</head>\n" +
                   "<body>\n" +
                   "<br>" +
                   "<h2 align =\"center\">Resumes</h2>" +
                   "<table class=\"table\" align =\"center\"  border=\"1\">\n" +
                   "<tr>\n" +
                   "<th>Name</th>\n" +
                   "<th>Contacts</th>\n" +
                   "<th>Personal</th>\n" +
                   "<th>Objective</th>\n" +
                   "<th>Achievement</th>\n" +
                   "<th>Qualifications</th>\n" +
                   "</tr>\n" +
                   "</body>\n" +
                   "</html>");

        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "<td>" + resume.getFullName() + "</td>\n" +
                            "<td>" + "Phone: " + resume.getContact(ContactType.PHONE) + "<br>" +
                            "Skype: " + resume.getContact(ContactType.SKYPE) + "<br>" +
                            "Mail: " + resume.getContact(ContactType.MAIL) + "<br>" +
                            "LinkedIn: " + resume.getContact(ContactType.LINKEDIN) + "<br>" +
                            "GitHub: " + resume.getContact(ContactType.GITHUB) + "<br>" +
                            "StackOverflow: " + resume.getContact(ContactType.STACKOVERFLOW) + "<br>" +
                            "HomePage: " + resume.getContact(ContactType.HOME_PAGE) + "</td>\n" +
                            "<td>" + resume.getSection(SectionType.PERSONAL) + "</td>\n" +
                            "<td>" + resume.getSection(SectionType.OBJECTIVE) + "</td>\n" +
                            "<td>" + resume.getSection(SectionType.ACHIEVEMENT) + "</td>\n" +
                            "<td>" + resume.getSection(SectionType.QUALIFICATIONS) + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" +
                "</section>\n" +
                "</body>\n" +
                "</html>\n");
    }

}
