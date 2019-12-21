package controllers.records;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import models.Tag;
import models.TagMap;
import models.User;
import models.validators.RecordValidator;
import utils.AgeUtil;
import utils.DBUtil;

/**
 * Servlet implementation class RecordsCreateServlet
 */
@WebServlet("/records/create")
public class RecordsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RecordsCreateServlet() {
        super();
    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Record r = new Record();

            User login_user = (User)request.getSession().getAttribute("login_user");
            r.setUser(login_user);
            Integer user_id = login_user.getId();
            r.setUser_id(user_id);
            String title = request.getParameter("title");
            r.setTitle(title);
            Integer genre = Integer.parseInt(request.getParameter("genre"));
            r.setGenre(genre);
            Integer value = Integer.parseInt(request.getParameter("value"));
            r.setValue(value);
            String impression = request.getParameter("impression");
            r.setImpression(impression);
            String mylife = request.getParameter("mylife");
            r.setMylife(mylife);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            r.setCreated_at(currentTime);
            r.setUpdated_at(currentTime);
            r.setDelete_flag(0);

            AgeUtil age = new AgeUtil();
            Date now = new Date(System.currentTimeMillis());
            Date birthday = login_user.getBirthday();
            int user_age = age.calcAge(birthday, now);
            r.setUser_age(user_age);


            String keywords = request.getParameter("keyword");
            Pattern pattern = Pattern.compile("^#\\w\\s$");
            Matcher matcher = pattern.matcher(keywords);
            List<String> tags = new ArrayList<String>();
            while(matcher.find()){
                tags.add(matcher.group());
            }

            List<String> errors = RecordValidator.validate(r);
            if(errors.size() >0){    //入力エラーがあった場合
                request.setAttribute("newrecord", r);
                request.setAttribute("tags", tags);
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
                rd.forward(request, response);

            }else{                  //入力エラーがなかった場合
                Tag t = new Tag();
                TagMap tm = new TagMap();
                Iterator<String> it = tags.iterator();
                while(it.hasNext()){
                    String tag = it.next();
                    Tag registered_tag = null;
                    try{
                        registered_tag = em.createNamedQuery("getRegisteredTag", Tag.class)
                                            .setParameter("tag", tag)
                                            .getSingleResult();
                    }catch(NoResultException ex){}

                    if(registered_tag == null){    //
                        t.setTag(tag);
                        t.setCreated_at(currentTime);
                        tm.setRecord_id(r.getId());
                        tm.setTag_id(t.getId());
                        tm.setCreated_at(currentTime);

                        em.getTransaction().begin();
                        em.persist(t);
                        em.persist(tm);
                        em.getTransaction().commit();
                    }else{
                        tm.setRecord_id(r.getId());
                        tm.setTag_id(t.getId());
                        tm.setCreated_at(currentTime);

                        em.getTransaction().begin();
                        em.persist(tm);
                        em.getTransaction().commit();
                    }
            }
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/");
            }
        }
    }
}
