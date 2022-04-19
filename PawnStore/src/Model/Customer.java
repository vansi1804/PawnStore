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
public class Customer {

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getFullname() {
        return _fullname;
    }

    public void setFullname(String _fullname) {
        this._fullname = _fullname;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String _gender) {
        this._gender = _gender;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public String getPhonenumber() {
        return _phonenumber;
    }

    public void setPhonenumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }

    public Customer() {
    }

    public Customer(String _id, String _fullname, String _gender, String _phonenumber,String _address) {
        this._id = _id;
        this._fullname = _fullname;
        this._gender = _gender;
        this._address = _address;
        this._phonenumber = _phonenumber;
    }

    private String _id;
    private String _fullname;
    private String _gender;
    private String _address;
    private String _phonenumber;
}
