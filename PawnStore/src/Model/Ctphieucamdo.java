/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "CTPHIEUCAMDO")
@NamedQueries({
    @NamedQuery(name = "Ctphieucamdo.findAll", query = "SELECT c FROM Ctphieucamdo c"),
    @NamedQuery(name = "Ctphieucamdo.findByMamathang", query = "SELECT c FROM Ctphieucamdo c WHERE c.ctphieucamdoPK.mamathang = :mamathang"),
    @NamedQuery(name = "Ctphieucamdo.findByMaphieu", query = "SELECT c FROM Ctphieucamdo c WHERE c.ctphieucamdoPK.maphieu = :maphieu"),
    @NamedQuery(name = "Ctphieucamdo.findBySoluong", query = "SELECT c FROM Ctphieucamdo c WHERE c.soluong = :soluong"),
    @NamedQuery(name = "Ctphieucamdo.findByTiencam", query = "SELECT c FROM Ctphieucamdo c WHERE c.tiencam = :tiencam"),
    @NamedQuery(name = "Ctphieucamdo.findByNgaydonglai", query = "SELECT c FROM Ctphieucamdo c WHERE c.ngaydonglai = :ngaydonglai"),
    @NamedQuery(name = "Ctphieucamdo.findByTtCtpcd", query = "SELECT c FROM Ctphieucamdo c WHERE c.ttCtpcd = :ttCtpcd")})
public class Ctphieucamdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CtphieucamdoPK ctphieucamdoPK;
    @Column(name = "SOLUONG")
    private Integer soluong;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TIENCAM")
    private BigDecimal tiencam;
    @Column(name = "NGAYDONGLAI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaydonglai;
    @Column(name = "TT_CTPCD")
    private Boolean ttCtpcd;
    @JoinColumn(name = "MAMATHANG", referencedColumnName = "MAMATHANG", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mathang mathang;
    @JoinColumn(name = "MAPHIEU", referencedColumnName = "MAPHIEU", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Phieucamdo phieucamdo;

    public Ctphieucamdo() {
    }

    public Ctphieucamdo(CtphieucamdoPK ctphieucamdoPK) {
        this.ctphieucamdoPK = ctphieucamdoPK;
    }

    public Ctphieucamdo(String mamathang, String maphieu) {
        this.ctphieucamdoPK = new CtphieucamdoPK(mamathang, maphieu);
    }

    public CtphieucamdoPK getCtphieucamdoPK() {
        return ctphieucamdoPK;
    }

    public void setCtphieucamdoPK(CtphieucamdoPK ctphieucamdoPK) {
        this.ctphieucamdoPK = ctphieucamdoPK;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getTiencam() {
        return tiencam;
    }

    public void setTiencam(BigDecimal tiencam) {
        this.tiencam = tiencam;
    }

    public Date getNgaydonglai() {
        return ngaydonglai;
    }

    public void setNgaydonglai(Date ngaydonglai) {
        this.ngaydonglai = ngaydonglai;
    }

    public Boolean getTtCtpcd() {
        return ttCtpcd;
    }

    public void setTtCtpcd(Boolean ttCtpcd) {
        this.ttCtpcd = ttCtpcd;
    }

    public Mathang getMathang() {
        return mathang;
    }

    public void setMathang(Mathang mathang) {
        this.mathang = mathang;
    }

    public Phieucamdo getPhieucamdo() {
        return phieucamdo;
    }

    public void setPhieucamdo(Phieucamdo phieucamdo) {
        this.phieucamdo = phieucamdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctphieucamdoPK != null ? ctphieucamdoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ctphieucamdo)) {
            return false;
        }
        Ctphieucamdo other = (Ctphieucamdo) object;
        if ((this.ctphieucamdoPK == null && other.ctphieucamdoPK != null) || (this.ctphieucamdoPK != null && !this.ctphieucamdoPK.equals(other.ctphieucamdoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Ctphieucamdo[ ctphieucamdoPK=" + ctphieucamdoPK + " ]";
    }
    
}
