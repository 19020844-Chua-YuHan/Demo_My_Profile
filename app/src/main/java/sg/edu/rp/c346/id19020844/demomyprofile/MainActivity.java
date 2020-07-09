package sg.edu.rp.c346.id19020844.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import android.app.SharedElementCallback;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int genderID = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("Name", strName);
                prefEdit.putFloat("GPA", gpa);
                prefEdit.putInt("Gender", genderID);

                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Step 1: Get User input and store as variable
        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int genderID = rgGender.getCheckedRadioButtonId();

        // Step 2: Obtain instance of SharedPreferences & SharedPreferences Editor
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 3: Add key-value pair
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", gpa);
        prefEdit.putInt("Gender", genderID);

        // Step 4: Call commit()
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 1: Obtain instance of SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2: Retrieve saved data from SharedPreferences object
        String strName = prefs.getString("Name", "John");
        float gpa = prefs.getFloat("GPA", 0);
        int genderID = prefs.getInt("Gender", R.id.radioGroupGender);

        // Step 3: Update UI elements with value
        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(genderID);
    }
}
