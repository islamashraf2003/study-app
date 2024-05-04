package com.example.faculty_of_science;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class cgpa extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button addButton;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cgpa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tableLayout = findViewById(R.id.tableLayout);
        addButton = findViewById(R.id.button6);
        calculateButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.resultTextView);

        // Set onClickListener for calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCGPA();
            }
        });

        // Set onClickListener for add semester button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSemester();
            }
        });
    }

    private void calculateCGPA() {
        // Calculate CGPA here
        // Iterate through rows and get values from EditText fields
        int rowCount = tableLayout.getChildCount();
        double totalCreditPoints = 0;
        double totalCredits = 0;
        double cgpa=0;

        for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header row
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            EditText creditHoursEditText = (EditText) row.getChildAt(1);
            EditText gradeEditText = (EditText) row.getChildAt(2);

            if (creditHoursEditText.length()==0) {

                if (gradeEditText.length()!=0) { // Assuming first grade is a default value
                    resultTextView.setText("   Enter Cridet Hours Of Semester In Row : " + i);
                    return;
                }
            }

            if (creditHoursEditText.length()!=0) {

                if (gradeEditText.length()==0) { // Assuming first grade is a default value
                    resultTextView.setText("Enter Grade Of Semester In Row : " + i);
                    return;
                }
            }

            if((gradeEditText.length()==0)&&(creditHoursEditText.length()==0))
            {
                break;
            }

            double creditHours = Double.parseDouble(creditHoursEditText.getText().toString());
            double grade = Double.parseDouble(gradeEditText.getText().toString());

            totalCreditPoints += creditHours * grade;
            totalCredits += creditHours;
        }

        cgpa = totalCreditPoints / totalCredits;

        // Display CGPA
        resultTextView.setText("Your CGPA is: " + cgpa);
    }

    private void addSemester() {
        // Add a new row to the table layout
        TableRow newRow = new TableRow(this);

        // Define TextView for semester
        TextView semesterTextView = new TextView(this);
        semesterTextView.setText("Semester" + (tableLayout.getChildCount()));
        TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
        semesterTextView.setLayoutParams(layoutParams1);
        semesterTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        semesterTextView.setPadding(16, 16, 16, 16); // In pixels
        semesterTextView.setTextSize(16); // In sp
        semesterTextView.setInputType(InputType.TYPE_CLASS_NUMBER);
        newRow.addView(semesterTextView);



        // Define EditText for credit hours
        EditText creditHoursEditText = new EditText(this);
        //int editText2Id = generateUniqueId();
        //creditHoursEditText.setId(editText2Id); // Generate unique ID for editText2
        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
        creditHoursEditText.setLayoutParams(layoutParams2);
        creditHoursEditText.setGravity(Gravity.CENTER_HORIZONTAL);
        creditHoursEditText.setPadding(16, 16, 16, 16); // In pixels
        creditHoursEditText.setTextSize(16); // In sp
        creditHoursEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        newRow.addView(creditHoursEditText);


        // Define EditText for grade
        EditText gradeEditText = new EditText(this);
        TableRow.LayoutParams layoutParams3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
        gradeEditText.setLayoutParams(layoutParams3);
        gradeEditText.setGravity(Gravity.CENTER_HORIZONTAL);
        gradeEditText.setPadding(16, 16, 16, 16); // In pixels
        gradeEditText.setTextSize(16); // In sp
        gradeEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        newRow.addView(gradeEditText);

        // Add new row to table layout
        tableLayout.addView(newRow);
    }

}