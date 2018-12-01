package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.sql.SQLException;
import java.util.Map;

public class AccountService {
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public void addNewUser(UsersDataSet usersDataSet) throws DBException {
        System.out.println(dbService.addUser(usersDataSet));
    }

    public UsersDataSet getUserById(long id) throws DBException {
        return dbService.getUser(id);
    }

    public UsersDataSet getUserByLogin(String login) throws DBException {
        return dbService.getUser(login);
    }

    public void deleteUser(String login) throws SQLException {
        dbService.deleteUser(login);
    }

    public Map<String, UsersDataSet> getAllUsers() throws SQLException {
        return dbService.getAllUsers();
    }
}
