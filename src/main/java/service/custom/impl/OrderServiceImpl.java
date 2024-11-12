package service.custom.impl;

import dto.Item;
import entity.ItemEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.DaoType;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);

    @Override
    public Item searchItem(String newVal) {
        ItemEntity itemEntity = orderDao.searchAllItems(newVal);
        if (itemEntity != null) {
            Item item = new ModelMapper().map(itemEntity, Item.class);
            return item;
        } else {
            return null;
        }
    }

    @Override
    public ObservableList<String> getAllItemCodes() {
       return orderDao.getAllItemCodes();
    }
}
