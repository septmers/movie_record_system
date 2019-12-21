package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;

@WebServlet("/users/destroy")
public class UsersDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UsersDestroyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            User login_user = (User)request.getSession().getAttribute("login_user");
            Integer user_id = login_user.getId();

            User u = em.find(User.class, user_id);
            u.setDelete_flag(1);
            Timestamp currenTime = new Timestamp(System.currentTimeMillis());
            u.setUpdated_at(currenTime);

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.setAttribute("flush", "Deleted your account!");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);

        }
    }

}
