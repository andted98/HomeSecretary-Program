//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class TaskToDoProgressUI extends JFrame {
    private List<ProgressTask> tasks;
    private JPanel taskPanel;

    public TaskToDoProgressUI() {
        setTitle("Task To-Do Progress");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Initialize the list of tasks
        tasks = new ArrayList<>();

        //Create a panel to hold the tasks and sliders
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        //Add some sample tasks
        addTask("[Task 1] - Surprise Birthday Party!");

        //Add the task panel to the frame
        getContentPane().add(taskPanel, BorderLayout.CENTER);
    }

    private void addTask(String taskName) {
        ProgressTask task = new ProgressTask(taskName);
        tasks.add(task);

        //Create a slider for the task
        JSlider slider = new JSlider(0, 100);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        //Add a change listener to the slider to update the progress value
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int progress = slider.getValue();
                task.setProgress(progress);
                System.out.println(task.getName() + " Progress: " + progress + "%");
            }
        });

        //Add the task name and slider to the task panel
        taskPanel.add(new JLabel(taskName));
        taskPanel.add(slider);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskToDoProgressUI().setVisible(true);
            }
        });
    }
}

class ProgressTask {
    private String name;
    private int progress;

    public ProgressTask (String name) {
        this.name = name;
        this.progress = 0;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
