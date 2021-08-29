package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebasedemo.auth.demo.model.AhmedabadRegionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText ffood;
    private Button addBtn;
    private ListView listView;

    private Uri imageUri;

    private static final int IMAGE_REQUEST = 2;

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

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("image", "image_path");
        map.put("title_en", "Shree Swaminarayan Mandir Kalupur");
        map.put("title_gu", "શ્રી સ્વામિનારાયણ મંદિર કાલુપુર");
        map.put("notes_en", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        map.put("notes_gu", "નવીદિલ્હી : એનસીપીના શરદ પવાર સંસદના સત્ર પહેલાં જ નરેન્દ્ર મોદીને મળતાં રાજકીય અટકળોનું બજાર તેજ બની ગયું છે. બંને વચ્ચે એક કલાક સુધી ચર્ચા થઈ હતી. પવાર ભાજપ ભણી ઢળી રહ્યા હોવાની વાતો ચાલી રહી છે પણ એનસીપીનાં સૂત્રોનો દાવો છે કે, પવાર નવા બનાવાયેલા સહકાર મંત્રાલય મુદ્દે પોતાની નારાજગી વ્યક્ત કરવા મોદીને મળ્યા હતા.મોદી સરકારે સહકાર મંત્રાલય અમિત શાહને સોંપતાં પવાર ધુંઆપુંઆ છે. સૂત્રોનો દાવો છે કે, પવારે મોદી સાથેની મુલાકાતમાં સાફ શબ્દોમાં કહ્યું કે, સહકારી ક્ષેત્ર રાજ્યનો વિષય છે અને કેન્દ્ર સરકારે સહકાર મંત્રાલય બનાવીને રાજ્યોના અધિકાર ક્ષેત્રમાં દખલગીરી કરી છે. આ પ્રકારની દખલગીરી દેશના ફેડરલ માળખાને ખતમ કરી નાંખશે ને પ્રચંડ આંદોલન થશે એવી ચીમકી પણ પવારે આપી હોવાનો સૂત્રોનો દાવો છે. પવારે આ મુદ્દે મોદીને પત્ર પણ લખ્યો છે. સહકાર મંત્રાલય અમિત શાહને સોંપીને મોદી સરકાર રાજકીય હરીફોને ખતમ કરવા માટે મંત્રાલયનો ઉપયોગ કરવા માગે છે એવો મત પવારે વ્યક્ત કર્યો છે.");
        FirebaseDatabase.getInstance().getReference().child("Ahmedabad").child("Kalupur Temple").updateChildren(map);*/

        /*HashMap<String, Object> map = new HashMap<>();
        map.put("image", "image_path");
        map.put("title_en", "Delhi Darwaja");
        map.put("title_gu", "શ્રી સ્વામિનારાયણ મંદિર કાલુપુર");
        map.put("notes_en", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        map.put("notes_gu", "નવીદિલ્હી : એનસીપીના શરદ પવાર સંસદના સત્ર પહેલાં જ નરેન્દ્ર મોદીને મળતાં રાજકીય અટકળોનું બજાર તેજ બની ગયું છે. બંને વચ્ચે એક કલાક સુધી ચર્ચા થઈ હતી. પવાર ભાજપ ભણી ઢળી રહ્યા હોવાની વાતો ચાલી રહી છે પણ એનસીપીનાં સૂત્રોનો દાવો છે કે, પવાર નવા બનાવાયેલા સહકાર મંત્રાલય મુદ્દે પોતાની નારાજગી વ્યક્ત કરવા મોદીને મળ્યા હતા.મોદી સરકારે સહકાર મંત્રાલય અમિત શાહને સોંપતાં પવાર ધુંઆપુંઆ છે. સૂત્રોનો દાવો છે કે, પવારે મોદી સાથેની મુલાકાતમાં સાફ શબ્દોમાં કહ્યું કે, સહકારી ક્ષેત્ર રાજ્યનો વિષય છે અને કેન્દ્ર સરકારે સહકાર મંત્રાલય બનાવીને રાજ્યોના અધિકાર ક્ષેત્રમાં દખલગીરી કરી છે. આ પ્રકારની દખલગીરી દેશના ફેડરલ માળખાને ખતમ કરી નાંખશે ને પ્રચંડ આંદોલન થશે એવી ચીમકી પણ પવારે આપી હોવાનો સૂત્રોનો દાવો છે. પવારે આ મુદ્દે મોદીને પત્ર પણ લખ્યો છે. સહકાર મંત્રાલય અમિત શાહને સોંપીને મોદી સરકાર રાજકીય હરીફોને ખતમ કરવા માટે મંત્રાલયનો ઉપયોગ કરવા માગે છે એવો મત પવારે વ્યક્ત કર્યો છે.");
        FirebaseDatabase.getInstance().getReference().child("Ahmedabad").child("Delhi Darwaja").updateChildren(map);*/

        // set value in your db dynamically
        /*addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_food_name = ffood.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("Firebase_Learning")
                        .child("Favorite Movie").setValue(txt_food_name);

            }
        });*/


        // retrieve data from your db - single value
        /*final ArrayList<String> list = new ArrayList<>();
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
        });*/

        // retrieve data from your db - whole object
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Ahmedabad");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AhmedabadRegionModel model = snapshot.getValue(AhmedabadRegionModel.class);
                    String str = model.getTitle_gu();
                    list.add(str);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////

        // uploading image with cloud storage
        /*addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImage();
            }
        });*/
    }

    public void openImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    //to handle the image upload process
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK) {
            imageUri = data.getData();
            uploadImage();
        }
    }

    public void uploadImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading . . .");
        progressDialog.show();

        if (imageUri != null) {
            final StorageReference fileRef = FirebaseStorage.getInstance().getReference().child("demo_uploads")
                    .child("demo_image" + "." + getFileExtension(imageUri));
            fileRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            Log.i("Downloaded url : ", url);
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Image upload success!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    private String getFileExtension(Uri imageUri) {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(imageUri));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

}


