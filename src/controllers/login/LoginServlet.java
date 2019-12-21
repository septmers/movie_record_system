package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;
import utils.EncryptUtil;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();

    }

    //ログイン画面を表示
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
        rd.forward(request, response);
    }

    //ログイン処理を実行
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //認証結果を格納する変数
        Boolean check_result = false;

        String user_name = request.getParameter("user_name");
        String plain_pass = request.getParameter("password");

        User u = null;

        if(user_name != null  &&  !user_name.equals("")  &&  plain_pass != null  &&  !plain_pass.equals("")){
            EntityManager em = DBUtil.createEntityManager();

            String password = EncryptUtil.getPasswordEncrypt(plain_pass, (String)this.getServletContext().getAttribute("salt"));

            //ユーザ名とパスワードが正しいかチェックする
            try{
                u = em.createNamedQuery("checkLoginCodeAndPassword" , User.class)
                        .setParameter("user_name", user_name)
                        .setParameter("password", password)
                        .getSingleResult();
            }catch (NoResultException ex){}

            em.close();

            //入力された社員番号とパスワードに該当するデータがあった場合
            if(u != null){
                check_result= true;
            }
        }

        if(!check_result){
            //認証出来なかったらログイン画面に戻る
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("user_name", user_name);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login.jsp");
            rd.forward(request, response);

        }else{
            //認証出来たらログイン状態にしてトップページへリダイレクト
            request.getSession().setAttribute("login_user", u);

            request.getSession().setAttribute("flush", "Hello");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
