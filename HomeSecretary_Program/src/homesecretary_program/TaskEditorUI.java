//Author - Andrew Tedesco

package homesecretary_program;

import static homesecretary_program.TaskManagementUI.taskList;
import static homesecretary_program.TaskManagementUI.taskListModel;
import static homesecretary_program.TaskManagementUI.tasks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;


public class TaskEditorUI extends JFrame {
    
    //Global variables
    private static Task updateTask;
    private static JTextField updateTaskTitleField, updateTaskDateField, updateTaskTimestampField;
    private static JComboBox updateTaskOptionField;
    
    //GUI action buttons
    private static JButton editTaskButton, deleteTaskButton;
    
    //GUI Labels
    private static JLabel lblUpdateTaskTitle, lblUpdateTaskDate, lblUpdateTaskTimestamp, lblUpdateTaskOption, lblUpdateTaskList;
    private static String TASK_FILE;
    
    //Declaration of String Array to be population as list in the JComboBox
    private final String[] taskoptionbox = {"Shopping", "Cleaning", "Washing", "Pick/Drop"};

    public TaskEditorUI() {
        buildTaskEditorGUI();
    }
    
    public void buildTaskEditorGUI() {

        //Setting up the JFrame format for the Task window
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Task Screen");
        setSize(500, 350);
        setLocationRelativeTo(null);

        //Panel containing how the grid layout of the screen
        JPanel managementDetailPanel = new JPanel(new GridLayout(6, 7, 5, 5));
        
        managementDetailPanel.add(new JLabel("TASK MANAGEMENT - 'EDIT & DELETE'"));    
        managementDetailPanel.add(new JLabel(" "));
        
        managementDetailPanel.add(lblUpdateTaskTitle = new JLabel("Task Title:"));
        managementDetailPanel.add(updateTaskTitleField = new JTextField(8));         
        
        managementDetailPanel.add(lblUpdateTaskDate = new JLabel("Update Date:"));
        managementDetailPanel.add(updateTaskDateField = new JTextField(8)); 
               
        managementDetailPanel.add(lblUpdateTaskTimestamp = new JLabel("Update Timestamp:"));
        managementDetailPanel.add(updateTaskTimestampField = new JTextField(8)); 
       
        managementDetailPanel.add(lblUpdateTaskOption = new JLabel("Update Location:"));
        managementDetailPanel.add(updateTaskOptionField = new JComboBox(taskoptionbox));
        
        //Panel containing the Edit & Delete buttons
        JPanel managementFormControlPanel = new JPanel(new FlowLayout());
        managementFormControlPanel.add(editTaskButton = new JButton("Edit Task"));
        managementFormControlPanel.add(deleteTaskButton = new JButton("Delete Task"));
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(managementDetailPanel, BorderLayout.CENTER);
        panel.add(managementFormControlPanel, BorderLayout.SOUTH);        
        add(panel, BorderLayout.CENTER); 
        
        //ActionListener for the editTaskButton
            editTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (updateTask != null) {
                        //Show input dialogs to indicate which are the mandatory fields
                        String newTitle = JOptionPane.showInputDialog(null, "Update Task Title:",
                                updateTask.getTitle());
                         String newDate = JOptionPane.showInputDialog(null, "Update Task Date:",
                                updateTask.getDate()); 
                         String newTimestamp = JOptionPane.showInputDialog(null, "Update Task Timestamp:",
                                updateTask.getTimestamp());                          
                         String newOption= JOptionPane.showInputDialog(null, "Update Task Option:",
                                updateTask.getOption());                          

                        if (newTitle != null && !newTitle.isEmpty()) {
                            //Update the task with the mandatory attributes
                            updateTask.setTitle(newTitle);
                            updateTask.setDate(newDate);
                            updateTask.setTimestamp(newTimestamp);
                            updateTask.setOption(newOption);                            
                            updateTaskList(taskListModel, tasks);
                            saveData(tasks);
                            System.out.println("Task edited successfully!");
                        } else {
                            //Show an error message if the new title is empty
                            JOptionPane.showMessageDialog(null, "Please enter a title for the task!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        //Show an error message if no task is selected for editing
                        JOptionPane.showMessageDialog(null, "Please select an task to edit!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            //ActionListener for the deleteTaskButton
            deleteTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        // Remove the selected task from the tasks list
                        tasks.remove(selectedIndex);
                        updateTaskList(taskListModel, tasks);
                        saveData(tasks);
                        System.out.println("Task deleted successfully!");
                    } else {
                        // Show an error message if no task is selected for deletion
                        JOptionPane.showMessageDialog(null, "Please select an task to delete.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            managementDetailPanel.add(lblUpdateTaskList = new JLabel("Saved Tasks:"));
            //Create the task list and add a ListSelectionListener to update the details
            taskListModel = new DefaultListModel<>();
            updateTaskList(taskListModel, tasks);
            taskList = new JList<>(taskListModel);
            taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane taskScrollPane = new JScrollPane(taskList);

            //Add the task list to the task panel
            managementDetailPanel.add(taskScrollPane);
            
            //Add the EventListener for tasks
            taskList.addListSelectionListener(e -> {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    //Update the selectedTask with the selected task details
                    updateTask = tasks.get(selectedIndex);
                    updateSelectedTask(updateTask);
                } else {
                    //Clear the selectedTask if no task is selected
                    updateTask = null;
                    updateSelectedTask(null);
                }
                //Enable or disable the edit and delete buttons based on selection
                editTaskButton.setEnabled(selectedIndex >= 0);
                deleteTaskButton.setEnabled(selectedIndex >= 0);
            });
    }

    //Method to update Task List
    public static void updateTaskList(DefaultListModel<Task> listModel, List<Task> taskList) {
        listModel.clear();
        for (Task task : taskList) {
            listModel.addElement(task);
        }
    }

    //Method to update the selected task details
    private static void updateSelectedTask(Task task) {
        if (task != null) {
            updateTaskTitleField.setText(task.getTitle());
            updateTaskDateField.setText(task.getDate());
            updateTaskTimestampField.setText(task.getTimestamp());
            updateTaskOptionField.setSelectedIndex(0);            
        } else {
            updateTaskTitleField.setText("");
            updateTaskDateField.setText("");
            updateTaskTimestampField.setText("");
            updateTaskOptionField.setSelectedIndex(0);            

        }
    }
    
    //Method to load date from the TASK_FILE
    public static void loadData(List<Task> tasks) {
        try {
            FileInputStream fis = new FileInputStream(TASK_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks.addAll((List<Task>) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    //Method to save data to the TASK_FILE
    public static void saveData(List<Task> tasks) {
        try {
            FileOutputStream fos = new FileOutputStream(TASK_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }
}
