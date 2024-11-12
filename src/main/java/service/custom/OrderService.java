package service.custom;

import dto.Item;
import javafx.collections.ObservableList;
import service.SuperService;

public interface OrderService extends SuperService {


    ObservableList<String> getAllItemCodes();

    Item searchItem(String newVal);
}
