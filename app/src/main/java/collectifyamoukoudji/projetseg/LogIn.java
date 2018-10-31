package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.andexert.library.RippleView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {

    private static final String TAG = "LogIn";

    private EditText userEmail;
    private EditText userPassword;
    private String type;

    private String userID;


    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuth;
    private FirebaseDatabase db;
    private Button btnSignIn;
    private FirebaseUser user;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();



        mAuth = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                    toastMessage("Successfully signed in with: " + user.getEmail());

                }else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    toastMessage("Successfully signed out.");
                }
            }
        };





        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);

        btnSignIn = (Button) findViewById(R.id.login);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
                alpha.setDuration(500);
                if(confirm()){

                    btnSignIn.startAnimation(alpha);
                    logIn();
                }

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mAuth);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.signOut();
        auth.removeAuthStateListener(mAuth);
    }

    private void openWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("UserEmail", user.getEmail());
        startActivity(intent);
    }


    private void logIn(){

        String email = userEmail.getText().toString();
        String password  = userPassword.getText().toString();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // there was an error
                    toastMessage("Entrez les bonnes informatiosn d'utilisateur");
                } else {
                    toastMessage("Authentification reussie.");
//
//
//                    if(type.equals("Administrateur")){
//                        openAdmin();
//                        finish();
//                    }else {
//
//                    }

                    openWelcome();
                    finish();

                }
            }
        });

    }

    public boolean confirm(){
        Boolean valide = false;

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString().trim();

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Entrer tout les details svp.", Toast.LENGTH_SHORT).show();
        }else {
            valide = true;
        }

        return valide;
    }

    private void openAdmin(){

        Intent intent = new Intent(this, AdminActivity.class);

        startActivity(intent);
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}