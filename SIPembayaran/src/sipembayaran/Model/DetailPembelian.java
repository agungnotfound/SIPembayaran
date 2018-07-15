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
@Table(name = "detail_pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailPembelian.findAll", query = "SELECT d FROM DetailPembelian d")
    , @NamedQuery(name = "DetailPembelian.findByIddetail", query = "SELECT d FROM DetailPembelian d WHERE d.iddetail = :iddetail")
    , @NamedQuery(name = "DetailPembelian.findByNamaBarang", query = "SELECT d FROM DetailPembelian d WHERE d.namaBarang = :namaBarang")
    , @NamedQuery(name = "DetailPembelian.findByHarga", query = "SELECT d FROM DetailPembelian d WHERE d.harga = :harga")
    , @NamedQuery(name = "DetailPembelian.findByJumlah", query = "SELECT d FROM DetailPembelian d WHERE d.jumlah = :jumlah")
    , @NamedQuery(name = "DetailPembelian.findByTotal", query = "SELECT d FROM DetailPembelian d WHERE d.total = :total")})
public class DetailPembelian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetail")
    private Integer iddetail;
    @Column(name = "nama_barang")
    private String namaBarang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "jumlah")
    private Double jumlah;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "id_pembelian", referencedColumnName = "id_pembelian")
    @ManyToOne
    private Pembelian idPembelian;

    public DetailPembelian() {
    }

    public DetailPembelian(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public Integer getIddetail() {
        return iddetail;
    }

    public void setIddetail(Integer iddetail) {
        this.iddetail = iddetail;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Pembelian getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(Pembelian idPembelian) {
        this.idPembelian = idPembelian;
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
        if (!(object instanceof DetailPembelian)) {
            return false;
        }
        DetailPembelian other = (DetailPembelian) object;
        if ((this.iddetail == null && other.iddetail != null) || (this.iddetail != null && !this.iddetail.equals(other.iddetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sipembayaran.Model.DetailPembelian[ iddetail=" + iddetail + " ]";
    }
    
}
