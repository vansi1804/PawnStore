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
public class TypeOfProduct {

    public TypeOfProduct() {
    }

    public TypeOfProduct(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public TypeOfProduct(TypeOfProduct typeOfProduct) {
        this.id = typeOfProduct.getTypeOfProductID();
        this.name = typeOfProduct.getTypeOfProductName();
    }

    public String getTypeOfProductID() {
        return id;
    }

    public void setTypeOfProductID(String id) {
        this.id = id;
    }

    public String getTypeOfProductName() {
        return name;
    }

    public void setTypeOfProductName(String name) {
        this.name = name;
    }
    protected String id;
    protected String name;
}
