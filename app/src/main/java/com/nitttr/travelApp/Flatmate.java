package com.nitttr.travelApp;

public class Flatmate {
    private String name;
    private String gender;
    private String chorePreferences;
    private String personalityType;
    private String lifestyle;
    private String doYouSmoke;
    private String doYouConsumeAlcohol;
    private int compatibilityScore;
    private int distance;
    private String locality;
    private String age;
    private String dietaryPreferences;
    private String occupation;
    private String lookingForGender;
    private String tidinessPreference;

    public Flatmate(String[] columns) {
        this.name = columns[0];
        this.gender = columns[1];
        this.chorePreferences = columns[2];
        this.personalityType = columns[3];
        this.lifestyle = columns[4];
        this.doYouSmoke = columns[5];
        this.doYouConsumeAlcohol = columns[6];
        this.age =columns[7];
        this.dietaryPreferences = columns[8];
        this.occupation = columns[9];
        this.lookingForGender = columns[10];
        this.tidinessPreference = columns[11];
        this.locality = columns[12];
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getChorePreferences() {
        return chorePreferences;
    }

    public String getPersonalityType() {
        return personalityType;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public String getDoYouSmoke() {
        return doYouSmoke;
    }

    public String getDoYouConsumeAlcohol() {
        return doYouConsumeAlcohol;
    }

    public String getLocality() {
        return locality;
    }

    public String getAge() {
        return age;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getLookingForGender() {
        return lookingForGender;
    }

    public String getTidinessPreference() {
        return tidinessPreference;
    }

    public void setCompatibilityScore(int compatibilityScore) {
        this.compatibilityScore = compatibilityScore;
    }

    public int getCompatibilityScore() {
        return compatibilityScore;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public boolean matches(UserPreferences userPreferences) {
        return matchesGender(userPreferences) && matchesChorePreferences(userPreferences) && matchesPersonality(userPreferences) && matchesLifestyle(userPreferences) && matchesSmoking(userPreferences) && matchesAlcoholConsumption(userPreferences) && matchesLocality(userPreferences) && matchesAge(userPreferences) && matchesDietaryPreferences(userPreferences) && matchesOccupation(userPreferences) && matchesLookingForGender(userPreferences) && matchesTidinessPreference(userPreferences);
    }

    public boolean matchesGender(UserPreferences userPreferences) {
        return userPreferences.getLookingForGender().equals(this.gender);
    }

    public boolean matchesChorePreferences(UserPreferences userPreferences) {
        return userPreferences.getChorePreferences().equals(this.chorePreferences);
    }

    public boolean matchesPersonality(UserPreferences userPreferences) {
        return userPreferences.getPersonality().equals(this.personalityType);
    }

    public boolean matchesLifestyle(UserPreferences userPreferences) {
        return userPreferences.getLifestyle().equals(this.lifestyle);
    }

    public boolean matchesSmoking(UserPreferences userPreferences) {
        return userPreferences.getDoYouSmoke().equals(this.doYouSmoke);
    }

    public boolean matchesAlcoholConsumption(UserPreferences userPreferences) {
        return userPreferences.getDoYouConsumeAlcohol().equals(this.doYouConsumeAlcohol);
    }

    public boolean matchesLocality(UserPreferences userPreferences) {
        return userPreferences.getLocality().equals(this.locality);
    }

    public boolean matchesAge(UserPreferences userPreferences) {
        return userPreferences.getAge().equals(this.age);
    }

    public boolean matchesDietaryPreferences(UserPreferences userPreferences) {
        return userPreferences.getDietaryPreferences().equals(this.dietaryPreferences);
    }

    public boolean matchesOccupation(UserPreferences userPreferences) {
        return userPreferences.getOccupation().equals(this.occupation);
    }

    public boolean matchesLookingForGender(UserPreferences userPreferences) {
        return userPreferences.getLookingForGender().equals(this.lookingForGender);
    }

    public boolean matchesTidinessPreference(UserPreferences userPreferences) {
        return userPreferences.getTidinessPreference().equals(this.tidinessPreference);
    }
}