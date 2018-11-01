package ejb.session.stateless;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import util.exception.VenueConflictException;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EventSessionBeanTest
{    
    private final EventSessionBeanRemote eventSessionBeanRemote = lookupEventSessionBeanRemote();
    
    
    
    public EventSessionBeanTest()
    {
        System.out.println("********** EventSessionBeanTest()");               
    }
    
    
    
    @BeforeClass
    public static void setUpClass() throws NamingException
    {
        System.out.println("********** EventSessionBeanTest.setUpClass()");
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException
    {
        System.out.println("********** EventSessionBeanTest.tearDownClass()");
    }
    
    @Before
    public void setUp() 
    {
        System.out.println("********** EventSessionBeanTest.setUp()");
    }
    
    @After
    public void tearDown()
    {
        System.out.println("********** EventSessionBeanTest.tearDown()");
    }
    
    
    
    @Test
    public void test01AddNewEvent1() throws VenueConflictException 
    {
        System.out.println("********** EventSessionBeanTest.test01AddNewEvent1");
        
        String eventName = "Lecture 1";
        Timestamp startDateTime = Timestamp.valueOf("2018-12-02 12:00:00");
        Timestamp endDateTime = Timestamp.valueOf("2018-12-02 14:00:00");
        Long venueId = 4l;
        Long systemUserId = 1l;
        Long result = eventSessionBeanRemote.addNewEvent(eventName, startDateTime, endDateTime, venueId, systemUserId);
        assertNotNull(result);
    }


    @Test
    public void test02AddNewEvent2() throws VenueConflictException 
    {
        System.out.println("********** EventSessionBeanTest.test02AddNewEvent2");
        
        String eventName = "First Consultation";
        Timestamp startDateTime = Timestamp.valueOf("2018-12-03 10:00:00");
        Timestamp endDateTime = Timestamp.valueOf("2018-12-03 11:00:00");
        Long venueId = 1l;
        Long systemUserId = 1l;
        Long result = eventSessionBeanRemote.addNewEvent(eventName, startDateTime, endDateTime, venueId, systemUserId);
        assertNotNull(result);
    }



    @Test(expected=VenueConflictException.class)
    public void test03AddNewEvent3() throws VenueConflictException
    {
        System.out.println("********** EventSessionBeanTest.test03AddNewEvent3");
        
        String eventName = "Tutorial 1";
        Timestamp startDateTime = Timestamp.valueOf("2018-12-03 10:00:00");
        Timestamp endDateTime = Timestamp.valueOf("2018-12-03 11:00:00");
        Long venueId = 1l;
        Long systemUserId = 1l;
        Long result = eventSessionBeanRemote.addNewEvent(eventName, startDateTime, endDateTime, venueId, systemUserId);
        assertNotNull(result);
    }
    
    
    
    private EventSessionBeanRemote lookupEventSessionBeanRemote() 
    {
        try 
        {
            Context context = new InitialContext();
            return (EventSessionBeanRemote) context.lookup("java:global/Is2103Lecture11/Is2103Lecture11-ejb/EventSessionBean!ejb.session.stateless.EventSessionBeanRemote");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}