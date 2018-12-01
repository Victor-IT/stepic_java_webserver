package dbService.dao;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet getUserById(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public UsersDataSet getUserByLogin(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public Map<String, UsersDataSet> getAllUsers() throws SQLException {
        return executor.execQuery("select * from users", result -> {
            Map<String, UsersDataSet> users = new HashMap<>();
            while (result.next()) {
                String login = result.getString(2);
                UsersDataSet usersDataSet = new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
                users.put(login, usersDataSet);
            }
            return users;
        });
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("select * from users where login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(UsersDataSet usersDataSet) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + usersDataSet.getLogin() + "', '" + usersDataSet.getPassword() + "')");
    }

    public void deleteUser(String login) throws SQLException {
        executor.execUpdate("delete users where login=" + login);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
