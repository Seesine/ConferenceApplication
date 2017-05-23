package repository;

import model.Attendant;
import model.Conference;
import model.Sections;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin on 4/6/2017.
 */
public class AttendantRepository implements CRUDRepository {
    private SessionFactory factory;

    public AttendantRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public boolean login(String username, String password) {
        Transaction tx = null;
        boolean ret = false;
        Session ses = factory.openSession();
        try {
            tx = ses.beginTransaction();
            Query query = ses.createQuery("FROM Attendant WHERE username = :username AND password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Attendant> attendantList = query.list();
            if (!attendantList.isEmpty()) {
                ret = true;
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            ses.close();
        }
        return ret;
        //return false;
    }

    public void save(String username, String password) {
        Transaction tx = null;
        boolean ret = false;
        Session ses = factory.openSession();
        try {
            tx = ses.beginTransaction();
            Query query = ses.createNativeQuery("INSERT INTO Attendant (username, password) VALUES (:username, :password)");
            query.setParameter("username", username);
            query.setParameter("password", password);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            ses.close();
        }
    }


    public List<Sections> findByConfId(int idC) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Sections> sectList = new ArrayList<Sections>();
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("FROM Sections where idConference = :idConference");
            query.setParameter("idConference", idC);
            sectList = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return sectList;
        }
    }

    public List<Conference> getAllConferences() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Conference> sectList = new ArrayList<Conference>();
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("FROM Conference");
            sectList = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return sectList;
        }
    }
}