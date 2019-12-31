package controllers.records;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import utils.DBUtil;


@WebServlet("/records/destroy")
public class RecordsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public RecordsDestroyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){

            EntityManager em = DBUtil.createEntityManager();

            Integer record_id = Integer.parseInt(request.getParameter("record_id"));
            Record r = em.find(Record.class, record_id);
            r.setDelete_flag(1);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setUpdated_at(currentTime);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "Deleted a record");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
