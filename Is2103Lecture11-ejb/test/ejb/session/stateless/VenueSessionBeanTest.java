package ejb.session.stateless;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class VenueSessionBeanTest 
{    
    private final VenueSessionBeanRemote venueSessionBeanRemote = lookupVenueSessionBeanRemote();
    
    
    
    public VenueSessionBeanTest() 
    {
        System.out.println("********** VenueSessionBeanTest()");
    }
    
    
    
    @BeforeClass
    public static void setUpClass() throws NamingException
    {
        System.out.println("********** VenueSessionBeanTest.setUpClass()");
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException
    {
        System.out.println("********** VenueSessionBeanTest.tearDownClass()");
    }
    
    @Before
    public void setUp() 
    {
        System.out.println("********** VenueSessionBeanTest.setUp()");
    }
    
    @After
    public void tearDown() 
    {
        System.out.println("********** VenueSessionBeanTest.tearDown()");
    }
    
    
    
    @Test
    public void test01RetrieveAllVenues() 
    {
        System.out.println("********** VenueSessionBeanTest.test01RetrieveAllVenues");
        
        List result = venueSessionBeanRemote.retrieveAllVenues();
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }
    
    
    
    private VenueSessionBeanRemote lookupVenueSessionBeanRemote() 
    {
        try 
        {
            Context context = new InitialContext();
            return (VenueSessionBeanRemote) context.lookup("java:global/Is2103Lecture11/Is2103Lecture11-ejb/VenueSessionBean!ejb.session.stateless.VenueSessionBeanRemote");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
