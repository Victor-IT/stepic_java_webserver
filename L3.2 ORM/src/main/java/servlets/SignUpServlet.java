package servlets;

import accounts.AccountService;
import dbService.DBException;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс обработки запроса на регистрацию
 */
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UsersDataSet usersDataSet = null;
        try {
            usersDataSet = accountService.getUser(login);
        } catch (DBException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=utf-8");
        if (usersDataSet != null || password == null) {
            response.getWriter().println("Username already exist");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            usersDataSet = new UsersDataSet(login, password);
            try {
                accountService.addNewUser(usersDataSet);
            } catch (DBException e) {
                e.printStackTrace();
            }
            response.getWriter().println("User added: " + login);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
