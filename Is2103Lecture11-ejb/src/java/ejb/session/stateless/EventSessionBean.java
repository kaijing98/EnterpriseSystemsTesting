package ejb.session.stateless;

import entity.Event;
import entity.SystemUser;
import entity.Venue;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.VenueConflictException;


@Stateless
@Local(EventSessionBeanLocal.class)
@Remote(EventSessionBeanRemote.class)

public class EventSessionBean implements EventSessionBeanLocal, EventSessionBeanRemote
{
    @PersistenceContext(unitName = "Is2103Lecture11-ejbPU")
    private EntityManager em;
        
    @EJB
    private SystemUserSessionBeanLocal systemUserSessionBeanLocal;
    @EJB
    private VenueSessionBeanLocal venueSessionBeanLocal;

    
        
    @Override
    public List <Event> getAllEvents()
    {
        Query query = em.createQuery("SELECT e FROM Event e");
        List <Event> events = query.getResultList();
        return events;
    }

    
    
    @Override
    public List <Event> getMyEvents(Long systemUserId)
    {
        SystemUser systemUser = systemUserSessionBeanLocal.retrieveSystemUserBySystemUserId(systemUserId);
        
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.systemUser = :inSytemUser");
        query.setParameter("inSytemUser", systemUser);
        
        return query.getResultList();
    }

    
    
    private Boolean checkVenueConflict(Timestamp startDateTime, Timestamp endDateTime, Long venueId)
    {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.venue = :venue AND e.startDateTime <= :endDateTime AND e.endDateTime >= :startDateTime");
        query.setParameter("venue", venueSessionBeanLocal.retrieveVenueByVenueId(venueId));
        query.setParameter("startDateTime", startDateTime);
        query.setParameter("endDateTime", endDateTime);
        
        List resultList = query.getResultList();
        
        if(resultList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    
    
    @Override
    public Long addNewEvent(String eventName, Timestamp startDateTime, Timestamp endDateTime, Long venueId, Long systemUserId) throws VenueConflictException
    {
        if(!checkVenueConflict(startDateTime, endDateTime, venueId))
        {
            SystemUser systemUser = systemUserSessionBeanLocal.retrieveSystemUserBySystemUserId(systemUserId);
            
            Event event = new Event(eventName, startDateTime, endDateTime, "Pending");         
            event.setVenue(venueSessionBeanLocal.retrieveVenueByVenueId(venueId));
            event.setSystemUser(systemUser);
            systemUser.getEvents().add(event);
            
            em.persist(event);
            em.flush();
            
            return event.getEventId();
        }
        else
        {
            throw new VenueConflictException("Venue conflict: " + venueId);
        }   
    }
    
    
    
    @Override
    public void approveEvents()
    {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.status = :inStatus");
        query.setParameter("inStatus", "Pending");
        List<Event> pendingEvents = query.getResultList();
        
        for(Event pendingEvent : pendingEvents)
        {            
            pendingEvent.setStatus("Approved");            
        }
    }
    
    
    
    @Override
    public List<Event> retrieveEventsBySystemUserId(Long systemUserId)
    {
        SystemUser systemUser = systemUserSessionBeanLocal.retrieveSystemUserBySystemUserId(systemUserId);
        
        if(systemUser != null)
        {
            Query query = em.createNamedQuery("selectEventsBySystemUser");
            query.setParameter("inSystemUser", systemUser);
            
            List<Event> events =  query.getResultList();
            
            for(Event event:events)
            {
                event.getVenue();
                em.detach(event.getSystemUser());
                event.getSystemUser().setPassword(null);
            }

            return events;
        }
        else
        {
            return new ArrayList<>();
        }
    }
    
    
    
    @Override
    public List<Event> retrieveEventsByVenueId(Long venueId)
    {
        Venue venue = venueSessionBeanLocal.retrieveVenueByVenueId(venueId);
        
        if(venue != null)
        {
            Query query = em.createNamedQuery("selectEventsByVenue");
            query.setParameter("inVenue", venue);
            
            List<Event> events =  query.getResultList();
            
            for(Event event:events)
            {
                event.getVenue();
                em.detach(event.getSystemUser());
                event.getSystemUser().setPassword(null);
            }

            return events;
        }
        else
        {
            return new ArrayList<>();
        }
    }
}