package is2103lecture11client;

import ejb.session.stateless.EventSessionBeanRemote;
import java.sql.Timestamp;
import java.util.Scanner;
import util.exception.VenueConflictException;



public class MainApp 
{
    private EventSessionBeanRemote eventSessionBeanRemote;
    
    
    
    public MainApp()
    {
    }

    
    
    public MainApp(EventSessionBeanRemote eventSessionBeanRemote)
    {
        this.eventSessionBeanRemote = eventSessionBeanRemote;
    }
    
    
    
    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to IS2103 Lecture 11 ***\n");
            System.out.println("1: Demo Add New Event");
            System.out.println("2: Exit\n");
            response = 0;
            
            while(response < 1 || response > 2)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    demo1();
                }
                else if (response == 2)
                {
                    break;
                }
                else
                {
                    System.out.print("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 2)
            {
                break;
            }
        }
    }
    
    
    
    private void demo1()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n*** IS2103 Lecture 11 :: 1 - Demo Add New Event ***\n");
        System.out.print("Enter Event Name> ");
        String eventName = scanner.nextLine().trim();
        System.out.print("Enter Start Date/Time> ");
        Timestamp startDateTime = Timestamp.valueOf(scanner.nextLine().trim());
        System.out.print("Enter End Date/Time> ");
        Timestamp endDateTime = Timestamp.valueOf(scanner.nextLine().trim());
        System.out.print("Enter Venue ID> ");
        Long venueId = scanner.nextLong();
        System.out.print("Enter System User ID> ");
        Long systemUserId = scanner.nextLong();
        
        try
        {
            Long newEventId = eventSessionBeanRemote.addNewEvent(eventName, startDateTime, endDateTime, venueId, systemUserId);
            System.out.println("New event added successfully!: " + newEventId + "\n");
        }
        catch (VenueConflictException ex)
        {
            System.out.println("An error has occurred while adding new event: " + ex.getMessage() + "!\n");
        }
        
        System.out.println("");
    }
}