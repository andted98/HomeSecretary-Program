//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SocialUI extends JFrame {

    //Store Members in an Array List
    public static List<Member> members = new ArrayList<>();

    //JFrame GUI Components - Member    
    public static DefaultListModel<Member> memberListModel;
    public static JList<Member> memberList;
    
    private JTextField txtnameSocial, txtsurnameSocial, txtageSocial, txtgenderSocial, txtemailSocial;
    private JTextField groupIDField;
    
    private JButton addButton, displayButton, createGroupButton;

    //Storing Array List in '.dat' file
    private static final String MEMBER_FILE = "MEMBER.dat";
    
    public SocialUI() {
        loadData(members);

        setTitle("Social Members & Groups");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
        
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        txtnameSocial = new JTextField(10);
        add(nameLabel);
        add(txtnameSocial);

        JLabel surnameabel = new JLabel("Surname:");
        txtsurnameSocial = new JTextField(10);
        add(surnameabel);
        add(txtsurnameSocial);

        JLabel ageLabel = new JLabel("Age:");
        txtageSocial = new JTextField(5);
        add(ageLabel);
        add(txtageSocial);

        JLabel genderLabel = new JLabel("Gender:");
        txtgenderSocial = new JTextField(7);
        add(genderLabel);
        add(txtgenderSocial);

        JLabel emailLabel = new JLabel("Email Address:");
        txtemailSocial = new JTextField(20);
        add(emailLabel);
        add(txtemailSocial);        

        JLabel groupLabel = new JLabel("Group:");
        groupIDField = new JTextField(10);
        add(groupLabel);
        add(groupIDField);
        
        JPanel socialDetailPanel = new JPanel(new GridLayout(1, 1, 5, 5));
                
        //Create the member list and add a ListSelectionListener to update the details
        memberListModel = new DefaultListModel<>();
        updateMemberList(memberListModel, members);
        memberList = new JList<>(memberListModel);
        memberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //JScrollPane memberScrollPane = new JScrollPane(memberList); 
        //socialDetailPanel.add(memberScrollPane);   

        //This Panel will contain form control buttons
        JPanel socialFormControlPanel = new JPanel(new FlowLayout());
        
        socialFormControlPanel.add(addButton = new JButton("Add Member"));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtnameSocial.getText();
                String surname = txtsurnameSocial.getText();
                String age = txtageSocial.getText();
                String gender = txtgenderSocial.getText();
                String email = txtemailSocial.getText();
                String group = groupIDField.getText();
                
                if (!group.isEmpty()) {
                        Member newMember = new Member(name, surname, age, gender, email, group);
                        members.add(newMember);
                        txtnameSocial.setText("");
                        txtsurnameSocial.setText("");
                        txtageSocial.setText("");                        
                        txtgenderSocial.setText("");
                        txtemailSocial.setText("");
                        groupIDField.setText("");                       
                        updateMemberList(memberListModel, members);
                        saveData(members);
                        System.out.println("Member added successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a group for this member.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }                    
                }
        });
        add(addButton);

        socialFormControlPanel.add(displayButton = new JButton("Display Members"));
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder membersString = new StringBuilder();
                for (Member member : members) {
                    membersString.append("Member - ").append(member.getName()).append(member.getSurname()).append(" || ").append(member.getGender()).append(" || ").append(member.getAge()).append(" || ").append(member.getEmail()).append("\n");
                }
                JOptionPane.showMessageDialog(null, membersString.toString(), "Member List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(displayButton);        

        socialFormControlPanel.add(createGroupButton = new JButton("Create Group"));
        createGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGroup();
            }
        });
        add(createGroupButton);   
        
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.add(socialFormControlPanel, BorderLayout.CENTER);
        panel.add(socialDetailPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        
    }

    private void createGroup() {
        String groupName = JOptionPane.showInputDialog(this, "Enter the name of the group:");

        if (groupName != null && !groupName.isEmpty()) {
            // Here, you could do something with the group name, like saving it or using it as needed.
            JOptionPane.showMessageDialog(this, "Group '" + groupName + "' created!");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid group name!");
        }
    }
    
    //Method to update Member List
    public static void updateMemberList(DefaultListModel<Member> listModel, List<Member> memberList) {
        listModel.clear();
        for (Member member : memberList) {
            listModel.addElement(member);
        }
    }
    
    //Method to load date from the MEMBER_FILE
    public static void loadData(List<Member> members) {
        try {
            FileInputStream fis = new FileInputStream(MEMBER_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            members.addAll((List<Member>) ois.readObject());
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    //Method to save data to the MEMBER_FILE
    public static void saveData(List<Member> member) {
        try {
            FileOutputStream fos = new FileOutputStream(MEMBER_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(members);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }    

}
