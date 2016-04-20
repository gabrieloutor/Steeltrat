package ind.br.vedax.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "RECEIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r ORDER BY r.idReceipt"),
    @NamedQuery(name = "Receipt.findLastNumberByIdReceipt", query = "SELECT MAX(r.idReceipt) FROM Receipt r"),
    @NamedQuery(name = "Receipt.findByIdReceipt", query = "SELECT r FROM Receipt r WHERE r.idReceipt = :idReceipt")})
public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RECEIPT")
    private Long idReceipt;
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Client idClient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReceipt")
    private List<ItemsReceipt> itemsReceiptList;

    public Receipt() {
    }

    public Receipt(Long idReceipt) {
        this.idReceipt = idReceipt;
    }

    public Long getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(Long idReceipt) {
        this.idReceipt = idReceipt;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
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
        hash += (idReceipt != null ? idReceipt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.idReceipt == null && other.idReceipt != null) || (this.idReceipt != null && !this.idReceipt.equals(other.idReceipt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receipt{" + "idReceipt=" + idReceipt + ", idClient=" + idClient + ", itemsReceiptList=" + itemsReceiptList + '}';
    }

}
