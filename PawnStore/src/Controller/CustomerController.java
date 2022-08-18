/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Customer;
import Service.ICustomerService;
import Service.impl.CustomerService;
import java.util.ArrayList;

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

    public ArrayList<Customer> getList() {
        return customerService.getList();
    }

    public Customer getCustomer(String id) {
        return customerService.getCustomer(id);
    }

    public boolean insert(Customer customer) {
        return customerService.insert(customer);
    }

    public boolean update(Customer customer) {
        return customerService.update(customer);
    }

    public boolean delete(Customer customer) {
        return customerService.delete(customer);
    }

    public ArrayList<Customer> findCustomerByIDKey(ArrayList<Customer> customers, String idKey) {
        return customerService.findCustomerByIDKey(customers, idKey);
    }

    public ArrayList<Customer> findCustomerByNameKey(ArrayList<Customer> customers, String nameKey) {
        return customerService.findCustomerByNameKey(customers, nameKey);
    }

    public ArrayList<Customer> findCustomerByGenderKey(ArrayList<Customer> customers, String gender) {
        return customerService.findCustomerByGenderKey(customers, gender);
    }

    public ArrayList<Customer> findCustomerByPhonenumberKey(ArrayList<Customer> customers, String phonenumberKey) {
        return customerService.findCustomerByPhonenumberKey(customers, phonenumberKey);
    }

    public ArrayList<Customer> findCustomerByAddressKey(ArrayList<Customer> customers, String addressKey) {
        return customerService.findCustomerByAddressKey(customers, addressKey);
    }

    public ArrayList<Customer> findCustomerByDeleteFlagKey(ArrayList<Customer> customers, boolean deleteflag) {
        return customerService.findCustomerByDeleteFlagKey(customers, deleteflag);
    }

    public ArrayList<Customer> findCustomerByIDKey(String idKey) {
        return customerService.findCustomerByIDKey(idKey);
    }

    public ArrayList<Customer> findCustomerByFullnameKey(String fullnameKey) {
        return customerService.findCustomerByFullnameKey(fullnameKey);
    }

    public ArrayList<Customer> findCustomerByGenderKey(String genderKey) {
        return customerService.findCustomerByGenderKey(genderKey);
    }

    public ArrayList<Customer> findCustomerByPhonenumberKey(String phonenumberKey) {
        return customerService.findCustomerByPhonenumberKey(phonenumberKey);
    }

    public ArrayList<Customer> findCustomerByAddressKey(String addressKey) {
        return customerService.findCustomerByAddressKey(addressKey);
    }

    public ArrayList<Customer> findCustomerByDeleteflagKey(String deleteflagKey) {
        return customerService.findCustomerByDeleteflagKey(deleteflagKey);
    }

    public ArrayList<Customer> findCustomerByKey(String idKey, String fullnameKey,
            String genderKey, String phonenumberKey, String addressKey, String deleteflagKey) {
        return customerService.findCustomerByKey(idKey, fullnameKey, genderKey, phonenumberKey, addressKey, deleteflagKey);
    }
}
