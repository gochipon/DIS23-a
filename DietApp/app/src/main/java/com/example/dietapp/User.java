package com.example.dietapp;

public class User {
    private static String gender;
    private static String username;
    private static String activityLevel;
    private static int age;
    private static float weight;
    private static float height;

    // コンストラクタ
    public User(String username, String gender, String activityLevel, int age, float weight, float height) {
        User.username = username;
        User.gender = gender;
        User.activityLevel = activityLevel;
        User.age = age;
        User.weight = weight;
        User.height = height;
    }

    public static String getGender() {
        return gender;
    }
    public static float getWeight() {
        return weight;
    }
    public static String getUsername() {
        return username;
    }
    public static float getHeight() {
        return height;
    }
    public static String getActivityLevel() {
        return activityLevel;
    }

    public static void setWeight(String username) {
        User.weight = weight;
    }
}
