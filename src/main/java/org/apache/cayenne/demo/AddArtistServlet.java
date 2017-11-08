package org.apache.cayenne.demo;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.web.WebUtil;
import org.example.cayenne.persistent.Artist;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddArtistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<html><body>");
        pw.println("<form method=\"post\" action =\"" + req.getContextPath() + "\" >");
        pw.println("First name: <input type=\"text\" name=\"name\"><br>\n" +
                "  <input type=\"submit\" value=\"Add artist\">");
        pw.println("</form>");
        pw.println("</body></html>");

        pw.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,java.io.IOException{

        String name = req.getParameter("name");
        BaseContext.bindThreadObjectContext(WebUtil.getCayenneRuntime(getServletContext()).getContext());
        ObjectContext context = BaseContext.getThreadObjectContext();
        if(!name.isEmpty()) {
            Artist picasso = context.newObject(Artist.class);
            picasso.setName(name);
            context.commitChanges();

            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html><head>");
            out.println("<title>SUCCESS</title></head><body>");
            out.println("<p> SUCCESS </p>");
            out.println("<button><a href=\"http://192.168.1.65:8080\" >Main Page </a></button><br>");
            out.println("</body></html>");
            out.close();
        }
    }
}