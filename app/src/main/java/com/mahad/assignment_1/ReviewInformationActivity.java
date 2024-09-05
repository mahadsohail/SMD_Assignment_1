package com.mahad.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReviewInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ri), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve sender and receiver data from the intent
        String senderFullName = getIntent().getStringExtra("senderFullName");
        String senderContact = getIntent().getStringExtra("senderContact");
        String senderAddress = getIntent().getStringExtra("senderAddress");
        String senderCountry = getIntent().getStringExtra("senderCountry");

        String receiverFullName = getIntent().getStringExtra("receiverFullName");
        String receiverCountry = getIntent().getStringExtra("receiverCountry");
        String receiverAddress = getIntent().getStringExtra("receiverAddress");
        String receiverContact = getIntent().getStringExtra("receiverContact");

        // Set sender information
        ((TextView) findViewById(R.id.sFullName)).setText(senderFullName);
        ((TextView) findViewById(R.id.sCountry)).setText(senderCountry);
        ((TextView) findViewById(R.id.sAddress)).setText(senderAddress);
        ((TextView) findViewById(R.id.sCI)).setText(senderContact);

        // Set receiver information
        ((TextView) findViewById(R.id.rfName)).setText(receiverFullName);
        ((TextView) findViewById(R.id.rCountry)).setText(receiverCountry);
        ((TextView) findViewById(R.id.rAddress)).setText(receiverAddress);
        ((TextView) findViewById(R.id.rCI)).setText(receiverContact);

        //FAB to get back to sender activity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(ReviewInformationActivity.this, SenderDetailsActivity.class);
            startActivity(intent);
        });

    }
}
