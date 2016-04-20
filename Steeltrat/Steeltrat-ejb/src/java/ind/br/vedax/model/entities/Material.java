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
@Table(name = "MATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByIdMaterial", query = "SELECT m FROM Material m WHERE m.idMaterial = :idMaterial"),
    @NamedQuery(name = "Material.findByNameMaterial", query = "SELECT m FROM Material m WHERE m.nameMaterial = :nameMaterial"),
    @NamedQuery(name = "Material.findByMarkMaterial", query = "SELECT m FROM Material m WHERE m.markMaterial = :markMaterial")})
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATERIAL")
    private Long idMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAME_MATERIAL")
    private String nameMaterial;
    @Size(max = 150)
    @Column(name = "MARK_MATERIAL")
    private String markMaterial;
    @OneToMany(mappedBy = "idMaterial")
    private List<ItemsReceipt> itemsReceiptList;

    public Material() {
    }

    public Material(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material(Long idMaterial, String nameMaterial) {
        this.idMaterial = idMaterial;
        this.nameMaterial = nameMaterial;
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public String getMarkMaterial() {
        return markMaterial;
    }

    public void setMarkMaterial(String markMaterial) {
        this.markMaterial = markMaterial;
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
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Material{" + "idMaterial=" + idMaterial + ", nameMaterial=" + nameMaterial + ", markMaterial=" + markMaterial + ", itemsReceiptList=" + itemsReceiptList + '}';
    }

}
