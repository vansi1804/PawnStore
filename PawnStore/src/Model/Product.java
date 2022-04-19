/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bau Kien
 */
public class Product extends TypeOfProduct{

    public Product() {
        super();
    }

    public Product(String id, String name, String information,String status, TypeOfProduct typeOfProduct) {
        super(typeOfProduct);
        this.id = id;
        this.name = name;
        this.information = information;
        this.status = status;
    }

    public String getProductID() {
        return id;
    }

    public void setProductID(String id) {
        this.id = id;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    private String id;
    private String name;
    private String information;
    private String status;
}
