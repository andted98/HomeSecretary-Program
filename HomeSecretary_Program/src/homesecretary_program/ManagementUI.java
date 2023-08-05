//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ManagementUI extends JFrame {
    //Store Events in an Array List
    public static List<Event> events = new ArrayList<>();

    //JFrame GUI Components - Event
    public static DefaultListModel<Event> eventListModel;
    public static JList<Event> eventList;
    
    private static JTextField EventTitle, EventDate, EventTimestamp, EventLocation;
 
    //GUI action buttons
    private static JButton addEventButton, displayEventButton;
    
    //GUI Labels
    private static JLabel lblEventTitle, lblEventDate, lblEventTimestamp, lblEventLocation, lblEventList;
    
    //Storing Array List in '.dat' file
    private static final String EVENT_FILE = "EVENT.dat";

    public ManagementUI() {
        buildAppManagementGUI();
    }
    
    public void buildAppManagementGUI() {
        loadData(events);

        //Setting up the JFrame format for the Event window
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Event Screen");
        setSize(500, 350);
        setLocationRelativeTo(null);

        //Panel containing how the grid layout of the screen
        JPanel managementDetailPanel = new JPanel(new GridLayout(6, 7, 5, 5));
        
        managementDetailPanel.add(new JLabel("EVENT MANAGEMENT - 'ADD & DISPLAY'"));    
        managementDetailPanel.add(new JLabel(" "));
        
        managementDetailPanel.add(lblEventTitle = new JLabel("Event Title:"));
        managementDetailPanel.add(EventTitle = new JTextField(8));         
        
        managementDetailPanel.add(lblEventDate = new JLabel("Event Date:"));
        managementDetailPanel.add(EventDate = new JTextField(8)); 
               
        managementDetailPanel.add(lblEventTimestamp = new JLabel("Event Timestamp:"));
        managementDetailPanel.add(EventTimestamp = new JTextField(8)); 
       
        managementDetailPanel.add(lblEventLocation = new JLabel("Event Location:"));
        managementDetailPanel.add(EventLocation = new JTextField(8));
        
        //This Panel will contain form control buttons
        JPanel managementFormControlPanel = new JPanel(new FlowLayout());
        
        //Panel containing the Add & Display buttons
        managementFormControlPanel.add(addEventButton = new JButton("Add Event"));
        managementFormControlPanel.add(displayEventButton = new JButton("Display Event"));
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(managementDetailPanel, BorderLayout.CENTER);
        panel.add(managementFormControlPanel, BorderLayout.SOUTH);        
        add(panel, BorderLayout.CENTER); 
        
        //ActionListener for the addEventButton
            addEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = EventTitle.getText();
                    String date = EventDate.getText();
                    String timestamp = EventTimestamp.getText();
                    String location = EventLocation.getText();                    

                    if (!title.isEmpty()) {
                        Event newEvent = new Event(title, date, timestamp, location);
                        events.add(newEvent);
                        EventTitle.setText("");
                        EventDate.setText("");
                        EventTimestamp.setText("");                        
                        EventLocation.setText("");
                        updateEventList(eventListModel, events);
                        saveData(events);
                        System.out.println("Event added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a title for the event.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            //ActionListener for the displayEventsButton
            displayEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder eventsString = new StringBuilder();
                    for (Event event : events) {
                        eventsString.append("Event - ").append(event.getTitle()).append(" occuring on ").append(event.getDate()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, eventsString.toString(), "Events", JOptionPane.INFORMATION_MESSAGE);
                }
            }); 
            
            managementDetailPanel.add(lblEventList = new JLabel("Saved Events:"));
            //Create the event list and add a ListSelectionListener to update the event details
            eventListModel = new DefaultListModel<>();
            updateEventList(eventListModel, events);
            eventList = new JList<>(eventListModel);
            eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane eventScrollPane = new JScrollPane(eventList);

            //Add the event list to the event panel
            managementDetailPanel.add(eventScrollPane);
    }

    //Method to update Event List
    public static void updateEventList(DefaultListModel<Event> listModel, List<Event> eventList) {
        listModel.clear();
        for (Event event : eventList) {
            listModel.addElement(event);
        }
    }

    //Method to load date from the EVENT_FILE
    public static void loadData(List<Event> events) {
        try {
            FileInputStream fis = new FileInputStream(EVENT_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            events.addAll((List<Event>) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    //Method to save data to the EVENT_FILE
    public static void saveData(List<Event> events) {
        try {
            FileOutputStream fos = new FileOutputStream(EVENT_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(events);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
}
