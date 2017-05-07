package activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import classes.Employee;
import Records.EmployeeRecord;
import com.example.hwardak.timeclock.R;

import java.util.HashMap;

import timeClockDatabase.EmployeesDataAccess;

public class EmployeeFormAdd extends AppCompatActivity {

    private static final String LogDatabase = "DATABASE: ";


    EditText employeeFormIdEditText;
    EditText employeeFormFirstNameEditText;
    EditText employeeFormLastNameEditText;
    EditText employeeFormPhoneNumberEditText;
    EditText employeeFormDOBEditText;
    EditText employeeFormAddressEditText;
    EditText employeeFormSINEditText;
    TextView employeeFormIdTextView2;
    Button multiModeButton;

    EmployeesDataAccess employeesDataAccess;

    EmployeeRecord employeeRecord =EmployeeRecord.getInstance();

    HashMap<Integer, Employee> EMPLOYEES = employeeRecord.getEMPLOYEES();

    // to hold the value of the key parsed from the string passed in.
    int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String dob;
    String homeAddress;
    String sin;

    Employee employee;

    int newEmployeeId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_form);

        employeesDataAccess = new EmployeesDataAccess(this);

        employeeFormIdEditText = (EditText) findViewById(R.id.employeeFormIdEditText);
        employeeFormFirstNameEditText = (EditText) findViewById(R.id.employeeFormFirstNameEditText);
        employeeFormLastNameEditText = (EditText) findViewById(R.id.employeeFormLastNameEditText);
        employeeFormPhoneNumberEditText = (EditText) findViewById(R.id.employeeFormPhoneNumberEditText);
        employeeFormDOBEditText = (EditText) findViewById(R.id.employeeFormDOBEditText);
        employeeFormAddressEditText = (EditText) findViewById(R.id.employeeFormAddressEditText);
        employeeFormSINEditText = (EditText) findViewById(R.id.employeeFormSINEditText);
        employeeFormIdTextView2 = (TextView) findViewById(R.id.employeeFormIdTextView2);
        multiModeButton = (Button) findViewById(R.id.employeeFormMultiModeButton);



        applyTextChangeListener();

    }


    private void applyTextChangeListener() {
        employeeFormIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("beforeTextChangeEF:", s.toString());

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChangeEF:", s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("afterTextChangeEF:", s.toString());

                //This if-block is it to check, as each keystroke is detected, if the user id is
                //available and acceptable.
                if(s.toString().length() > 0) {
                    newEmployeeId = Integer.parseInt(s.toString());

                    if(employeeRecord.employeeIdExists(newEmployeeId)) {
                        employeeFormIdEditText.setBackgroundColor(Color.parseColor("#F7EB6A"));
                        employeeFormIdTextView2.setBackgroundColor(Color.parseColor("#F7EB6A"));
                        employeeFormIdTextView2.setText("Id already in use, enter another.");
                        multiModeButton.setEnabled(false);
                        newEmployeeId = -1;
                    } else if(s.length() != 3) {
                        employeeFormIdTextView2.setText("Id must be 3 digits.");
                        employeeFormIdTextView2.setBackgroundColor(Color.parseColor("#F7BA6A"));
                        employeeFormIdEditText.setBackgroundColor(Color.parseColor("#F7BA6A"));

                    } else {
                        employeeFormIdTextView2.setBackgroundColor(0);
                        employeeFormIdTextView2.setText("");
                        employeeFormIdEditText.setBackgroundColor(Color.parseColor("#80F073"));
                        multiModeButton.setEnabled(true);
                    }
                } else {
                    employeeFormIdEditText.setBackgroundColor(Color.parseColor("#F7EB6A"));
                    employeeFormIdTextView2.setBackgroundColor(Color.parseColor("#F7EB6A"));
                    employeeFormIdTextView2.setText("Please enter a valid ID");
                    multiModeButton.setEnabled(false);
                }

            }
        });
    }

    //**** Until the backstack was modified the fName and lName fields' backgroundcolor would
    //change to green upon a clicking the add button if the field values were acceptable.
    //Now that the back stack is modified to return the user back to the employee list view, the
    //background changing color is no longer nessecary. 
    public void onAddButtonClick(View view) {

        /*
        This boolean is a flag to be marked false if any of the editText inputs
        are unacceptable.
         */
        boolean pass = true;

        /*
        Check if the ID provided is three digits in length.
         */
        //In later version, the manager should be able to set what the
        //conditions of an acceptable ID are.


        if (employeeFormFirstNameEditText.getText().toString().trim().length() > 0) {
            firstName = employeeFormFirstNameEditText.getText().toString();
            employeeFormFirstNameEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("firstName = ", String.valueOf(firstName));
        } else {
            employeeFormFirstNameEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormFirstNameEditText.setText("");
            employeeFormFirstNameEditText.setHint("Enter first name...");
            pass = false;

        }

        if (employeeFormLastNameEditText.getText().toString().trim().length() > 0) {
            lastName = employeeFormLastNameEditText.getText().toString();
            employeeFormLastNameEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("lastName =", String.valueOf(lastName));
        } else {
            employeeFormLastNameEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormLastNameEditText.setText("");
            employeeFormLastNameEditText.setHint("Enter last name...");
            pass = false;
        }

        if (employeeFormPhoneNumberEditText.getText().toString().trim().length() > 0) {
            phoneNumber = employeeFormPhoneNumberEditText.getText().toString();
            employeeFormPhoneNumberEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("phoneN =", String.valueOf(phoneNumber));
        } else {
            employeeFormPhoneNumberEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormPhoneNumberEditText.setText("");
            employeeFormPhoneNumberEditText.setHint("Enter phone number...");
            pass = false;
        }

        if (employeeFormDOBEditText.getText().toString().trim().length() > 0) {
            dob = employeeFormDOBEditText.getText().toString();
            employeeFormDOBEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("dob =", String.valueOf(dob));
        } else {
            employeeFormDOBEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormDOBEditText.setText("");
            employeeFormDOBEditText.setHint("Enter Date of Birth...");
            pass = false;
        }

        if (employeeFormAddressEditText.getText().toString().trim().length() > 0) {
            homeAddress = employeeFormAddressEditText.getText().toString();
            employeeFormAddressEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("homeAddress =", String.valueOf(homeAddress));
        } else {
            employeeFormAddressEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormAddressEditText.setText("");
            employeeFormAddressEditText.setHint("Enter home address...");
            pass = false;
        }

        if (employeeFormSINEditText.getText().toString().trim().length() > 0) {
            sin = employeeFormSINEditText.getText().toString();
            employeeFormSINEditText.setBackgroundColor(Color.parseColor("#80F073"));
            Log.d("sin =", String.valueOf(sin));
        } else {
            employeeFormSINEditText.setBackgroundColor(Color.parseColor("#FA5757"));
            employeeFormSINEditText.setText("");
            employeeFormSINEditText.setHint("Enter Social Insurance Number...");
            pass = false;
        }

        /*
		 * Create a new Employee instance and fulfill its attributes.
		 */
        if (pass) {
            employee = new Employee();
            employee.setId(newEmployeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPhoneNumber(phoneNumber);
            employee.setDob(dob);
            employee.setHomeAddress(homeAddress);
            employee.setSin(sin);
            employee.logOn(false);
            employeeRecord.addEmployee(employee);


            employeeFormIdEditText.setHint("...");
            employeeFormFirstNameEditText.setHint("...");
            employeeFormLastNameEditText.setHint("...");
            employeeFormPhoneNumberEditText.setHint("...");
            employeeFormDOBEditText.setHint("...");
            employeeFormAddressEditText.setHint("...");
            employeeFormSINEditText.setHint("...");

            employeesDataAccess.open();
            employeesDataAccess.addEmployeeToTable(employee);

            Log.d(LogDatabase, "Employee added to table");

            employeesDataAccess.retrieveEmployeeList();

            multiModeButton.setEnabled(false);


            Intent intent = new Intent(this, ViewEmployeesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            Toast toast =  Toast.makeText(this, "Employee Successfully Added", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /*
 Check if the selected ID is already assigned by check for it in the HashMap.
  */
    public boolean employeeIdExists() {
        if (employeeRecord.employeeIdExists(id)) {
            return true;
        } else {
            return  false;
        }
    }

}

