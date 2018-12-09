package servlets;

import accounts.AccountService;

import javax.servlet.http.HttpServlet;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }
}
