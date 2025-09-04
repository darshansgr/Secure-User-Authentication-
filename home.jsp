<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("username");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<h2>Welcome, <%= user %>!</h2>
<a href="logout">Logout</a>
