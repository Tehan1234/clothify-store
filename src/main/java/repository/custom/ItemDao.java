package repository.custom;

import entity.ItemEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

public interface ItemDao extends CrudDao<ItemEntity> {

    ObservableList<String> getAllSupplierIds();

    public String getNextItemId();
}
