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
    private String phoneNumber;
    private String address;
    private boolean deleteFlag;

    public Customer() {
    }

    public Customer(String id, String fullname, String gender, String phonenumber, String address, boolean deleteflag) {
        this.id = id;
        this.fullname = fullname;
        this.gender = gender;
        this.phoneNumber = phonenumber;
        this.address = address;
        this.deleteFlag = deleteflag;
    }

    public Customer(String id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return id + " - " + fullname + " - " + gender + " - " + phoneNumber
                + " - " + address + (deleteFlag ? " - Ngưng phục vụ" : " - Phục vụ");
    }

}
