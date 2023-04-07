/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Service.ICustomerService;
import Service.impl.CustomerService;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerController {

    private static CustomerController instance;

    public static CustomerController getCurrentInstance() {
        if (instance == null) {
            instance = new CustomerController();
        }
        return instance;
    }

    private final ICustomerService customerService = new CustomerService();

    public List<Customer> findAll() {
        return customerService.findAll();
    }

    public List<Customer> findAllServing() {
        return customerService.findAllServing();
    }

    public Customer findOneById(String id) {
        return customerService.findOneById(id);
    }

    public boolean insert(Customer customer) {
        return customerService.insert(customer);
    }

    public boolean update(Customer customer) {
        return customerService.update(customer);
    }

    public List<Customer> filterByKey(String idKey, String fullnameKey, String gender,
            String phoneNumberKey, String addressKey, Boolean deleteFlagKey) {
        return customerService.filterByKey(idKey, fullnameKey, gender, phoneNumberKey, addressKey, deleteFlagKey);
    }

}
