package com.example.dietapp;

public class Goal {

    private static float targetWeight;
    private static float targetDuration;
    private static int day;

    // コンストラクタ
    public Goal(float targetWeight, float targetDuration, int day) {
        Goal.targetWeight = targetWeight;
        Goal.targetDuration = targetDuration;
        Goal.day = day;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public float getTargetDuration() {
        return targetDuration;
    }
    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        Goal.day = day;
    }
}
