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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "timeworks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timeworks.findAll", query = "SELECT t FROM Timeworks t"),
    @NamedQuery(name = "Timeworks.findByIdTimeWorks", query = "SELECT t FROM Timeworks t WHERE t.idTimeWorks = :idTimeWorks"),
    @NamedQuery(name = "Timeworks.findByStartWorks", query = "SELECT t FROM Timeworks t WHERE t.startWorks = :startWorks"),
    @NamedQuery(name = "Timeworks.findByStopWorkTime", query = "SELECT t FROM Timeworks t WHERE t.stopWorkTime = :stopWorkTime")})
public class Timeworks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTimeWorks")
    private Integer idTimeWorks;
    @Column(name = "startWorks")
    @Temporal(TemporalType.TIME)
    private Date startWorks;
    @Column(name = "stopWorkTime")
    @Temporal(TemporalType.TIME)
    private Date stopWorkTime;
     @JsonIgnore
    @JoinColumn(name = "idUser", referencedColumnName = "isUsers")
    @ManyToOne
    private Users idUser;

    public Timeworks() {
    }

    public Timeworks(Integer idTimeWorks) {
        this.idTimeWorks = idTimeWorks;
    }

    public Integer getIdTimeWorks() {
        return idTimeWorks;
    }

    public void setIdTimeWorks(Integer idTimeWorks) {
        this.idTimeWorks = idTimeWorks;
    }

    public Date getStartWorks() {
        return startWorks;
    }

    public void setStartWorks(Date startWorks) {
        this.startWorks = startWorks;
    }

    public Date getStopWorkTime() {
        return stopWorkTime;
    }

    public void setStopWorkTime(Date stopWorkTime) {
        this.stopWorkTime = stopWorkTime;
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
        hash += (idTimeWorks != null ? idTimeWorks.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timeworks)) {
            return false;
        }
        Timeworks other = (Timeworks) object;
        if ((this.idTimeWorks == null && other.idTimeWorks != null) || (this.idTimeWorks != null && !this.idTimeWorks.equals(other.idTimeWorks))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Timeworks[ idTimeWorks=" + idTimeWorks + " ]";
    }
    
}
