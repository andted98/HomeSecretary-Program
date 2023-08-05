//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

//This class represents a task with - title, date, timestamp & location
public class Task implements Serializable {
    private String taskTitle, taskDate, taskTimestamp, taskOption;

    //Constructor to create a task
    public Task (String title, String date, String timestamp, String option) {
        this.taskTitle = title;
        this.taskDate = date;
        this.taskTimestamp = timestamp;
        this.taskOption = option;
    }

    //Getter method - Task Title
    public String getTitle() {
        return taskTitle;
    }

    //Getter method - Task Date
    public String getDate() {
        return taskDate;
    }
    
    //Getter method - Task Timestamp
    public String getTimestamp() {
        return taskTimestamp;
    }

    //Getter method - Task Location
    public String getOption() {
        return taskOption;
    }

    //Setter method - Event Title
    public void setTitle(String title) {
        this.taskTitle = title;
    }

    //Setter method - Task Date
    public void setDate(String date) {
        this.taskDate = date;
    }    

    //Setter method - Task Timestamp
    public void setTimestamp(String timestamp) {
        this.taskTimestamp = timestamp;
    }
    
    //Setter method - Task Option
    public void setOption(String option) {
        this.taskOption = option;
    }    
    
    //Override the toString method 
    @Override
    public String toString() {
        return taskTitle;
    }
}