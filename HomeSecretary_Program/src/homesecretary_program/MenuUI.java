//Author - Andrew Tedesco

package homesecretary_program;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.*;

public class MenuUI extends JFrame{
        
    //Calling the ProfileUI
    ProfileUI profileUserInterface = new ProfileUI();
    //Calling the EventManagementUI
    ManagementUI managementUserInterface = new ManagementUI();
    //Calling the EventEditorUI
    EventEditorUI eventEditorUserInterface = new EventEditorUI();
    //Calling the TaskManagementUI
    TaskManagementUI taskManagementUserInterface = new TaskManagementUI();
    //Calling the TaskEditorUI
    TaskEditorUI taskEditorUserInterface = new TaskEditorUI();    
    //Calling the CalendarUI
    CalendarUI calendarUserInterface = new CalendarUI();
    //Calling the SocialUI
    SocialUI socialUserInterface = new SocialUI();
    //Calling the TaskToDroProgressUI
    TaskToDoProgressUI progressUserInterface = new TaskToDoProgressUI();
    
    //Default Constructor
    public MenuUI() {

    }
    
    //Constructor Overload
    public MenuUI(String param, String userProfile) {
        
        //Checking if userProfile is equal to User
        if (userProfile.equals ("User")) {
            
            //Getting User Details
            User userData = ApplicationLogic.GetUserData(param);
            
            //Assigning the string Variable loginUser with User Name and Surname
            String loginUser = userData.getName() + " " + userData.getSurname();
            
            //Setting up the Window Title
            setTitle("Menu Screen | " + loginUser + " - " + userProfile);
            
            //Calling the GUI method for User
            builtMainMenuGUI_User();
        }
    }
    
    private void builtMainMenuGUI_User() {
        
        setSize(425, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Create the File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Create the Group/Friends tab       
        JMenuItem socialAction = new JMenuItem("Group/Members");
        fileMenu.add(socialAction);
        
        socialAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Group/Friends UI
                socialUserInterface.setVisible(true);
            }
        });
        
         //Create the Profile tab       
        JMenuItem profileAction = new JMenuItem("Profile");
        fileMenu.add(profileAction);
        
        profileAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Profile UI form
                profileUserInterface.setVisible(true);
            }
        });
                     
        //Create the Notification tab
        JMenuItem notificationAction = new JMenuItem("Notifications");
        fileMenu.add(notificationAction);
        
        notificationAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //NotificationUI();
                JOptionPane.showMessageDialog(null, "Pending Notifications - ", "Notifications", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        JMenuItem settingsAction = new JMenuItem("Settings");
        fileMenu.add(settingsAction);
        
        settingsAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SettingsUI(); --- UI still in development phase
            }
        }); 
        
        JMenuItem aboutAction = new JMenuItem("About");
        fileMenu.add(aboutAction);
        
        aboutAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Software Engineering - Creating Quality Products (6CC550) || Andrew Tedesco", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        }); 
                  
        //Exit the program        
        Action exitAction = new AbstractAction("Exit") {
                public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the application
            }
        };
        
        JMenuItem exitItem = new JMenuItem(exitAction);
        fileMenu.add(exitItem);    

        //Create the View menu
        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);

        //Create the Calendar tab        
        JMenuItem calendarAction = new JMenuItem("Calendar");
        viewMenu.add(calendarAction);
        
        calendarAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Calendar View-mode
                calendarUserInterface.setVisible(true);
            }
        });

        //Create the To-Do Progress tab        
        JMenuItem progressAction = new JMenuItem("To-Do Progress");
        viewMenu.add(progressAction);

        progressAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the ToDo-Progress UI form
                progressUserInterface.setVisible(true);
            }
        });
                
        //Create the Event menu
        JMenu eventMenu = new JMenu("Event");
        menuBar.add(eventMenu);
        
        JMenuItem add_displayAction = new JMenuItem("Add & Display");
        eventMenu.add(add_displayAction);
        
        add_displayAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Event (Add & Display) UI form
                managementUserInterface.setVisible(true);
            }
        });

        JMenuItem edit_deleteAction = new JMenuItem("Edit & Delete");
        eventMenu.add(edit_deleteAction);
        
        edit_deleteAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Event (Edit & Delete) UI form
                eventEditorUserInterface.setVisible(true);
            }
        });

        //Create the Task menu
        JMenu taskMenu = new JMenu("Task");
        menuBar.add(taskMenu);
        
        JMenuItem taskadd_displayAction = new JMenuItem("Add & Display");
        taskMenu.add(taskadd_displayAction);
        
        taskadd_displayAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Task (Add & Display) UI form
                taskManagementUserInterface.setVisible(true);
            }
        });

        JMenuItem taskedit_deleteAction = new JMenuItem("Edit & Delete");
        taskMenu.add(taskedit_deleteAction);
        
        taskedit_deleteAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Open the Task (Edit & Delete) UI form
                taskEditorUserInterface.setVisible(true);
            }
        });
        
        //Make the GUI visible
        setVisible(true);
    }
    
}
