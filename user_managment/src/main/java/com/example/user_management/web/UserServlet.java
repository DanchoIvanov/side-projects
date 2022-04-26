package com.example.user_management.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.user_management.dao.UserDAO;
import com.example.user_management.model.User;

@WebServlet(urlPatterns = {"/"})
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserDAO userDAO;

    public UserServlet() throws SQLException {
        this.userDAO = new UserDAO();
    }

    public void init() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch(action) {
                case "/update":
                    this.updateUser(request, response);
                    return;
                case "/new":
                    this.showNewForm(request, response);
                    return;
                case "/edit":
                    this.showEditForm(request, response);
                    return;
                case "/delete":
                    this.deleteUser(request, response);
                    return;
                case "/insert":
                    this.insertUser(request, response);
                    return;
                default:
                    this.listUser(request, response);
                    return;
            }
        } catch (SQLException var5) {
            throw new ServletException(var5);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> listUser = this.userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = this.userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthdate"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        User newUser = new User(firstName, lastName, birthDate, phoneNumber, email);
        this.userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthdate"));
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        User user = new User(id, firstName, lastName, birthDate, phoneNumber, email);
        this.userDAO.updateUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
