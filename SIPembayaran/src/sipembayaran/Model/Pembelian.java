/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sipembayaran.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author YAS
 */
@Entity
@Table(name = "pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pembelian.findAll", query = "SELECT p FROM Pembelian p")
    , @NamedQuery(name = "Pembelian.findByIdPembelian", query = "SELECT p FROM Pembelian p WHERE p.idPembelian = :idPembelian")
    , @NamedQuery(name = "Pembelian.findByTanggal", query = "SELECT p FROM Pembelian p WHERE p.tanggal = :tanggal")
    , @NamedQuery(name = "Pembelian.findBySupplier", query = "SELECT p FROM Pembelian p WHERE p.supplier = :supplier")})
public class Pembelian implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_pembelian")
    private String idPembelian;
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(name = "supplier")
    private String supplier;
    @OneToMany(mappedBy = "idPembelian")
    private List<DetailPembelian> detailPembelianList;

    public Pembelian() {
    }

    public Pembelian(String idPembelian) {
        this.idPembelian = idPembelian;
    }

    public String getIdPembelian() {
        return idPembelian;
    }

    public void setIdPembelian(String idPembelian) {
        String oldIdPembelian = this.idPembelian;
        this.idPembelian = idPembelian;
        changeSupport.firePropertyChange("idPembelian", oldIdPembelian, idPembelian);
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        Date oldTanggal = this.tanggal;
        this.tanggal = tanggal;
        changeSupport.firePropertyChange("tanggal", oldTanggal, tanggal);
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        String oldSupplier = this.supplier;
        this.supplier = supplier;
        changeSupport.firePropertyChange("supplier", oldSupplier, supplier);
    }

    @XmlTransient
    public List<DetailPembelian> getDetailPembelianList() {
        return detailPembelianList;
    }

    public void setDetailPembelianList(List<DetailPembelian> detailPembelianList) {
        this.detailPembelianList = detailPembelianList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPembelian != null ? idPembelian.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembelian)) {
            return false;
        }
        Pembelian other = (Pembelian) object;
        if ((this.idPembelian == null && other.idPembelian != null) || (this.idPembelian != null && !this.idPembelian.equals(other.idPembelian))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sipembayaran.Model.Pembelian[ idPembelian=" + idPembelian + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
