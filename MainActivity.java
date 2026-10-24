package com.example.androidfundamentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LifecycleDemo";
    private EditText etInputData;
    private Button btnSendForward, btnAnonymousClick, btnInterfaceClick;
    private TextView tvResultBack;

    // Modern ActivityResultLauncher for receiving data BACKWARD from SecondActivity
    private final ActivityResultLauncher<Intent> secondActivityLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                String returnedData = result.getData().getStringExtra("RETURN_KEY");
                tvResultBack.setText("Received Back: " + returnedData);
                Toast.makeText(this, "Data returned: " + returnedData, Toast.LENGTH_SHORT).show();
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate called");

        etInputData = findViewById(R.id.etInputData);
        btnSendForward = findViewById(R.id.btnSendForward);
        btnAnonymousClick = findViewById(R.id.btnAnonymousClick);
        btnInterfaceClick = findViewById(R.id.btnInterfaceClick);
        tvResultBack = findViewById(R.id.tvResultBack);

        // 1. Anonymous OnClickListener
        btnAnonymousClick.setOnClickListener(v -> 
            Toast.makeText(this, "Anonymous Listener Clicked", Toast.LENGTH_SHORT).show()
        );

        // 2. Interface OnClickListener Implementation
        btnInterfaceClick.setOnClickListener(this);

        // 3. Forward Data Transfer & Launch SecondActivity
        btnSendForward.setOnClickListener(v -> {
            String message = etInputData.getText().toString();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("FORWARD_KEY", message);
            secondActivityLauncher.launch(intent);
        });
    }

    // Interface Click Handling
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnInterfaceClick) {
            Toast.makeText(this, "Interface Listener Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    // XML android:onClick Method
    public void onXmlButtonClick(View view) {
        Toast.makeText(this, "XML OnClick Clicked", Toast.LENGTH_SHORT).show();
    }

    // Activity Lifecycle Callbacks
    @Override
    protected void onStart() { super.onStart(); Log.d(TAG, "onStart called"); }
    @Override
    protected void onResume() { super.onResume(); Log.d(TAG, "onResume called"); }
    @Override
    protected void onPause() { super.onPause(); Log.d(TAG, "onPause called"); }
    @Override
    protected void onStop() { super.onStop(); Log.d(TAG, "onStop called"); }
    @Override
    protected void onRestart() { super.onRestart(); Log.d(TAG, "onRestart called"); }
    @Override
    protected void onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy called"); }
}
