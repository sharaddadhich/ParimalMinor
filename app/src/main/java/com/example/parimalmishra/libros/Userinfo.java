package com.example.parimalmishra.libros;

/**
 * Created by PARIMAL MISHRA on 9/25/2017.
 */

public class Userinfo {
    public String FirstName;
    public String LastName;
    public String Age;
    public String ContactNumber;
    public String Email;

    public Userinfo(){

    }

    public Userinfo(String firstName, String lastName, String age, String contactNumber, String email) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
        ContactNumber = contactNumber;
        Email = email;
    }
}
