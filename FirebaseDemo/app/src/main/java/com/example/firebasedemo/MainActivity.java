package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText ffood;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add);
        ffood = findViewById(R.id.ffood);

        // firebase realtime database demo
        //FirebaseDatabase.getInstance().getReference().child("Firebase_Learning").child("Android").setValue("i'm the storm!");

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("Name", "Vivek Soni");
        map.put("Email", "viveksoni100@gmail.com");
        FirebaseDatabase.getInstance().getReference().child("Firebase_Learning").updateChildren(map);*/

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_food_name = ffood.getText().toString();

                FirebaseDatabase.getInstance().getReference().child("Firebase_Learning")
                        .child("Favorite Food").setValue(txt_food_name);

            }
        });

    }
}
