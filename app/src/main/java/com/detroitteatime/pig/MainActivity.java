package com.detroitteatime.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;

    TextView p1score, p2score, roundTxt;
    int p1, p2, round;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = (Button) findViewById(R.id.button);
        p1score = (TextView) findViewById(R.id.p1);
        p2score = (TextView) findViewById(R.id.p2);
        roundTxt = (TextView) findViewById(R.id.round);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("p1", p1);
                intent.putExtra("p2", p2);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });


        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);


        Intent intent = getIntent();
        p1 =  intent.getIntExtra("p1", 0);
        p1score.setText("P1 : " + p1);
        p2 =  intent.getIntExtra("p2", 0);
        p2score.setText("P2 : " + p2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());

        if(val2 == 1 || val1 == 1){
            Intent intent = new Intent(MainActivity.this,Player2.class);
            intent.putExtra("p1", p1);
            intent.putExtra("p2", p2);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }else{
            setDie(val1, die1);
            setDie(val2, die2);

            round = round + val1 + val2;
            roundTxt.setText("Round : " + round);
            p1 = p1 + val1 + val2;
            p1score.setText("P1 : " + p1);
            if(p1 >=  100){
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Player 1");
                alertDialog.setMessage("Yipeeeeee");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                p1score.setText("P1 : 0");
                                p2score.setText("P2 : 0");
                                roundTxt.setText("Round : 0");
                                p1 = p2 = round = 0;
                            }
                        });
                alertDialog.show();
            }
        }

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/