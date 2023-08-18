package com.example.dietapp;

public class User {
    private String gender;
    private String activityLevel;
    private int age;
    private float weight;
    private float height;

    // コンストラクタ
    public User(String gender, String activityLevel, int age, float weight, float height) {
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }
}
