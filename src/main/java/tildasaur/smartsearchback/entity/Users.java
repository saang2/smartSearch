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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByIsUsers", query = "SELECT u FROM Users u WHERE u.isUsers = :isUsers"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByUserTelegramId", query = "SELECT u FROM Users u WHERE u.userTelegramId = :userTelegramId")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "isUsers")
    private Integer isUsers;
    @Size(max = 100)
    @Column(name = "userName")
    private String userName;
    @Size(max = 100)
    @Column(name = "userTelegramId")
    private String userTelegramId;
    @OneToMany(mappedBy = "idUserMentions")
    private Collection<Mentions> mentionsCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Timeworks> timeworksCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Userroom> userroomCollection;
     @JsonIgnore
    @JoinColumn(name = "idWorkRole", referencedColumnName = "idWorkRole")
    @ManyToOne
    private Workrole idWorkRole;

    public Users() {
    }

    public Users(Integer isUsers) {
        this.isUsers = isUsers;
    }

    public Integer getIsUsers() {
        return isUsers;
    }

    public void setIsUsers(Integer isUsers) {
        this.isUsers = isUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTelegramId() {
        return userTelegramId;
    }

    public void setUserTelegramId(String userTelegramId) {
        this.userTelegramId = userTelegramId;
    }

    @XmlTransient
    public Collection<Mentions> getMentionsCollection() {
        return mentionsCollection;
    }

    public void setMentionsCollection(Collection<Mentions> mentionsCollection) {
        this.mentionsCollection = mentionsCollection;
    }

    @XmlTransient
    public Collection<Timeworks> getTimeworksCollection() {
        return timeworksCollection;
    }

    public void setTimeworksCollection(Collection<Timeworks> timeworksCollection) {
        this.timeworksCollection = timeworksCollection;
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

    public Workrole getIdWorkRole() {
        return idWorkRole;
    }

    public void setIdWorkRole(Workrole idWorkRole) {
        this.idWorkRole = idWorkRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isUsers != null ? isUsers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.isUsers == null && other.isUsers != null) || (this.isUsers != null && !this.isUsers.equals(other.isUsers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tildasaur.smartsearchback.entity.Users[ isUsers=" + isUsers + " ]";
    }
    
}
