package repository.custom;

import dto.Item;
import entity.ItemEntity;
import entity.OrderEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface OrderDao extends CrudDao<OrderEntity> {



    public ObservableList<String> getAllItemCodes();

    ItemEntity searchAllItems(String newVal);
}
