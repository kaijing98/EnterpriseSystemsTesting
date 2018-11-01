package is2103lecture11client;

import ejb.session.stateless.EventSessionBeanRemote;
import javax.ejb.EJB;



public class Main 
{
    @EJB
    private static EventSessionBeanRemote eventSessionBeanRemote;
    
    
    
    public static void main(String[] args) 
    {
        MainApp mainApp = new MainApp(eventSessionBeanRemote);
        mainApp.runApp();
    }    
}