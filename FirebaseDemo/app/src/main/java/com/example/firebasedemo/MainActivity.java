package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText ffood;
    private Button addBtn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.add);
        ffood = findViewById(R.id.ffood);
        listView = findViewById(R.id.listView);

        // firebase realtime database demo
        //FirebaseDatabase.getInstance().getReference().child("Firebase_Learning").child("Android").setValue("i'm the storm!");

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("Name", "Vivek Soni");
        map.put("Email", "viveksoni100@gmail.com");
        FirebaseDatabase.getInstance().getReference().child("Firebase_Learning").updateChildren(map);*/

        // set value in your db dynamically
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_food_name = ffood.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("Firebase_Learning")
                        .child("Favorite Food").setValue(txt_food_name);

            }
        });


        // retrieve data from your db
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Firebase_Learning");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
