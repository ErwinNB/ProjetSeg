package collectifyamoukoudji.projetseg;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Welcome extends AppCompatActivity {

    private TextView welcome;

    private TextView info;

    private String userID;

    private ListView list;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseUsers;
    private List<Users> users;
    private ListView listViewUser;
    private Users CurrentUSer;
    private static final String TAG = "WELCOME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = firebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        users = new ArrayList<>();
        userID = user.getUid();



        setupUI();



        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Users user = dataSnapshot.child(userID).getValue(Users.class);

                info.setText("Salut! " +user.get_email()+", vous etes authentifié en tant que "+user.get_type());
                info.setVisibility(View.VISIBLE);
                users.clear();
                CurrentUSer = dataSnapshot.child(userID).getValue(Users.class);
                for(DataSnapshot postDataSnapshot : dataSnapshot.getChildren()){
                    //getting product
                     user = postDataSnapshot.getValue(Users.class);
                    users.add(user);
                }
                if (CurrentUSer.get_type().equals("Administrateur")) {
                    //creatig adapter
                    UsersList userAdapter = new UsersList(Welcome.this, users);
                    //attaching adapter to Listview
                    listViewUser.setAdapter(userAdapter);
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


    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);

    }
    @Override
    public void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(mAuthListener);
    }

    public void setupUI(){
        welcome = (TextView)findViewById(R.id.Welcome);
        info =  (TextView)findViewById(R.id.textInfo);
        listViewUser = (ListView)findViewById(R.id.listViewUsers);

    }


//    private void showData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            Users uInfo = ds.child(userID).getValue(Users.class);
////
////            uInfo.set_firstname(ds.child(userID).getValue(Users.class); //set the name
////            uInfo.set_email(ds.child(userID).getValue(Users.class).get_email()); //set the email
////            uInfo.set_type(ds.child(userID).getValue(Users.class).get_type()); //set the phone_num
//
//            //display all the information
//            Log.d(TAG, "showData: name: " + uInfo.get_firstname());
//            Log.d(TAG, "showData: email: " + uInfo.get_email());
//            Log.d(TAG, "showData: phone_num: " + uInfo.get_type());
//
//            info.setText(uInfo.get_email()+" Vous etes authentifié en tant que "+uInfo.get_type());
//
//        }
//    }




    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
