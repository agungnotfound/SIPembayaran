/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author YAS
 */
@Entity
@Table(name = "detail_penjualan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailPenjualan.findAll", query = "SELECT d FROM DetailPenjualan d")
    , @NamedQuery(name = "DetailPenjualan.findByIddetail", query = "SELECT d FROM DetailPenjualan d WHERE d.iddetail = :iddetail")
    , @NamedQuery(name = "DetailPenjualan.findByNama", query = "SELECT d FROM DetailPenjualan d WHERE d.nama = :nama")
    , @NamedQuery(name = "DetailPenjualan.findByHarga", query = "SELECT d FROM DetailPenjualan d WHERE d.harga = :harga")
    , @NamedQuery(name = "DetailPenjualan.findByJumlah", query = "SELECT d FROM DetailPenjualan d WHERE d.jumlah = :jumlah")
    , @NamedQuery(name = "DetailPenjualan.findByTotal", query = "SELECT d FROM DetailPenjualan d WHERE d.total = :total")})
public class DetailPenjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetail")
    private Integer iddetail;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "id_penjualan", referencedColumnName = "id_penjualan")
    @ManyToOne
    private Penjualan idPenjualan;
    @JoinColumn(name = "id_menu", referencedColumnName = "id_menu")
    @ManyToOne
    private Menu idMenu;

    public DetailPenjualan() {
    }

    public DetailPenjualan(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public DetailPenjualan(Integer iddetail, String nama) {
        this.iddetail = iddetail;
        this.nama = nama;
    }

    public Integer getIddetail() {
        return iddetail;
    }

    public void setIddetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Penjualan getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(Penjualan idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetail != null ? iddetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailPenjualan)) {
            return false;
        }
        DetailPenjualan other = (DetailPenjualan) object;
        if ((this.iddetail == null && other.iddetail != null) || (this.iddetail != null && !this.iddetail.equals(other.iddetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sipembayaran.Model.DetailPenjualan[ iddetail=" + iddetail + " ]";
    }
    
}
