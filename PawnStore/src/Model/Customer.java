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
public class Customer {

    private String id;
    private String fullname;
    private String gender;
    private String phonenumber;
    private String address;
    private boolean deleteflag;

    public Customer() {
    }

    public Customer(String id, String fullname, String gender, String phonenumber, String address, boolean deleteflag) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.deleteflag = deleteflag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(boolean deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Override
    public String toString() {
        return id + " - " + fullname + " - " + gender + " - " + phonenumber
                + " - " + address + (deleteflag ? " - Ngưng phục vụ" : " - Phục vụ");
    }

}
