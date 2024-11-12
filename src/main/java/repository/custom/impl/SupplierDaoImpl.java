package repository.custom.impl;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        try {
            Session session = HibernateUtil.getSupplierSession();
            session.beginTransaction();
            session.persist(supplierEntity);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public SupplierEntity searchById(String id) {
        try {
            Session session = HibernateUtil.getEmployeeSession();
            return session.get(SupplierEntity.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        ObservableList<SupplierEntity> supplier= FXCollections.observableArrayList();
        try {
            Session session = HibernateUtil.getSupplierSession();
            Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity", SupplierEntity.class);
            List<SupplierEntity> supplierEntityList=query.list();
            supplier.addAll(supplierEntityList);
            return supplier;
        } catch (HibernateException e) {
            return supplier;
        }
    }


    @Override
    public String getNextSupplierId() {
        String hql = "SELECT s.supplierId FROM SupplierEntity s ORDER BY s.supplierId DESC";

        try (Session session = HibernateUtil.getSupplierSession()) {
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);

            String lastID = query.uniqueResult();
            if (lastID == null) {
                lastID = "S000";
            }
            return lastID;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving next Employee ID", e);
        }
    }


}
