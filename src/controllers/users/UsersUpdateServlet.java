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

/**
 * Servlet implementation class UsersUpdateServlet
 */
@WebServlet("/users/update")
public class UsersUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UsersUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            User login_user = (User)request.getSession().getAttribute("login_user");
            Integer user_id = login_user.getId();
            User u = em.find(User.class, user_id);

            //現在の値と異なるユーザー名が入力されていたら
            //重複チェックを行う
            //現在の値と同じ社ユーザー名を入力されていたら
            //値をセットする
            Boolean user_name_duplicate_check_flag = true;
            String user_name = request.getParameter("user_name");
            if(u.getUser_name().equals(user_name)){
                user_name_duplicate_check_flag = false;
            }else{
                u.setUser_name(user_name);
            }

            //パスワード欄に入力があったら
            //パスワード欄の入力値チェックを行う。
            Boolean password_chack_flag = true;
            String password = request.getParameter("password");
            if(password ==null || password.equals("")){
                password_chack_flag= false;
            }else{
                u.setPassword(EncryptUtil.getPasswordEncrypt(password, (String)this.getServletContext().getAttribute("salt")));
            }

            String name = request.getParameter("name");
            u.setName(name);
            String birthday = request.getParameter("birthday");
            Integer sex = Integer.parseInt(request.getParameter("sex"));
            u.setSex(sex);
            Timestamp currentTime= new Timestamp(System.currentTimeMillis());
            u.setUpdated_at(currentTime);

            List<String> errors = UserValidator.validate(u, user_name_duplicate_check_flag, password_chack_flag, birthday);
            if(errors.size() > 0){
                em.close();
                request.setAttribute("user", u);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/users/edit.jsp");
                rd.forward(request, response);
            }else{
                u.setBirthday(Date.valueOf(request.getParameter("birthday")));
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "Updated your account info");
                response.sendRedirect(request.getContextPath() + "/users/show?id=" + user_id);
            }
        }
    }

}
