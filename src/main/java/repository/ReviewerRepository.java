package repository;

import model.CM;
import model.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ReviewerRepository implements CRUDRepository{
    //TODO
    /*LOGIN function
    *@param: user si pass
    *@return: true/false if found in database
    */
    private static SessionFactory factory;
    private List<CM> reviewerList = new ArrayList<>();
    public ReviewerRepository() {
        try {
            this.factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        //aa
        try {
            tx = session.beginTransaction();
            reviewerList = session.createQuery("FROM CM").list();
            for (Iterator iterator = reviewerList.iterator(); iterator.hasNext(); ) {
                CM c = (CM) iterator.next();
                //System.out.print("Id: " + c.getId());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public boolean login(String username, String password){
        for(int i = 0; i < reviewerList.size(); i++){
            if(reviewerList.get(i).getUsername().equals(username) && reviewerList.get(i).getPassword().equals(password))
                return true;
        }
        return false;
    }
    public void save(String username, String password, String name, String affiliation, String email, String webpage) {
        Transaction tx = null;
        boolean ret = false;
        Session ses = factory.openSession();
        try{
            tx = ses.beginTransaction();
            Query query = ses.createNativeQuery("INSERT INTO CM (username, password, name, affiliation, email, webpage) VALUES (:username, :password, :name, :affiliation, :email, :webpage)");
            query.setParameter("username", username);
            query.setParameter("password", password);
            query.setParameter("name", name);
            query.setParameter("affiliation", affiliation);
            query.setParameter("email", email);
            query.setParameter("webpage", webpage);
            query.executeUpdate();
            tx.commit();
        }
        catch (HibernateException ex){
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        finally{
            ses.close();
        }
    }
}
