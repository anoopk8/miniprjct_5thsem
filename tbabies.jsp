<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get top babies</title>
    </head>
    
    <body bgcolor="magenta"><center>
        <%
            ResultSet rs=null;
            PreparedStatement ps=null;
            String yrs=request.getParameter("yr");
            String top=request.getParameter("topbabies");
            String type=request.getParameter("grp");
           
           try{ 
           Class.forName("oracle.jdbc.driver.OracleDriver");
          Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
           if(!type.equals("bth"))
          ps=con.prepareStatement("select * from "+type+""+yrs+" where rownum<= "+top);
           else
           ps=con.prepareStatement("select Given_name,amount from(select given_name,amount from MA"+yrs+" union select Given_name,amount from FE"+yrs+" order by amount desc)where rownum<="+top);    
          rs=ps.executeQuery();}
           catch(Exception e){
           
           }
          %>
          <h2>TOP BABIES</h2>
          <b>------------------------------------------------------------------------</b><br>
          <%
          while(rs.next())
          {
              
            out.println("<b><i>"+rs.getString(1)+"</b></i><br>");
          }
          %>
 
    </center>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
       <%@include file="footer.html"%> 
    </body>
</html>
