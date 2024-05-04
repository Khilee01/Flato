package com.nitttr.travelApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class formPageActivity2 extends AppCompatActivity {

    private Spinner lookingForGenderSpinner;
    private Spinner chorePreferencesSpinner;
    private Spinner personalityTypeSpinner;
    private Spinner lifestyleSpinner;
    private Spinner doYouSmokeSpinner;
    private Spinner doYouConsumeAlcoholSpinner;
    private Spinner localitySpinner;

    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_page2);

        lookingForGenderSpinner = findViewById(R.id.lookingForGenderSpinner);
        chorePreferencesSpinner = findViewById(R.id.chorePreferencesSpinner);
        personalityTypeSpinner = findViewById(R.id.personalityTypeSpinner);
        lifestyleSpinner = findViewById(R.id.lifestyleSpinner);
        doYouSmokeSpinner = findViewById(R.id.doYouSmokeSpinner);
        doYouConsumeAlcoholSpinner = findViewById(R.id.doYouConsumeAlcoholSpinner);
        localitySpinner = findViewById(R.id.localitySpinner);

        Button recommendFlatmatesButton = findViewById(R.id.recommendButton);
        recommendFlatmatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendFlatmates();
            }
        });

        // Load user preferences from intent extras
        String dietaryPreferences = getIntent().getStringExtra("dietaryPreferences");
        String age = getIntent().getStringExtra("age");
        String gender = getIntent().getStringExtra("gender");
        String personality = getIntent().getStringExtra("personality");
        String tidinessPreference = getIntent().getStringExtra("tidinessPreference");
        String occupation = getIntent().getStringExtra("occupation");
        String lookingForGender = lookingForGenderSpinner.getSelectedItem().toString();
        String chorePreferences = chorePreferencesSpinner.getSelectedItem().toString();
        String personalityType = personalityTypeSpinner.getSelectedItem().toString();
        String lifestyle = lifestyleSpinner.getSelectedItem().toString();
        String doYouSmoke = doYouSmokeSpinner.getSelectedItem().toString();
        String doYouConsumeAlcohol = doYouConsumeAlcoholSpinner.getSelectedItem().toString();
        String locality = localitySpinner.getSelectedItem().toString();

        userPreferences = new UserPreferences(dietaryPreferences, age, gender, personality, tidinessPreference, occupation, lookingForGender, chorePreferences, personalityType, lifestyle, doYouSmoke, doYouConsumeAlcohol, locality);
    }

    private void recommendFlatmates() {
        try {
            FlatmateRecommender recommender = new FlatmateRecommender(getResources().openRawResource(R.raw.flatmatefinder));
            List<Flatmate> recommendations = recommender.getRecommendations(userPreferences);

            // Display recommended flatmates
            StringBuilder recommendedNames = new StringBuilder();
            for (Flatmate flatmate : recommendations) {
                recommendedNames.append(flatmate.getName()).append(", ");
            }
            String finalRecommendedNames = recommendedNames.toString();
            if (recommendations.isEmpty()) {
                finalRecommendedNames = "No recommended flatmates found";
            }
            Toast.makeText(this, finalRecommendedNames, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading flatmates.csv", Toast.LENGTH_SHORT).show();
        }
    }
}