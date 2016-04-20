package ind.br.vedax.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "STANDARD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Standard.findAll", query = "SELECT s FROM Standard s"),
    @NamedQuery(name = "Standard.findByIdStandard", query = "SELECT s FROM Standard s WHERE s.idStandard = :idStandard"),
    @NamedQuery(name = "Standard.findByNameStandard", query = "SELECT s FROM Standard s WHERE s.nameStandard = :nameStandard"),
    @NamedQuery(name = "Standard.findByMarkStandard", query = "SELECT s FROM Standard s WHERE s.markStandard = :markStandard")})
public class Standard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_STANDARD")
    private Long idStandard;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME_STANDARD")
    private String nameStandard;
    @Size(max = 150)
    @Column(name = "MARK_STANDARD")
    private String markStandard;
    @OneToMany(mappedBy = "idStandard")
    private List<ItemsReceipt> itemsReceiptList;

    public Standard() {
    }

    public Standard(Long idStandard) {
        this.idStandard = idStandard;
    }

    public Standard(Long idStandard, String nameStandard) {
        this.idStandard = idStandard;
        this.nameStandard = nameStandard;
    }

    public Long getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(Long idStandard) {
        this.idStandard = idStandard;
    }

    public String getNameStandard() {
        return nameStandard;
    }

    public void setNameStandard(String nameStandard) {
        this.nameStandard = nameStandard;
    }

    public String getMarkStandard() {
        return markStandard;
    }

    public void setMarkStandard(String markStandard) {
        this.markStandard = markStandard;
    }

    @XmlTransient
    public List<ItemsReceipt> getItemsReceiptList() {
        return itemsReceiptList;
    }

    public void setItemsReceiptList(List<ItemsReceipt> itemsReceiptList) {
        this.itemsReceiptList = itemsReceiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStandard != null ? idStandard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Standard)) {
            return false;
        }
        Standard other = (Standard) object;
        if ((this.idStandard == null && other.idStandard != null) || (this.idStandard != null && !this.idStandard.equals(other.idStandard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Standard{" + "idStandard=" + idStandard + ", nameStandard=" + nameStandard + ", markStandard=" + markStandard + ", itemsReceiptList=" + itemsReceiptList + '}';
    }

}
