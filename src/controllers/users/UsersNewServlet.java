package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;


@WebServlet("/users/new")
public class UsersNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UsersNewServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        request.setAttribute("user", new User());
        request.setAttribute("_token", request.getSession().getId());

        em.close();
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
        rd.forward(request, response);
    }

}
