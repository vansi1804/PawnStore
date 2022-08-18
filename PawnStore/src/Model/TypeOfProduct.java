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
public class TypeOfProduct {

    private String id;
    private String name;
    private boolean deleteflag;

    public TypeOfProduct() {
    }

    public TypeOfProduct(String id, String name, boolean deleteflag) {
        this.id = id;
        this.name = name;
        this.deleteflag = deleteflag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + (deleteflag ? "Ngưng phục vụ" : "Phục vụ");
    }

}
