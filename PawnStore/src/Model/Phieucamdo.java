/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NVS
 */
@Entity
@Table(name = "PHIEUCAMDO")
@NamedQueries({
    @NamedQuery(name = "Phieucamdo.findAll", query = "SELECT p FROM Phieucamdo p"),
    @NamedQuery(name = "Phieucamdo.findByMaphieu", query = "SELECT p FROM Phieucamdo p WHERE p.maphieu = :maphieu"),
    @NamedQuery(name = "Phieucamdo.findByNgaylap", query = "SELECT p FROM Phieucamdo p WHERE p.ngaylap = :ngaylap"),
    @NamedQuery(name = "Phieucamdo.findByTientra", query = "SELECT p FROM Phieucamdo p WHERE p.tientra = :tientra"),
    @NamedQuery(name = "Phieucamdo.findByTrangthai", query = "SELECT p FROM Phieucamdo p WHERE p.trangthai = :trangthai"),
    @NamedQuery(name = "Phieucamdo.findByTtPcd", query = "SELECT p FROM Phieucamdo p WHERE p.ttPcd = :ttPcd")})
public class Phieucamdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MAPHIEU")
    private String maphieu;
    @Column(name = "NGAYLAP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaylap;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIENTRA")
    private Double tientra;
    @Column(name = "TRANGTHAI")
    private Boolean trangthai;
    @Column(name = "TT_PCD")
    private Boolean ttPcd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieucamdo")
    private Collection<Ctphieucamdo> ctphieucamdoCollection;
    @OneToMany(mappedBy = "maphieu")
    private Collection<Phieuthanhtoan> phieuthanhtoanCollection;
    @JoinColumn(name = "MAKHACHHANG", referencedColumnName = "MAKHACHHANG")
    @ManyToOne
    private Khachhang makhachhang;
    @JoinColumn(name = "TEN_TAI_KHOAN", referencedColumnName = "TEN_TAI_KHOAN")
    @ManyToOne
    private Taikhoan tenTaiKhoan;

    public Phieucamdo() {
    }

    public Phieucamdo(String maphieu) {
        this.maphieu = maphieu;
    }

    public String getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    public Date getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Date ngaylap) {
        this.ngaylap = ngaylap;
    }

    public Double getTientra() {
        return tientra;
    }

    public void setTientra(Double tientra) {
        this.tientra = tientra;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Boolean getTtPcd() {
        return ttPcd;
    }

    public void setTtPcd(Boolean ttPcd) {
        this.ttPcd = ttPcd;
    }

    public Collection<Ctphieucamdo> getCtphieucamdoCollection() {
        return ctphieucamdoCollection;
    }

    public void setCtphieucamdoCollection(Collection<Ctphieucamdo> ctphieucamdoCollection) {
        this.ctphieucamdoCollection = ctphieucamdoCollection;
    }

    public Collection<Phieuthanhtoan> getPhieuthanhtoanCollection() {
        return phieuthanhtoanCollection;
    }

    public void setPhieuthanhtoanCollection(Collection<Phieuthanhtoan> phieuthanhtoanCollection) {
        this.phieuthanhtoanCollection = phieuthanhtoanCollection;
    }

    public Khachhang getMakhachhang() {
        return makhachhang;
    }

    public void setMakhachhang(Khachhang makhachhang) {
        this.makhachhang = makhachhang;
    }

    public Taikhoan getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(Taikhoan tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maphieu != null ? maphieu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phieucamdo)) {
            return false;
        }
        Phieucamdo other = (Phieucamdo) object;
        if ((this.maphieu == null && other.maphieu != null) || (this.maphieu != null && !this.maphieu.equals(other.maphieu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Phieucamdo[ maphieu=" + maphieu + " ]";
    }
    
}
