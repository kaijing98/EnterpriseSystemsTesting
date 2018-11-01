package ejb.session.stateless;

import entity.Venue;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
@Local(VenueSessionBeanLocal.class)
@Remote(VenueSessionBeanRemote.class)

public class VenueSessionBean implements VenueSessionBeanLocal, VenueSessionBeanRemote
{
    @PersistenceContext(unitName = "Is2103Lecture11-ejbPU")
    private EntityManager em;
    
    
    
    @Override
    public Venue retrieveVenueByVenueId(Long venueId)
    {
        Venue venue = null;
        
        if(venueId != null)
        {
            venue = em.find(Venue.class, venueId);
        }
     
        return venue;
    }
    
    
    
    @Override
    public List <Venue> retrieveAllVenues()
    {
        Query query = em.createQuery("SELECT v FROM Venue v");
        return query.getResultList();
    }
}