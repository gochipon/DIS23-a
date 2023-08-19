package com.example.dietapp;

public class Character {

    private static String character_type;
    private static String character_traits;
    private static String appearance_attributes;
    private static String imageUrl;
    // コンストラクタ
    public Character(String character_type, String character_traits, String appearance_attributes, String imageUrl) {
        Character.character_type = character_type;
        Character.character_traits = character_traits;
        Character.appearance_attributes = appearance_attributes;
        Character.imageUrl = imageUrl;
    }

    public String getCharacterType() {
        return character_type;
    }

    public String getCharacterTrait() {
        return character_traits;
    }

    public static String getAppearance_attributes() {return appearance_attributes;}
    public static String getImageUrl() {return imageUrl;}
}
