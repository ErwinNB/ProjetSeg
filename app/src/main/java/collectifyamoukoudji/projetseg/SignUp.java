package collectifyamoukoudji.projetseg;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private EditText prenom;
    private EditText nom;
    private EditText adressemail;
    private EditText mdp;
    private EditText confmdp;
    private EditText adress;
    private Button btnContinuer;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        setupUI();

        firebaseAuth = firebaseAuth.getInstance();

        btnContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm()){

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
                                adress.setText(null);
                                confmdp.setText(null);
                                mdp.setText(null);
                            }
                        }
                    });

                }



            }
        });
    }

    public void setupUI(){

        prenom = (EditText)findViewById(R.id.editTextPrenom);
        nom = (EditText)findViewById(R.id.editTextNom);
        adressemail = (EditText)findViewById(R.id.editTextEmail);
        mdp = (EditText)findViewById(R.id.mdp);
        confmdp = (EditText)findViewById(R.id.confmdp);
        adress = (EditText)findViewById(R.id.editTextAdresse);
        btnContinuer = (Button)findViewById(R.id.btnContinuer);
    }

    public boolean confirm () {
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

}
