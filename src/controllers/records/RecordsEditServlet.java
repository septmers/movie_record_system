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
import models.User;
import utils.DBUtil;

@WebServlet("/records/edit")
public class RecordsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RecordsEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em= DBUtil.createEntityManager();

        Record record = em.find(Record.class, Integer.parseInt(request.getParameter("id")));
        List<Tag> tags = em.createNamedQuery("getTags", Tag.class)
                            .setParameter("record_id", record.getId())
                            .getResultList();

        em.close();

        User login_user = (User)request.getSession().getAttribute("login_user");
        if(login_user.getId()  == record.getUser().getId()){

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("record", record);
            request.setAttribute("tags", tags);
            request.getSession().setAttribute("record_id", record.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/edit.jsp");
        rd.forward(request, response);
    }

}
