package com.mahad.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ReceiverDetailsActivity extends AppCompatActivity {

    TextInputEditText rEmail, rfName, rCI, rCountry, rAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receiver_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //retrieving ids
        rEmail = findViewById(R.id.rEmail);
        rfName = findViewById(R.id.rfName);
        rCI = findViewById(R.id.rCI);
        rAddress = findViewById(R.id.rAddress);
        rCountry = findViewById(R.id.rCountry);

        //dialer input for contact
        rCI.setInputType(InputType.TYPE_CLASS_PHONE);

        Button btnRNext = findViewById(R.id.btnRNext);
        btnRNext.setOnClickListener(v -> {

            Intent senderIntent = getIntent();
            String senderFullName = senderIntent.getStringExtra("senderFullName");
            String senderContact = senderIntent.getStringExtra("senderContact");
            String senderAddress = senderIntent.getStringExtra("senderAddress");
            String senderCountry = senderIntent.getStringExtra("senderCountry");

            if (inputChecks()){
                Intent intent = new Intent(ReceiverDetailsActivity.this, ReviewInformationActivity.class);
                //sender data
                intent.putExtra("senderFullName", senderFullName);
                intent.putExtra("senderContact", senderContact);
                intent.putExtra("senderAddress", senderAddress);
                intent.putExtra("senderCountry", senderCountry);

                //receiver data
                intent.putExtra("receiverFullName", Objects.requireNonNull(rfName.getText()).toString().trim());
                intent.putExtra("receiverContact", Objects.requireNonNull(rCI.getText()).toString().trim());
                intent.putExtra("receiverAddress", Objects.requireNonNull(rAddress.getText()).toString().trim());
                intent.putExtra("receiverCountry", Objects.requireNonNull(rCountry.getText()).toString().trim());
                startActivity(intent);
            }
        });

    }

    //necessary checks
    private boolean inputChecks(){
        String email = Objects.requireNonNull(rEmail.getText()).toString().trim();
        String fullname = Objects.requireNonNull(rfName.getText()).toString().trim();
        String ci = Objects.requireNonNull(rCI.getText()).toString().trim();
        String country = Objects.requireNonNull(rCountry.getText()).toString().trim();
        String address = Objects.requireNonNull(rAddress.getText()).toString().trim();

        if (email.isEmpty()||fullname.isEmpty()||ci.isEmpty()||country.isEmpty()||address.isEmpty()){
            Toast.makeText(this,"All fields must be filled",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!email.contains("@")){
            Toast.makeText(this, "Incorrect email format", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}