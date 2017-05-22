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
    List<Pair> idList = new ArrayList<>();

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
            String s = " ";
            fileList = session.createQuery("FROM File").list();
            idList = session.createQuery("SELECT P.idO, P.idD FROM "+ Pair.class.getName()+ " P").list();
            int temp = 0;
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

    public File getById(int id){
        for(int i = 0 ; i < fileList.size(); i++){
            if(fileList.get(i).getIdF() == id)
                return fileList.get(i);
        }
        return null;
    }

    public void updateFile(File file, File newFile)
    {
        for (File f: fileList) {
            if (f.getIdF() == file.getIdF())
            {
                Session session = factory.openSession();
                Transaction tx = null;
                try
                {
                    tx = session.beginTransaction();
                    File fg = (File) session.get(File.class, file.getIdF());
                    fg.setReviewCount(newFile.getReviewCount());
                    fg.setLevel(newFile.getLevel());
                    session.update(fg);
                    tx.commit();

                } catch (HibernateException e) {
                    if (tx != null) tx.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            }
        }
    }


    class Pair{
        private int idO, idD, idPrimary;
        Pair(int idO, int idD){
            this.idD = idD;
            this.idO = idO;
        }
        public Pair(){}

        public int getIdO() {
            return idO;
        }

        public void setIdO(int idO) {
            this.idO = idO;
        }

        public int getIdD() {
            return idD;
        }

        public void setIdD(int idD) {
            this.idD = idD;
        }

        public int getIdPrimary() {
            return idPrimary;
        }

        public void setIdPrimary(int idPrimary) {
            this.idPrimary = idPrimary;
        }
    }
}

