//package com.example.dietapp;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//
//public class Splash extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setDuration(3000);
//        fadeOut.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {}
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(Splash.this, MainActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {}
//        });
//
//        findViewById(R.id.activity_splash).startAnimation(fadeOut);
//    }
//}
