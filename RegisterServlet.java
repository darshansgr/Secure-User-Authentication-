package com.secureauth.servlet;

import com.secureauth.util.DBConnection;
import com.secureauth.util.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");

        try {
            String hashedPass = PasswordUtil.hashPassword(pass);
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            pst.setString(1, uname);
            pst.setString(2, hashedPass);
            int row = pst.executeUpdate();

            if (row > 0) {
                response.sendRedirect("login.jsp");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Registration failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
