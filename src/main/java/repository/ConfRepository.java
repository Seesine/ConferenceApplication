package repository;

import model.Conference;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by stefanvacareanu on 13/05/2017.
 */
public class ConfRepository {
    private static SessionFactory factory;
    private List<Conference> conferences = new ArrayList<Conference>();


    public ConfRepository()
    {
        try
        {
            this.factory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try
        {
            tx = session.beginTransaction();
            conferences = session.createQuery("FROM Conference").list();
            for (Iterator iterator = conferences.iterator(); iterator.hasNext();)
            {
                Conference f = (Conference) iterator.next();
                System.out.print("IdConference: " + f.getIdConference());
                System.out.print("noParticipants: " + f.getNoParticipants());
                System.out.println("Name: " + f.getName());
                System.out.println("Deadline: " + f.getDeadline());
            }
            tx.commit();
        }
        catch (HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
    }

    public List<Conference> getAll(){
        return conferences;
    }
}
