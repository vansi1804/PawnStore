/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author NVS
 */
public class Information {

    protected String _id;
    protected String _name;

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Information() {
    }

    public Information(String _id, String _name) {
        this._id = _id;
        this._name = _name;
    }
}
