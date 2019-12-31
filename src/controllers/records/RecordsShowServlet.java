package controllers.records;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import models.Tag;
import utils.DBUtil;

@WebServlet("/records/show")
public class RecordsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RecordsShowServlet() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Integer record_id = Integer.parseInt(request.getParameter("id"));
        Record record = em.find(Record.class, record_id);
        List<Tag> tags = em.createNamedQuery("getTags", Tag.class)
                           .setParameter("record_id", record_id)
                           .getResultList();

        request.setAttribute("record", record);
        request.setAttribute("tags", tags);
        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/show.jsp");
        rd.forward(request, response);
    }

}
