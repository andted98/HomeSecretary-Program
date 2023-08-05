//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

//This class represents a member with - name, surname, age, gender, email address
public class Member implements Serializable {
    private String memberName, memberSurname, memberAge, memberGender, memberEmail, memberGroup;

    //Constructor to create a member
    public Member (String name, String surname, String age, String gender, String email, String group) {
        this.memberName = name;
        this.memberSurname = surname;
        this.memberAge = age;
        this.memberGender = gender;
        this.memberEmail = email;
        this.memberGroup = group;
    }

    //Getter method - Member Name
    public String getName() {
        return memberName;
    }

    //Getter method - Member Surname
    public String getSurname() {
        return memberSurname;
    }
    
    //Getter method - Member Age
    public String getAge() {
        return memberAge;
    }

    //Getter method - Member Gender
    public String getGender() {
        return memberGender;
    }
    
    //Getter method - Member Email Adress
    public String getEmail() {
        return memberEmail;
    }    
    
    //Getter method - Member Group
    public String getGroup() {
        return memberGroup;
    }   
    
    //Setter method - Member Name
    public void setName(String name) {
        this.memberName = name;
    }

    //Setter method - Member Surname
    public void setSurname(String surname) {
        this.memberSurname = surname;
    }    

    //Setter method - Member Age
    public void setAge(String age) {
        this.memberAge = age;
    }
    
    //Setter method - Member Gender
    public void setGender(String gender) {
        this.memberGender = gender;
    }    

    //Setter method - Member Email Adress
    public void setEmail(String email) {
        this.memberEmail = email;
    } 

    //Setter method - Member Group
    public void setGroup(String group) {
        this.memberGroup = group;
    } 
    
    //Override the toString method 
    @Override
    public String toString() {
        return memberName;
    }
}