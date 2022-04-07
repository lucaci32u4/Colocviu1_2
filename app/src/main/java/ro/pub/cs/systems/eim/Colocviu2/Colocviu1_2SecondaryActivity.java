package ro.pub.cs.systems.eim.Colocviu2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Colocviu1_2SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_colocviu1_2_secondary);
        ArrayList<Integer> ints = getIntent().getExtras().getIntegerArrayList("ints");
        int sum = ints.stream().mapToInt(i -> i).sum();
        Intent intent = new Intent();
        intent.putExtra("sum", sum);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}