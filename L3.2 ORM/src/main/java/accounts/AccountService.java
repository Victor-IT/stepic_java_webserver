package accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import java.util.List;

public class AccountService {
    private final DBService dbService;

    public AccountService(DBService dbService) {
        this.dbService = dbService;
    }

    public Long addNewUser(UsersDataSet dataSet) throws DBException {
        return dbService.addUser(dataSet);
    }

    public List<UsersDataSet> getAll() throws DBException {
        return dbService.getAll();
    }

    public UsersDataSet getUser(String login) throws DBException {
        return dbService.getUser(login);
    }
}
