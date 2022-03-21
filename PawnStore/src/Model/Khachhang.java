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
@Table(name = "KHACHHANG")
@NamedQueries({
    @NamedQuery(name = "Khachhang.findAll", query = "SELECT k FROM Khachhang k"),
    @NamedQuery(name = "Khachhang.findByMakhachhang", query = "SELECT k FROM Khachhang k WHERE k.makhachhang = :makhachhang"),
    @NamedQuery(name = "Khachhang.findByTenkhachhang", query = "SELECT k FROM Khachhang k WHERE k.tenkhachhang = :tenkhachhang"),
    @NamedQuery(name = "Khachhang.findBySdt", query = "SELECT k FROM Khachhang k WHERE k.sdt = :sdt"),
    @NamedQuery(name = "Khachhang.findByDiachi", query = "SELECT k FROM Khachhang k WHERE k.diachi = :diachi"),
    @NamedQuery(name = "Khachhang.findByCmnd", query = "SELECT k FROM Khachhang k WHERE k.cmnd = :cmnd"),
    @NamedQuery(name = "Khachhang.findByTtKh", query = "SELECT k FROM Khachhang k WHERE k.ttKh = :ttKh")})
public class Khachhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MAKHACHHANG")
    private String makhachhang;
    @Column(name = "TENKHACHHANG")
    private String tenkhachhang;
    @Column(name = "SDT")
    private String sdt;
    @Column(name = "DIACHI")
    private String diachi;
    @Column(name = "CMND")
    private String cmnd;
    @Column(name = "TT_KH")
    private Boolean ttKh;
    @OneToMany(mappedBy = "makhachhang")
    private Collection<Phieucamdo> phieucamdoCollection;

    public Khachhang() {
    }

    public Khachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(String makhachhang) {
        this.makhachhang = makhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public Boolean getTtKh() {
        return ttKh;
    }

    public void setTtKh(Boolean ttKh) {
        this.ttKh = ttKh;
    }

    public Collection<Phieucamdo> getPhieucamdoCollection() {
        return phieucamdoCollection;
    }

    public void setPhieucamdoCollection(Collection<Phieucamdo> phieucamdoCollection) {
        this.phieucamdoCollection = phieucamdoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (makhachhang != null ? makhachhang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khachhang)) {
            return false;
        }
        Khachhang other = (Khachhang) object;
        if ((this.makhachhang == null && other.makhachhang != null) || (this.makhachhang != null && !this.makhachhang.equals(other.makhachhang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Khachhang[ makhachhang=" + makhachhang + " ]";
    }
    
}
