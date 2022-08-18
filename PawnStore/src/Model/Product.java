/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NVS
 */
@SuppressWarnings("ClassWithoutLogger")
public class Product {

    private String id;
    private TypeOfProduct typeOfProduct;
    private String name;
    private String info;
    private String status;

    public Product() {
    }

    public Product(String id, TypeOfProduct typeOfProduct, String name, String info, String status) {
        this.id = id;
        this.typeOfProduct = typeOfProduct;
        this.name = name;
        this.info = info;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypeOfProduct getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " - " + typeOfProduct.getId() + " - " + name 
                + " - " + info + " - " + status;
    }
}
