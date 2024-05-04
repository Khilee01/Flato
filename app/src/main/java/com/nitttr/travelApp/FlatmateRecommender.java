package com.nitttr.travelApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FlatmateRecommender {
    private List<Flatmate> flatmates;

    public FlatmateRecommender(InputStream inputStream) throws IOException {
        this.flatmates = new ArrayList<>();
        readFlatmatesFromInputStream(inputStream);
    }

    private void readFlatmatesFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            Flatmate flatmate = new Flatmate(columns);
            flatmates.add(flatmate);
        }
    }

    public List<Flatmate> getRecommendations(UserPreferences userPreferences) {
        List<Flatmate> recommendedFlatmates = new ArrayList<>();
        int k = 5; // Set the number of nearest neighbors to consider

        // Calculate the distance between the user preferences and each flatmate
        for (Flatmate flatmate : flatmates) {
            int distance = calculateDistance(userPreferences, flatmate);
            flatmate.setDistance(distance);
        }

        // Sort the flatmates based on the distance
        Collections.sort(flatmates, new Comparator<Flatmate>() {
            @Override
            public int compare(Flatmate o1, Flatmate o2) {
                return Integer.compare(o1.getDistance(), o2.getDistance());
            }
        });

        // Get the K-nearest flatmates
        for (int i = 0; i < k; i++) {
            recommendedFlatmates.add(flatmates.get(i));
        }

        // Calculate the compatibility score for the K-nearest flatmates
        for (Flatmate flatmate : recommendedFlatmates) {
            int compatibilityScore = calculateCompatibilityScore(userPreferences, flatmate);
            flatmate.setCompatibilityScore(compatibilityScore);
        }

        // Sort the recommended flatmates based on the compatibility score
        recommendedFlatmates.sort(new Comparator<Flatmate>() {
            @Override
            public int compare(Flatmate o1, Flatmate o2) {
                return Integer.compare(o2.getCompatibilityScore(), o1.getCompatibilityScore());
            }
        });

        return recommendedFlatmates;
    }

    private int calculateDistance(UserPreferences userPreferences, Flatmate flatmate) {
        int distance = 0;

        // Assign weights to each attribute based on priority
        int dietaryPreferencesWeight = 1;
        int ageWeight = 2;
        int genderWeight = 10;
        int tidinessPreferenceWeight = 6;
        int occupationWeight = 4;
        int lookingForGenderWeight = 10;
        int chorePreferencesWeight = 5;
        int personalityTypeWeight = 3;
        int lifestyleWeight = 2;
        int doYouSmokeWeight = 7;
        int doYouConsumeAlcoholWeight = 1;
        int localityWeight = 9;

        // Calculate the distance based on each attribute
        distance += dietaryPreferencesWeight * (userPreferences.getDietaryPreferences() != flatmate.getDietaryPreferences() ? 1 : 0);
        distance += ageWeight * (userPreferences.getAge().equals(flatmate.getAge())? 1 : 0);
        distance += genderWeight * (userPreferences.getGender().equals(flatmate.getGender()) ? 0 : 1);
        distance += tidinessPreferenceWeight * (userPreferences.getTidinessPreference() != flatmate.getTidinessPreference() ? 1 : 0);
        distance += occupationWeight * (userPreferences.getOccupation().equals(flatmate.getOccupation()) ? 0 : 1);
        distance += lookingForGenderWeight * (userPreferences.getLookingForGender().equals(flatmate.getGender()) ? 0 : 1);
        distance += chorePreferencesWeight * (userPreferences.getChorePreferences() != flatmate.getChorePreferences() ? 1 : 0);
        distance += personalityTypeWeight * (userPreferences.getPersonalityType().equals(flatmate.getPersonalityType()) ? 0 : 1);
        distance += lifestyleWeight * (userPreferences.getLifestyle().equals(flatmate.getLifestyle()) ? 0 : 1);
        distance += doYouSmokeWeight * (userPreferences.getDoYouSmoke() != flatmate.getDoYouSmoke() ? 1 : 0);
        distance += doYouConsumeAlcoholWeight * (userPreferences.getDoYouConsumeAlcohol().equals(flatmate.getDoYouConsumeAlcohol()) ? 0 : 1);
        distance += localityWeight * (userPreferences.getLocality().equals(flatmate.getLocality()) ? 0 : 1);

        return distance;
    }

    private int calculateCompatibilityScore(UserPreferences userPreferences, Flatmate flatmate) {
        int compatibilityScore = 0;

        // Calculate the compatibility score based on the user preferences and the flatmate attributes
        if (userPreferences.getDietaryPreferences() == flatmate.getDietaryPreferences()) {
            compatibilityScore += 1;
        }
        if (userPreferences.getAge() == flatmate.getAge()) {
            compatibilityScore += 2;
        }
        if (userPreferences.getTidinessPreference() == flatmate.getTidinessPreference()) {
            compatibilityScore += 6;
        }
        if (userPreferences.getOccupation().equals(flatmate.getOccupation())) {
            compatibilityScore += 4;
        }
        if (userPreferences.getLookingForGender().equals(flatmate.getGender())) {
            compatibilityScore += 10;
        }
        if (userPreferences.getChorePreferences() == flatmate.getChorePreferences()) {
            compatibilityScore += 5;
        }
        if (userPreferences.getPersonalityType().equals(flatmate.getPersonalityType())) {
            compatibilityScore += 3;
        }
        if (userPreferences.getLifestyle().equals(flatmate.getLifestyle())) {
            compatibilityScore += 2;
        }
        if (userPreferences.getDoYouSmoke() == flatmate.getDoYouSmoke()) {
            compatibilityScore += 7;
        }
        if (userPreferences.getDoYouConsumeAlcohol().equals(flatmate.getDoYouConsumeAlcohol())) {
            compatibilityScore += 1;
        }
        if (userPreferences.getLocality().equals(flatmate.getLocality())) {
            compatibilityScore += 9;
        }

        return compatibilityScore;
    }
}