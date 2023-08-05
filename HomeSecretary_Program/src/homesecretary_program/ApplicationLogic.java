//Author - Andrew Tedesco

package homesecretary_program;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import javax.swing.JOptionPane;

public class ApplicationLogic implements Serializable {
        
    //Declare and intiliaze hashmap "myUser"
    private static Map<String, User> myUser = new HashMap<String, User>();
    
    //FilePath where the Events will be saved
    public static final String eventsFileName = "events.txt";    
    
    //FilePath where the Users will be saved
    String userFileName = "Users.obj";
    
    public ApplicationLogic() {
        boolean loadedUserHashMap = loadFromDisk_User(userFileName);
        if (loadedUserHashMap == false) {
            addUserRecord("6874", "Mr.", "Beecham", 45, "Male", "Grp-01");
            addUserRecord("6299", "Mrs.", "Beecham", 42, "Female", "Grp-01");
            addUserRecord("3318", "John", "Beecham", 26, "Male", "Grp-01");
            addUserRecord("9248", "Anna", "Beecham", 22, "Female", "Grp-01");
            addUserRecord("3215", "Thomas", "Beecham", 19, "Male", "Grp-01");
            addUserRecord("5485", "Lisa", "Beecham", 15, "Female", "Grp-01");
        } 
    }
    
    //Add User record to the hashmap
    public void addUserRecord(String userID, String userName, String userSurname, int userAge, String userGender, String userGroup) {
       myUser.put(userID, new User(userID, userName, userSurname, userAge, userGender, userGroup));
    //Calling the saveToDisk_User method to save the user Object in the file passed through String parametre
    saveToDisk_User(userFileName);
    }
    
    //Method to save the data into an ObjectOutput Stream.
    public boolean saveToDisk_User(String path) {
        //Try - catch in case of error
        try {
            //Input and output are always done through streams in Java. This is an Object output stream
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path)); //open stream
            out.writeObject(myUser); //Write object
            out.close(); //Close Stream
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "ERROR: " + ioe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; //Unsuccessful
        }
        return true; //Successful
    }
    
    //Method to load the data into an ObjectInput Stream.
    public boolean loadFromDisk_User(String path) {
        if (path != null) {
            try {
                File file = new File(path);
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                myUser = (HashMap<String, User>) in.readObject();
                in.close();
                return true; //This will return the hashmap loaded with the data or null
            } catch (FileNotFoundException fnfe) {
                //JOptionPane.showMessageDialog(null, "ERROR: " + fnfe.getMessage());
                return false;
            } catch (Exception e) { //any other exceptions
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                return false;
            }
        }
        return false;
    }
    
    //Method to return an object of type User
    public static User GetUserData(String getUserData) {
        User userData = myUser.get(getUserData);
        return userData;
    }
    
    //Method to check if the key name (UserID) already exists in the hashmap
    public static boolean GetUserID(String userID) {
        if (myUser.containsKey(userID)) {
            return true;
        } else {
            return false;
        }
    }
}