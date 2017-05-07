package classes;

import java.util.ArrayList;

/**
 * Created by HWardak on 16-05-02.
 */
public class Employee {


    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dob;
    private String homeAddress;
    private String sin;
    private boolean loggedOn;

    private ArrayList<Shift> shifts;


    public Employee() {
        shifts = new ArrayList<>(0);
    }


    //Do I need this initial constructor if I have the setId(int id) method?
    //Which one is better to use?
    public Employee(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public int isOn() {
        if(loggedOn){
            return 1;
        } else {
            return 0;
        }
    }


    public void logOn(boolean logOn) {
        this.loggedOn = logOn;
    }

    public void logOnInterger(int isOnInteger){
        if(isOnInteger == 1 ){
            loggedOn = true;
            }
        else {
            loggedOn = false;
        }

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    @Override
    public String toString() {
        return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", +" + "loggedIn =" + loggedOn + "]";
    }

    public String toStringCode() {
        return id + "," + firstName + "," + lastName + ",";
    }

    public ArrayList<Shift> getShifts() {
        if(shifts == null){
            shifts = new ArrayList<>();
        }

        return shifts;
    }

    public void setShifts(ArrayList<Shift> shifts) {
        this.shifts = shifts;
    }


}
