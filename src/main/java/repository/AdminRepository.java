package repository;

import org.hibernate.SessionFactory;

import java.sql.Connection;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AdminRepository {
    SessionFactory factory;
    public AdminRepository(SessionFactory factory)
    {

    }
    public boolean login(String username, String password) {
        return true;
    }
}
