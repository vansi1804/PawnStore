/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ICustomerDAO;
import Mapper.impl.CustomerMapper;
import Model.Customer;
import Support.CheckSupport;
import java.util.ArrayList;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerDAO extends ADAO<Customer> implements ICustomerDAO {

    private static final String SELECTQUERY = "Select _id, _fullname, _gender, _phonenumber, _address, _deleteflag From Customer";
    private static final String UPDATEQUERY = "Update Customer Set _fullname = ?, _gender = ?, _phonenumber = ?, _address = ?, _deleteflag = ? Where _id = ?";
    private static final String INSERTQUERY = "Insert Into Customer(_id, _fullname, _gender, _phonenumber, _address, _deleteflag) Values(?,?,?,?,?,?)";
    private static final String DELETEQUERY = "Delete from Customer Where _id = ?";

    @Override
    public ArrayList<Customer> getList() {
        return getList(SELECTQUERY, new CustomerMapper());
    }

    @Override
    public Customer getCustomer(String id) {
        String query = SELECTQUERY + " Where _id = ?";
        return getObject(query, new CustomerMapper(), id);
    }

    @Override
    public boolean insert(Customer customer) {
        return insert(INSERTQUERY, customer.getId(), customer.getFullname(), customer.getGender(), customer.getPhonenumber(),
                customer.getAddress(), customer.getDeleteflag());
    }

    @Override
    public boolean update(Customer customer) {
        return update(UPDATEQUERY, customer.getFullname(), customer.getGender(), customer.getPhonenumber(), customer.getAddress(),
                customer.getDeleteflag(), customer.getId());
    }

    @Override
    public boolean delete(Customer customer) {
        return delete(DELETEQUERY, customer.getId());
    }

    @Override
    public ArrayList<Customer> findCustomerByIDKey(String idKey) {
        String query = SELECTQUERY + " Where _id like N'%" + idKey + "%'";
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByFullnameKey(String fullnameKey) {
        String query = SELECTQUERY + " Where _fullname like N'%" + fullnameKey + "%'";
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByGenderKey(String genderKey) {
        String query = SELECTQUERY + " Where _gender = N'" + genderKey + "' ";
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByPhonenumberKey(String phonenumberKey) {
        String query = SELECTQUERY + " Where _phonenumber like N'%" + phonenumberKey + "%'";
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByAddressKey(String addressKey) {
        String query = SELECTQUERY + " Where _address like N'%" + addressKey + "%'";
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByDeleteflagKey(String deleteflagKey) {
        String query = SELECTQUERY + " Where _deleteflag = " + deleteflagKey;
        return getList(query, new CustomerMapper());
    }

    @Override
    public ArrayList<Customer> findCustomerByKey(String idKey, String fullnameKey,
            String genderKey, String phonenumberKey, String addressKey, String deleteflagKey) {

        String query = SELECTQUERY;
        boolean isIDKeyEmpty = CheckSupport.isBlank(idKey);
        boolean isFullnameKeyEmpty = CheckSupport.isBlank(fullnameKey);
        boolean isGenderKeyEmpty = CheckSupport.isBlank(genderKey);
        boolean isPhonenumberKeyEmpty = CheckSupport.isBlank(phonenumberKey);
        boolean isAddressKeyEmpty = CheckSupport.isBlank(addressKey);
        boolean isDeleteflagKeyEmpty = CheckSupport.isBlank(deleteflagKey);
        if (!isIDKeyEmpty || !isFullnameKeyEmpty || !isGenderKeyEmpty || !isPhonenumberKeyEmpty || !isAddressKeyEmpty || !isDeleteflagKeyEmpty) {
            query += " Where ";
        }
        if (!isIDKeyEmpty) {
            query += " _id like N'%" + idKey + "%' ";
        }
        if (!isIDKeyEmpty && (!isFullnameKeyEmpty || !isGenderKeyEmpty || !isPhonenumberKeyEmpty || !isAddressKeyEmpty || !isDeleteflagKeyEmpty)) {
            query += " And ";
        }
        if (!isFullnameKeyEmpty) {
            query += " _fullname like N'%" + fullnameKey + "%' ";
        }
        if (!isFullnameKeyEmpty && (!isGenderKeyEmpty || !isPhonenumberKeyEmpty || !isAddressKeyEmpty || !isDeleteflagKeyEmpty)) {
            query += " And ";
        }
        if (!isGenderKeyEmpty) {
            query += " _gender = N'" + genderKey + "' ";
        }
        if (!isGenderKeyEmpty && (!isPhonenumberKeyEmpty || !isAddressKeyEmpty || !isDeleteflagKeyEmpty)) {
            query += " And ";
        }
        if (!isPhonenumberKeyEmpty) {
            query += " _phonenumber like N'%" + phonenumberKey + "%' ";
        }
        if (!isPhonenumberKeyEmpty && (!isAddressKeyEmpty || !isDeleteflagKeyEmpty)) {
            query += " And ";
        }
        if (!isAddressKeyEmpty) {
            query += " _address like N'%" + addressKey + "%' ";
        }
        if (!isAddressKeyEmpty && !isDeleteflagKeyEmpty) {
            query += " And ";
        }
        if (!isDeleteflagKeyEmpty) {
            query += " _deleteflag = " + deleteflagKey;
        }
        return getList(query, new CustomerMapper());
    }

}
