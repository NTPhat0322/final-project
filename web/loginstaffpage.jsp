<%-- 
    Document   : loginstaffpage
    Created on : Feb 18, 2025, 11:12:57 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainServlet" accept-charset="UTF-8">
            <label for="staff_name">Staff name</label>
            <p><input type="text" id="staff_name" name="txtname" placeholder="enter staff's name" required>*</p>
            <p><input type="submit" value="login" onclick="setHiddenValue()"></p>
            <input type="hidden" name="action" value="loginStaff">
        </form>
        <div>
            <%
                if(request.getAttribute("result") != null) {
                    out.print(request.getAttribute("result"));
                }
            %>
        </div>
    </body>
</html>
