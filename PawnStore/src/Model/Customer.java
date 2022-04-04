/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NVS
 */
public class Customer extends Information {

    private String _phoneNumber;
    private String _address;
    private String _identityNumber;

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String _address) {
        this._address = _address;
    }

    public String getIdentityNumber() {
        return _identityNumber;
    }

    public void setIdentityNumber(String _identityNumber) {
        this._identityNumber = _identityNumber;
    }

    public Customer() {
        super();
    }

    public Customer(String _id, String _name,String _phoneNumber, String _address, String _identityNumber) {
        super(_id, _name);
        this._phoneNumber = _phoneNumber;
        this._address = _address;
        this._identityNumber = _identityNumber;
    }

}
