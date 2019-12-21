package controllers.toppage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import utils.recordDao;


@WebServlet("/index.html")
public class topPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public topPageIndexServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer genre = null;
        Integer value = null;
        Integer ages  = null;
        //「0」･･･Under 10
        //「1」･･･10代
        //「2」･･･20代
        //「3」･･･30代
        //「4」･･･40代
        //「5」･･･50代
        //「6」･･･60代
        //「7」･･･Over 70
        Integer sex = null;
        String keyword = null;
        try{
            genre = Integer.parseInt(request.getParameter("genre"));
            value = Integer.parseInt(request.getParameter("value"));
            ages = Integer.parseInt(request.getParameter("ages"));
            sex = Integer.parseInt(request.getParameter("sex"));
            keyword = request.getParameter("keyword");
        }catch(Exception e){}

        recordDao dao = new recordDao();
        List<Record> records = new ArrayList<Record>();
        try{
            records = dao.getRecordExtraction(genre, value, ages, sex, keyword);
        }catch(NullPointerException e){}

        EntityManager em = DBUtil.createEntityManager();

        Iterator<Record> it = records.iterator();
        while(it.hasNext()){
            Record record = it.next();
            Integer user_id = record.getUser_id();
            User u = em.createNamedQuery("getUsers", User.class)
                       .setParameter("id", user_id)
                       .getSingleResult();
            try{
                record.setUser(u);
            }catch(NullPointerException e){}
        }
        em.close();

        request.setAttribute("records", records);
        request.setAttribute("genre", genre);
        request.setAttribute("value", value);
        request.setAttribute("ages", ages);
        request.setAttribute("sex", sex);
        request.setAttribute("keyword", keyword);
        request.setAttribute("_token", request.getSession().getId());

        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request, response);
    }

}
