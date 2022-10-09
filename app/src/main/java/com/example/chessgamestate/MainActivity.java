package com.example.chessgamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener  {

    private TextView textView;
    private ChessState state;
    int click;
    ChessState firstInstance;
    ChessState secondInstance;
    ChessState thirdInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        click = 0;
        setContentView(R.layout.activity_main);

        Button runTestButton = findViewById(R.id.runTest);
        textView = findViewById(R.id.multiLineText);
        state = new ChessState();
        runTestButton.setOnClickListener(this);
        state.setRunTestButton(runTestButton);
        state.setTextView(textView);
        firstInstance = new ChessState();
        secondInstance = new ChessState(firstInstance);
        thirdInstance = new ChessState();
    }

    @Override
    public void onClick(View view) {
        //set to empty text
        textView.append(String.valueOf(firstInstance));
        if(firstInstance.getTurnCount() > 2) {
            textView.append("\nThis is the Second Instance: \n");
            textView.append(String.valueOf(secondInstance));
            textView.append("\nThis is the Third Instance: \n");
            textView.append(String.valueOf(thirdInstance));
        }
    }
}