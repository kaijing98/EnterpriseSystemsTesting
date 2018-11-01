package ejb.session.stateless;

import entity.SystemUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SystemUserSessionBeanTest
{    
    private final SystemUserSessionBeanRemote systemUserSessionBeanRemote = lookupSystemUserSessionBeanRemote();
    
    
    
    public SystemUserSessionBeanTest()
    {
        System.out.println("********** SystemUserSessionBeanTest()");
    }
    
    
    
    @BeforeClass
    public static void setUpClass() throws NamingException
    {
        System.out.println("********** SystemUserSessionBeanTest.setUpClass()");        
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException
    {
        System.out.println("********** SystemUserSessionBeanTest.tearDownClass()");
    }
    
    @Before
    public void setUp() 
    {
        System.out.println("********** SystemUserSessionBeanTest.setUp()");
    }
    
    @After
    public void tearDown()
    {
        System.out.println("********** SystemUserSessionBeanTest.tearDown()");
    }
    
    
    
    @Test
    public void test01RetrieveSystemUser1()
    {
        System.out.println("********** SystemUserSessionBeanTest.test01RetrieveSystemUser1");
        
        String userName = "user1";        
        SystemUser expResult = new SystemUser();
        expResult.setUserName(userName);
        expResult.setSystemUserId(1l);
        SystemUser result = systemUserSessionBeanRemote.retrieveSystemUserByUserName(userName);
        assertEquals(expResult, result);
    }

    
    
    @Test
    public void test02RetrieveSystemUser2() 
    {
        System.out.println("********** SystemUserSessionBeanTest.test02RetrieveSystemUser2");
        
        String userName = "user2";        
        SystemUser expResult = new SystemUser();
        expResult.setUserName(userName);
        expResult.setSystemUserId(2l);
        SystemUser result = systemUserSessionBeanRemote.retrieveSystemUserByUserName(userName);
        assertEquals(expResult, result);
    }

    
    
    @Test
    public void test03RetrieveSystemUser9() 
    {
        System.out.println("********** SystemUserSessionBeanTest.test03RetrieveSystemUser9");
        
        String userName = "user9";        
        SystemUser result = systemUserSessionBeanRemote.retrieveSystemUserByUserName(userName);
        assertNull(result);
    }
    
    
    
    private SystemUserSessionBeanRemote lookupSystemUserSessionBeanRemote() 
    {
        try 
        {
            Context context = new InitialContext();
            return (SystemUserSessionBeanRemote) context.lookup("java:global/Is2103Lecture11/Is2103Lecture11-ejb/SystemUserSessionBean!ejb.session.stateless.SystemUserSessionBeanRemote");
        }
        catch (NamingException ne)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
