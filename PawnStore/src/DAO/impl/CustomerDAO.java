/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.impl;

import DAO.ICustomerDAO;
import Mapper.impl.CustomerMapper;
import Model.Customer;
import Support.CheckSupport;
import java.util.List;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class CustomerDAO extends ADAO<Customer> implements ICustomerDAO {

    private static final String SELECTQUERY = "Select id, fullname, gender, phone_number, address, delete_flag From customer";
    private static final String INSERTQUERY = "Insert Into customer(id, fullname, gender, phone_number, address, delete_flag) Values(?,?,?,?,?,?)";
    private static final String UPDATEQUERY = "Update customer Set fullname = ?, gender = ?, phone_number = ?, address = ?, delete_flag = ? Where id = ?";

    @Override
    public List<Customer> findAll() {
        return findAll(SELECTQUERY, new CustomerMapper());
    }

    @Override
    public List<Customer> findAllByStatus(Boolean deleteFlag) {
        String query = SELECTQUERY
                + (deleteFlag == null ? "" : " Where delete_flag = ?");
        return findAll(query, new CustomerMapper(), deleteFlag);
    }

    @Override
    public Customer findOneById(String id) {
        String query = SELECTQUERY + " Where id = ?";
        return findOne(query, new CustomerMapper(), id);
    }

    @Override
    public boolean insert(Customer customer) {
        return insert(INSERTQUERY, customer.getId(), customer.getFullname(), customer.getGender(), customer.getPhoneNumber(),
                customer.getAddress(), customer.getDeleteFlag());
    }

    @Override
    public boolean update(Customer customer) {
        return update(UPDATEQUERY, customer.getFullname(), customer.getGender(), customer.getPhoneNumber(), customer.getAddress(),
                customer.getDeleteFlag(), customer.getId());
    }

    @Override
    public List<Customer> filterByKey(String idKey, String fullnameKey, String gender,
            String phoneNumberKey, String addressKey, Boolean deleteFlagKey) {
        String query = SELECTQUERY
                + " Where 1 = 1"
                + (CheckSupport.isNullOrBlank(idKey) ? "" : " And id Like '%" + idKey + "%'")
                + (CheckSupport.isNullOrBlank(fullnameKey) ? "" : " And fullname Like '%" + fullnameKey + "%'")
                + (CheckSupport.isNullOrBlank(gender) ? "" : " And gender = N'" + gender + "'")
                + (CheckSupport.isNullOrBlank(phoneNumberKey) ? "" : " And phone_number Like '%" + phoneNumberKey + "%'")
                + (CheckSupport.isNullOrBlank(addressKey) ? "" : " And address Like '%" + addressKey + "%'")
                + (deleteFlagKey == null ? "" : " And delete_flag = " + deleteFlagKey);
        return findAll(query, new CustomerMapper());
    }

}
