package com.example.dietapp;

public class Goal {

    private static float targetWeight;
    private static String targetDuration;

    // コンストラクタ
    public Goal(float targetWeight, String targetDuration) {
        Goal.targetWeight = targetWeight;
        Goal.targetDuration = targetDuration;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public String getTargetDuration() {
        return targetDuration;
    }
}
