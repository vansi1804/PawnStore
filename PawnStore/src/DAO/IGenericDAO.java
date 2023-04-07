/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Mapper.IObjectMapper;
import java.util.List;

/**
 *
 * @author NVS
 * @param <T>
 */
public interface IGenericDAO<T> {

    <T> List<T> findAll(String query, IObjectMapper<T> rowMapper, Object... parameters);

    T findOne(String query, IObjectMapper<T> rowMapper, Object... parameters);

    boolean insert(String query, Object... parameters);

    boolean update(String query, Object... parameters);

    boolean delete(String query, Object... parameters);

    int count(String query, Object... parameters);

}
