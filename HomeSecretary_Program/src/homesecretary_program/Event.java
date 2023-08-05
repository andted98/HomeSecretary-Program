//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

//This class represents an event with - title, date, timestamp & location
public class Event implements Serializable {
    private String eventTitle, eventDate, eventTimestamp, eventLocation;

    //Constructor to create an event
    public Event(String title, String date, String timestamp, String location) {
        this.eventTitle = title;
        this.eventDate = date;
        this.eventTimestamp = timestamp;
        this.eventLocation = location;
    }

    //Getter method - Event Title
    public String getTitle() {
        return eventTitle;
    }

    //Getter method - Event Date
    public String getDate() {
        return eventDate;
    }
    
    //Getter method - Event Timestamp
    public String getTimestamp() {
        return eventTimestamp;
    }

    //Getter method - Event Location
    public String getLocation() {
        return eventLocation;
    }

    //Setter method - Event Title
    public void setTitle(String title) {
        this.eventTitle = title;
    }

    //Setter method - Event Date
    public void setDate(String date) {
        this.eventDate = date;
    }    

    //Setter method - Event Timestamp
    public void setTimestamp(String timestamp) {
        this.eventTimestamp = timestamp;
    }
    
    //Setter method - Event Location
    public void setLocation(String location) {
        this.eventLocation = location;
    }    
    
    //Override the toString method 
    @Override
    public String toString() {
        return eventTitle;
    }
}