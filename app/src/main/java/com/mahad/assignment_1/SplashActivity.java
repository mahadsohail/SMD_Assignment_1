package com.mahad.assignment_1;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the ImageView
        View splashImage = findViewById(R.id.splashImage);

        //Starting the animation below the visible area
        splashImage.post(() -> {
            float screenHeight = getResources().getDisplayMetrics().heightPixels;
            splashImage.setTranslationY(screenHeight);

            // Animation
            ObjectAnimator animator = ObjectAnimator.ofFloat(splashImage, "translationY", splashImage.getTranslationY(), 0);
            animator.setDuration(5000);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.start();
        });

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, SenderDetailsActivity.class);
            startActivity(i);
            finish();
        }, 5000);
    }
}
