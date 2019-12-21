package controllers.users;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;


@WebServlet("/users/create")
public class UsersCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsersCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            User u = new User();
            u.setName(request.getParameter("name"));
            u.setBirthday(Date.valueOf(request.getParameter("birthday")));
            u.setSex(Integer.parseInt(request.getParameter("sex")));
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            u.setCreated_at(currentTime);
            u.setUpdated_at(currentTime);
            u.setUser_name(request.getParameter("user_name"));
            u.setDelete_flag(0);

            String password = request.getParameter("password");
            u.setPassword(EncryptUtil.getPasswordEncrypt(password,(String)this.getServletContext().getAttribute("salt")));

            //バリデーションを実行
            List<String> errors = UserValidator.validate(u, true, true);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("errors", errors);
                request.setAttribute("user", u);
                request.setAttribute("_token", request.getSession().getId());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/new.jsp");
                rd.forward(request, response);

            }else{
                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "Create new account!");

                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }

}
