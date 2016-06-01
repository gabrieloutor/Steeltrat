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
@Table(name = "POSITIONSTEELTRAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionSteeltrat.findAll", query = "SELECT p FROM PositionSteeltrat p ORDER BY p.namePosition"),
    @NamedQuery(name = "PositionSteeltrat.findByIdPosition", query = "SELECT p FROM PositionSteeltrat p WHERE p.idPosition = :idPosition"),
    @NamedQuery(name = "PositionSteeltrat.findByNamePosition", query = "SELECT p FROM PositionSteeltrat p WHERE p.namePosition = :namePosition")})
public class PositionSteeltrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_POSITION")
    private Long idPosition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "NAME_POSITION")
    private String namePosition;
    @OneToMany(mappedBy = "idPosition")
    private List<Employee> employeeList;

    public PositionSteeltrat() {
    }

    public PositionSteeltrat(Long idPosition) {
        this.idPosition = idPosition;
    }

    public PositionSteeltrat(Long idPosition, String namePosition) {
        this.idPosition = idPosition;
        this.namePosition = namePosition;
    }

    public Long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPosition != null ? idPosition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PositionSteeltrat)) {
            return false;
        }
        PositionSteeltrat other = (PositionSteeltrat) object;
        if ((this.idPosition == null && other.idPosition != null) || (this.idPosition != null && !this.idPosition.equals(other.idPosition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PositionSteeltrat{" + "idPosition=" + idPosition + ", namePosition=" + namePosition + ", employeeList=" + employeeList + '}';
    }

}
