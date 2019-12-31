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

@WebServlet("/tagged/records/index")
public class TaggedRecordsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TaggedRecordsIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        List <Record> records = em.createNamedQuery("getTaggedRecords", Record.class)
                                 .setParameter("tag_id", Integer.parseInt(request.getParameter("id")))
                                 .getResultList();
        long records_count = (long)em.createNamedQuery("getTaggedRecordsCount", Long.class)
                                      .setParameter("tag_id", Integer.parseInt(request.getParameter("id")))
                                      .getSingleResult();

        Tag tag = em.find(Tag.class, Integer.parseInt(request.getParameter("id")));
        String tag_name = tag.getTag();

        request.setAttribute("records", records);
        request.setAttribute("tag_name", tag_name);
        request.setAttribute("records_count", records_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/tagged_index.jsp");
        rd.forward(request, response);
    }
}
