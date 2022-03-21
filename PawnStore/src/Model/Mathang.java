/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author NVS
 */
@Entity
@Table(name = "MATHANG")
@NamedQueries({
    @NamedQuery(name = "Mathang.findAll", query = "SELECT m FROM Mathang m"),
    @NamedQuery(name = "Mathang.findByMamathang", query = "SELECT m FROM Mathang m WHERE m.mamathang = :mamathang"),
    @NamedQuery(name = "Mathang.findByTenmathang", query = "SELECT m FROM Mathang m WHERE m.tenmathang = :tenmathang"),
    @NamedQuery(name = "Mathang.findByTtMh", query = "SELECT m FROM Mathang m WHERE m.ttMh = :ttMh")})
public class Mathang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MAMATHANG")
    private String mamathang;
    @Column(name = "TENMATHANG")
    private String tenmathang;
    @Column(name = "TT_MH")
    private Boolean ttMh;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mathang")
    private Collection<Ctphieucamdo> ctphieucamdoCollection;
    @JoinColumn(name = "MALOAI", referencedColumnName = "MALOAI")
    @ManyToOne
    private Loaimathang maloai;

    public Mathang() {
    }

    public Mathang(String mamathang) {
        this.mamathang = mamathang;
    }

    public String getMamathang() {
        return mamathang;
    }

    public void setMamathang(String mamathang) {
        this.mamathang = mamathang;
    }

    public String getTenmathang() {
        return tenmathang;
    }

    public void setTenmathang(String tenmathang) {
        this.tenmathang = tenmathang;
    }

    public Boolean getTtMh() {
        return ttMh;
    }

    public void setTtMh(Boolean ttMh) {
        this.ttMh = ttMh;
    }

    public Collection<Ctphieucamdo> getCtphieucamdoCollection() {
        return ctphieucamdoCollection;
    }

    public void setCtphieucamdoCollection(Collection<Ctphieucamdo> ctphieucamdoCollection) {
        this.ctphieucamdoCollection = ctphieucamdoCollection;
    }

    public Loaimathang getMaloai() {
        return maloai;
    }

    public void setMaloai(Loaimathang maloai) {
        this.maloai = maloai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mamathang != null ? mamathang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mathang)) {
            return false;
        }
        Mathang other = (Mathang) object;
        if ((this.mamathang == null && other.mamathang != null) || (this.mamathang != null && !this.mamathang.equals(other.mamathang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Mathang[ mamathang=" + mamathang + " ]";
    }
    
}
