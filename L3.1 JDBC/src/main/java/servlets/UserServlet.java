package servlets;

import accounts.AccountService;
import com.google.gson.Gson;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private final AccountService accountService;

    public UserServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    // get public user profile
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, UsersDataSet> userList = null;
        try {
            userList = accountService.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson(userList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    // unregister
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsersDataSet usersDataSet = null;
        try {
            usersDataSet = accountService.getUserByLogin(request.getParameter("login"));
        } catch (DBException e) {
            e.printStackTrace();
        }
        if (usersDataSet == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("User not found");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            accountService.deleteUser(usersDataSet.getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
