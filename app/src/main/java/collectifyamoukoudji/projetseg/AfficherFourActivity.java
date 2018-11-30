package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AfficherFourActivity extends AppCompatActivity {
    private TextView textViewAdresse;
    private TextView textViewTelephone;
    private TextView textViewCourriel;
    private TextView textViewDescription;
    private TextView textViewSiteWeb;
    private TextView textViewOrgName;
    private DatabaseReference databaseUsers;
    private String iduser;
    private Users cuser;
    private Organisation org;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficherfour);
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        getFournisseur();
        setupUI();
        //fillUI();
    }

    private void fillUI() {
        String num = cuser.get_currentOrganisation().get_organisationAddress().get_num();
        String sname = cuser.get_currentOrganisation().get_organisationAddress().get_sname();
        String city = cuser.get_currentOrganisation().get_organisationAddress().get_city();
        String pCode = cuser.get_currentOrganisation().get_organisationAddress().get_pcode();
        String tel = cuser.get_currentOrganisation().get_organisationAddress().get_phonenum();
        String email = cuser.get_currentOrganisation().get_organisationAddress().get_phonenum();
        String description = cuser.get_currentOrganisation().get_organisationDescription();
        String name = cuser.get_currentOrganisation().get_organisationName();
        String webSite = cuser.get_currentOrganisation().get_organisationAddress().get_website();
        textViewAdresse.setText(num+" "+sname+","+city+","+pCode);
        textViewTelephone.setText(tel);
        textViewCourriel.setText(email);
        textViewDescription.setText(description);
        textViewSiteWeb.setText(webSite);
        textViewOrgName.setText(name);
    }

    private void setupUI() {
        textViewAdresse = (TextView)findViewById(R.id.textViewAdresse);
        textViewTelephone = (TextView)findViewById(R.id.textViewTelephone);
        textViewCourriel = (TextView)findViewById(R.id.textViewCourreil);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewSiteWeb = (TextView)findViewById(R.id.textViewWeb);
        textViewOrgName = (TextView)findViewById(R.id.textViewNomOrg);
    }

    public void getFournisseur() {

         iduser = getIntent().getStringExtra("idUser");
        toastMessage(iduser);
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(iduser != null){
                    Users user  = dataSnapshot.child(iduser).getValue(Users.class);
                    toastMessage(user.toString());
                    cuser = new Users(user.getId(), user.get_firstname(), user.get_lastname(), user.get_email(), user.get_type(), user.get_currentOrganisation());
                    Log.d("DEBUG", "Value is: " + cuser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                toastMessage("Failed to alter database.");
                Log.w("DEBUG", "Failed to read value.", databaseError.toException());
            }
        });
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
