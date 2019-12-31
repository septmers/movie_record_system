package controllers.records;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import models.Tag;

@WebServlet("/records/new")
public class RecordsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public RecordsNewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Record record = new Record();
        Tag tag = new Tag();

        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("record", record);
        request.setAttribute("tag", tag);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/new.jsp");
        rd.forward(request, response);
    }

}
