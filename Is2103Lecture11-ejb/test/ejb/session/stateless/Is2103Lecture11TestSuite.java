package ejb.session.stateless;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({SystemUserSessionBeanTest.class, 
                        VenueSessionBeanTest.class, 
                        EventSessionBeanTest.class})

public class Is2103Lecture11TestSuite 
{
    @BeforeClass
    public static void setUpClass() throws Exception 
    {
        System.out.println("********** Is2103Lecture11TestSuite.setUpClass()");        
    }

    @AfterClass
    public static void tearDownClass() throws Exception 
    {
        System.out.println("********** Is2103Lecture11TestSuite.tearDownClass()");
    }

    @Before
    public void setUp() throws Exception 
    {
        System.out.println("********** Is2103Lecture11TestSuite.setUp()");
    }

    @After
    public void tearDown() throws Exception 
    {
        System.out.println("********** Is2103Lecture11TestSuite.tearDown()");
    }    
}
