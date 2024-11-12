package repository.custom.impl;

import dto.Item;
import entity.ItemEntity;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public OrderEntity searchById(String id) {
        return null;
    }

    @Override
    public ObservableList<OrderEntity> getAll() {
        return null;
    }

    @Override
    public ObservableList<String> getAllItemCodes() {
        ObservableList<String> itemCodeList = FXCollections.observableArrayList();
        String hql = "SELECT s.itemCode FROM ItemEntity s";  // Select IDs as String

        try (Session session = HibernateUtil.getOrderSession()) {
            Query<String> query = session.createQuery(hql, String.class);
            List<String> ids = query.list();  // Retrieve list of String IDs
            itemCodeList.addAll(ids);  // Add to ObservableList
        } catch (HibernateException e) {
            e.printStackTrace();  // Print exception for debugging
        }

        return itemCodeList;
    }

    @Override
    public ItemEntity searchAllItems(String newVal) {
        try {
            Session session = HibernateUtil.getItemSession();
            return session.get(ItemEntity.class, newVal);
        } catch (HibernateException e) {
            return null;
        }
    }


}
