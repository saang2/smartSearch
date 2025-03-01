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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author timof
 */
@Entity
@Table(name = "room")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.findByIdRoom", query = "SELECT r FROM Room r WHERE r.idRoom = :idRoom"),
    @NamedQuery(name = "Room.findByNameRoom", query = "SELECT r FROM Room r WHERE r.nameRoom = :nameRoom"),
    @NamedQuery(name = "Room.findByDateCreateRoom", query = "SELECT r FROM Room r WHERE r.dateCreateRoom = :dateCreateRoom")})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRoom")
    private Integer idRoom;
    @Size(max = 100)
    @Column(name = "nameRoom")
    private String nameRoom;
    @Column(name = "DateCreateRoom")
    @Temporal(TemporalType.DATE)
    private Date dateCreateRoom;
    @OneToMany(mappedBy = "idRoom")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "idRoom")
    private Collection<Userroom> userroomCollection;

    public Room() {
    }

    public Room(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public Date getDateCreateRoom() {
        return dateCreateRoom;
    }

    public void setDateCreateRoom(Date dateCreateRoom) {
        this.dateCreateRoom = dateCreateRoom;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Userroom> getUserroomCollection() {
        return userroomCollection;
    }

    public void setUserroomCollection(Collection<Userroom> userroomCollection) {
        this.userroomCollection = userroomCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRoom != null ? idRoom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.idRoom == null && other.idRoom != null) || (this.idRoom != null && !this.idRoom.equals(other.idRoom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Room[ idRoom=" + idRoom + " ]";
    }
    
}
