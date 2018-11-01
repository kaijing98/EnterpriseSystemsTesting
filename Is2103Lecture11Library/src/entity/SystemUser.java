package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity

public class SystemUser implements Serializable 
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemUserId;
    private String userName;
    private String password;
    
    @OneToMany(mappedBy = "systemUser")
    private List<Event> events;

    
    
    public SystemUser()
    {
        events = new ArrayList<>();
    }

    
    
    public SystemUser(String userName, String password)
    {
        this();
        
        this.userName = userName;
        this.password = password;
    }
    
    
    
    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (systemUserId != null ? systemUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the systemUserId fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.systemUserId == null && other.systemUserId != null) || (this.systemUserId != null && !this.systemUserId.equals(other.systemUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SystemUser[id=" + systemUserId + "]";
    }
    
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}