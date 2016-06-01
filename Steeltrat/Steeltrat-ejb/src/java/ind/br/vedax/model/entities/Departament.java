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
@Table(name = "DEPARTAMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departament.findAll", query = "SELECT d FROM Departament d ORDER BY d.nameDepartament"),
    @NamedQuery(name = "Departament.findByIdDepartament", query = "SELECT d FROM Departament d WHERE d.idDepartament = :idDepartament"),
    @NamedQuery(name = "Departament.findByNameDepartament", query = "SELECT d FROM Departament d WHERE d.nameDepartament = :nameDepartament")})
public class Departament implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DEPARTAMENT")
    private Long idDepartament;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "NAME_DEPARTAMENT")
    private String nameDepartament;
    @OneToMany(mappedBy = "idDepartament")
    private List<Employee> employeeList;

    public Departament() {
    }

    public Departament(Long idDepartament) {
        this.idDepartament = idDepartament;
    }

    public Departament(Long idDepartament, String nameDepartament) {
        this.idDepartament = idDepartament;
        this.nameDepartament = nameDepartament;
    }

    public Long getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(Long idDepartament) {
        this.idDepartament = idDepartament;
    }

    public String getNameDepartament() {
        return nameDepartament;
    }

    public void setNameDepartament(String nameDepartament) {
        this.nameDepartament = nameDepartament;
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
        hash += (idDepartament != null ? idDepartament.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departament)) {
            return false;
        }
        Departament other = (Departament) object;
        if ((this.idDepartament == null && other.idDepartament != null) || (this.idDepartament != null && !this.idDepartament.equals(other.idDepartament))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Departament{" + "idDepartament=" + idDepartament + ", nameDepartament=" + nameDepartament + ", employeeList=" + employeeList + '}';
    }

}
