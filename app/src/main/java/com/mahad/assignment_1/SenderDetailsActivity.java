package com.mahad.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SenderDetailsActivity extends AppCompatActivity {


    TextInputEditText sEmail, sFullName, sCI, sCountry, sAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sender_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.asd), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //retrieving ids
        sEmail = findViewById(R.id.sEmail);
        sFullName = findViewById(R.id.sFullName);
        sCI = findViewById(R.id.sCI);
        sCountry = findViewById(R.id.sCountry);
        sAddress = findViewById(R.id.sAddress);

        //input type for contact information
        sCI.setInputType(InputType.TYPE_CLASS_PHONE);



        Button btnSNext = findViewById(R.id.btnSNext);
        btnSNext.setOnClickListener(v -> {
            if(inputChecks()){
            Intent intent = new Intent(SenderDetailsActivity.this, ReceiverDetailsActivity.class);
            intent.putExtra("senderFullName", Objects.requireNonNull(sFullName.getText()).toString().trim());
            intent.putExtra("senderContact", Objects.requireNonNull(sCI.getText()).toString().trim());
            intent.putExtra("senderCountry", Objects.requireNonNull(sCountry.getText()).toString().trim());
            intent.putExtra("senderAddress", Objects.requireNonNull(sAddress.getText()).toString().trim());

            startActivity(intent);}
        });

        }

        //necessary checks
        private boolean inputChecks(){
            String email = Objects.requireNonNull(sEmail.getText()).toString().trim();
            String fullname = Objects.requireNonNull(sFullName.getText()).toString().trim();
            String ci = Objects.requireNonNull(sCI.getText()).toString().trim();
            String country = Objects.requireNonNull(sCountry.getText()).toString().trim();
            String address = Objects.requireNonNull(sAddress.getText()).toString().trim();

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
