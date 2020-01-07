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
import models.User;
import utils.DBUtil;

@WebServlet("/myrecords/index")
public class MyRecordsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MyRecordsIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        User login_user = (User)request.getSession().getAttribute("login_user");
        Integer user_id = login_user.getId();

        List <Record> records = em.createNamedQuery("getMyRecords", Record.class)
                                  .setParameter("user_id", user_id)
                                  .setFirstResult(5 * (page-1))
                                  .setMaxResults(5)
                                  .getResultList();

        Long record_count = em.createNamedQuery("getMyRecordsCount", Long.class)
                                .setParameter("user_id", user_id)
                                .getSingleResult();

        request.setAttribute("page", page);
        request.setAttribute("records", records);
        request.setAttribute("record_count", record_count);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/my_index.jsp");
        rd.forward(request, response);
    }

}
