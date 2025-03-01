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
@Table(name = "deletemessage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deletemessage.findAll", query = "SELECT d FROM Deletemessage d"),
    @NamedQuery(name = "Deletemessage.findByIsDeleteMessage", query = "SELECT d FROM Deletemessage d WHERE d.isDeleteMessage = :isDeleteMessage"),
    @NamedQuery(name = "Deletemessage.findByDeleteContent", query = "SELECT d FROM Deletemessage d WHERE d.deleteContent = :deleteContent")})
public class Deletemessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "isDeleteMessage")
    private Integer isDeleteMessage;
    @Size(max = 800)
    @Column(name = "deleteContent")
    private String deleteContent;

    public Deletemessage() {
    }

    public Deletemessage(Integer isDeleteMessage) {
        this.isDeleteMessage = isDeleteMessage;
    }

    public Integer getIsDeleteMessage() {
        return isDeleteMessage;
    }

    public void setIsDeleteMessage(Integer isDeleteMessage) {
        this.isDeleteMessage = isDeleteMessage;
    }

    public String getDeleteContent() {
        return deleteContent;
    }

    public void setDeleteContent(String deleteContent) {
        this.deleteContent = deleteContent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isDeleteMessage != null ? isDeleteMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deletemessage)) {
            return false;
        }
        Deletemessage other = (Deletemessage) object;
        if ((this.isDeleteMessage == null && other.isDeleteMessage != null) || (this.isDeleteMessage != null && !this.isDeleteMessage.equals(other.isDeleteMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Deletemessage[ isDeleteMessage=" + isDeleteMessage + " ]";
    }
    
}
