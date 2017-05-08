package Records;

import android.util.Log;

import classes.Employee;
import classes.Shift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import timeClockDatabase.ShiftsDataAccess;


public class EmployeeRecord {

    Employee employee;

    public static EmployeeRecord employeeRecord;

    private static final HashMap<Integer, Employee> EMPLOYEES = new HashMap<Integer, Employee>();


    /*
     * private Constructor to prevent external instantiation, and restrict
     * class to a single instance.
     */
    private EmployeeRecord() {

    }


    public static HashMap<Integer, Employee> getEMPLOYEES() {
        return EMPLOYEES;
    }


    public static EmployeeRecord getInstance() {
        if (employeeRecord == null)
            employeeRecord = new EmployeeRecord();
        return employeeRecord;
    }


    public void addEmployee(Employee employee) {
        EMPLOYEES.put(employee.getId(), employee);
    }

    /*
     * This method is to invoke a query which will return a list of Employees from the
     * Employee table in the database. From the returned list, it will populate the HashMap.
     */
    public void updateEmployeeListViewFromDatabase(List<Employee> employees) {
        EMPLOYEES.clear();
        for (int i = 0; i < employees.size(); i++) {
            EMPLOYEES.put(employees.get(i).getId(), employees.get(i));
            Log.d("DATABASE", "employee " + employees.get(i).getId() + " added to HashMap");

        }
    }


    /*
     * Check to see if the employee Id is already assigned, returns true if
     * already assigned.
     */
    public boolean employeeIdExists(int employeeId) {
        if (EMPLOYEES.containsKey(employeeId)) {
            return true;
        } else return false;
    }



//////////////////////////////////////////////////////////////////////
// HELPER METHODS


    /*
   * Iterator through HashMap, calling each values toString.
   */
    public void printEmployeeList() {
        Iterator<Integer> employeeKeyIterator = EMPLOYEES.keySet().iterator();
        while (employeeKeyIterator.hasNext()) {
            int x = employeeKeyIterator.next();
            Log.d("Employee " + x, EMPLOYEES.get(x).toString());
        }
    }
//    public static void printMap(Map mp) {
//        Iterator it = mp.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry)it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException


    public void retrieveEmployeeShifts(ShiftsDataAccess shiftsDataAccess) {
        for (Integer key : EMPLOYEES.keySet()) {
           ArrayList<Shift> employeeShifts= shiftsDataAccess.getEmployeeShifts(key);
            EMPLOYEES.get(key).setShifts(employeeShifts);
        }

    }
}