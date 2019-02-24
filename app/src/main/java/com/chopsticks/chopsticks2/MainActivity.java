package com.chopsticks.chopsticks2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean player1Turn = true;
    boolean sideOne = true;
    boolean firstClick = true;
    Button tempButton;
    int tempTotal;
    int handsDown1 = 0;
    int handsDown2 = 0;
    //////////////////////
    // ON CREATE METHOD //
    //////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button topLeft = findViewById(R.id.top_left);
        Button topRight = findViewById(R.id.top_right);
        Button bottomLeft = findViewById(R.id.bottom_left);
        Button bottomRight = findViewById(R.id.bottom_right);
        topLeft.setEnabled(false);
        topRight.setEnabled(false);
        bottomLeft.setEnabled(true);
        bottomRight.setEnabled(true);
        topLeft.setText("1");
        topRight.setText("1");
        bottomLeft.setText("1");
        bottomRight.setText("1");
    }

    //////////////////////////
    // END ON CREATE METHOD //
    //////////////////////////

    public void buttonClicked(View v) {
        if (firstClick) {
            tempButton = (Button) v;
            tempTotal = Integer.parseInt(tempButton.getText().toString());
            firstClick = false;
            enableDisable(sideOne);
            sideOne = !sideOne;
        } else {
            tempTotal += Integer.parseInt(((Button) v).getText().toString());
            tempTotal = tempTotal % 10;
            if (tempTotal == 0) {
                tempButton.setVisibility(View.GONE);
                if (player1Turn) {
                    handsDown1++;
                } else {
                    handsDown2++;
                }
            }
            if (handsDown1 == 2) {
                winner("Player 1 wins!");
            } else if (handsDown2 == 2) {
                winner("Player 2 wins!");
            } else {
                tempButton.setText(tempTotal);
                firstClick = true;
                player1Turn = !player1Turn;
            }
        }
    }

    public void winner(String winner) {
        setContentView(R.layout.winner);
        TextView win = findViewById(R.id.endGame);
        win.setText(winner);
    }

    public void enableDisable(boolean side) {
        Button topLeft = findViewById(R.id.top_left);
        Button topRight = findViewById(R.id.top_right);
        Button bottomLeft = findViewById(R.id.bottom_left);
        Button bottomRight = findViewById(R.id.bottom_right);
        topLeft.setEnabled(side);
        topRight.setEnabled(side);
        bottomLeft.setEnabled(!side);
        bottomRight.setEnabled(!side);
    }
}
