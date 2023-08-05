//Author - Andrew Tedesco

package homesecretary_program;

import java.io.Serializable;

public class Person implements Serializable {
    
    private String name, surname, gender;
    private int age;
    
    //Default Constructor
    public Person() { 
    }
    
    public Person(String personName, String personSurname, int personAge, String personGender) {
        this.name = personName;
        this.surname = personSurname;
        this.age = personAge;
        this.gender = personGender;
    }
    
    //--------------------------Get Methods------------------------------
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public String getGender() {
        return this.gender;
    }

    //--------------------------Set Methods------------------------------
   
    public void setName(String name) {
        this.name = name;
    }
        
    public void setSurname(String surname) {
        this.surname = surname;
    }
            
    public void setAge(int age) {
        this.age = age;
    }
                
    public void setGender(String gender) {
        this.gender = gender;
    }
                    
    //toString method
    @Override
    public String toString() {
        //String viewPersonalDetails contain the details of the Person
        String viewPersonalDetails = "\nName :" + this.name + "\nSurname : " + this.surname + "\nAge : " + this.age + "\nGender : " + this.gender;
        return viewPersonalDetails;
    }   
}