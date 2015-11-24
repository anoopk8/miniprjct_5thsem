package controllers;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetBabyGraph extends HttpServlet {

    boolean flag=false;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"        <title>Baby Graph</title>\n" +
"    </head>\n" +
"    \n" +
" <link rel=\"stylesheet\" href=\"//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css\">\n" +
" <script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js\"></script>\n" +
" <script src=\"//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js\"></script>\n" +
" <script src=\"//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js\"></script>\n" +
"\n" +
" \n" +
"    <style>\n" +
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
"}\n" +

"#id {\n" +
"    background: url(img/by1.jpg);\n" +
"    background-size:1380px 800px;\n" +
"    background-repeat: no-repeat;\n" +
"    background-attachment:fixed; \n" +
"} \n" +
"    </style>\n" +
"    <body id=\"id\" >\n" +
"");
        
         String name=request.getParameter("naam");
         
         String year=request.getParameter("yr");
         String gen=request.getParameter("gender");
         int maxyr=2013;
         int yrs=Integer.parseInt(year);
            
         PreparedStatement ps=null;
         Connection con;
         ResultSet rs=null;
     
         Class.forName("oracle.jdbc.driver.OracleDriver");
         con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
         out.println("<marquee behavior='alternate' scrolldelay='40'><center><h2 style='color:yellow'><i>Graphical representation of Baby "+name+" from year "+yrs+" to 2013  </h2></center></marquee>");
      
out.println("<div id=\"myfirstchart\" style=\"height: 500px; background-color:orange;\"></div>\n" +
"         <script>\n" +
"        new Morris.Line({\n" +
"  // ID of the element in which to draw the chart.\n" +
"  element: 'myfirstchart',\n" +
"  // Chart data records -- each entry in this array corresponds to a point on\n" +
"  // the chart.\n" +
"  data: [\n" +
"");

   while(yrs<=maxyr)
         {
         flag=false;
         ps=con.prepareStatement("select * from "+gen+""+yrs+" where Given_Name='"+name+"'");
         rs=ps.executeQuery();
         if(rs.next())

         out.println("{ year:'"+yrs+"', value:"+rs.getString(3)+"},");
         
            
           yrs++;
         rs=null; 
            
         }
   
        
         
      out.println("],\n" +
"  // The name of the data record attribute that contains x-values.\n" +
"  xkey: 'year',\n" +
"  // A list of names of data record attributes that contain y-values.\n" +
"  ykeys: ['value'],\n" +
"  // Labels for the ykeys -- will be displayed when you hover over the\n" +
"  // chart.\n" +
"  labels: ['Value']\n" +
"});\n" +
"        \n" +
              
   
"        </script>\n" +
"      \n" +
"        \n" +
              
"        \n" +
"    </body>\n" +
"    \n" +
"</html>\n" +
""); 
      if(flag==true)
   {
   // out.println("<center><h2 style='color:red'><em>No data found for the given name</em></h2></center>");  
    }
   out.println("<center><b><a href=\"tbabies.jsp\" target=\"_blank\">Top Babies List</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href=\"graphform.jsp\" >Birth Graph</a></b></center>");  
   
         }
         
         catch(Exception e){
         
         }     
         
         
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
