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

public class AddPaintingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<html><body>");
        pw.println("<form method=\"post\" action =\"" + req.getContextPath() + "\" >");
        pw.println("Painting tittle: <input type=\"text\" name=\"tittle\"><br>\n" +
                "Artist: <input type=\"text\" name=\"artist\"> <br> <input type=\"submit\" value=\"Add painting\">");
        pw.println("</form>");
        pw.println("</body></html>");


        pw.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head>");
        out.println("<title>ADD</title></head><body>");

        String tittle = req.getParameter("tittle");
        String artist = req.getParameter("artist");

        BaseContext.bindThreadObjectContext(WebUtil.getCayenneRuntime(getServletContext()).getContext());
        ObjectContext context = BaseContext.getThreadObjectContext();
        if (!tittle.isEmpty() && !artist.isEmpty()) {
            List<Artist> artists = ObjectSelect.query(Artist.class).select(context);
            Artist art = null;
            for (Artist a : artists) {
                if (a.getName().equals(artist)) {
                    art = a;
                }
            }
            if (art != null) {
                Painting painting = context.newObject(Painting.class);
                painting.setTittle(tittle);
                painting.setArtist(art);
                context.commitChanges();
                out.println("<p> SUCCESS </p>");
            } else {
                out.println("<p> FAIL </p>");
            }

        }
        out.println("<button><a href=\"http://192.168.1.65:8080\" >Main Page </a></button><br>");
        out.println("</body></html>");
        out.close();
    }
}