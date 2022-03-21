/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author NVS
 */
@Embeddable
public class CtphieucamdoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MAMATHANG")
    private String mamathang;
    @Basic(optional = false)
    @Column(name = "MAPHIEU")
    private String maphieu;

    public CtphieucamdoPK() {
    }

    public CtphieucamdoPK(String mamathang, String maphieu) {
        this.mamathang = mamathang;
        this.maphieu = maphieu;
    }

    public String getMamathang() {
        return mamathang;
    }

    public void setMamathang(String mamathang) {
        this.mamathang = mamathang;
    }

    public String getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mamathang != null ? mamathang.hashCode() : 0);
        hash += (maphieu != null ? maphieu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtphieucamdoPK)) {
            return false;
        }
        CtphieucamdoPK other = (CtphieucamdoPK) object;
        if ((this.mamathang == null && other.mamathang != null) || (this.mamathang != null && !this.mamathang.equals(other.mamathang))) {
            return false;
        }
        if ((this.maphieu == null && other.maphieu != null) || (this.maphieu != null && !this.maphieu.equals(other.maphieu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.CtphieucamdoPK[ mamathang=" + mamathang + ", maphieu=" + maphieu + " ]";
    }
    
}
