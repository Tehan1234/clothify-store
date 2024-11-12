package repository.custom;

import entity.EmployeeEntity;
import repository.CrudDao;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {

    public String getNextEmployeeId();


    boolean checkPasswordExists(String password);
}