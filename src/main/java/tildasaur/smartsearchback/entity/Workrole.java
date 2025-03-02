/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tildasaur.smartsearchback.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "workrole")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workrole.findAll", query = "SELECT w FROM Workrole w"),
    @NamedQuery(name = "Workrole.findByIdWorkRole", query = "SELECT w FROM Workrole w WHERE w.idWorkRole = :idWorkRole"),
    @NamedQuery(name = "Workrole.findByNameWorkRole", query = "SELECT w FROM Workrole w WHERE w.nameWorkRole = :nameWorkRole")})
public class Workrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idWorkRole")
    private Integer idWorkRole;
    @Size(max = 100)
    @Column(name = "nameWorkRole")
    private String nameWorkRole;
    @OneToMany(mappedBy = "idWorkRole")
    private Collection<Users> usersCollection;

    public Workrole() {
    }

    public Workrole(Integer idWorkRole) {
        this.idWorkRole = idWorkRole;
    }

    public Integer getIdWorkRole() {
        return idWorkRole;
    }

    public void setIdWorkRole(Integer idWorkRole) {
        this.idWorkRole = idWorkRole;
    }

    public String getNameWorkRole() {
        return nameWorkRole;
    }

    public void setNameWorkRole(String nameWorkRole) {
        this.nameWorkRole = nameWorkRole;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWorkRole != null ? idWorkRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workrole)) {
            return false;
        }
        Workrole other = (Workrole) object;
        if ((this.idWorkRole == null && other.idWorkRole != null) || (this.idWorkRole != null && !this.idWorkRole.equals(other.idWorkRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Workrole[ idWorkRole=" + idWorkRole + " ]";
    }
    
}
