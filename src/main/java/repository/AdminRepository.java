package repository;

import java.sql.Connection;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AdminRepository {
    Connection connection;
    public AdminRepository(Connection connection) {
        this.connection = connection;
    }
    public boolean login(String username, String password) {
        return true;
    }
}
