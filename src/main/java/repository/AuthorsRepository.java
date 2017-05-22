package repository;

import model.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cosmin on 4/6/2017.
 */
public class AuthorsRepository implements CRUDRepository
{
    private static SessionFactory factory;
    private Statement statement;
    private Connection connection;
    private int idforfile = 0;
    private List<Integer> fls = new ArrayList<Integer>();
    @SuppressWarnings("unchecked")
    public AuthorsRepository()
    {
        String url = "jdbc:mysql://localhost:3306/cms";
        String user = "root";
        String password = "";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
            statement.execute("USE cms");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

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
        Session session = factory.openSession();
        Transaction tx = null;
        List<Author> sectList = new ArrayList<Author>();
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("FROM Author");
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

    public List<File> getAllFiles()
    {
        List<File> sectList = new ArrayList<File>();
        try {
            String query = "SELECT * FROM legaf WHERE ida = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, idforfile);
            ResultSet result = preparedStmt.executeQuery();
            while (result.next())
            {
                fls.add(result.getInt("idf"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        for (Integer idrez : fls)
        {
            List<File> rzm = new ArrayList<File>();
            Session session = factory.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                org.hibernate.query.Query query = session.createQuery("FROM File WHERE idF = :idF");
                query.setParameter("idF", idrez);
                rzm = query.list();
                sectList.add(rzm.get(0));
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.close();

            }
        }
        return sectList;
    }

    public List<Conference> getAllConferences()
    {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Conference> sectList = new ArrayList<Conference>();
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("FROM Sections");
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

    public List<Sections> findByConfId(int idC)
    {
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

    public List<String> returnDeadline(int idC)
    {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Conference> sectList;
        Conference conf = null;
        List<String> deadlineList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            org.hibernate.query.Query query = session.createQuery("FROM Conference where idConference = :idConference");
            query.setParameter("idConference", idC);
            sectList = query.list();

            conf = sectList.get(0);;
            if (conf!= null)
            {
                deadlineList.add(conf.getDeadlineProposal());
                deadlineList.add(conf.getDeadlineAbstract());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            return deadlineList;
        }
    }

    public boolean login(String username,String password)
    {
        List<Author> authors = getAllAuthor();
        Author rez = null;
        for(Author aut : authors)
            if(aut.getUsername().equals(username) && aut.getPassword().compareTo(password) == 0)
            {
                rez = aut;
            }

        if (rez!=null) {
            this.idforfile = rez.getIda();
            return true;
        }
        else
            return false;
    }
}
