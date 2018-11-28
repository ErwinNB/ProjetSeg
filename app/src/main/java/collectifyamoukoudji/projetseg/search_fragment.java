package collectifyamoukoudji.projetseg;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class search_fragment extends Fragment {

    View myView;

    private Button search;
    private String iduser;
    private Users cuser;
    private DatabaseReference databaseUser;
    private DatabaseReference databaseService;
    private ArrayList<String> ServiceOffert;
    private ArrayList<Users> listFourSvc;
    private ArrayAdapter<String> spinnerArrayAdapter;
    private ArrayAdapter<String> spinnerArrayAdapterTslt;
    private ArrayAdapter<String> spinnerArrayAdapterRate;
    private Button btnRechercher;
    private RadioGroup radioGroup;
    private RadioButton rbTypeSvc;
    private RadioButton rbTimeSlot;
    private RadioButton rbRating;
    private ArrayAdapter<String> ListArrayAdapter;
    private ListView listeFournisseurs;
    private Spinner spinnerTsvc;
    private Spinner spinnerTiSlt;
    private Spinner spinnerRate;
    private final String[] timeslt = new String[]{"Plages Horraires", "8h - 9h", "9h - 10h", "10h - 11h",
            "11h - 12h", "12h - 13h", "13h - 14h", "14h - 15h", "15h - 16h", "16h - 17h", "17h - 18h", "18h - 19h", "19h - 20h"
    };
    private final List<String> timesltList = new ArrayList<>(Arrays.asList(timeslt));
    private final String[] rating = new String[]{"Evaluations",
            "0",
            "1",
            "2",
            "3",
            "4"
    };
    private final List<String> ratingList = new ArrayList<>(Arrays.asList(rating));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.search_layout, container, false);
        setupUI();

        listFourSvc = new ArrayList<>();


            databaseUser = FirebaseDatabase.getInstance().getReference("Users");

            databaseUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (iduser != null) {
                        Users user = dataSnapshot.child(iduser).getValue(Users.class);
                        cuser = new Users(user.getId(), user.get_firstname(), user.get_lastname(), user.get_email(), user.get_type());
                        Log.d("DEBUG", "Value is: " + cuser);
                    }

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        cuser = postSnapshot.getValue(Users.class);
                        if(cuser.get_type().equals("Fournisseur de services")) {
                            listFourSvc.add(cuser);
                            Log.d("DEBUG", "Value is: " + listFourSvc.toString());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Failed to read value
                    toastMessage("Failed to alter database.");
                    Log.w("DEBUG", "Failed to read value.", databaseError.toException());
                }
            });


        loadEntries();
        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastMessage(listFourSvc.toString());
            }
        });
        return myView;
    }


    private void setupUI() {
        databaseService = FirebaseDatabase.getInstance().getReference("Services");
        databaseUser = FirebaseDatabase.getInstance().getReference("Users");
        spinnerTsvc = (Spinner) myView.findViewById(R.id.spinnerTypeDeSvc);
        spinnerTiSlt = (Spinner) myView.findViewById(R.id.spinnerTimeSlt);
        spinnerRate = (Spinner) myView.findViewById(R.id.spinnerRating);
        btnRechercher = (Button) myView.findViewById(R.id.buttonSearchSvc);
        radioGroup = (RadioGroup) myView.findViewById(R.id.rGroupe);
        rbTypeSvc = (RadioButton) myView.findViewById(R.id.rbTypeSvc);
        rbTimeSlot = (RadioButton) myView.findViewById(R.id.rbTimeSlot);
        rbRating = (RadioButton) myView.findViewById(R.id.rbRating);
        ServiceOffert = new ArrayList<>();
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };
        spinnerArrayAdapterTslt = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, ratingList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };
        spinnerArrayAdapterRate = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, timesltList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }
        };
        //fill the spinner with the Db
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapterTslt.setDropDownViewResource(R.layout.spinner_item);
        spinnerArrayAdapterRate.setDropDownViewResource(R.layout.spinner_item);
        spinnerTsvc.setAdapter(spinnerArrayAdapter);
        spinnerTiSlt.setAdapter(spinnerArrayAdapterTslt);
        spinnerRate.setAdapter(spinnerArrayAdapterRate);

    }

    private void loadEntries() {
        spinnerArrayAdapter.add("Type de services...");
        databaseService.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    Service value = postsnapshot.getValue(Service.class);

                    if (!(ServiceOffert.contains(value.getServiceName()))) {
                        spinnerArrayAdapter.add(value.getServiceName() + " - " + String.valueOf(value.getRate()));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value

            }
        });
    }


    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void searchFournisseur() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // si le premier radiobtn
        if (selectedId == rbTypeSvc.getId()) {
            final String searchedString = spinnerTsvc.getSelectedItem().toString();
        } else if (selectedId == rbTimeSlot.getId()) {

        } else {

        }
    }



}