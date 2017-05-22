package repository;

import model.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mikha on 5/13/2017.
 */
public class FileRepository {

    private static SessionFactory factory;
    List<File> fileList = new ArrayList<>();

    public FileRepository(){
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
            fileList = session.createQuery("FROM File").list();
            for (Iterator iterator = fileList.iterator(); iterator.hasNext();)
            {
                File f = (File) iterator.next();
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
    public void save(File f){
        fileList.add(f);
    }

    public List<File> getAll(){
        return fileList;
    }
}
