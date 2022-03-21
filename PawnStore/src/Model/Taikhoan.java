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
@Table(name = "TAIKHOAN")
@NamedQueries({
    @NamedQuery(name = "Taikhoan.findAll", query = "SELECT t FROM Taikhoan t"),
    @NamedQuery(name = "Taikhoan.findByTenTaiKhoan", query = "SELECT t FROM Taikhoan t WHERE t.tenTaiKhoan = :tenTaiKhoan"),
    @NamedQuery(name = "Taikhoan.findByMatKhau", query = "SELECT t FROM Taikhoan t WHERE t.matKhau = :matKhau"),
    @NamedQuery(name = "Taikhoan.findByHoVaTenUser", query = "SELECT t FROM Taikhoan t WHERE t.hoVaTenUser = :hoVaTenUser")})
public class Taikhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TEN_TAI_KHOAN")
    private String tenTaiKhoan;
    @Column(name = "MAT_KHAU")
    private String matKhau;
    @Column(name = "HO_VA_TEN_USER")
    private String hoVaTenUser;
    @OneToMany(mappedBy = "tenTaiKhoan")
    private Collection<Phieucamdo> phieucamdoCollection;

    public Taikhoan() {
    }
    
    public Taikhoan(String tenTK, String mk, String hovaten) {
        this.setTenTaiKhoan(tenTK);
        this.setMatKhau(mk);
        this.setHoVaTenUser(hovaten);
    }
    public Taikhoan(Taikhoan tk) {
        this.setTenTaiKhoan(tk.tenTaiKhoan);
        this.setMatKhau(tk.matKhau);
        this.setHoVaTenUser(tk.hoVaTenUser);
    }

    public Taikhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoVaTenUser() {
        return hoVaTenUser;
    }

    public void setHoVaTenUser(String hoVaTenUser) {
        this.hoVaTenUser = hoVaTenUser;
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
        hash += (tenTaiKhoan != null ? tenTaiKhoan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taikhoan)) {
            return false;
        }
        Taikhoan other = (Taikhoan) object;
        if ((this.tenTaiKhoan == null && other.tenTaiKhoan != null) || (this.tenTaiKhoan != null && !this.tenTaiKhoan.equals(other.tenTaiKhoan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Taikhoan[ tenTaiKhoan=" + tenTaiKhoan + " ]";
    }
    
}
