package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class Welcome extends AppCompatActivity {

    private TextView welcome;

    private TextView info;

    private String userID;

    private ListView list;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseUsers;

    private static final String TAG = "WELCOME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = firebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        userID = user.getUid();



        setupUI();



        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Users person = dataSnapshot.child(userID).getValue(Users.class);

                info.setText("Salut! " +person.get_email()+", vous etes authentifié en tant que "+person.get_type());
                info.setVisibility(View.VISIBLE);


                if (person.get_type().toString().equals("Administrateur")){

                    showData(dataSnapshot);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    toastMessage("Successfully signed in with: " + user.getEmail());
                    userID = user.getUid();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };




//
//        getInfo();

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
//        info.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public void setupUI(){
        welcome = (TextView)findViewById(R.id.Welcome);
        info =  (TextView)findViewById(R.id.textInfo);
        list = (ListView)findViewById(R.id.listViewUsers);
    }



    public void getInfo(){

        String email = getIntent().getStringExtra("UserEmail");
        String type = getIntent().getStringExtra("UserType");
        info.setText(email+" Vous etes authentifié en tant que "+type);
    }

    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<Users> array  = new ArrayList<>();
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

            userID = postSnapshot.getKey();



            Users obj = dataSnapshot.getValue(Users.class);

            obj = postSnapshot.getValue(Users.class);

            String t = dataSnapshot.getKey().toString();


            array.add(obj);

        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        list.setAdapter(adapter);

    }



    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
