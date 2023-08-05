//Author - Andrew Tedesco

package homesecretary_program;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileUI extends JFrame {
     
    public JLabel lblProfileName, lblProfileSurname, lblProfileAge, lblProfileGender, lblProfileEmail;
    public JTextField txtProfileName, txtProfileSurname, txtProfileAge, txtProfileGender, txtProfileEmail;
            
    public ProfileUI() {

        setTitle("Profile Details");
        setSize(450, 300);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //Set Layout for the JFrame
        JPanel profileDetailPanel = new JPanel(new GridLayout(5, 3, 5, 5));
      
        //Initialize components
        profileDetailPanel.add(lblProfileName = new JLabel("Name:"));
        profileDetailPanel.add(txtProfileName = new JTextField(8));

        profileDetailPanel.add(lblProfileSurname = new JLabel("Surname:"));
        profileDetailPanel.add(txtProfileSurname = new JTextField(8));     
        
        profileDetailPanel.add(lblProfileAge = new JLabel("Age:"));
        profileDetailPanel.add(txtProfileAge = new JTextField(8));        
        
        profileDetailPanel.add(lblProfileGender = new JLabel("Gender:"));
        profileDetailPanel.add(txtProfileGender = new JTextField(8));
        
        profileDetailPanel.add(lblProfileEmail = new JLabel("Email Address:"));
        profileDetailPanel.add(txtProfileEmail = new JTextField(8));        
        
        //Set the nameField as uneditable
        txtProfileName.setEditable(false);
        txtProfileSurname.setEditable(false);
        txtProfileAge.setEditable(false);
        txtProfileGender.setEditable(false);
        txtProfileEmail.setEditable(false);        
             
        //Add panels to frame
        JPanel panel = new JPanel(new GridLayout(1, 1, 5, 5));
        panel.add(profileDetailPanel, BorderLayout.CENTER);        
        add(panel, BorderLayout.CENTER);
    }  
}