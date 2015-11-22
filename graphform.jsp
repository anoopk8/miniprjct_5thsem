<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Search popularity graph</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
 <style>
    #ide {
    background: url(img/by2.jpg);
    background-size:1380px 800px;
    background-repeat: no-repeat;
} 
</style>
    <body id="ide"><center>
    
         <font color="maroon"><h2>Baby Report</h2>
        <form action="GetBabyGraph" method="post">
           <b>Baby Name</b><input type="text" name="naam"><br><br>
            &nbsp;&nbsp;&nbsp;<b>From Year</b><input type="text" name="yr"><br><br>
           <b>Sex</b><select name="gender">
                <option value="ma">Male</option>
                <option value="fe">Female</option>
            </select><br><br>
            <input type="submit" value="Go">
        </form>
          </font>
    </center>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    <%@include file="footer.html"%>
    </body>
</html>
