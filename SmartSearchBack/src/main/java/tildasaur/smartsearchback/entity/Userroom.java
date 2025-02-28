/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tildasaur.smartsearchback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "userroom")
@NamedQueries({
    @NamedQuery(name = "Userroom.findAll", query = "SELECT u FROM Userroom u"),
    @NamedQuery(name = "Userroom.findByIdUserRole", query = "SELECT u FROM Userroom u WHERE u.idUserRole = :idUserRole")})
public class Userroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUserRole")
    private Integer idUserRole;
    @JsonIgnore
    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne
    private Role idRole;
    @JsonIgnore
    @JoinColumn(name = "idRoom", referencedColumnName = "idRoom")
    @ManyToOne
    private Room idRoom;
    @JsonIgnore
    @JoinColumn(name = "idUser", referencedColumnName = "isUsers")
    @ManyToOne
    private Users idUser;

    public Userroom() {
    }

    public Userroom(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Integer getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Integer idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserRole != null ? idUserRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userroom)) {
            return false;
        }
        Userroom other = (Userroom) object;
        if ((this.idUserRole == null && other.idUserRole != null) || (this.idUserRole != null && !this.idUserRole.equals(other.idUserRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Userroom[ idUserRole=" + idUserRole + " ]";
    }
    
}
