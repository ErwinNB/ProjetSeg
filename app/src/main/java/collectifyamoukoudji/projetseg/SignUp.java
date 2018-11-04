package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class SignUp extends AppCompatActivity {

    private EditText prenom;
    private EditText nom;
    private EditText adressemail;
    private EditText mdp;
    private EditText confmdp;
    private Button btnContinuer;
    private Spinner spinner;
<<<<<<< HEAD
=======
    private Users user;
>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29

    //private ProSwipeButton btnContinuer;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
<<<<<<< HEAD

=======
        user = new Users();
>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29
        setupUI();

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth = firebaseAuth.getInstance();

        btnContinuer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(confirm()&& valider()){

                    String user_email = adressemail.getText().toString().trim();
                    String user_mdp = mdp.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
=======

>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29
                            }else {
                                Toast.makeText(SignUp.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                prenom.setText(null);
                                nom.setText(null);
                                adressemail.setText(null);
                                confmdp.setText(null);
                                mdp.setText(null);
<<<<<<< HEAD
                            }
                        }
                    });

                    addUser();
=======

                            }
                        }
                    });
                    addUser();
                    openWelcome();

>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29

                }



            }
        });

        /*btnContinuer.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                if(confirm() && valider()){

                    String user_email = adressemail.getText().toString().trim();
                    String user_mdp = mdp.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                btnContinuer.showResultIcon(false);
                            }else {
                                Toast.makeText(SignUp.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                                prenom.setText(null);
                                nom.setText(null);
                                adressemail.setText(null);
                                confmdp.setText(null);
                                mdp.setText(null);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

                    addUser();
                    openWelcome();

                }



            }

        });*/
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
               // btnContinuer.showResultIcon(false);
                return false;
            }
        }else {
            System.out.println("\n\n\n\n\n\n\nemail a echoué:"+user_mdp+" "+user_mdpconf+" "+user_email);
            Toast.makeText(SignUp.this, "Veuillez rentrer une adresse email valide!", Toast.LENGTH_SHORT).show();
           // btnContinuer.showResultIcon(false);
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

    private void addUser(){

        String fname = prenom.getText().toString();
        String lname = nom.getText().toString();
        String email = adressemail.getText().toString();
        String type = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(email)){

            String id  = databaseUsers.push().getKey();

            Users client = new Users(id, fname, lname, email, type);
<<<<<<< HEAD

=======
            user =  client;
>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29
            databaseUsers.child(id).setValue(client);


            Toast.makeText(this, "User Info Added to Database", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Missing email address", Toast.LENGTH_SHORT).show();
        }


    }

    private void openWelcome(){
        Intent intent = new Intent(this, Welcome.class);
<<<<<<< HEAD
=======
        intent.putExtra("UserEmail", user.get_email());
        intent.putExtra("UserType", user.get_type());
        intent.putExtra("UserName", user.get_firstname());
>>>>>>> 4e845dd925247f42a761e0f62c90a2da2ee90c29
        startActivity(intent);
    }

    private void authUser() {

        String user_email = adressemail.getText().toString().trim();
        String user_mdp = mdp.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(user_email, user_mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SignUp.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    prenom.setText(null);
                    nom.setText(null);
                    adressemail.setText(null);
                    confmdp.setText(null);
                    mdp.setText(null);
                }
            }
        });

    }

}



