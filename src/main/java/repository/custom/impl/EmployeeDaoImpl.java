package repository.custom.impl;

import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        try {
            Session session = HibernateUtil.getEmployeeSession();
            session.beginTransaction();
            session.persist(employeeEntity);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        try {
            Session session = HibernateUtil.getEmployeeSession();
            session.beginTransaction();
            session.merge(employeeEntity.getEmployeeId(),employeeEntity);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Session session = HibernateUtil.getEmployeeSession();
            session.beginTransaction();
            session.remove(session.get(EmployeeEntity.class,id));
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public EmployeeEntity searchById(String id) {
        try {
            Session session = HibernateUtil.getEmployeeSession();
            return session.get(EmployeeEntity.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
        ObservableList<EmployeeEntity> employee= FXCollections.observableArrayList();
        try {
            Session session = HibernateUtil.getEmployeeSession();
            Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class);
            List<EmployeeEntity> employeeEntityList=query.list();
            employee.addAll(employeeEntityList);
            return employee;
        } catch (HibernateException e) {
            return employee;
        }
    }

    @Override
    public String getNextEmployeeId() {
        // Use "EmployeeEntity" to match your entity class name
        String hql = "SELECT e.employeeId FROM EmployeeEntity e ORDER BY e.employeeId DESC";

        try (Session session = HibernateUtil.getEmployeeSession()) {
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);

            String lastID = query.uniqueResult();
            if (lastID == null) {
                lastID = "E000";
            }
            return lastID;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving next Employee ID", e);
        }
    }

    @Override
    public boolean checkPasswordExists(String password) {
        String hql = "SELECT COUNT(e) FROM EmployeeEntity e WHERE e.password = :password";         boolean exists = false;
        try (Session session = HibernateUtil.getEmployeeSession()) {
                Query<Long> query = session.createQuery(hql, Long.class);
                query.setParameter("password", password);
                Long count = query.uniqueResult();
                exists = count > 0;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return exists;
        }
    }




