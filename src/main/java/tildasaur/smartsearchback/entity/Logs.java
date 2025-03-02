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
@Table(name = "logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByIdLogs", query = "SELECT l FROM Logs l WHERE l.idLogs = :idLogs"),
    @NamedQuery(name = "Logs.findByDataCreate", query = "SELECT l FROM Logs l WHERE l.dataCreate = :dataCreate"),
    @NamedQuery(name = "Logs.findByDataEditing", query = "SELECT l FROM Logs l WHERE l.dataEditing = :dataEditing"),
    @NamedQuery(name = "Logs.findByDateDeliting", query = "SELECT l FROM Logs l WHERE l.dateDeliting = :dateDeliting")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLogs")
    private Integer idLogs;
    @Column(name = "dataCreate")
    @Temporal(TemporalType.TIME)
    private Date dataCreate;
    @Column(name = "dataEditing")
    @Temporal(TemporalType.TIME)
    private Date dataEditing;
    @Column(name = "dateDeliting")
    @Temporal(TemporalType.TIME)
    private Date dateDeliting;
     @JsonIgnore
    @JoinColumn(name = "idMessage", referencedColumnName = "idMessage")
    @ManyToOne
    private Message idMessage;

    public Logs() {
    }

    public Logs(Integer idLogs) {
        this.idLogs = idLogs;
    }

    public Integer getIdLogs() {
        return idLogs;
    }

    public void setIdLogs(Integer idLogs) {
        this.idLogs = idLogs;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public Date getDataEditing() {
        return dataEditing;
    }

    public void setDataEditing(Date dataEditing) {
        this.dataEditing = dataEditing;
    }

    public Date getDateDeliting() {
        return dateDeliting;
    }

    public void setDateDeliting(Date dateDeliting) {
        this.dateDeliting = dateDeliting;
    }

    public Message getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Message idMessage) {
        this.idMessage = idMessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogs != null ? idLogs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.idLogs == null && other.idLogs != null) || (this.idLogs != null && !this.idLogs.equals(other.idLogs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Logs[ idLogs=" + idLogs + " ]";
    }
    
}
