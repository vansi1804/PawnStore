/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.ICustomerDAO;
import DAO.impl.CustomerDAO;
import Model.Customer;
import Service.ICustomerService;
import Support.MessageSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerService implements ICustomerService {

    private final ICustomerDAO customerDAO = new CustomerDAO();

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public List<Customer> findAllServing() {
        return customerDAO.findAllServing();
    }

    @Override
    public Customer findOneById(String id) {
        return customerDAO.findOneById(id);
    }

    @Override
    public boolean insert(Customer customer) {
        if (this.findOneById(customer.getId()) != null) {
            MessageSupport.ErrorMessage("Lỗi", "Tồn tại một khách hàng khác có CMND/CCCD: " + customer.getId());
            return false;
        }
        return customerDAO.insert(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public List<Customer> filterByKey(String idKey, String fullnameKey, String gender,
            String phoneNumberKey, String addressKey, Boolean deleteFlagKey) {
        return customerDAO.filterByKey(idKey, fullnameKey, gender, phoneNumberKey, addressKey, deleteFlagKey);
    }

}
