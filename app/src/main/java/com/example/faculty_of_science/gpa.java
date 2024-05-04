package com.example.faculty_of_science;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class gpa extends AppCompatActivity  {

    private static final int BASE_ID = 1000; // Starting value for your IDs
    private int nextId = 0; // Initialize nextId
    // Define a method to generate unique IDs based on your system
    private int generateUniqueId() {
        return BASE_ID + nextId++; // Increment nextId for each new ID
    }
    String[] grads= { "None","A", "A-", "B+", "B", "C+", "C","D+","D","F" };
    Spinner spinner1,spinner2,spinner3;
    TableLayout table;
    TableRow tr;
    TextView resultTextView;
    Button b1,calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gpa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        spinner3=findViewById(R.id.spinner3);

        resultTextView = findViewById(R.id.resultTextView);

        b1=findViewById(R.id.button6);
        calculate = findViewById(R.id.button);

        table=findViewById(R.id.tableLayout);

        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grads);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(ad);
        spinner2.setAdapter(ad);
        spinner3.setAdapter(ad);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGPA();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow tr = new TableRow(gpa.this);

                EditText editText1 = new EditText(gpa.this);
                int editText1Id = generateUniqueId();
                editText1.setId(editText1Id); // Generate unique ID for editText1
                TableRow.LayoutParams layoutParams1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
                editText1.setLayoutParams(layoutParams1);
                editText1.setGravity(Gravity.CENTER_HORIZONTAL);
                editText1.setPadding(16, 16, 16, 16); // In pixels
                editText1.setTextSize(16); // In sp

                EditText editText2 = new EditText(gpa.this);
                int editText2Id = generateUniqueId();
                editText2.setId(editText2Id); // Generate unique ID for editText2
                TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
                editText2.setLayoutParams(layoutParams2);
                editText2.setGravity(Gravity.CENTER_HORIZONTAL);
                editText2.setPadding(16, 16, 16, 16); // In pixels
                editText2.setTextSize(16); // In sp
                editText2.setInputType(InputType.TYPE_CLASS_NUMBER);

                Spinner spinner4 = new Spinner(gpa.this);
                int spinner = generateUniqueId();
                spinner4.setId(spinner); // Generate unique ID for spinner4
                TableRow.LayoutParams spinnerParams = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 8.0f);
                spinner4.setLayoutParams(spinnerParams);
                spinner4.setGravity(Gravity.CENTER_HORIZONTAL);
                spinner4.setPadding(14, 14, 14, 14); // In pixels (optional for padding around the spinner)
                spinner4.setAdapter(ad);
                tr.addView(editText1);
                tr.addView(editText2);
                tr.addView(spinner4);

                // Add the TableRow to your layout (replace 'tableLayout' with your TableLayout reference)
                TableLayout tableLayout = findViewById(R.id.tableLayout);
                tableLayout.addView(tr);
            }
        });
    }
    private void calculateGPA() {
        float totalCredits = 0;
        float totalGradePoints = 0;

        for (int i = 1; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            EditText editText = (EditText) row.getChildAt(0);
            EditText creditsEditText = (EditText) row.getChildAt(1);
            Spinner spinner = (Spinner) row.getChildAt(2);

            String grade = spinner.getSelectedItem().toString();
            String creditsStr = creditsEditText.getText().toString();

            // Check if credits are empty
            if (creditsStr.isEmpty()) {

                if (!(grade.equals(grads[0]))) { // Assuming first grade is a default value
                    resultTextView.setText("   Enter Cridet Hours Of Subject In Row : " + i);
                    return;
                }
            }

            if (!(creditsStr.isEmpty())) {

                if (grade.equals(grads[0])) { // Assuming first grade is a default value
                    resultTextView.setText("Select Grade Of Subject In Row : " + i);
                    return;
                }
            }

            if(grade.equals(grads[0])&&creditsStr.isEmpty())
            {
                break;
            }

            if (!creditsStr.isEmpty()) {
                int credits = Integer.parseInt(creditsStr);
                float gradePoint = getGradePoint(grade);

                totalCredits += credits;
                totalGradePoints += gradePoint * credits;
            }

        }


        float gpa = totalCredits > 0 ? totalGradePoints / totalCredits : 0;

        // Display or use the calculated GPA
        // For example, you can display it in a TextView
        resultTextView.setText("Your GPA Is : " + gpa);
    }

    private float getGradePoint(String grade) {
        switch (grade) {
            case "A":
                return 4.0f;
            case "A-":
                return 3.67f;
            case "B+":
                return 3.33f;
            case "B":
                return 3.0f;
            case "C+":
                return 2.67f;
            case "C":
                return 2.33f;
            case "D":
                return 2.0f;
            default:
                return 0.0f;
        }
    }
}