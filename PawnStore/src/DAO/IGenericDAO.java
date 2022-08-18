/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Mapper.IObjectMapper;
import java.util.ArrayList;

/**
 *
 * @author NVS
 * @param <T>
 */
public interface IGenericDAO<T> {

    <T> ArrayList<T> getList(String query, IObjectMapper<T> rowMapper, Object... parameters);

    T getObject(String query, IObjectMapper<T> rowMapper, Object... parameters);

    boolean insert(String query, Object... parameters);

    boolean update(String query, Object... parameters);

    boolean delete(String query, Object... parameters);

    int count(String query, Object... parameters);

}
