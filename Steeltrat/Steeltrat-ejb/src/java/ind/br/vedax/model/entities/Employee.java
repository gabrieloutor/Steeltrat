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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "EMPLOYEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByIdEmployee", query = "SELECT e FROM Employee e WHERE e.idEmployee = :idEmployee"),
    @NamedQuery(name = "Employee.findByNameEmployee", query = "SELECT e FROM Employee e WHERE e.nameEmployee = :nameEmployee"),
    @NamedQuery(name = "Employee.findByCpf", query = "SELECT e FROM Employee e WHERE e.cpf = :cpf")})
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMPLOYEE")
    private Long idEmployee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "NAME_EMPLOYEE")
    private String nameEmployee;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "CPF")
    private String cpf;
    @JoinColumn(name = "ID_DEPARTAMENT", referencedColumnName = "ID_DEPARTAMENT")
    @ManyToOne
    private Departament idDepartament;
    @JoinColumn(name = "ID_POSITION", referencedColumnName = "ID_POSITION")
    @ManyToOne
    private PositionSteeltrat idPosition;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    @ManyToOne
    private UserSteeltrat idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmployee")
    private List<ItemsReceipt> itemsReceiptList;

    public Employee() {
    }

    public Employee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Employee(Long idEmployee, String nameEmployee, String cpf) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.cpf = cpf;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Departament getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(Departament idDepartament) {
        this.idDepartament = idDepartament;
    }

    public PositionSteeltrat getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(PositionSteeltrat idPosition) {
        this.idPosition = idPosition;
    }

    public UserSteeltrat getIdUser() {
        return idUser;
    }

    public void setIdUser(UserSteeltrat idUser) {
        this.idUser = idUser;
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
        hash += (idEmployee != null ? idEmployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.idEmployee == null && other.idEmployee != null) || (this.idEmployee != null && !this.idEmployee.equals(other.idEmployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "idEmployee=" + idEmployee + ", nameEmployee=" + nameEmployee + ", cpf=" + cpf + ", idDepartament=" + idDepartament + ", idPosition=" + idPosition + ", idUser=" + idUser + ", itemsReceiptList=" + itemsReceiptList + '}';
    }

}
