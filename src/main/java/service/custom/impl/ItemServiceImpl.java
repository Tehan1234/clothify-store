package service.custom.impl;

import dto.Employee;
import dto.Item;
import entity.EmployeeEntity;
import entity.ItemEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import service.custom.ItemService;
import util.DaoType;

public class ItemServiceImpl  implements ItemService {
    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);

    @Override
    public boolean addItem(Item item) {
        ItemEntity itemEntity = new ModelMapper().map(item, ItemEntity.class);
        return  itemDao.save(itemEntity);
    }

    @Override
    public Item searchItem(String id) {
        ItemEntity itemEntity = itemDao.searchById(id);
        if (itemEntity != null) {
                Item item = new ModelMapper().map(itemEntity, Item.class);
            return item;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateItem(Item item) {
        ItemEntity itemEntity = new ModelMapper().map(item, ItemEntity.class);
        return  itemDao.update(itemEntity);
    }

    @Override
    public ObservableList<ItemEntity> getAllItem() {
       return itemDao.getAll();
    }

    @Override
    public boolean deleteItem(String id) {
        return itemDao.delete(id);
    }

    @Override
    public String getNextItem() {
        String nextItemId = itemDao.getNextItemId();
        return nextItemId;
    }

    @Override
    public ObservableList<String> getAllSuppliers() {
        return itemDao.getAllSupplierIds();
    }
}
