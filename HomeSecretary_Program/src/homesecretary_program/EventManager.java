//Author - Andrew Tedesco

package homesecretary_program;

import java.io.*;
import java.util.List;


//Manages a list of events & provides methods - add, edit and delete
public class EventManager implements Serializable {
    private List<Event> events;

    //Constructor class 
    public EventManager(List<Event> events) {
        this.events = events;
    }

    //Method to add new events to the list
    public void addEvent(Event event) {
        events.add(event);
    }

    //Method to edit the events in the .dat file
    public void editEvent(int index, Event event) {
        events.set(index, event);
    }

    //Method to delete the selected events
    public void deleteEvent(int index) {
        events.remove(index);
    }

    //Getter method - Retrieve the event list
    public List<Event> getEvents() {
        return events;
    }
}