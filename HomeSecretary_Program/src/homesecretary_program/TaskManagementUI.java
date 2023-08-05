//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TaskManagementUI extends JFrame {
    //Store Tasks in an Array List
    public static List<Task> tasks = new ArrayList<>();

    //JFrame GUI Components - Task
    public static DefaultListModel<Task> taskListModel;
    public static JList<Task> taskList;
    
    private static JTextField TaskTitle, TaskDate, TaskTimestamp;
    private static JComboBox TaskOption;
 
    //GUI action buttons
    private static JButton addTaskButton, displayTaskButton;
    
    //GUI Labels
    private static JLabel lblTaskTitle, lblTaskDate, lblTaskTimestamp, lblTaskOption, lblTaskList;
    
    //Declaration of String Array to be population as list in the JComboBox
    private final String[] taskoptionbox = {"Shopping", "Cleaning", "Washing", "Pick/Drop"};
    
    //Storing Array List in '.dat' file
    private static final String TASK_FILE = "TASK.dat";

    public TaskManagementUI() {
        buildAppTaskManagementGUI();
    }
    
    public void buildAppTaskManagementGUI() {
        loadData(tasks);

        //Setting up the JFrame format for the Task window
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Task Screen");
        setSize(500, 350);
        setLocationRelativeTo(null);

        //Panel containing how the grid layout of the screen
        JPanel managementDetailPanel = new JPanel(new GridLayout(6, 7, 5, 5));
        
        managementDetailPanel.add(new JLabel("TASK MANAGEMENT - 'ADD & DISPLAY'"));    
        managementDetailPanel.add(new JLabel(" "));
        
        managementDetailPanel.add(lblTaskTitle = new JLabel("Task Title:"));
        managementDetailPanel.add(TaskTitle = new JTextField(8));         
        
        managementDetailPanel.add(lblTaskDate = new JLabel("Task Date:"));
        managementDetailPanel.add(TaskDate = new JTextField(8)); 
               
        managementDetailPanel.add(lblTaskTimestamp = new JLabel("Task Timestamp:"));
        managementDetailPanel.add(TaskTimestamp = new JTextField(8)); 
       
        managementDetailPanel.add(lblTaskOption = new JLabel("Task Option (Choose relevant option...):"));
        managementDetailPanel.add(TaskOption = new JComboBox(taskoptionbox));
        
        //Panel containing the Add & Display buttons
        JPanel managementFormControlPanel = new JPanel(new FlowLayout());
        managementFormControlPanel.add(addTaskButton = new JButton("Add Task"));
        managementFormControlPanel.add(displayTaskButton = new JButton("Display Task"));
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(managementDetailPanel, BorderLayout.CENTER);
        panel.add(managementFormControlPanel, BorderLayout.SOUTH);        
        add(panel, BorderLayout.CENTER); 
        
        //ActionListener for the addTaskButton
            addTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String title = TaskTitle.getText();
                    String date = TaskDate.getText();
                    String timestamp = TaskTimestamp.getText();
                    String option = TaskOption.getActionCommand();

                    if (!title.isEmpty()) {
                        Task newTask = new Task(title, date, timestamp, option);
                        tasks.add(newTask);
                        TaskTitle.setText("");
                        TaskDate.setText("");
                        TaskTimestamp.setText("");                        
                        TaskOption.setSelectedIndex(0);
                        updateTaskList(taskListModel, tasks);
                        saveData(tasks);
                        System.out.println("Task added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a title for the task.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            //ActionListener for the displayTasksButton
            displayTaskButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder tasksString = new StringBuilder();
                    for (Task task : tasks) {
                        tasksString.append("Task - ").append(task.getTitle()).append(" occuring on ").append(task.getDate()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, tasksString.toString(), "Tasks", JOptionPane.INFORMATION_MESSAGE);
                }
            }); 
            
            managementDetailPanel.add(lblTaskList = new JLabel("Saved Tasks:"));
            //Create the task list and add a ListSelectionListener to update the details
            taskListModel = new DefaultListModel<>();
            updateTaskList(taskListModel, tasks);
            taskList = new JList<>(taskListModel);
            taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane taskScrollPane = new JScrollPane(taskList);

            //Add the task list to the task panel
            managementDetailPanel.add(taskScrollPane);
    }

    //Method to update Task List
    public static void updateTaskList(DefaultListModel<Task> listModel, List<Task> taskList) {
        listModel.clear();
        for (Task task : taskList) {
            listModel.addElement(task);
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
