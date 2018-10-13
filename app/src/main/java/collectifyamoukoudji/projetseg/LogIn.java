package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn extends AppCompatActivity {


    EditText userEmail;
    EditText userPassword;
    Button soumettre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);
        soumettre = (Button) findViewById(R.id.login);

        soumettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
    }

    private void logIn(String email, String password){

        DatabaseReference logData = FirebaseDatabase.getInstance().getReference("User");
        DatabaseReference admin = logData.child("Administrateur");
        DatabaseReference fournisseur = logData.child("Fournisseur");
        DatabaseReference client = logData.child("client");

        if (admin.child(email)) {
            if (password == admin.child(email).password)
                Intent intent = new Intent(getApplicationContext(), Administrateur.class);
        }
        else {
            if (fournisseur.child(email)) {
                if (userPassword == fournisseur.child(email).password)
                    Intent intent = new Intent(getApplicationContext(), Fournisseur.class);
            }
            if (client.child(email)) {
                if (userPassword == client.child(email).password)
                    Intent intent = new Intent(getApplicationContext(), Client.class);
            }
        }
    }
}
