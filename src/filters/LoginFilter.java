package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;



@WebFilter("/*")
public class LoginFilter implements Filter {


    public LoginFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!servlet_path.matches("/css.*") && !servlet_path.equals("/users/new") && !servlet_path.equals("/users/create")){  //CSSフォルダ内は認証処理から除外する
            HttpSession session = ((HttpServletRequest)request).getSession();

            //セッションスコープに保存された従業員（ログインユーザ）情報を取得
            User u = (User)session.getAttribute("login_user");

            if(!servlet_path.equals("/login")){    //ログイン画面以外
                //ログアウトしている状態　または　削除済みのユーザであれば
                //ログイン画面にリダイレクト
                if(u == null || u.getDelete_flag() == 1){
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }

            }else{    //ログイン画面にて
                if(u != null){      //既にログインしている場合
                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
