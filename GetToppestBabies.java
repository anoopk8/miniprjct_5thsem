
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


public class GetToppestBabies extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>Get top babies</title>\n" +
                    
      "<style>\n" +
     "a:link {\n" +
"    color: green;\n" +
"    background-color: transparent;\n" +
"    text-decoration: none;\n" +
"}\n" +
"a:visited {\n" +
"    color:maroon;\n" +
"    background-color: transparent;\n" +
"    text-decoration: none;\n" +
"}\n" +
"a:hover {\n" +
"    color:cyan;\n" +
"    background-color: transparent;\n" +
"    text-decoration: underline;\n" +
"}\n" +
"a:active {\n" +
"    color:red;\n" +
"    background-color: transparent;\n" +
"    text-decoration: underline;\n" +
"}\n"+
                    "</style>\n"+

                    
"    </head>\n" +
"    \n" +
"    <body bgcolor=\"orange\"><center>");
    
           
            
            
        ResultSet rs=null;
         PreparedStatement ps=null;
        Connection con=null;
       
           
            String yrs=request.getParameter("yr");
            String top=request.getParameter("topbabies");
            String type=request.getParameter("grp");
         
         
           Class.forName("oracle.jdbc.driver.OracleDriver");
          con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
           if(!type.equals("bth"))
          ps=con.prepareStatement("select * from "+type+""+yrs+" where rownum<= "+top);
           else
           ps=con.prepareStatement("select Given_name,amount from(select given_name,amount from MA"+yrs+" union select Given_name,amount from FE"+yrs+" order by amount desc)where rownum<="+top);    
          rs=ps.executeQuery();
            
          out.println(" <h2><marquee behavior='alternate' scrolldelay='30'>Top Babies List</marquee></h2>\n" +
         "<b>=========================================================================================================================================</b><br>");
           out.println("<h2><em>Name and their Amount(Highest Amount denotes their highest position)</em></h2>");  
          out.println("<textarea rows='25' cols='100' style='background-color:orange;text-align: center; font-size:20px; border:none'>"); 
         
            while(rs.next())
          {
              
           out.println(rs.getString(1)+"                 "+rs.getString(2));
          }   
          out.println("</textarea><br>");
           out.println("</center>\n" +           
" <center><b><a href=\"tbabies.jsp\" target=\"_blank\">Top Babies List</a>&nbsp;&nbsp;||&nbsp;&nbsp;<a href=\"graphform.jsp\" >Birth Graph</a></b></center>\n"+
"    </body>\n" +
"</html>");
    }
    catch(Exception e){}
        
       
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

