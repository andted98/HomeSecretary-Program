//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

public class UserProfile implements Serializable {
    private String name, surname, gender, email;
    private int age;
    
    //Class created to save 'Add Friend' info into a hashmap
    public UserProfile(String name, String surname, int age, String gender, String email) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public int getAge() {
        return age;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getEmail() {
        return email;
    }

}
