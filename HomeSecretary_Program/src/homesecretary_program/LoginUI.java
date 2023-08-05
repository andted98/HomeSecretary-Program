//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginUI extends JFrame {
    
    public JComboBox cmbLoginUserAs;
    public JTextField txtUserID;
    private JLabel lblLoginUserAs, lblUserID;
    private JButton exitButton, clearButton, loginButton;   
    
    // Instance of ApplicationLogic Class
    ApplicationLogic aLogic = new ApplicationLogic();
        
    //Declaration of String Array to be population as list in the JComboBox
    private final String[] loginAsUser = {"User", "Admin"};
    public String userID;
                
    //Default Constructor
    public LoginUI() {

        //Calling the buildLoginGUI() method
        buildLoginGUI();

        //Assigning an on click event to login button. login() method will be executed
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                //method to login
                Login();
            }
        });
        
        //Assigning an on click event to clear button. clearData() method will be executed
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Method to clear all field records
                clearData();
            }
        });

        //Exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    //Building the User Interface to capture the Login Details and Identify User
    public void buildLoginGUI() {
        
        setTitle("Login Screen - 6CC550");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        //This Panel will contain data input about the login Details of the user in a grid with 5 rows and 2 columns based on fields
        JPanel loginDetailPanel = new JPanel(new GridLayout(4, 2));
     
        loginDetailPanel.add(new JLabel("SCHEDULE APP (V.01)"));
        loginDetailPanel.add(new JLabel(" "));
        loginDetailPanel.add(new JLabel(" "));        

        loginDetailPanel.add(lblLoginUserAs = new JLabel("Profile"));
        loginDetailPanel.add(cmbLoginUserAs = new JComboBox(loginAsUser));
        loginDetailPanel.add(new JLabel(" "));   
        
        loginDetailPanel.add(lblUserID = new JLabel("PIN Code"));
        loginDetailPanel.add(txtUserID = new JTextField(8));
        loginDetailPanel.add(new JLabel(" "));        
        
        //This Panel will contain form control buttons
        JPanel loginFormControlPanel = new JPanel(new FlowLayout());

        //This Panel will contain form control buttons
        loginFormControlPanel.add(loginButton = new JButton("Login"));
        loginFormControlPanel.add(clearButton = new JButton("Clear"));
        loginFormControlPanel.add(exitButton = new JButton("Exit"));

        //Add panels to frame
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(loginDetailPanel, BorderLayout.CENTER);
        panel.add(loginFormControlPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    //The ClearData() Method
    public void clearData() {
        this.txtUserID.setText("");        
        this.cmbLoginUserAs.setSelectedIndex(0);
    }
    
    public void Login() {
        userID = txtUserID.getText();
        
        //Checking if the string variable passwordID is not empty
        if (userID.trim().equals("")) {
            
            //Displaying a message box for the user to submit the Password
            JOptionPane.showMessageDialog(null, "User ID is Mandatory! Kindly submit the User ID", "Login Failed",JOptionPane.ERROR_MESSAGE);
            
        } else {
            if (cmbLoginUserAs.getSelectedItem().equals("User") == true) {
                
                boolean userExists = ApplicationLogic.GetUserID(userID);
                //Validation if user exists and already added within the Table
                
                if (userExists == true) {
                    
                    //Getting User Data and Bind them to the form components
                    User userData = ApplicationLogic.GetUserData(userID);
                    JOptionPane.showMessageDialog(null, "Welcome - " + userData.getName() + " " + userData.getSurname());
                    
                    //Closing the window after being used
                    dispose();
                    
                    String getuserID = this.txtUserID.getText();
                    
                    MenuUI mainMenuInterface = new MenuUI(getuserID, cmbLoginUserAs.getSelectedItem().toString());
                    mainMenuInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    mainMenuInterface.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect UserID! No User Found!", "Search", JOptionPane.INFORMATION_MESSAGE);
                }

            }    
        }    
    }
}
