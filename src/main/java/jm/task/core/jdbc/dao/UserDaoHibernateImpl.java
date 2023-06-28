package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Transaction tx = null;
        try (Session session = Util.getSession().openSession()) {
             tx = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class).executeUpdate();
            tx.commit();
            System.out.println("Database successfully created");
        }  catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(ex);
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USER").executeUpdate();
            transaction.commit();
            System.out.println("Table dropped");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            Transaction transaction = null;
            try (Session session = Util.getSession().openSession()) {
                transaction = session.beginTransaction();
                session.save(new User (name, lastName, age));
                // commit transaction
                transaction.commit();
                System.out.println("New User added to database");
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("Failed to add a User");
                e.printStackTrace();
            }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
           session.delete(session.get(User.class, id));
           transaction.commit();
            System.out.println("User has been deleted");
        } catch (Exception ex){
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Won't delete by id");
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = Util.getSession().openSession()) {

            list = session.createQuery("from User", User.class).list();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE USER").executeUpdate();
            transaction.commit();
            System.out.println("Table cleaned");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
