package com.example.androidfundamentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView tvReceivedForward;
    private EditText etReturnData;
    private Button btnSendBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvReceivedForward = findViewById(R.id.tvReceivedForward);
        etReturnData = findViewById(R.id.etReturnData);
        btnSendBack = findViewById(R.id.btnSendBack);

        // Receive Data Forward from MainActivity
        String forwardData = getIntent().getStringExtra("FORWARD_KEY");
        tvReceivedForward.setText("Received Forward: " + (forwardData != null ? forwardData : "No Data"));

        // Send Data Backward to MainActivity
        btnSendBack.setOnClickListener(v -> {
            String responseText = etReturnData.getText().toString();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("RETURN_KEY", responseText);
            setResult(Activity.RESULT_OK, returnIntent);
            finish(); // Closes SecondActivity and returns result
        });
    }
}
