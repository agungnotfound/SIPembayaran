/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author YAS
 */
@Entity
@Table(name = "penjualan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Penjualan.findAll", query = "SELECT p FROM Penjualan p")
    , @NamedQuery(name = "Penjualan.findByIdPenjualan", query = "SELECT p FROM Penjualan p WHERE p.idPenjualan = :idPenjualan")
    , @NamedQuery(name = "Penjualan.findByTanggal", query = "SELECT p FROM Penjualan p WHERE p.tanggal = :tanggal")
    , @NamedQuery(name = "Penjualan.findByNoMeja", query = "SELECT p FROM Penjualan p WHERE p.noMeja = :noMeja")
    , @NamedQuery(name = "Penjualan.findByNama", query = "SELECT p FROM Penjualan p WHERE p.nama = :nama")})
public class Penjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_penjualan")
    private String idPenjualan;
    @Column(name = "tanggal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Column(name = "no_meja")
    private Integer noMeja;
    @Column(name = "nama")
    private String nama;
    @OneToMany(mappedBy = "idPenjualan")
    private List<DetailPenjualan> detailPenjualanList;

    public Penjualan() {
    }

    public Penjualan(String idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public String getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(String idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getNoMeja() {
        return noMeja;
    }

    public void setNoMeja(Integer noMeja) {
        this.noMeja = noMeja;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @XmlTransient
    public List<DetailPenjualan> getDetailPenjualanList() {
        return detailPenjualanList;
    }

    public void setDetailPenjualanList(List<DetailPenjualan> detailPenjualanList) {
        this.detailPenjualanList = detailPenjualanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPenjualan != null ? idPenjualan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penjualan)) {
            return false;
        }
        Penjualan other = (Penjualan) object;
        if ((this.idPenjualan == null && other.idPenjualan != null) || (this.idPenjualan != null && !this.idPenjualan.equals(other.idPenjualan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sipembayaran.Model.Penjualan[ idPenjualan=" + idPenjualan + " ]";
    }
    
}
