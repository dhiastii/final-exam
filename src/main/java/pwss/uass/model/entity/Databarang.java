/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pwss.uass.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "databarang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Databarang.findAll", query = "SELECT d FROM Databarang d"),
    @NamedQuery(name = "Databarang.findById", query = "SELECT d FROM Databarang d WHERE d.id = :id"),
    @NamedQuery(name = "Databarang.findByNamabrg", query = "SELECT d FROM Databarang d WHERE d.namabrg = :namabrg"),
    @NamedQuery(name = "Databarang.findByJumlahbrg", query = "SELECT d FROM Databarang d WHERE d.jumlahbrg = :jumlahbrg")})
public class Databarang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "namabrg")
    private String namabrg;
    @Column(name = "jumlahbrg")
    private Integer jumlahbrg;
    @Lob
    @Column(name = "gambarbrg")
    private byte[] gambarbrg;

    public Databarang() {
    }

    public Databarang(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamabrg() {
        return namabrg;
    }

    public void setNamabrg(String namabrg) {
        this.namabrg = namabrg;
    }

    public Integer getJumlahbrg() {
        return jumlahbrg;
    }

    public void setJumlahbrg(Integer jumlahbrg) {
        this.jumlahbrg = jumlahbrg;
    }

    public byte[] getGambarbrg() {
        return gambarbrg;
    }

    public void setGambarbrg(byte[] gambarbrg) {
        this.gambarbrg = gambarbrg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Databarang)) {
            return false;
        }
        Databarang other = (Databarang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pwss.uass.model.entity.Databarang[ id=" + id + " ]";
    }
    
}
