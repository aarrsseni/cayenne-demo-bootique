package org.apache.cayenne.demo;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.web.WebUtil;
import org.apache.cayenne.query.ObjectSelect;
import org.example.cayenne.persistent.Artist;
import org.example.cayenne.persistent.Painting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body>");

        BaseContext.bindThreadObjectContext(WebUtil.getCayenneRuntime(getServletContext()).getContext());
        ObjectContext context = BaseContext.getThreadObjectContext();
        ObjectSelect<Artist> object1 = ObjectSelect.query(Artist.class).localCache("cache1");
        List<Artist> artists = object1.select(context);

        pw.println("<p><br>");
        for (Artist a : artists){
            pw.println(a.getName() + "<br>");
            List<Painting> paintings = a.getPainting();
            for(Painting p : paintings){
                pw.println(" - " + p.getTittle() + "<br>");
            }
        }
        pw.println("</p><br>");

        pw.println("<button><a href=\"http://192.168.1.65:8080\" >Main Page </a></button><br>");
        pw.println("</body></html>");

        pw.close();
    }
}