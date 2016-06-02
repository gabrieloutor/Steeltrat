package ind.br.vedax.model.entities;

import java.io.Serializable;
import java.util.Date;
import ind.br.vedax.util.DateUtil;
import ind.br.vedax.util.DecimalUtil;
import java.text.DecimalFormat;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "ITEMSRECEIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemsReceipt.findAll", query = "SELECT i FROM ItemsReceipt i ORDER BY i.idReceipt.idReceipt, i.idItemReceipt, i.dateItemReceipt"),
    @NamedQuery(name = "ItemsReceipt.findLastNumberByIdItemReceipt", query = "SELECT MAX(i.idItemReceipt) FROM ItemsReceipt i WHERE i.idReceipt = :idReceipt "),
    @NamedQuery(name = "ItemsReceipt.findByIdItemReceipt", query = "SELECT i FROM ItemsReceipt i WHERE i.idItemReceipt = :idItemReceipt"),
    @NamedQuery(name = "ItemsReceipt.findByNfClient", query = "SELECT i FROM ItemsReceipt i WHERE i.nfClient = :nfClient"),
    @NamedQuery(name = "ItemsReceipt.findByOrderClient", query = "SELECT i FROM ItemsReceipt i WHERE i.orderClient = :orderClient"),
    @NamedQuery(name = "ItemsReceipt.findByAmountPiece", query = "SELECT i FROM ItemsReceipt i WHERE i.amountPiece = :amountPiece"),
    @NamedQuery(name = "ItemsReceipt.findByAmountSpecimen", query = "SELECT i FROM ItemsReceipt i WHERE i.amountSpecimen = :amountSpecimen"),
    @NamedQuery(name = "ItemsReceipt.findByWeight", query = "SELECT i FROM ItemsReceipt i WHERE i.weight = :weight"),
    @NamedQuery(name = "ItemsReceipt.findByNumberTransport", query = "SELECT i FROM ItemsReceipt i WHERE i.numberTransport = :numberTransport"),
    @NamedQuery(name = "ItemsReceipt.findByMarkItemReceipt", query = "SELECT i FROM ItemsReceipt i WHERE i.markItemReceipt = :markItemReceipt"),
    @NamedQuery(name = "ItemsReceipt.findByDateItemReceipt", query = "SELECT i FROM ItemsReceipt i WHERE i.dateItemReceipt = :dateItemReceipt")})
public class ItemsReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ITEM_RECEIPT")
    private Long idItemReceipt;
    @Size(max = 15)
    @Column(name = "NF_CLIENT")
    private String nfClient;
    @Size(max = 25)
    @Column(name = "ORDER_CLIENT")
    private String orderClient;
    @Column(name = "AMOUNT_PIECE")
    private Integer amountPiece;
    @Column(name = "AMOUNT_SPECIMEN")
    private Integer amountSpecimen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "WEIGHT")
    private Double weight;
    @Column(name = "NUMBER_TRANSPORT")
    private Integer numberTransport;
    @Size(max = 150)
    @Column(name = "MARK_ITEM_RECEIPT")
    private String markItemReceipt;
    @Column(name = "DATE_ITEM_RECEIPT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateItemReceipt;
    @JoinColumn(name = "ID_EMPLOYEE", referencedColumnName = "ID_EMPLOYEE")
    @ManyToOne(optional = false)
    private Employee idEmployee;
    @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL")
    @ManyToOne
    private Material idMaterial;
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID_PRODUCT")
    @ManyToOne
    private Product idProduct;
    @JoinColumn(name = "ID_RECEIPT", referencedColumnName = "ID_RECEIPT")
    @ManyToOne(optional = false)
    private Receipt idReceipt;
    @JoinColumn(name = "ID_STANDARD", referencedColumnName = "ID_STANDARD")
    @ManyToOne
    private Standard idStandard;

    public ItemsReceipt() {
    }

    public ItemsReceipt(Long idItemReceipt) {
        this.idItemReceipt = idItemReceipt;
    }

    public Long getIdItemReceipt() {
        return idItemReceipt;
    }

    public void setIdItemReceipt(Long idItemReceipt) {
        this.idItemReceipt = idItemReceipt;
    }

    public String getNfClient() {
        return nfClient;
    }

    public void setNfClient(String nfClient) {
        this.nfClient = nfClient;
    }

    public String getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(String orderClient) {
        this.orderClient = orderClient;
    }

    public Integer getAmountPiece() {
        return amountPiece;
    }

    public String getFormattedAmountPiece() {
        return DecimalUtil.integerToDecimal(amountPiece);
    }

    public void setAmountPiece(Integer amountPiece) {
        this.amountPiece = amountPiece;
    }

    public Integer getAmountSpecimen() {
        return amountSpecimen;
    }
    
    public String getFormattedAmountSpecimen() {
        return DecimalUtil.integerToDecimal(amountSpecimen);
    }

    public void setAmountSpecimen(Integer amountSpecimen) {
        this.amountSpecimen = amountSpecimen;
    }

    public Double getWeight() {
        return weight;
    }
    
    public String getFormattedWeight() {
        return DecimalUtil.doubleToDecimal(weight);
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getNumberTransport() {
        return numberTransport;
    }

    public String getFormattedNumberTransport() {
        return DecimalUtil.integerToDecimal(numberTransport);
    }
    
    public void setNumberTransport(Integer numberTransport) {
        this.numberTransport = numberTransport;
    }

    public String getMarkItemReceipt() {
        return markItemReceipt;
    }

    public void setMarkItemReceipt(String markItemReceipt) {
        this.markItemReceipt = markItemReceipt;
    }

    public Date getDateItemReceipt() {
        return dateItemReceipt;
    }

    public void setDateItemReceipt(Date dateItemReceipt) {
        this.dateItemReceipt = dateItemReceipt;
    }

    public String getFormattedDate() {
        return DateUtil.date2String(dateItemReceipt);
    }

    public Employee getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Employee idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public Receipt getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(Receipt idReceipt) {
        this.idReceipt = idReceipt;
    }

    public Standard getIdStandard() {
        return idStandard;
    }

    public void setIdStandard(Standard idStandard) {
        this.idStandard = idStandard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idItemReceipt != null ? idItemReceipt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsReceipt)) {
            return false;
        }
        ItemsReceipt other = (ItemsReceipt) object;
        if ((this.idItemReceipt == null && other.idItemReceipt != null) || (this.idItemReceipt != null && !this.idItemReceipt.equals(other.idItemReceipt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItemsReceipt{" + "idItemReceipt=" + idItemReceipt + ", nfClient=" + nfClient + ", orderClient=" + orderClient + ", amountPiece=" + amountPiece + ", amountSpecimen=" + amountSpecimen + ", weight=" + weight + ", numberTransport=" + numberTransport + ", markItemReceipt=" + markItemReceipt + ", dateItemReceipt=" + dateItemReceipt + ", idEmployee=" + idEmployee + ", idMaterial=" + idMaterial + ", idProduct=" + idProduct + ", idReceipt=" + idReceipt + ", idStandard=" + idStandard + '}';
    }

}
