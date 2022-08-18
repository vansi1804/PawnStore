/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.impl;

import DAO.ICustomerDAO;
import DAO.impl.CustomerDAO;
import Model.Customer;
import Service.ICustomerService;
import Support.CheckSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerService implements ICustomerService {

    private final ICustomerDAO customerDAO = new CustomerDAO();

    @Override
    public ArrayList<Customer> getList() {
        return customerDAO.getList();
    }

    @Override
    public Customer getCustomer(String id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    public boolean insert(Customer customer) {
        return customerDAO.insert(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDAO.delete(customer);
    }

    @Override
    public ArrayList<Customer> findCustomerByIDKey(ArrayList<Customer> customers, String idKey) {
        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (CheckSupport.constains(customer.getId(), idKey)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByNameKey(ArrayList<Customer> customers, String nameKey) {
        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (CheckSupport.constains(customer.getFullname(), nameKey)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByGenderKey(ArrayList<Customer> customers, String gender) {

        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getGender().equals(gender)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByPhonenumberKey(ArrayList<Customer> customers, String phonenumberKey) {

        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (CheckSupport.constains(customer.getPhonenumber(), phonenumberKey)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByAddressKey(ArrayList<Customer> customers, String addressKey) {
        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (CheckSupport.constains(customer.getAddress(), addressKey)) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByDeleteFlagKey(ArrayList<Customer> customers, boolean deleteflag) {
        ArrayList<Customer> results = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getDeleteflag() == deleteflag) {
                results.add(customer);
            }
        }
        return results;
    }

    @Override
    public ArrayList<Customer> findCustomerByIDKey(String idKey) {
        return customerDAO.findCustomerByIDKey(idKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByFullnameKey(String fullnameKey) {
        return customerDAO.findCustomerByFullnameKey(fullnameKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByGenderKey(String genderKey) {
        return customerDAO.findCustomerByGenderKey(genderKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByPhonenumberKey(String phonenumberKey) {
        return customerDAO.findCustomerByPhonenumberKey(phonenumberKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByAddressKey(String addressKey) {
        return customerDAO.findCustomerByAddressKey(addressKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByDeleteflagKey(String deleteflagKey) {
        return customerDAO.findCustomerByDeleteflagKey(deleteflagKey);
    }

    @Override
    public ArrayList<Customer> findCustomerByKey(String idKey, String fullnameKey
            , String genderKey, String phonenumberKey, String addressKey, String deleteflagKey) {
        return customerDAO.findCustomerByKey(idKey, fullnameKey, genderKey, phonenumberKey, addressKey, deleteflagKey);
    }
}
