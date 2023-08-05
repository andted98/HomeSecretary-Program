//Author - Andrew Tedesco

package homesecretary_program;

import static homesecretary_program.ManagementUI.eventList;
import static homesecretary_program.ManagementUI.eventListModel;
import static homesecretary_program.ManagementUI.events;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;


public class EventEditorUI extends JFrame {
    
    //Global variables
    private static Event updateEvent;
    private static JTextField updateEventTitleField, updateEventDateField, updateEventTimestampField, updateEventLocationField;
 
    //GUI action buttons
    private static JButton editEventButton, deleteEventButton;
    
    //GUI Labels
    private static JLabel lblUpdateEventTitle, lblUpdateEventDate, lblUpdateEventTimestamp, lblUpdateEventLocation, lblUpdateEventList;
    private static String EVENT_FILE;

    public EventEditorUI() {
        buildEventEditorGUI();
    }
    
    public void buildEventEditorGUI() {

        //Setting up the JFrame format for the Event window
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Event Screen");
        setSize(500, 350);
        setLocationRelativeTo(null);

        //Panel containing how the grid layout of the screen
        JPanel managementDetailPanel = new JPanel(new GridLayout(6, 7, 5, 5));
        
        managementDetailPanel.add(new JLabel("EVENT MANAGEMENT - 'EDIT & DELETE'"));    
        managementDetailPanel.add(new JLabel(" "));
        
        managementDetailPanel.add(lblUpdateEventTitle = new JLabel("Event Title:"));
        managementDetailPanel.add(updateEventTitleField = new JTextField(8));         
        
        managementDetailPanel.add(lblUpdateEventDate = new JLabel("Update Date:"));
        managementDetailPanel.add(updateEventDateField = new JTextField(8)); 
               
        managementDetailPanel.add(lblUpdateEventTimestamp = new JLabel("Update Timestamp:"));
        managementDetailPanel.add(updateEventTimestampField = new JTextField(8)); 
       
        managementDetailPanel.add(lblUpdateEventLocation = new JLabel("Update Location:"));
        managementDetailPanel.add(updateEventLocationField = new JTextField(8));
        
        //Panel containing the Edit & Delete buttons
        JPanel managementFormControlPanel = new JPanel(new FlowLayout());
        managementFormControlPanel.add(editEventButton = new JButton("Edit Event"));
        managementFormControlPanel.add(deleteEventButton = new JButton("Delete Event"));
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(managementDetailPanel, BorderLayout.CENTER);
        panel.add(managementFormControlPanel, BorderLayout.SOUTH);        
        add(panel, BorderLayout.CENTER); 
        
        //ActionListener for the editEventButton
            editEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (updateEvent != null) {
                        //Show input dialogs to indicate which are the mandatory fields
                        String newTitle = JOptionPane.showInputDialog(null, "Update Event Title:",
                                updateEvent.getTitle());
                         String newDate = JOptionPane.showInputDialog(null, "Update Event Date:",
                                updateEvent.getDate()); 
                         String newTimestamp = JOptionPane.showInputDialog(null, "Update Event Timestamp:",
                                updateEvent.getTimestamp());                          
                         String newLocation = JOptionPane.showInputDialog(null, "Update Event Location:",
                                updateEvent.getLocation());                          

                        if (newTitle != null && !newTitle.isEmpty()) {
                            //Update the event with the mandatory attributes
                            updateEvent.setTitle(newTitle);
                            updateEvent.setDate(newDate);
                            updateEvent.setTimestamp(newTimestamp);
                            updateEvent.setLocation(newLocation);                            
                            updateEventList(eventListModel, events);
                            saveData(events);
                            System.out.println("Event edited successfully!");
                        } else {
                            //Show an error message if the new title is empty
                            JOptionPane.showMessageDialog(null, "Please enter a title for the event!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        //Show an error message if no event is selected for editing
                        JOptionPane.showMessageDialog(null, "Please select an event to edit!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //ActionListener for the deleteEventsButton
            deleteEventButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = eventList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        //Remove the selected event from the cached list
                        events.remove(selectedIndex);
                        updateEventList(eventListModel, events);
                        saveData(events);
                        System.out.println("Event deleted successfully!");
                    } else {
                        //Show an error message if no event is selected for deletion
                        JOptionPane.showMessageDialog(null, "Please select an event to delete.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            managementDetailPanel.add(lblUpdateEventList = new JLabel("Saved Events:"));
            //Create the event list and add a ListSelectionListener to update the list
            eventListModel = new DefaultListModel<>();
            updateEventList(eventListModel, events);
            eventList = new JList<>(eventListModel);
            eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane eventScrollPane = new JScrollPane(eventList);

            //Add the event list to the event panel
            managementDetailPanel.add(eventScrollPane);
            
            //Add the EventListener for events
            eventList.addListSelectionListener(e -> {
                int selectedIndex = eventList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    // Update the selectedTask with the selected task details
                    updateEvent = events.get(selectedIndex);
                    updateSelectedEvent(updateEvent);
                } else {
                    //Clear the event if none is selected
                    updateEvent = null;
                    updateSelectedEvent(null);
                }
                //Enable or disable the edit and delete buttons based on selection
                editEventButton.setEnabled(selectedIndex >= 0);
                deleteEventButton.setEnabled(selectedIndex >= 0);
            });
    }

    //Method to update Event List
    public static void updateEventList(DefaultListModel<Event> listModel, List<Event> eventList) {
        listModel.clear();
        for (Event event : eventList) {
            listModel.addElement(event);
        }
    }

    //Method to update the selected event details
    private static void updateSelectedEvent(Event event) {
        if (event != null) {
            updateEventTitleField.setText(event.getTitle());
            updateEventDateField.setText(event.getDate());
            updateEventTimestampField.setText(event.getTimestamp());
            updateEventLocationField.setText(event.getLocation());            
        } else {
            updateEventTitleField.setText("");
            updateEventDateField.setText("");
            updateEventTimestampField.setText("");
            updateEventLocationField.setText("");            

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
