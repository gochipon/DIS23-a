package com.example.dietapp;

import android.graphics.Bitmap;

public class Food {
    private static String breakfast_name;
    private static String lunch_name;
    private static String dinner_name;
    private static float breakfast_calories;
    private static float lunch_calories;
    private static float dinner_calories;
    private static Bitmap breakfast_picture;
    private static Bitmap lunch_picture;
    private static Bitmap dinner_picture;
    // コンストラクタ
    public Food(String breakfast_name, String lunch_name, String dinner_name, float breakfast_calories, float lunch_calories, float dinner_calories
                //Bitmap breakfast_picture=None, Bitmap lunch_picture=None, Bitmap dinner_picture=None) {
                ){
        this.breakfast_name = breakfast_name;
        this.lunch_name = lunch_name;
        this.dinner_name = dinner_name;
        this.breakfast_calories = breakfast_calories;
        this.lunch_calories = lunch_calories;
        this.dinner_calories = dinner_calories;
//        this.breakfast_picture = breakfast_picture;
//        this.lunch_picture = lunch_picture;
//        this.dinner_picture = dinner_picture;
    }
    // ゲッター
    public static String getBreakfastName() {
        return breakfast_name;
    }
    public static String getLunchName() {
        return lunch_name;
    }
    public static String getDinnerName() {
        return dinner_name;
    }
    public static float getBreakfastCalories() {
        return breakfast_calories;
    }
    public static float getLunchCalories() {
        return lunch_calories;
    }
    public static float getDinnerCalories() {
        return dinner_calories;
    }
//    public static Bitmap getBreakfastPicture() {
//        return breakfast_picture;
//    }
//    public static Bitmap getLunchPicture() {
//        return lunch_picture;
//    }
//    public static Bitmap getDinnerPicture() {
//        return dinner_picture;
//    }
    public static void setBreakfast(String breakfast_name, float breakfast_calories) {
        Food.breakfast_name = breakfast_name;
        Food.breakfast_calories = breakfast_calories;
//        Food.breakfast_picture = breakfast_picture;
    }
    public static void setLunch(String lunch_name, float lunch_calories) {
        Food.lunch_name = lunch_name;
        Food.lunch_calories = lunch_calories;
//        Food.lunch_picture = lunch_picture;
    }
    public static void setDinner(String dinner_name, float dinner_calories) {
        Food.dinner_name = dinner_name;
        Food.dinner_calories = dinner_calories;
//        Food.dinner_picture = dinner_picture;
    }
}
