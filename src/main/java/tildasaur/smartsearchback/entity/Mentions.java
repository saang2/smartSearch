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
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "mentions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mentions.findAll", query = "SELECT m FROM Mentions m"),
    @NamedQuery(name = "Mentions.findByIdMentions", query = "SELECT m FROM Mentions m WHERE m.idMentions = :idMentions")})
public class Mentions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMentions")
    private Integer idMentions;
    @JsonIgnore
    @JoinColumn(name = "idMessage", referencedColumnName = "idMessage")
    @ManyToOne
    private Message idMessage;
     @JsonIgnore
    @JoinColumn(name = "idUserMentions", referencedColumnName = "isUsers")
    @ManyToOne
    private Users idUserMentions;

    public Mentions() {
    }

    public Mentions(Integer idMentions) {
        this.idMentions = idMentions;
    }

    public Integer getIdMentions() {
        return idMentions;
    }

    public void setIdMentions(Integer idMentions) {
        this.idMentions = idMentions;
    }

    public Message getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Message idMessage) {
        this.idMessage = idMessage;
    }

    public Users getIdUserMentions() {
        return idUserMentions;
    }

    public void setIdUserMentions(Users idUserMentions) {
        this.idUserMentions = idUserMentions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMentions != null ? idMentions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mentions)) {
            return false;
        }
        Mentions other = (Mentions) object;
        if ((this.idMentions == null && other.idMentions != null) || (this.idMentions != null && !this.idMentions.equals(other.idMentions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Mentions[ idMentions=" + idMentions + " ]";
    }
    
}
