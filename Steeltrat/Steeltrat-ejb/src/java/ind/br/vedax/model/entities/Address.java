package ind.br.vedax.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a ORDER BY a.zipcode, a.numberAddress"),
    @NamedQuery(name = "Address.findByIdAddress", query = "SELECT a FROM Address a WHERE a.idAddress = :idAddress"),
    @NamedQuery(name = "Address.findByZipcode", query = "SELECT a FROM Address a WHERE a.zipcode = :zipcode"),
    @NamedQuery(name = "Address.findByZipcodeAndNumberAddress", query = "SELECT a FROM Address a WHERE a.zipcode = :zipcode AND a.numberAddress = :numberAddress"),
    @NamedQuery(name = "Address.findByNumberAddress", query = "SELECT a FROM Address a WHERE a.numberAddress = :numberAddress")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ADDRESS")
    private Long idAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMBER_ADDRESS")
    private int numberAddress;

    public Address() {
    }

    public Address(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Address(Long idAddress, String zipcode, int numberAddress) {
        this.idAddress = idAddress;
        this.zipcode = zipcode;
        this.numberAddress = numberAddress;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getFormattedZipcode(){
        return zipcode.substring(0,5)+"-"+zipcode.substring(5,8);
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    
    public int getNumberAddress() {
        return numberAddress;
    }

    public void setNumberAddress(int numberAddress) {
        this.numberAddress = numberAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAddress != null ? idAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.idAddress == null && other.idAddress != null) || (this.idAddress != null && !this.idAddress.equals(other.idAddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Address{" + "idAddress=" + idAddress + ", zipcode=" + zipcode + ", numberAddress=" + numberAddress + '}';
    }

}
