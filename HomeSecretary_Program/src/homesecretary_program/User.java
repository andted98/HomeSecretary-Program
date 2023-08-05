//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

public class User extends Person implements Serializable {
    
    private String ID, Group;
    
    //Default Constructor
    public User() {
        super();
    }
   
    //In the over-ride constructor all the parameters will be passed.
    public User(String userID, String personName, String personSurname, int personAge, String personGender, String userGroup) {
        //Parameters from Super Class - Person
        super(personName, personSurname, personAge, personGender);
        
        this.ID = userID;
        this.Group = userGroup;
    }
    
    //--------------------------Get Methods------------------------------
    public String getUserID() {
        return this.ID;
    }

    public String getUserGroup() {
        return this.Group;
    }

    //--------------------------Set Methods------------------------------
    public void setUserID(String user_ID) {
        this.ID = user_ID;
    }
                    
    public void setUserGroup(String user_Group) {
        this.Group = user_Group;
    }
    
}
