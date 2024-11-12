package service.custom.impl;

import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao= DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity employEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        return  employeeDao.save(employEntity);
    }

    @Override
    public Employee searchEmployee(String id) {
        EmployeeEntity employeeEntity = employeeDao.searchById(id);
        if (employeeEntity != null) {
            Employee employee = new ModelMapper().map(employeeEntity, Employee.class);
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity entity = new ModelMapper().map(employee,EmployeeEntity.class);
        return employeeDao.update(entity);

    }

    @Override
    public ObservableList<EmployeeEntity> getAllEmployees() {
       return employeeDao.getAll();
    }

    @Override
    public boolean deleteEmployee(String id) {
         return employeeDao.delete(id);
    }

    @Override
    public String getNextEmployeeId() {
        String nextEmployeeId = employeeDao.getNextEmployeeId();
        return nextEmployeeId;
    }

    @Override
    public boolean isPasswordExists(String password) {
        return employeeDao.checkPasswordExists(password);
    }
}