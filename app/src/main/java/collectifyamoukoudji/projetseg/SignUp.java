package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    private EditText prenom;
    private EditText nom;
    private EditText adressemail;
    private EditText mdp;
    private EditText confmdp;
    private Button btnContinuer;
    private Spinner spinner;
    private Users user;
    private String id;
    private List<String> plantsList;
    private ArrayAdapter<String> spinnerArrayAdapter;
    //private ProSwipeButton btnContinuer;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference db;
    private DatabaseReference databaseUsers;
    private FirebaseUser databaseUser;

    private String userID;

    private static final String TAG = "Signup";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_sign_up);
        user = new Users();



        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = firebaseAuth.getInstance();


        setupUI();



        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users value = dataSnapshot.getValue(Users.class);
                Log.d(TAG, "Value is: " + value);

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){

                     value = postsnapshot.getValue(Users.class);
                        Log.d(TAG, "Value is: " + value.get_type());
//
                     if (value.get_type().equals("Administrateur") && plantsList.size() > 2){
                            plantsList.remove(2);
                            spinnerArrayAdapter.notifyDataSetChanged();
                     }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                toastMessage("Failed to alter database.");
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        btnContinuer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(confirm()&& valider()){

                    toastMessage("Enregistrement...");
                    logIn();

                }

            }
        });
    }
    //
    //valide les informations passé par lutilisateur email et mot de passe
    private boolean valider() {
        String user_email = adressemail.getText().toString().trim();
        String user_mdp = mdp.getText().toString().trim();
        String user_mdpconf = confmdp.getText().toString().trim();
        if ( isEmailAdress(user_email)){
            if (user_mdp.equals(user_mdpconf)){
                return true;
            }else{
                System.out.println("\n\n\n\n\n\n\nmdp a echoué:"+user_mdp+" "+user_mdpconf+" "+user_email);
                Toast.makeText(SignUp.this, "Votre motdepasse ne correspond pas!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            System.out.println("\n\n\n\n\n\n\nemail a echoué:"+user_mdp+" "+user_mdpconf+" "+user_email);
            Toast.makeText(SignUp.this, "Veuillez rentrer une adresse email valide!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public static boolean isEmailAdress(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }

    public void setupUI(){

        prenom = (EditText)findViewById(R.id.editTextPrenom);
        nom = (EditText)findViewById(R.id.editTextNom);
        adressemail = (EditText)findViewById(R.id.editTextEmail);
        mdp = (EditText)findViewById(R.id.mdp);
        confmdp = (EditText)findViewById(R.id.confmdp);
        btnContinuer = (Button) findViewById(R.id.btnContinuer);
        spinner = (Spinner)findViewById(R.id.typeDecompte);
        // Initializing a String Array
        String[] plants = new String[]{
                "Clients",
                "Fournisseur de services",
                "Administrateur"
        };

        plantsList = new ArrayList<>(Arrays.asList(plants));

        // Initializing an ArrayAdapter
        spinnerArrayAdapter = new ArrayAdapter<>(
                this,R.layout.spinner_item,plantsList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public boolean confirm(){
        Boolean valide = false;

        String fname = prenom.getText().toString();
        String lname = nom.getText().toString();
        String email = adressemail.getText().toString();
        String password = mdp.getText().toString().trim();
        String passwordconf = confmdp.getText().toString().trim();

        if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || passwordconf.isEmpty()){
            Toast.makeText(this,"Please enter all the details.", Toast.LENGTH_SHORT).show();
        }else {
            valide = true;
        }

        return valide;
    }

    private  void logIn(){
        final String user_email = adressemail.getText().toString().trim();
        final String user_mdp = mdp.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(user_email, user_mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(SignUp.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    addUser();
                    prenom.setText(null);
                    nom.setText(null);
                    adressemail.setText(null);
                    confmdp.setText(null);
                    mdp.setText(null);

                }
            }
        });

    }

    private void addUser(){

        String fname = prenom.getText().toString();
        String lname = nom.getText().toString();
        String email = adressemail.getText().toString();
        String type = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(email)){

            databaseUser = firebaseAuth.getCurrentUser();

            id = databaseUser.getUid();
            Users client = new Users(id, fname, lname, email, type);
//            user =  client;
            databaseUsers.child(id).setValue(client);

            Toast.makeText(this, "User Info Added to Database", Toast.LENGTH_LONG).show();
            openWelcome();
        }
        else {
            Toast.makeText(this, "Missing email address", Toast.LENGTH_SHORT).show();
        }


    }

    private void openWelcome(){

        Intent intent = new Intent(this, Welcome.class);
//        intent.putExtra("UserEmail", user.get_email());
//        intent.putExtra("UserType", user.get_type());
//        intent.putExtra("UserName", user.get_firstname());
        startActivity(intent);
    }


    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}



