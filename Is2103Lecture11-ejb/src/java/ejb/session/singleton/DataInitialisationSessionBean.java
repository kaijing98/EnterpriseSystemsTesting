package ejb.session.singleton;

import entity.SystemUser;
import entity.Venue;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Singleton
@LocalBean
@Startup

public class DataInitialisationSessionBean
{

    @PersistenceContext(unitName = "Is2103Lecture11-ejbPU")
    private EntityManager em;
    
    
    
    public DataInitialisationSessionBean()
    {
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        if(em.find(SystemUser.class, 1l) == null)
        {
            initialiseData();
        }
    }
    
    
    
    private void initialiseData()
    {
        SystemUser systemUser = new SystemUser("user1", "password");
        em.persist(systemUser);
        em.flush();
        systemUser = new SystemUser("user2", "password");
        em.persist(systemUser);
        em.flush();
        systemUser = new SystemUser("user3", "password");
        em.persist(systemUser);
        em.flush();
        
        Venue venue = new Venue("DR1", "COM1-B1-14B");
        em.persist(venue);
        em.flush();
        venue = new Venue("DR2", "COM1-B1-14A");
        em.persist(venue);
        em.flush();
        venue = new Venue("DR6", "COM2-02-12");
        em.persist(venue);
        em.flush();
        venue = new Venue("LT15", "AS6-01");
        em.persist(venue);
        em.flush();
    }
}