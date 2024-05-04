package com.nitttr.travelApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formPageActivity extends AppCompatActivity {
    Spinner dietaryPreferencesSpinner, ageSpinner, genderSpinner, personalitySpinner, tidinessPreferenceSpinner, occupationSpinner;
    Button nxtPgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_page);
        nxtPgBtn=findViewById(R.id.nxtPgBtn);
        dietaryPreferencesSpinner = findViewById(R.id.dietaryPreferencesSpinner);
        ageSpinner = findViewById(R.id.ageSpinner);
        genderSpinner = findViewById(R.id.genderSpinner);
        personalitySpinner = findViewById(R.id.personalitySpinner);
        tidinessPreferenceSpinner = findViewById(R.id.tidinessPreferenceSpinner);
        occupationSpinner = findViewById(R.id.occupationSpinner);

        nxtPgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(formPageActivity.this,formPageActivity2.class);
                intent.putExtra("dietaryPreferences", dietaryPreferencesSpinner.getSelectedItem().toString());
                intent.putExtra("age", ageSpinner.getSelectedItem().toString());
                intent.putExtra("gender", genderSpinner.getSelectedItem().toString());
                intent.putExtra("personality", personalitySpinner.getSelectedItem().toString());
                intent.putExtra("tidinessPreference", tidinessPreferenceSpinner.getSelectedItem().toString());
                intent.putExtra("occupation", occupationSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });
    }
}