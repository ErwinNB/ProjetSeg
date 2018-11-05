package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
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

public class AdminActivity extends AppCompatActivity {

    private Button users;
    private Button services;
    private String i;
    private static final String TAG = "ADMIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        i = getIntent().getStringExtra("iduser");


        setupUI();
        final AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(500);


        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.startAnimation(alpha);
                openWelcome();
//                finish();
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openService();
                services.startAnimation(alpha);
//                finish();
            }
        });
}


    private void openWelcome(){
        Intent intent = new Intent(this, Welcome.class);
        intent.putExtra("iduser", i);
        startActivity(intent);
    }

    public void openService() {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    public void setupUI() {
        users = (Button) findViewById(R.id.btnUtilisateur);
        services = (Button) findViewById(R.id.btnServices);
    }

    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}