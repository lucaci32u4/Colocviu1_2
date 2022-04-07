package ro.pub.cs.systems.eim.Colocviu2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    private ArrayList<Integer> integers = new ArrayList<>();
    private boolean changed = true;
    private int cache = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);
        if (savedInstanceState != null) {
            ArrayList<Integer> saved = savedInstanceState.getIntegerArrayList("ints");
            if (saved != null) {
                integers = saved;
                changed = savedInstanceState.getBoolean("changed");
                cache = savedInstanceState.getInt("cache");
            }
        }
        refreshPreview();
        findViewById(R.id.bt_add).setOnClickListener(view -> {
            String content = ((EditText) findViewById(R.id.ed_add)).getText().toString();
            try {
                int n = Integer.parseInt(content);
                integers.add(n);
                changed = true;
                refreshPreview();
            } catch (NumberFormatException nfe) {
                Toast.makeText(this, "Number not valid", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.bt_compute).setOnClickListener(view -> {
            Intent intent = new Intent(this, Colocviu1_2SecondaryActivity.class);
            intent.putExtra("ints", integers);
            startActivityForResult(intent, 7);
        });
    }


    void refreshPreview() {
        ((TextView) findViewById(R.id.text_show)).setText(integers.stream().map(Object::toString).collect(Collectors.joining(" + ")));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("ints", integers);
        outState.putBoolean("changed", changed);
        outState.putInt("chache", cache);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == Activity.RESULT_OK) {
            int sum = data.getExtras().getInt("sum");
            cache = sum;
            changed = false;
            Toast.makeText(this, "Sum is " + sum, Toast.LENGTH_SHORT).show();
        }
    }
}