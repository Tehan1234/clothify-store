package repository.custom.impl;

import entity.EmployeeEntity;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.ItemDao;
import util.HibernateUtil;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity itemEntity) {
        try {
            Session session = HibernateUtil.getItemSession();
            session.beginTransaction();
            session.persist(itemEntity);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public boolean update(ItemEntity itemEntity) {
        try {
            Session session = HibernateUtil.getItemSession();
            session.beginTransaction();
            session.merge(itemEntity.getItemCode(),itemEntity);
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
            Session session = HibernateUtil.getItemSession();
            session.beginTransaction();
            session.remove(session.get(ItemEntity.class,id));
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public ItemEntity searchById(String id) {
        try {
            Session session = HibernateUtil.getItemSession();
            return session.get(ItemEntity.class, id);
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public ObservableList<ItemEntity> getAll() {
        ObservableList<ItemEntity> itemEntitiesAll= FXCollections.observableArrayList();
        try {
            Session session = HibernateUtil.getItemSession();
            Query<ItemEntity> query = session.createQuery("FROM ItemEntity ", ItemEntity.class);
            List<ItemEntity> itemEntities=query.list();
            itemEntitiesAll.addAll(itemEntities);
            return itemEntitiesAll;
        } catch (HibernateException e) {
            return itemEntitiesAll;
        }
    }



    @Override
    public ObservableList<String> getAllSupplierIds() {
        ObservableList<String> supplierIdList = FXCollections.observableArrayList();
        String hql = "SELECT s.supplierId FROM SupplierEntity s";  // Select IDs as String

        try (Session session = HibernateUtil.getSupplierSession()) {
            Query<String> query = session.createQuery(hql, String.class);
            List<String> ids = query.list();  // Retrieve list of String IDs
            supplierIdList.addAll(ids);  // Add to ObservableList
        } catch (HibernateException e) {
            e.printStackTrace();  // Print exception for debugging
        }

        return supplierIdList;
    }

    @Override
    public String getNextItemId() {
        String hql = "SELECT e.itemCode FROM ItemEntity e ORDER BY e.itemCode DESC";

        try (Session session = HibernateUtil.getItemSession()) {
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);

            String lastID = query.uniqueResult();
            if (lastID == null) {
                lastID = "I000";
            }
            return lastID;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving next Item ID", e);
        }
    }


}
