/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NVS
 */
@Entity
@Table(name = "PHIEUTHANHTOAN")
@NamedQueries({
    @NamedQuery(name = "Phieuthanhtoan.findAll", query = "SELECT p FROM Phieuthanhtoan p"),
    @NamedQuery(name = "Phieuthanhtoan.findByMaphieuthanhtoan", query = "SELECT p FROM Phieuthanhtoan p WHERE p.maphieuthanhtoan = :maphieuthanhtoan"),
    @NamedQuery(name = "Phieuthanhtoan.findByNgaylapphieuthanhtoan", query = "SELECT p FROM Phieuthanhtoan p WHERE p.ngaylapphieuthanhtoan = :ngaylapphieuthanhtoan"),
    @NamedQuery(name = "Phieuthanhtoan.findByTienlai", query = "SELECT p FROM Phieuthanhtoan p WHERE p.tienlai = :tienlai"),
    @NamedQuery(name = "Phieuthanhtoan.findByTongtien", query = "SELECT p FROM Phieuthanhtoan p WHERE p.tongtien = :tongtien"),
    @NamedQuery(name = "Phieuthanhtoan.findByTtPtt", query = "SELECT p FROM Phieuthanhtoan p WHERE p.ttPtt = :ttPtt")})
public class Phieuthanhtoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MAPHIEUTHANHTOAN")
    private String maphieuthanhtoan;
    @Column(name = "NGAYLAPPHIEUTHANHTOAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaylapphieuthanhtoan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIENLAI")
    private Double tienlai;
    @Column(name = "TONGTIEN")
    private BigDecimal tongtien;
    @Column(name = "TT_PTT")
    private Boolean ttPtt;
    @JoinColumn(name = "MAPHIEU", referencedColumnName = "MAPHIEU")
    @ManyToOne
    private Phieucamdo maphieu;

    public Phieuthanhtoan() {
    }

    public Phieuthanhtoan(String maphieuthanhtoan) {
        this.maphieuthanhtoan = maphieuthanhtoan;
    }

    public String getMaphieuthanhtoan() {
        return maphieuthanhtoan;
    }

    public void setMaphieuthanhtoan(String maphieuthanhtoan) {
        this.maphieuthanhtoan = maphieuthanhtoan;
    }

    public Date getNgaylapphieuthanhtoan() {
        return ngaylapphieuthanhtoan;
    }

    public void setNgaylapphieuthanhtoan(Date ngaylapphieuthanhtoan) {
        this.ngaylapphieuthanhtoan = ngaylapphieuthanhtoan;
    }

    public Double getTienlai() {
        return tienlai;
    }

    public void setTienlai(Double tienlai) {
        this.tienlai = tienlai;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public Boolean getTtPtt() {
        return ttPtt;
    }

    public void setTtPtt(Boolean ttPtt) {
        this.ttPtt = ttPtt;
    }

    public Phieucamdo getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(Phieucamdo maphieu) {
        this.maphieu = maphieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maphieuthanhtoan != null ? maphieuthanhtoan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phieuthanhtoan)) {
            return false;
        }
        Phieuthanhtoan other = (Phieuthanhtoan) object;
        if ((this.maphieuthanhtoan == null && other.maphieuthanhtoan != null) || (this.maphieuthanhtoan != null && !this.maphieuthanhtoan.equals(other.maphieuthanhtoan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Phieuthanhtoan[ maphieuthanhtoan=" + maphieuthanhtoan + " ]";
    }
    
}
