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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GabrielOutor
 */
@Entity
@Table(name = "USERSTEELTRAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSteeltrat.findAll", query = "SELECT u FROM UserSteeltrat u"),
    @NamedQuery(name = "UserSteeltrat.findByIdUser", query = "SELECT u FROM UserSteeltrat u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "UserSteeltrat.findByUsername", query = "SELECT u FROM UserSteeltrat u WHERE u.username = :username"),
    @NamedQuery(name = "UserSteeltrat.findByPassword", query = "SELECT u FROM UserSteeltrat u WHERE u.password = :password"),
    @NamedQuery(name = "UserSteeltrat.findForLogin", query = "SELECT u FROM UserSteeltrat u WHERE u.password = :password AND u.username = :username")})
public class UserSteeltrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Long idUser;
    @Size(max = 15)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 30)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "idUser")
    private List<Employee> employeeList;

    public UserSteeltrat() {
        this.idUser = Long.valueOf(-1);
    }

    public UserSteeltrat(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSteeltrat)) {
            return false;
        }
        UserSteeltrat other = (UserSteeltrat) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserSteeltrat{" + "idUser=" + idUser + ", username=" + username + ", password=" + password + ", employeeList=" + employeeList + '}';
    }

}
