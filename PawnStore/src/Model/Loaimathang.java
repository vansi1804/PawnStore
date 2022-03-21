/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author NVS
 */
@Entity
@Table(name = "LOAIMATHANG")
@NamedQueries({
    @NamedQuery(name = "Loaimathang.findAll", query = "SELECT l FROM Loaimathang l"),
    @NamedQuery(name = "Loaimathang.findByMaloai", query = "SELECT l FROM Loaimathang l WHERE l.maloai = :maloai"),
    @NamedQuery(name = "Loaimathang.findByTenloai", query = "SELECT l FROM Loaimathang l WHERE l.tenloai = :tenloai")})
public class Loaimathang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MALOAI")
    private String maloai;
    @Column(name = "TENLOAI")
    private String tenloai;
    @OneToMany(mappedBy = "maloai")
    private Collection<Mathang> mathangCollection;

    public Loaimathang() {
    }

    public Loaimathang(String maloai) {
        this.maloai = maloai;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public Collection<Mathang> getMathangCollection() {
        return mathangCollection;
    }

    public void setMathangCollection(Collection<Mathang> mathangCollection) {
        this.mathangCollection = mathangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maloai != null ? maloai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaimathang)) {
            return false;
        }
        Loaimathang other = (Loaimathang) object;
        if ((this.maloai == null && other.maloai != null) || (this.maloai != null && !this.maloai.equals(other.maloai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Loaimathang[ maloai=" + maloai + " ]";
    }
    
}
