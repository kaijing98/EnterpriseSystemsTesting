package ejb.session.stateless;

import entity.SystemUser;



public interface SystemUserSessionBeanRemote 
{
    public SystemUser retrieveSystemUserBySystemUserId(Long systemUserId);
    
    public SystemUser retrieveSystemUserByUserName(String userName);   

    public Long loginSystemUser(String userName, String password);  
}