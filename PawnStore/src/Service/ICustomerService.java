/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Customer;
import java.util.List;

/**
 *
 * @author NVS
 */
public interface ICustomerService {

    public List<Customer> findAll();

    List<Customer> findAllServing();

    Customer findOneById(String id);

    boolean insert(Customer customer);

    boolean update(Customer customer);

    public List<Customer> filterByKey(String idKey, String fullnameKey, String gender,
            String phoneNumberKey, String addressKey, Boolean deleteFlagKey);

}
