package com.example.survey.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class SurveyEntity {
    private int age;
    private String gender;
    private String region;
    private String surveyID;
    private int score;

    public SurveyEntity() {
        // Default constructor needed by Jackson
    }

    public SurveyEntity(int age, String gender, String region, String surveyID, int score) {
        this.age = age;
        this.gender = gender;
        this.region = region;
        this.surveyID = surveyID;
        this.score = score;
    }

    @JsonProperty
    public int getAge() {
        return age;
    }

    @JsonProperty
    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty
    public String getGender() {
        return gender;
    }

    @JsonProperty
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty
    public String getRegion() {
        return region;
    }

    @JsonProperty
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty
    public String getSurveyID() {
        return surveyID;
    }

    @JsonProperty
    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    @JsonProperty
    public int getScore() {
        return score;
    }

    @JsonProperty
    public void setScore(int score) {
        this.score = score;
    }
}
