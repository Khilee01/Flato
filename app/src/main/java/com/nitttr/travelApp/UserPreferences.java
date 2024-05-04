package com.nitttr.travelApp;

public class UserPreferences {
    private String dietaryPreferences;
    private String age;
    private String gender;
    private String personality;
    private String tidinessPreference;
    private String occupation;
    private String lookingForGender;
    private String chorePreferences;
    private String personalityType;
    private String lifestyle;
    private String doYouSmoke;
    private String doYouConsumeAlcohol;
    private String locality;

    public UserPreferences(String dietaryPreferences, String age, String gender, String personality, String tidinessPreference, String occupation, String lookingForGender, String chorePreferences, String personalityType, String lifestyle, String doYouSmoke, String doYouConsumeAlcohol, String locality) {
        this.dietaryPreferences = dietaryPreferences;
        this.age = age;
        this.gender = gender;
        this.personality = personality;
        this.tidinessPreference = tidinessPreference;
        this.occupation = occupation;
        this.lookingForGender = lookingForGender;
        this.chorePreferences = chorePreferences;
        this.personalityType = personalityType;
        this.lifestyle = lifestyle;
        this.doYouSmoke = doYouSmoke;
        this.doYouConsumeAlcohol = doYouConsumeAlcohol;
        this.locality = locality;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPersonality() {
        return personality;
    }

    public String getTidinessPreference() {
        return tidinessPreference;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getLookingForGender() {
        return lookingForGender;
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
}