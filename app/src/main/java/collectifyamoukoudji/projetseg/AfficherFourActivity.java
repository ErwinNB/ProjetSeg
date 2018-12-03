package collectifyamoukoudji.projetseg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class AfficherFourActivity extends AppCompatActivity {
    private TextView textViewAdresse;
    private TextView textViewTelephone;
    private TextView textViewCourriel;
    private TextView textViewDescription;
    private TextView textViewSiteWeb;
    private TextView textViewOrgName;
    private Button btnRate;
    private Button btnBook;
    private DatabaseReference databaseUsers;
    private DatabaseReference rateReference;
    private String iduser;
    private String clientemail;
    private Users cuser;
    private Organisation org;
    private double rate;
    private ArrayList<Double> previousrate;
    private ArrayList<String> days;
    private ArrayList<String> times;
    private ArrayList<String> dic;
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayAdapter<String> spinnerArrayAdapter2;
    private String jour;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficherfour);
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        iduser = getIntent().getStringExtra("idUser");
        clientemail = getIntent().getStringExtra("clientemail");
//        toastMessage(iduser);
        setupUI();
        getFournisseur();
        //fillUI();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //showBookDialog(textViewOrgName.getText().toString());
                openBook();

            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRateDialog(textViewOrgName.getText().toString());
            }
        });
    }

    private void showRateDialog(final String orgName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rating_dialog, null);
        dialogBuilder.setView(dialogView);

        final RatingBar ratingBar = (RatingBar) dialogView.findViewById(R.id.rating);
        final Button buttonRate = (Button) dialogView.findViewById(R.id.buttonRate);


        dialogBuilder.setTitle(orgName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //rate = rating;

                rate = Double.valueOf(rating);
            }
        });

        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                previousrate.add(rate);


                rate();

                //toastMessage("Stars : " + rate);
                //toastMessage("newRating : " + newRating);



            }
        });

    }

    private void showBookDialog(final String orgName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.booking_dialog, null);
        dialogBuilder.setView(dialogView);



        final TextView org_name = (TextView) dialogView.findViewById(R.id.textViewNomOrg);
        final TextView client_name = (TextView) dialogView.findViewById(R.id.textViewClient);
        final TextView booking_id = (TextView) dialogView.findViewById(R.id.textBookingid);
        final Spinner sTimeslt = (Spinner) dialogView.findViewById(R.id.spinnerTimeSlt);
        final Spinner sDays = (Spinner) dialogView.findViewById(R.id.spinnerDays);
        final Button buttonBook = (Button) dialogView.findViewById(R.id.buttonBooking);
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner_item);
        spinnerArrayAdapter2 = new ArrayAdapter<String>(this.getApplicationContext(),R.layout.spinner_item);

        spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        sDays.setAdapter(spinnerArrayAdapter);
        sTimeslt.setAdapter(spinnerArrayAdapter2);


        sDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jour = spinnerArrayAdapter.getItem(position).toString();

                //loadTime(jour);




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //loadDays(sDays);

        dialogBuilder.setTitle(orgName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        rateReference =FirebaseDatabase.getInstance().getReference("Users").child(iduser).child("_currentOrganisation").child("_rdvlist");

        String key = rateReference.push().getKey();

        org_name.setText(orgName);
        client_name.setText(cuser.get_email());
        booking_id.setText(key);


        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });

    }

    private void loadDays(final Spinner s){



        rateReference =FirebaseDatabase.getInstance().getReference("Users").child(iduser).child("_currentOrganisation").child("_organisationHorraire");

        rateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Horraire value = dataSnapshot.getValue(Horraire.class);

                    ArrayList<ArrayList<Boolean>> arr = value.get_array();

                    for (int i = 0; i < 12 ; i++) {
                        for (int j = 0; j < 7; j++) {
                            if(arr.get(i).get(j).equals(true)){
                                dic.add(days.get(j)+"-"+times.get(i));
                            }

                        }
                    }


                        for (String str : dic){
                            for (String da : days){
                                if(str.contains(da)){
                                    spinnerArrayAdapter.add(da);
                                }
                             }

                         }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void loadTime(final String s){



        rateReference =FirebaseDatabase.getInstance().getReference("Users").child(iduser).child("_currentOrganisation").child("_organisationHorraire");

        rateReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Horraire value = dataSnapshot.getValue(Horraire.class);

                ArrayList<ArrayList<Boolean>> arr = value.get_array();

                for (int i = 0; i < 12 ; i++) {
                    for (int j = 0; j < 7; j++) {
                        if(arr.get(i).get(j).equals(true)){
                            dic.add(days.get(j)+"-"+times.get(i));
                        }

                    }
                }


                for (String str : dic){

                    for (String t : times){
                        if(s != null){
                            if( str.contains(s) && str.contains(t)){
                                spinnerArrayAdapter2.add(t);
                            }
                        }

                    }

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void rate(){


        rateReference =FirebaseDatabase.getInstance().getReference("Users").child(iduser).child("_currentOrganisation").child("_rating");

        rateReference.setValue(previousrate);

        toastMessage("Merci!");

        }


    private void setupUI() {

        days = new ArrayList<>();
        times = new ArrayList<>();
        dic = new ArrayList<>();

        days.add("LUN");
        days.add("MAR");
        days.add("MER");
        days.add("JED");
        days.add("VEN");
        days.add("SAM");
        days.add("DIM");

        times.add("8-9h");
        times.add("9-10h");
        times.add("10-11h");
        times.add("11-12h");
        times.add("12-13h");
        times.add("13-14h");
        times.add("14-15h");
        times.add("15-16h");
        times.add("16-17h");
        times.add("17-18h");
        times.add("18-19h");
        times.add("19-20h");

        textViewAdresse = (TextView)findViewById(R.id.textViewAdresse);
        textViewTelephone = (TextView)findViewById(R.id.textViewTelephone);
        textViewCourriel = (TextView)findViewById(R.id.textViewCourreil);
        textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewSiteWeb = (TextView)findViewById(R.id.textViewWeb);
        textViewOrgName = (TextView)findViewById(R.id.textViewNomOrg);
        btnRate = (Button)findViewById(R.id.buttonEvaluer);
        btnBook = (Button)findViewById(R.id.buttonReserver);


    }

    public void openBook(){
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("idUser", iduser);
        intent.putExtra("client", clientemail);
        intent.putExtra("orgName", textViewOrgName.getText().toString());
        startActivity(intent);
    }

    public void getFournisseur() {
         iduser = getIntent().getStringExtra("idUser");
         toastMessage(iduser);

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(iduser != null){
                    Users user  = dataSnapshot.child(iduser).getValue(Users.class);
                    cuser = new Users(user.getId(), user.get_firstname(), user.get_lastname(), user.get_email(), user.get_type(), user.get_currentOrganisation());
                    previousrate = cuser.get_currentOrganisation().get_rating();
                    Log.d("DEBUG", "Value is: " + cuser);

                    String num = cuser.get_currentOrganisation().get_organisationAddress().get_num();
                    String sname = cuser.get_currentOrganisation().get_organisationAddress().get_sname();
                    String city = cuser.get_currentOrganisation().get_organisationAddress().get_city();
                    String pays = cuser.get_currentOrganisation().get_organisationAddress().get_country();
                    String pCode = cuser.get_currentOrganisation().get_organisationAddress().get_pcode();
                    String tel = cuser.get_currentOrganisation().get_organisationAddress().get_phonenum();
                    String email = cuser.get_currentOrganisation().get_organisationAddress().get_shopemail();
                    String description = cuser.get_currentOrganisation().get_organisationDescription();
                    String name = cuser.get_currentOrganisation().get_organisationName();
                    String webSite = cuser.get_currentOrganisation().get_organisationAddress().get_website();
                    textViewAdresse.setText(num+" "+sname+" ,"+city+" ,"+ pays+" ,"+pCode);
                    textViewTelephone.setText(tel);
                    textViewCourriel.setText(email);
                    textViewDescription.setText(description);
                    textViewSiteWeb.setText(webSite);
                    textViewOrgName.setText(name);
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
