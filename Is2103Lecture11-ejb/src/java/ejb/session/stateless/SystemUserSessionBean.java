package ejb.session.stateless;

import entity.SystemUser;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
@Local(SystemUserSessionBeanLocal.class)
@Remote(SystemUserSessionBeanRemote.class)

public class SystemUserSessionBean implements SystemUserSessionBeanLocal, SystemUserSessionBeanRemote
{
    @PersistenceContext(unitName = "Is2103Lecture11-ejbPU")
    private EntityManager em;
    
    
    
    @Override
    public SystemUser retrieveSystemUserBySystemUserId(Long systemUserId)
    {
        SystemUser systemUser = null;
        
        if(systemUserId != null)
        {
            systemUser = em.find(SystemUser.class, systemUserId);
        }
     
        return systemUser;
    }
    
    
    
    @Override
    public SystemUser retrieveSystemUserByUserName(String userName)
    {
        Query query = em.createQuery("SELECT su FROM SystemUser su WHERE su.userName = :inUserName");
        query.setParameter("inUserName", userName);
        SystemUser systemUser = null;
        
        try
        {
            systemUser = (SystemUser)query.getSingleResult();
        }
        catch(NoResultException ex)
        {
        }
        finally
        {
            return systemUser;
        }
    }
    
    
    
    @Override
    public Long loginSystemUser(String userName, String password)
    {
        Long systemUserId = null;
        
        
                
        SystemUser systemUser = retrieveSystemUserByUserName(userName);
        
        if(systemUser != null)
        {
            if(systemUser.getPassword().equals(password))
            {
                systemUserId = systemUser.getSystemUserId();
            }
        }
        
        return systemUserId;
    }
}