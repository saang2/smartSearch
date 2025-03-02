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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "editmessage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editmessage.findAll", query = "SELECT e FROM Editmessage e"),
    @NamedQuery(name = "Editmessage.findByIdEditMessage", query = "SELECT e FROM Editmessage e WHERE e.idEditMessage = :idEditMessage"),
    @NamedQuery(name = "Editmessage.findByEditContent", query = "SELECT e FROM Editmessage e WHERE e.editContent = :editContent")})
public class Editmessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEditMessage")
    private Integer idEditMessage;
    @Size(max = 800)
    @Column(name = "EditContent")
    private String editContent;

    public Editmessage() {
    }

    public Editmessage(Integer idEditMessage) {
        this.idEditMessage = idEditMessage;
    }

    public Integer getIdEditMessage() {
        return idEditMessage;
    }

    public void setIdEditMessage(Integer idEditMessage) {
        this.idEditMessage = idEditMessage;
    }

    public String getEditContent() {
        return editContent;
    }

    public void setEditContent(String editContent) {
        this.editContent = editContent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEditMessage != null ? idEditMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editmessage)) {
            return false;
        }
        Editmessage other = (Editmessage) object;
        if ((this.idEditMessage == null && other.idEditMessage != null) || (this.idEditMessage != null && !this.idEditMessage.equals(other.idEditMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Editmessage[ idEditMessage=" + idEditMessage + " ]";
    }
    
}
