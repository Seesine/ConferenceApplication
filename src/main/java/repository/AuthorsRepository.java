package repository;

import model.Author;
import model.Conference;
import model.File;
import model.Sections;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cosmin on 4/6/2017.
 */
public class AuthorsRepository implements CRUDRepository
{
    private static SessionFactory factory;
    //Lista de autori
    private List<Author> authors = new ArrayList<Author>();
    //Lista de fisiere
    private List<File> files = new ArrayList<File>();
    //Lista de sesiuni
    private List<Conference> confes = new ArrayList<Conference>();
    //Lista de sectiuni
    private List<Sections> sections = new ArrayList<Sections>();

    @SuppressWarnings("unchecked")
    public AuthorsRepository()
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
            authors = session.createQuery("FROM Author").list();
            tx.commit();

            tx = session.beginTransaction();
            files = session.createQuery("FROM File").list();
            tx.commit();

            tx = session.beginTransaction();
            confes = session.createQuery("FROM Conference ").list();
            tx.commit();

            tx = session.beginTransaction();
            sections = session.createQuery("FROM Sections ").list();
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

    public List<Author> getAllAuthor()
    {
        return authors;
    }

    public List<File> getAllFiles()
    {
        return files;
    }

    public List<Conference> getAllConferences()
    {
        return confes;
    }

    public List<Sections> getAllSections()
    {
        return sections;
    }

    public boolean login(String username,String password)
    {
        Author rez = null;
        for(Author aut : authors)
            if(aut.getUsername().equals(username) && aut.getPassword().compareTo(password) == 0)
                rez = aut;
        if (rez!=null)
            return true;
        else
            return false;
    }
}
