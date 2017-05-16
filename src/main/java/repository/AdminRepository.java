package repository;

import model.Admin;
import model.CM;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class AdminRepository implements CRUDRepository {
    private SessionFactory factory;
    public AdminRepository(SessionFactory factory)
    {
        this.factory = factory;
    }
    public boolean login(String username, String password) {
        boolean ret = false;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM Admin where username = :username and password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Admin> adminList = query.list();
            if (!adminList.isEmpty())
                ret = true;
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ret;
    }
}
