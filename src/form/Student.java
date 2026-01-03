/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kishore E
 */
package form;

public class Student {
    private String roll;
    private String name;
    private String dept;
    private String phone;

    public Student(String roll, String name, String dept, String phone) {
        this.roll = roll;
        this.name = name;
        this.dept = dept;
        this.phone = phone;
    }

    public String getRoll() {
        return roll;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getPhone() {
        return phone;
    }
}

