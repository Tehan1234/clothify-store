package service.custom.impl;

import dto.Employee;
import dto.Supplier;
import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import util.DaoType;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao=DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = new ModelMapper().map(supplier, SupplierEntity.class);
        return  supplierDao.save(supplierEntity);
    }

    @Override
    public Supplier searchSupplier(String id) {
        SupplierEntity supplierEntity = supplierDao.searchById(id);
        return  supplierEntity==null? null: new ModelMapper().map(supplierEntity, Supplier.class);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public ObservableList<SupplierEntity> getAllSuppliers() {
        return  supplierDao.getAll();

    }

    @Override
    public boolean deleteSupplier(String id) {
        return false;
    }

    @Override
    public String getNextSupplierId() {
        String nextSupplierId= supplierDao.getNextSupplierId();
        return nextSupplierId;
    }
}
