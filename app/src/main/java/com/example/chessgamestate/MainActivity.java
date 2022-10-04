package com.example.chessgamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runTestButton = findViewById(R.id.runTest);
        TextView textView = findViewById(R.id.multiLineText);
        ChessState state = new ChessState();
        runTestButton.setOnClickListener(state);
        state.setRunTestButton(runTestButton);
        state.setTextView(textView);
    }
}