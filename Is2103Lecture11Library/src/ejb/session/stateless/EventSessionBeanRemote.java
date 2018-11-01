package ejb.session.stateless;

import entity.Event;
import entity.Venue;
import java.sql.Timestamp;
import java.util.List;
import util.exception.VenueConflictException;



public interface EventSessionBeanRemote 
{
    public List<Event> getAllEvents();
    
    public List <Event> getMyEvents(Long systemUserId);
    
    public Long addNewEvent(String eventName, Timestamp startDateTime, Timestamp endDateTime, Long venueId, Long systemUserId) throws VenueConflictException;
    
    public void approveEvents();
    
    public List<Event> retrieveEventsBySystemUserId(Long systemUserId);
    
    public List<Event> retrieveEventsByVenueId(Long venueId);
}