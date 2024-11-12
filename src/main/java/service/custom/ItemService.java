package service.custom;

import dto.Item;
import entity.ItemEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ItemService extends SuperService {
    boolean addItem(Item item);

    Item searchItem(String id);

    boolean updateItem(Item employee);

    ObservableList<ItemEntity> getAllItem();

    boolean deleteItem(String id);

    public String getNextItem();


    ObservableList<String> getAllSuppliers();

}
