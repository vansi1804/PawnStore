/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Model.Customer;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
public interface ICustomerDAO {

    ArrayList<Customer> getList();

    Customer getCustomer(String id);

    boolean insert(Customer customer);

    boolean update(Customer customer);

    boolean delete(Customer customer);

    ArrayList<Customer> findCustomerByIDKey(String idKey);

    ArrayList<Customer> findCustomerByFullnameKey(String fullnameKey);

    ArrayList<Customer> findCustomerByGenderKey(String genderKey);

    ArrayList<Customer> findCustomerByPhonenumberKey(String phonenumberKey);

    ArrayList<Customer> findCustomerByAddressKey(String addressKey);

    ArrayList<Customer> findCustomerByDeleteflagKey(String deleteflagKey);
    
    ArrayList<Customer> findCustomerByKey(String idKey,String fullnameKey
            ,String genderKey,String phonenumberKey,String addressKey,String deleteflagKey);
}
