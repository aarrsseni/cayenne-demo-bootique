package org.apache.cayenne.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StartPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        pw.println("<html><body>");
        pw.println("<button><a href=\"http://192.168.1.65:8080/show\" >Show </a></button><br>");
        pw.println("<button><a href=\"http://192.168.1.65:8080/addArtist\" >Add artist </a></button><br>");
        pw.println("<button><a href=\"http://192.168.1.65:8080/addPainting\" >Add painting </a></button><br>");
        pw.println("</body></html>");

        pw.close();
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,java.io.IOException{
    }
}
