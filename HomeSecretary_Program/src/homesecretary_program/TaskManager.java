//Author - Andrew Tedesco

package homesecretary_program;

import java.io.*;
import java.util.List;

//Manages a list of tasks & provides methods - add, edit and delete
public class TaskManager implements Serializable {
    private List<Task> tasks;

    //Constructor class
    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    //Method to add new tasks to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    //Method to edit the tasks in the .dat file
    public void editTask(int index, Task task) {
        tasks.set(index, task);
    }

    //Method to delete the selected tasks
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    //Getter method - Retrieve the task list
    public List<Task> getTasks() {
        return tasks;
    }
}