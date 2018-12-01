package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс обработки запроса на регистрацию
 */
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    // sign up
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        UserProfile userProfile = accountService.getUserByLogin(login);

        if (userProfile != null) {
            response.setContentType("txt/html;charset=utf-8");
            response.getWriter().println("Username already exist");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            userProfile = new UserProfile(login, request.getParameter("pass"));
            accountService.addNewUser(userProfile);
            response.setContentType("txt/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
