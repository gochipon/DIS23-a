package com.example.dietapp;

public class Goal {

    private static float targetWeight;
    private static float targetDuration;

    // コンストラクタ
    public Goal(float targetWeight, float targetDuration) {
        Goal.targetWeight = targetWeight;
        Goal.targetDuration = targetDuration;
    }

    public float getTargetWeight() {
        return targetWeight;
    }

    public float getTargetDuration() {
        return targetDuration;
    }
}
