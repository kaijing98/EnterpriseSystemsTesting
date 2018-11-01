package ejb.session.stateless;

import entity.Venue;
import java.util.List;



public interface VenueSessionBeanLocal
{    
    public Venue retrieveVenueByVenueId(Long venueId);
    
    public List<Venue> retrieveAllVenues();    
}