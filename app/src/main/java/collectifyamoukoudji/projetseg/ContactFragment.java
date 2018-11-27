package collectifyamoukoudji.projetseg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ContactFragment extends Fragment{

    View myView;
    private TextView LUN, MAR, MER, JED, VEN, SAM, DIM;
    private CheckBox[][] checkBoxes;
    private ArrayList<ArrayList<Boolean>> plageHorraire;
    private ArrayList<String> semaine;
    private Button  BtnEnregistrerPlage, btnRight, btnLeft;
    private String iduser;
    private DatabaseReference databaseUser;
    private Users cuser;
    private Organisation corganisation;
    private String [] days;
    private int count;
    private boolean flag;
    private int index;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.contact_layout, container, false);
        checkBoxes = new CheckBox[10][7];
        plageHorraire = new ArrayList<>();
        semaine = new ArrayList<>();

        count = 0;

        flag = true;

        index = 0;

        setupUI();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            iduser = bundle.getString("iduser");




            databaseUser = FirebaseDatabase.getInstance().getReference("Users");

            loadAdd();

            databaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if(iduser != null){
                        Users user  = dataSnapshot.child(iduser).getValue(Users.class);
                        cuser = new Users(user.getId(), user.get_firstname(), user.get_lastname(), user.get_email(), user.get_type(), user.get_currentOrganisation());
                        corganisation = user.get_currentOrganisation();
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

        BtnEnregistrerPlage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAvailability();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    count += 7;
                    index += 1;
                    setupUI();
                    loadAdd();
                }catch (ArrayIndexOutOfBoundsException e){
                    count += -7;
                    index += -1;
                    toastMessage("Vous ne pouvez que enregistrer votre horraire par 365 jours");
                }
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    count += -7;
                    index += -1;
                    setupUI();
                    loadAdd();
                }catch (ArrayIndexOutOfBoundsException e){
                    count += 7;
                    index += 1;
                    toastMessage("Vous ne pouvez pas enregistrer une date dans le passer");
                }


            }
        });

        return myView;
    }


    //DONE
    private void addAvailability() {

        for (int i = 0; i < 10 ; i++) {
            ArrayList<Boolean> tmp = new ArrayList<>();
            for (int j = 0; j < 7; j++) {

               tmp.add(checkBoxes[i][j].isChecked());

            }
            plageHorraire.add(tmp);
        }

        addOrganisation();

    }

    private void setupUI() {

        LUN = (TextView) myView.findViewById(R.id.textviewLUN);
        MAR = (TextView) myView.findViewById(R.id.textViewMAR);
        MER = (TextView) myView.findViewById(R.id.textViewMER);
        JED = (TextView) myView.findViewById(R.id.textViewJEU);
        VEN = (TextView) myView.findViewById(R.id.textViewVEN);
        SAM = (TextView) myView.findViewById(R.id.textViewSAM);
        DIM = (TextView) myView.findViewById(R.id.textViewDIM);
        btnRight = (Button) myView.findViewById(R.id.rightbtn);
        btnLeft = (Button) myView.findViewById(R.id.lefbtn);

        days = new String[7];

        days = getWeekDate();



        LUN.setText(days[count]);
        MAR.setText(days[count+1]);
        MER.setText(days[count+2]);
        JED.setText(days[count+3]);
        VEN.setText(days[count+4]);
        SAM.setText(days[count+5]);
        DIM.setText(days[count+6]);



        BtnEnregistrerPlage = (Button) myView.findViewById(R.id.buttonEnregistrerPlage);
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 7; j++) {
                String buttonID = "checkBox" +(7 * i + j+1);
                int resID = getResources().getIdentifier(buttonID, "id",getContext().getPackageName());
                checkBoxes[i][j] = (CheckBox) myView.findViewById(resID);
                checkBoxes[i][j].setChecked(false);
            }
        }
    }

    private void addOrganisation() {
        //getting the value


        Query nameQuery = FirebaseDatabase.getInstance().getReference().child("Users").child(iduser).child("_currentOrganisation").child("_organisationHorraire").orderByChild("_startweek");
        nameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.getChildrenCount() > 0){
////                    flag = "";
//                    toastMessage("Not able to add");
//
//                }else{
                    //getting a unique id using push().getKey() method
                    //it will create a unique id and will use it as the Primary Key for our Product

                    String id = databaseUser.push().getKey();

                    semaine.add(LUN.getText().toString());
                    semaine.add(MAR.getText().toString());
                    semaine.add(MER.getText().toString());
                    semaine.add(JED.getText().toString());
                    semaine.add(VEN.getText().toString());
                    semaine.add(SAM.getText().toString());
                    semaine.add(DIM.getText().toString());




                    //creating a Product
                    Horraire newh = new Horraire(id, LUN.getText().toString(), semaine, true, plageHorraire);

                    if (!flag){
                        corganisation.get_organisationHorraire().set(index, newh);
                    }else {
                        corganisation.get_organisationHorraire().add(index, newh);
                    }



                    cuser.set_currentOrganisation(corganisation);

                        //Saving the Product
                    databaseUser.child(iduser).setValue(cuser);

                    plageHorraire.clear();
                    semaine.clear();

                    toastMessage("Saved");
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void loadAdd() {


        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Users user = dataSnapshot.child(iduser).getValue(Users.class);

                cuser = new Users(user.getId(), user.get_firstname(), user.get_lastname(), user.get_email(), user.get_type(), user.get_currentOrganisation());
                corganisation = user.get_currentOrganisation();



                    if (corganisation.get_organisationHorraire().size() <=index ){

                        flag = true;
                    }else{
                        flag = false;
                    }

                if (!flag) {
                    plageHorraire = corganisation.get_organisationHorraire().get(index).get_array();
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 7; j++) {
                            checkBoxes[i][j].setChecked(plageHorraire.get(i).get(j));
                        }
                    }

                    plageHorraire.clear();
                }
                plageHorraire.clear();


                Log.d("DEBUG", "Value is: " + cuser);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

            }
        });



    }



    private String[] getWeekDate() {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String[] days = new String[365];
        for (int i = 0; i < 365; i++)
        {
            days[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;

    }

    private void toastMessage (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}