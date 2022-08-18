/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Mapper;

import java.sql.ResultSet;

/**
 *
 * @author NVS
 * @param <T>
 */
public interface IObjectMapper<T> {

    T mapObject(ResultSet rs);
}
